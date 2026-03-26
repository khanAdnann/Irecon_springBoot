package com.irecon.innovation.services.Impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.irecon.innovation.entity.Common_data_validation;
import com.irecon.innovation.entity.MastercardAtmRawData;
import com.irecon.innovation.entity.SwitchTLFRawData;
import com.irecon.innovation.repository.CommonDataValidationRepository;
import com.irecon.innovation.repository.MastercardAtmRawDataRepository;
import com.irecon.innovation.repository.SwitchTLFRawDataRepository;
import com.irecon.innovation.services.MastercardAtmRawDataService;
import com.irecon.innovation.services.SwitchTLFRawDataService;

@Service
public class MastercardAtmRawDataServiceImpl implements MastercardAtmRawDataService {
	private static final Logger logger = LoggerFactory.getLogger(MastercardAtmRawDataServiceImpl.class);

	@Autowired
	private MastercardAtmRawDataRepository mastercardAtmRawDataRepository;

	@Autowired
	private CommonDataValidationRepository commonDataValidationRepository;

	@Override
	public boolean SaveMastercardAtmFile(MultipartFile file, String fileDate) {
		ExecutorService executor = Executors.newFixedThreadPool(6); // You can adjust thread count
		List<Future<?>> futures = new ArrayList<>();

		BufferedReader reader = null;
		List<MastercardAtmRawData> batch = new ArrayList<>();
		int batchSize = 20000;
		long count = 0;
		logger.info("SaveMastercardAtmFile " + file.getOriginalFilename());
		try {
		    reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
		    String line;

		    while ((line = reader.readLine()) != null) {
		        if ((count > 2 && line.trim().startsWith("NREC")) || line.trim().startsWith("FREC")) {
		            MastercardAtmRawData data = parseLineToData(line, fileDate, file.getOriginalFilename());
		            batch.add(data);
		            count++;

		            if (batch.size() == batchSize) {
		                // Submit batch to thread
		                List<MastercardAtmRawData> batchToSave = new ArrayList<>(batch);
		                futures.add(executor.submit(() -> {
		                    mastercardAtmRawDataRepository.saveAll(batchToSave);
		                }));
		                batch.clear(); // Reset batch
		            }
		        }
		    }

		    // Save remaining records
		    if (!batch.isEmpty()) {
		        List<MastercardAtmRawData> finalBatch = new ArrayList<>(batch);
		        futures.add(executor.submit(() -> {
		            mastercardAtmRawDataRepository.saveAll(finalBatch);
		        }));
		    }

		    // Wait for all tasks to finish
		    for (Future<?> future : futures) {
		        future.get(); // blocks until done
		    }

		    // Shutdown executor
		    executor.shutdown();

		    // Update status
		    int updateStatus = commonDataValidationRepository.updateStatusByFileTypeOrFiledate(
		        "MASTERCARD ATM", fileDate, file.getOriginalFilename(), String.valueOf(count), "Y"
		    );

		    logger.info(updateStatus > 0 ? "Upload status updated." : "Unable to update status.");
		    logger.info("Successfully uploaded file.");

		    return true;

		} catch (Exception e) {
		    logger.error("Error processing file", e);
		    return false;
		} finally {
		    try {
		        if (reader != null) reader.close();
		    } catch (Exception e) {
		        logger.error("Error closing reader", e);
		    }
		}
		}
	
	private MastercardAtmRawData parseLineToData(String line, String fileDate, String filename) {
	    MastercardAtmRawData data = new MastercardAtmRawData();

	    data.setMsgTypeInd(line.substring(0, 4).trim());
	    data.setSwitchSerialNum(line.substring(4, 17).trim());
	    data.setProcessorAcqOrIss(line.substring(13, 14).trim());
	    data.setProcessorId(line.substring(14, 18).trim());
	    data.setTransactionDate(line.substring(18, 24).trim());
	    data.setTransactionTime(line.substring(24, 30).trim());
	    data.setPanLength(line.substring(30, 32).trim());
	    data.setPan(line.substring(32, 51).trim());
	    data.setProcessingCode(line.substring(51, 57).trim());
	    data.setTraceNumber(line.substring(57, 63).trim());
	    data.setMerchantType(line.substring(63, 67).trim());
	    data.setPosEntry(line.substring(67, 70).trim());
	    data.setReferenceNumber(line.substring(70, 82).trim());
	    data.setAcquirerInstitutionId(line.substring(82, 92).trim());
	    data.setTerminalId(line.substring(92, 102).trim());
	    data.setResponseCode(line.substring(102, 104).trim());
	    data.setBrand(line.substring(104, 107).trim());
	    data.setAdviceReasonCode(line.substring(107, 114).trim());
	    data.setInteracurrencyAgreementCode(line.substring(114, 118).trim());
	    data.setAuthorizationId(line.substring(118, 124).trim());
	    data.setCurrencyCode(line.substring(124, 127).trim());
	    data.setImpliedDecimal(line.substring(127, 128).trim());
	    data.setCompletedAmtTrans(line.substring(128, 140).trim());
	    data.setCompletedAmtIndicator(line.substring(140, 141).trim());
	    data.setCashBackAmt(line.substring(141, 153).trim());
	    data.setCashBackIndicator(line.substring(153, 154).trim());
	    data.setAccessFee(line.substring(154, 162).trim());
	    data.setAccessFeeIndicator(line.substring(162, 163).trim());
	    data.setCurrencyCodeSettlement(line.substring(163, 166).trim());
	    data.setImpliedDecimalSettlement(line.substring(166, 167).trim());
	    data.setConversionRateSettlement(line.substring(167, 175).trim());
	    data.setCompletedAmtSettlement(line.substring(175, 187).trim());
	    data.setCompletedAmtSettlementIndicator(line.substring(187, 188).trim());
	    data.setInterchangeFee(line.substring(188, 198).trim());
	    data.setInterchangeFeeIndicator(line.substring(198, 199).trim());
	    data.setServiceLevelIndicator(line.substring(199, 201).trim());
	    data.setResponseCode2(line.substring(201, 203).trim());
	    data.setFiller(line.substring(205, 214).trim());
	    data.setPositiveIdIndicator(line.substring(214, 215).trim());
	    data.setAtmSurchargeFreeProgramId(line.substring(215, 216).trim());
	    data.setCrossBorderIndicator(line.substring(216, 217).trim());
	    data.setCrossBorderCurrencyIndicator(line.substring(217, 218).trim());
	    data.setVisaIsaFeeIndicator(line.substring(218, 219).trim());
	    data.setRequestAtmTransLocal(line.substring(219, 231).trim());
	    data.setFillerAdj(line.substring(231, 243).trim());
	    data.setTraceNumberAdjustmentTrans(line.substring(243, 249).trim());
	    data.setReconActivity("");
	    data.setFileDate(fileDate);
	    data.setFileName(filename);

	    return data;
	}
}
