
package com.irecon.innovation.services.Impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.irecon.innovation.entity.Common_data_validation;
import com.irecon.innovation.entity.JcbIssRawData;
import com.irecon.innovation.repository.CommonDataValidationRepository;
import com.irecon.innovation.repository.JcbIssRawDataRepository;
import com.irecon.innovation.services.JcbIssRawDataService;

@Service
public class JcbIssRawDataServiceImpl implements JcbIssRawDataService {

    private static final Logger logger = LoggerFactory.getLogger(JcbIssRawDataServiceImpl.class);
    
    @Autowired
    private DataSource dataSource;

    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CommonDataValidationRepository commonDataValidationRepository;

    private static final int BATCH_SIZE = 1000;
    private static final int THREAD_CHUNK_SIZE = 100_000; // 100k records per thread


    @Override
    public boolean SaveJcbIssRawData(MultipartFile file, String fileDate) {
        logger.info("Save method SaveJcbIssRawData using **Parallel Batch Insert** for fileDate " + fileDate);
        final String  INSERT_SQL = "insert into  jcb_jcb_iss_rawdata ( PARTICIPANT_ID ,TRANSACTION_TYPE ,FROM_ACCOUNT_TYPE ,TO_ACCOUNT_TYPE  ,TXN_SERIAL_NO ,RESPONSE_CODE ,PAN_NUMBER ,MEMBER_NUMBER ,APPROVAL_NUMBER ,SYS_TRACE_AUDIT_NO ,TRANSACTION_DATE ,TRANSACTION_TIME ,MERCHANT_CATEGORY_CD  ,CARD_ACC_SETTLE_DT  ,CARD_ACC_ID  ,CARD_ACC_TERMINAL_ID  ,CARD_ACC_TERMINAL_LOC ,ACQUIRER_ID  ,NETWORK_ID  ,ACCOUNT_1_NUMBER ,ACCOUNT_1_BRANCH_ID  ,ACCOUNT_2_NUMBER ,ACCOUNT_2_BRANCH_ID  ,TXN_CURRENCY_CODE ,TXN_AMOUNT ,ACTUAL_TXN_AMT ,TXN_ACTIVITY_FEE ,ISS_SETTLE_CURRENCY_CD ,ISS_SETTLE_AMNT  ,ISS_SETTLE_FEE ,ISS_SETTLE_PROCESS_FEE ,CARDHOLDER_BILL_CURRNCY_C  ,CARDHOLDER_BILL_AMOUNT ,CARDHOLDER_BILL_ACT_FEE  ,CARDHOLDER_BILL_PROCESS_F  ,CARDHOLDER_BILL_SERV_FEE ,TXN_ISS_CONVERSION_RT ,TXN_CARDHOLDER_CONV_RT ,PART_ID,DCRS_TRAN_NO ,CreatedDate , CreatedBy , FILEDATE,FPAN,CYCLE ,FILENAME) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate(),?,STR_to_date('" + 
                
       fileDate + "','%Y/%m/%d'),?,?,?) ";
        long dataCount = 0L;int batch=0;
        List<Object[]> allParams = new ArrayList<>(1_000_000); // for 1 million records

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String thisLine;
            Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT_SQL);
            
            
            while ((thisLine = reader.readLine()) != null) {
                ps.setString(1, thisLine.substring(0, 3));
                ps.setString(2, thisLine.substring(3, 5));
                ps.setString(3, thisLine.substring(5, 7));
                ps.setString(4, thisLine.substring(7, 9));
                ps.setString(5, thisLine.substring(9, 21));
                ps.setString(6, thisLine.substring(21, 23));
                String pan = thisLine.substring(23, 42).trim();
                String Update_Pan = "";
                if (pan.length() <= 16 && pan != null && pan.trim() != "" && pan.length() > 0) {
                  Update_Pan = String.valueOf(pan.substring(0, 6)) + "XXXXXX" + pan.substring(pan.length() - 4);
                } else if (pan.length() >= 16 && pan != null && pan.trim() != "" && pan.length() > 0) {
                  Update_Pan = String.valueOf(pan.substring(0, 6)) + "XXXXXXXXX" + pan.substring(pan.length() - 4);
                } else {
                  Update_Pan = null;
                } 
                ps.setString(7, Update_Pan);
                ps.setString(8, thisLine.substring(42, 43));
                ps.setString(9, thisLine.substring(43, 49));
                ps.setString(10, thisLine.substring(49, 61));
                ps.setString(11, thisLine.substring(61, 67));
                ps.setString(12, thisLine.substring(67, 73));
                ps.setString(13, thisLine.substring(73, 77));
                ps.setString(14, thisLine.substring(77, 83));
                ps.setString(15, thisLine.substring(83, 98));
                ps.setString(16, thisLine.substring(98, 106));
                ps.setString(17, thisLine.substring(106, 146));
                ps.setString(18, thisLine.substring(146, 157));
                ps.setString(19, thisLine.substring(157, 160));
                ps.setString(20, thisLine.substring(160, 179));
                ps.setString(21, thisLine.substring(179, 189));
                ps.setString(22, thisLine.substring(189, 208));
                ps.setString(23, thisLine.substring(208, 218));
                ps.setString(24, thisLine.substring(218, 221));
                ps.setString(25, 
                    String.valueOf(thisLine.substring(221, 234).replaceAll("^0*", "0")) + "." + thisLine.substring(234, 236));
                ps.setString(26, 
                    String.valueOf(thisLine.substring(236, 249).replaceAll("^0*", "0")) + "." + thisLine.substring(249, 251));
                ps.setString(27, thisLine.substring(251, 266));
                ps.setString(28, thisLine.substring(266, 269));
                ps.setString(29, 
                    String.valueOf(thisLine.substring(269, 282).replaceAll("^0*", "0")) + "." + thisLine.substring(282, 284));
                ps.setString(30, thisLine.substring(284, 299));
                ps.setString(31, thisLine.substring(299, 314));
                ps.setString(32, thisLine.substring(314, 317));
                ps.setString(33, thisLine.substring(317, 332));
                ps.setString(34, thisLine.substring(332, 347));
                ps.setString(35, thisLine.substring(347, 362));
                ps.setString(36, thisLine.substring(362, 377));
                ps.setString(37, thisLine.substring(377, 392));
                ps.setString(38, thisLine.substring(392, 407));
                ps.setInt(39, 1);
                ps.setString(40, (String)null);
                ps.setString(41, "AUTOMATION");
                ps.setString(42, thisLine.substring(23, 42));
                ps.setString(43, "1");
                ps.setString(44, file.getOriginalFilename());
                ps.addBatch();
                dataCount++;
                if (dataCount == 40000) {
                	dataCount = 1;
                  ps.executeBatch();
                  logger.info("Executed batch is " + batch);
           
                  batch++;
                } 
              }
            ps.executeBatch();
       
            con.close();
            logger.info("Total records to insert: " + dataCount);

            // Split into thread chunks (100k records per chunk)
           
            // Update status
            int updateStatus = commonDataValidationRepository.updateStatusByFileTypeOrFiledate(
                    "JCB ISSUER", fileDate, file.getOriginalFilename(), String.valueOf(dataCount), "Y");

        	logger.info(updateStatus > 0 ? "Upload status updated." : "Unable to update status.");

            logger.info("✅ Successfully inserted " + dataCount + " records.");
            return true;

        } catch (Exception e) {
            logger.error("Error during parallel batch insert", e);
            throw new RuntimeException("Error during parallel batch insert: " + e.getMessage());
        }
    }

   
}