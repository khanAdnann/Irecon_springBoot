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
import com.irecon.innovation.entity.DfsIssRawData;
import com.irecon.innovation.repository.CommonDataValidationRepository;
import com.irecon.innovation.repository.DfsIssRawDataRepository;
import com.irecon.innovation.services.DfsIssRawDataService;

@Service
public class DfsIssRawDataServiceImpl implements DfsIssRawDataService{

	private static final Logger logger = LoggerFactory.getLogger(DfsIssRawDataServiceImpl.class);
	
	@Autowired
	private DfsIssRawDataRepository dfsIssRawDataRepository;
	
	@Autowired
	private CommonDataValidationRepository commonDataValidationRepository;
	
	 @Override
	    public boolean SaveDfsIssRawData(MultipartFile file, String fileDate) {
	        logger.info("Save method SaveNfsIssRawData " + fileDate);



	        Set<DfsIssRawData> rawDataSet = new HashSet<>();
	        BufferedReader reader = null;
	        Long DataCount = 0L;


	        try {
	            reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
	            String thisLine;

	            while ((thisLine = reader.readLine()) != null) {
	            	DataCount++;
	                DfsIssRawData data = new DfsIssRawData();
	                // Mapping values from the line to the entity
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
	                data.setNetworkId(thisLine.substring(157, 160));
	                data.setAccount1Number(thisLine.substring(160, 175));
	                data.setAccount1BranchId(thisLine.substring(175, 180));
	                data.setAccount2Number(thisLine.substring(180, 195));
	                data.setAccount2BranchId(thisLine.substring(195, 200));
	                data.setTxnCurrencyCode(thisLine.substring(200, 203));
	                data.setTxnAmount(
	                    thisLine.substring(203, 216).replaceAll("^0*", "0") + "." +
	                    thisLine.substring(216, 218));
	                data.setActualTxnAmt(thisLine.substring(218, 233));
	                data.setTxnActivityFee(thisLine.substring(233, 248));
	                data.setIssSettleCurrencyCd(thisLine.substring(248, 251));
	                data.setIssSettleAmnt(
	                    thisLine.substring(251, 264).replaceAll("^0*", "0") + "." +
	                    thisLine.substring(264, 266));
	                data.setIssSettleFee(thisLine.substring(266, 281));
	                data.setIssSettleProcessFee(thisLine.substring(281, 296));
	                data.setCardholderBillCurrncyC(thisLine.substring(296, 299));
	                data.setCardholderBillAmount(thisLine.substring(299, 314));
	                data.setCardholderBillActFee(thisLine.substring(314, 329));
	                data.setCardholderBillProcessF(thisLine.substring(329, 344));
	                data.setCardholderBillServFee(thisLine.substring(344, 359));
	                data.setTxnIssConversionRt(thisLine.substring(359, 374));
	                data.setTxnCardholderConvRt(thisLine.substring(374, 389));
	                data.setPartId("1");
	                data.setDcrsTranNo(thisLine.substring(389, 405));
	                data.setCreatedDate(new Date());
	                data.setCreatedBy("AUTOMATION");
	                data.setFileDate(fileDate);
	                data.setFpan(thisLine.substring(23, 42));
	                data.setCycle("1");
	                data.setFilename(file.getOriginalFilename());

	                rawDataSet.add(data);
	            }

	            // Save all data to the repository
	            dfsIssRawDataRepository.saveAll(rawDataSet);
	            
	           int  updateStatus= commonDataValidationRepository.updateStatusByFileTypeOrFiledate("DFS ISSUER", fileDate, file.getOriginalFilename(), String.valueOf(DataCount), "Y");
	       	logger.info(updateStatus > 0 ? "Upload status updated." : "Unable to update status.");
	            logger.info("Successfully Inserted ");
	            return true;

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
	                    reader.close();
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	                throw new RuntimeException("Error while closing the file reader: " + e.getMessage());
	            }
	        }
	    }
	 
}
