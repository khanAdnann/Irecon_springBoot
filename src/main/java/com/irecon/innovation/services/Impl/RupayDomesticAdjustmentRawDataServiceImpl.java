
package com.irecon.innovation.services.Impl;


import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.irecon.innovation.entity.RupayDomesticAdjustmentRawData;

import com.irecon.innovation.repository.CommonDataValidationRepository;

import com.irecon.innovation.repository.RupayDomesticAdjustmmentRawDataRepository;

import com.irecon.innovation.services.RupayDomesticAdjustmentRawDataService;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@Service
public class RupayDomesticAdjustmentRawDataServiceImpl implements RupayDomesticAdjustmentRawDataService{

	private static final Logger logger = LoggerFactory.getLogger(RupayDomesticAdjustmentRawDataServiceImpl.class);

	@Autowired
	private RupayDomesticAdjustmmentRawDataRepository rupayAdjustmmentRawDataRepository;

	@Autowired
	private CommonDataValidationRepository commonDataValidationRepository;

	@Override
	public boolean SaveRupayDomesticAdjustmentRawData(MultipartFile file, String fileDate,String Cycle) {
		logger.info("Save method SaveNfsIssRawData " + fileDate);



		 List<RupayDomesticAdjustmentRawData> batchList = new ArrayList<>();
		    int batchSize = 1000;
		    int Count = 0;

		    try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
		        String[] fields;
		        while ((fields = reader.readNext()) != null) {
		        	Count++;

		            // Skip header row
		            if (Count == 1) continue;

		            // End of report check
		            String joinedLine = String.join(",", fields);
		            if (joinedLine.contains("---END OF REPORT---") || joinedLine.contains("---End of Report---")) {
		                break;
		            }

		            // Map to Entity
		            RupayDomesticAdjustmentRawData entity = new RupayDomesticAdjustmentRawData();
		            int i = 0;

		            entity.setReportDate(safeGet(fields, i++));
		            entity.setDisputeRaiseDate(safeGet(fields, i++));
		            entity.setDisputeRaisedSettlDate(safeGet(fields, i++));
		            entity.setCaseNumber(safeGet(fields, i++));
		            entity.setSchemeName(safeGet(fields, i++));
		            entity.setTransactionFlag(safeGet(fields, i++));
		            entity.setFunctionCode(safeGet(fields, i++));
		            entity.setFunctionCodeDescription(safeGet(fields, i++));
		            entity.setPrimaryAccountNumber(safeGet(fields, i++));
		            entity.setProcessingCode(safeGet(fields, i++));
		            entity.setTransactionDate(safeGet(fields, i++));
		            entity.setTransactionAmount(safeGet(fields, i++));
		            entity.setTxnCurrencyCode(safeGet(fields, i++));
		            entity.setSettlementAmount(safeGet(fields, i++));
		            entity.setSettlementCcyCode(safeGet(fields, i++));
		            entity.setConversionRate(safeGet(fields, i++));
		            entity.setTxnSettlementDate(safeGet(fields, i++));
		            entity.setAmountsAdditional(safeGet(fields, i++));
		            entity.setControlNumber(safeGet(fields, i++));
		            entity.setDisputeOriginatorPid(safeGet(fields, i++));
		            entity.setDisputeDestinationPid(safeGet(fields, i++));
		            entity.setAcquireRefData(safeGet(fields, i++));
		            entity.setApprovalCode(safeGet(fields, i++));
		            entity.setOriginatorPoint(safeGet(fields, i++));
		            entity.setPosEntryMode(safeGet(fields, i++));
		            entity.setPosConditionCode(safeGet(fields, i++));
		            entity.setAcquirerInstituteidCode(safeGet(fields, i++));
		            entity.setAcquirerNameCountry(safeGet(fields, i++));
		            entity.setIssuerInstiIdCode(safeGet(fields, i++));
		            entity.setIssuerNameCountry(safeGet(fields, i++));
		            entity.setCardType(safeGet(fields, i++));
		            entity.setCardBrand(safeGet(fields, i++));
		            entity.setCardAcceptorTerminalid(safeGet(fields, i++));
		            entity.setCardAcceptorName(safeGet(fields, i++));
		            entity.setCardAcceptLocationAdd(safeGet(fields, i++));
		            entity.setCardAcceptCountryCode(safeGet(fields, i++));
		            entity.setCardAcceptBussCode(safeGet(fields, i++));
		            entity.setDisputeReasonCode(safeGet(fields, i++));
		            entity.setDisputeReasonCdDesc(safeGet(fields, i++));
		            entity.setDisputeAmt(safeGet(fields, i++));
		            entity.setFullPartialIndicator(safeGet(fields, i++));
		            entity.setDisputeMemberMsgText(safeGet(fields, i++));
		            entity.setDisputeDocumentIndicator(safeGet(fields, i++));
		            entity.setDocumentAttachedDate(safeGet(fields, i++));
		            entity.setMti(safeGet(fields, i++));
		            entity.setIncentiveAmount(safeGet(fields, i++));
		            entity.setTierCdNonfullfill(safeGet(fields, i++));
		            entity.setTierCdFulfill(safeGet(fields, i++));
		            entity.setDeadlineDate(safeGet(fields, i++));
		            entity.setDaysToAct(safeGet(fields, i++));
		            entity.setDirectionIwOw(safeGet(fields, i++));

		            // Set extra fields (file metadata)
		            entity.setFileDate(fileDate);
		            entity.setCreatedBy("INT12016");
		            entity.setCycle(Cycle);
		            entity.setFilename(file.getOriginalFilename());

		            // Add to batch
		            batchList.add(entity);

		            // Save in batch
		            if (batchList.size() == batchSize) {
		            	rupayAdjustmmentRawDataRepository.saveAll(batchList);
		                batchList.clear();
		            }
		        }

		        // Save remaining records
		        if (!batchList.isEmpty()) {
		        	rupayAdjustmmentRawDataRepository.saveAll(batchList);
		        }

		        int updateStatus = commonDataValidationRepository.updateStatusByFileTypeOrFiledate("RUPAY DOM ADJUSTMENT CYCLE "+ Cycle, fileDate, file.getOriginalFilename(), String.valueOf(Count), "Y");
		        logger.error(updateStatus > 0 ? "Upload Status updated" : "unable to update");

				logger.info("Successfully Inserted ");
				return true;

		    }  catch (IOException | CsvValidationException e) {
		        e.printStackTrace();
		        System.out.println("Error processing CSV at line: " + Count);
		    	return false;
		    }
		
		    
	}
	private String safeGet(String[] arr, int index) {
	    return (index < arr.length) ? arr[index].trim() : "";
	}
}
