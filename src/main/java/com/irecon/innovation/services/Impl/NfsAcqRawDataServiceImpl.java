package com.irecon.innovation.services.Impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.irecon.innovation.entity.Common_data_validation;
import com.irecon.innovation.entity.NfsAcqRawData;
import com.irecon.innovation.repository.CommonDataValidationRepository;
import com.irecon.innovation.repository.NfsAcqRawDataRepository;
import com.irecon.innovation.services.NfsAcqRawDataService;

@Service
public class NfsAcqRawDataServiceImpl implements NfsAcqRawDataService {
	private static final Logger logger = LoggerFactory.getLogger(NfsAcqRawDataServiceImpl.class);

    @Autowired
    private NfsAcqRawDataRepository nfsAcqRawDataRepository;
    
    @Autowired
	private CommonDataValidationRepository commonDataValidationRepository;


    @Override
    public boolean SaveNfsAcqRawData(MultipartFile file, String fileDate) {
        logger.info("Save method SaveNfsAcqRawData " + fileDate);



        Set<NfsAcqRawData> rawDataSet = new HashSet<>();
        BufferedReader reader = null;
        Long DataCount = 0L,batchcount=0L;

        try {
            reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String thisLine;

            while ((thisLine = reader.readLine()) != null) {
            	DataCount++;
            	batchcount++;
                NfsAcqRawData data = new NfsAcqRawData();
                // Mapping values from the line to the entity (same as before)
                data.setParticipantId(thisLine.substring(0, 3));
                data.setTransactionType(thisLine.substring(3, 5));
                data.setFromAccountType(thisLine.substring(5, 7));
                data.setToAccountType(thisLine.substring(7, 9));
                data.setTxnSerialNo(thisLine.substring(9, 21));
                data.setResponseCode(thisLine.substring(21, 23));
                data.setPanNumber(thisLine.substring(23, 42));
                data.setMemberNumber(thisLine.substring(42, 43));
                data.setApprovalNumber(thisLine.substring(43, 49));
                data.setSysTraceAuditNo(thisLine.substring(49, 61));
                data.setTransactionDate(thisLine.substring(61, 67));
                data.setTransactionTime(thisLine.substring(67, 73));
                data.setMerchantCategoryCd(thisLine.substring(73, 77));
                data.setCardAccSettleDt(thisLine.substring(77, 83));
                data.setCardAccId(thisLine.substring(83, 98));
                data.setCardAccTerminalId(thisLine.substring(98, 106));
                data.setCardAccTerminalLoc(thisLine.substring(106, 146));
                data.setAcquirerId(thisLine.substring(146, 157));
                data.setAcqSettleDate(thisLine.substring(157, 163));
                data.setTxnCurrencyCode(thisLine.substring(163, 166));
                data.setTxnAmount(
                    thisLine.substring(166, 179).replaceAll("^0*", "0") + "." +
                    thisLine.substring(179, 181));
                data.setActualTxnAmt(thisLine.substring(181, 196));
                data.setTxnActivityFee(thisLine.substring(196, 211));
                data.setAcqSettleCurrencyCd(thisLine.substring(211, 214));
                data.setAcqSettleAmnt(
                    thisLine.substring(214, 227).replaceAll("^0*", "0") + "." +
                    thisLine.substring(227, 229));
                data.setAcqSettleFee(thisLine.substring(229, 244));
                data.setAcqSettleProcessFee(thisLine.substring(244, 259));
                data.setTxnAcqConvRate(thisLine.substring(259, 274));

                // Additional data
                data.setPartId("1"); // Static value or logic
                data.setCreatedDate(new Date()); // Current date
                data.setCreatedBy("AUTOMATION");
                data.setFileDate(fileDate); // Use the provided fileDate
                data.setCycle("1"); // From the input parameter
                data.setFpan(thisLine.substring(23, 42)); // Example
                data.setFilename(file.getOriginalFilename()); // From file

                rawDataSet.add(data);
                
                if(batchcount==20000) {
                    nfsAcqRawDataRepository.saveAll(rawDataSet);
                    batchcount=0L;
                }
                
            }

            // Save all data to the repository
            nfsAcqRawDataRepository.saveAll(rawDataSet);
            
            
            int updateStatus = commonDataValidationRepository.updateStatusByFileTypeOrFiledate("NFS ACQUIRER", fileDate, file.getOriginalFilename(), String.valueOf(DataCount), "Y");
			if(updateStatus>0) {
				logger.error("Upload Status updated");
			}else {
				
				logger.error("unabele to update ");
			}	
            logger.info("Successfully Inserted ");
            return true; // Return true to indicate successful upload

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while reading the file: " + e.getMessage());
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while saving data to the database: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unexpected error: " + e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close(); // Ensure that the reader is closed to avoid resource leak
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Error while closing the file reader: " + e.getMessage());
            }
        }
    }
}