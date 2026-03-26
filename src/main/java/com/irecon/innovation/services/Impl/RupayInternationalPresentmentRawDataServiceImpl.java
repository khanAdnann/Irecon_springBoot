
package com.irecon.innovation.services.Impl;

import java.io.BufferedReader;
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
import com.irecon.innovation.entity.RupayDomesticOfflinePresentmentRawData;
import com.irecon.innovation.entity.RupayDomesticPresentmentRawData;
import com.irecon.innovation.entity.RupayInternationalOfflinePresentmentRawData;
import com.irecon.innovation.entity.RupayInternationalPresentmentRawData;
import com.irecon.innovation.repository.CommonDataValidationRepository;

import com.irecon.innovation.repository.RupayDomesticAdjustmmentRawDataRepository;
import com.irecon.innovation.repository.RupayDomesticPrasentmentRawDataRepository;
import com.irecon.innovation.repository.RupayInternationalOfflinePrasentmentRawDataRepository;
import com.irecon.innovation.repository.RupayInternationalPrasentmentRawDataRepository;
import com.irecon.innovation.services.RupayDomesticAdjustmentRawDataService;
import com.irecon.innovation.services.RupayDomesticPresentmentRawDataService;
import com.irecon.innovation.services.RupayInternationalPresentmentRawDataService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@Service
public class RupayInternationalPresentmentRawDataServiceImpl implements RupayInternationalPresentmentRawDataService {

	private static final Logger logger = LoggerFactory.getLogger(RupayInternationalPresentmentRawDataServiceImpl.class);

	@Autowired
	private RupayInternationalPrasentmentRawDataRepository rupayPrasentmentRawDataRepository;
	@Autowired
	private RupayInternationalOfflinePrasentmentRawDataRepository rupayOfflinePrasentmentRawDataRepository;

	@Autowired
	private CommonDataValidationRepository commonDataValidationRepository;

	@Override
	public boolean SaveRupayInernationalPresetmentRawData(MultipartFile file, String fileDate, String Cycle) {
		logger.info("Save method SaveRupayInernationalPresetmentRawData " + fileDate);

		List<RupayInternationalPresentmentRawData> batchList = new ArrayList<>();
		List<RupayInternationalOfflinePresentmentRawData> OPbatchList = new ArrayList<>();
		int batchSize = 1000;
		int Count = 0;
		int sr_no = 1, rowCount = 0, batchNumber = 0;

		try {
			String line;
			BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));

			while ((line = br.readLine()) != null) {
				// Skip headers or footer lines
				if (line.contains("Report Date") || line.contains("Presentment Raise Date")
						|| line.contains("End of Report") || line.contains("END OF REPORT")) {
					continue;
				}

				Count++;
				String[] tempArr = line.split("\\,", -1);

				// Map CSV fields into Presentment entity
				if (file.getOriginalFilename().startsWith("OfflinePresentment")) {

					RupayInternationalOfflinePresentmentRawData p = new RupayInternationalOfflinePresentmentRawData();
					p.setReportDate(clean(tempArr[0]));
					p.setPresentmentRaiseDate(clean(tempArr[1]));
					p.setPresentmentSettlementDate(clean(tempArr[2]));
					p.setFunctionCodeAndDescription(clean(tempArr[3]));
					p.setPrimaryAccountNumber(clean(tempArr[4]));
					p.setDateLocalTransaction(clean(tempArr[5]));
					p.setRrn(clean(tempArr[6]));
					p.setProcessingCode(clean(tempArr[7]));
					p.setCurrencyCodeTransaction(clean(tempArr[8]));
					p.seteCommerceIndicator(clean(tempArr[9]));
					p.setAmountTransaction(clean(tempArr[10]));
					p.setAmountAdditional(clean(tempArr[11]));
					p.setSettlementAmountTransaction(clean(tempArr[12]));
					p.setSettlementAmountAdditional(clean(tempArr[13]));
					p.setApprovalCode(clean(tempArr[14]));
					p.setOriginatorPoint(clean(tempArr[15]));
					p.setPosEntryMode(clean(tempArr[16]));
					p.setPosConditionCode(clean(tempArr[17]));
					p.setAcquirerInstitutionIdCode(clean(tempArr[18]));
					p.setTransactionOriginatorInstIdCode(clean(tempArr[19]));
					p.setAcquirerNameAndCountry(clean(tempArr[20]));
					p.setIssuerInstitutionIdCode(clean(tempArr[21]));
					p.setTransDestInstIdCode(clean(tempArr[22]));
					p.setIssuerNameAndCountry(clean(tempArr[23]));
					p.setCardType(clean(tempArr[24]));
					p.setCardBrand(clean(tempArr[25]));
					p.setCardAcceptorTerminalId(clean(tempArr[26]));
					p.setCardAcceptorName(clean(tempArr[27]));
					p.setCardAcceptorLocationAddress(clean(tempArr[28]));
					p.setCardAcceptorCountryCode(clean(tempArr[29]));
					p.setCardAcceptorBusinessCode(clean(tempArr[30]));
					p.setCardAcceptorIdCode(clean(tempArr[31]));
					p.setCardAcceptorStateName(clean(tempArr[32]));
					p.setCardAcceptorCity(clean(tempArr[33]));
					p.setAged(clean(tempArr[34]));
					p.setMti(clean(tempArr[35]));
					p.setFileDate(fileDate);
					p.setCycle(Cycle);
					p.setFilename(file.getOriginalFilename());

					OPbatchList.add(p);
					// Execute batch every 1000 records
					if (OPbatchList.size() == batchSize) {
						batchNumber++;

						rupayOfflinePrasentmentRawDataRepository.saveAll(OPbatchList);

						logger.info("Batch Executed " + batchNumber);
						OPbatchList.clear();
					}

				} else {

					RupayInternationalPresentmentRawData p = new RupayInternationalPresentmentRawData();
					p.setReportDate(clean(tempArr[0]));
					p.setPresentmentRaiseDate(clean(tempArr[1]));
					p.setPresentmentSettlementDate(clean(tempArr[2]));
					p.setFunctionCodeAndDescription(clean(tempArr[3]));
					p.setPan(clean(tempArr[4]));
					p.setLocalTransaction(clean(tempArr[5]));
					p.setRrn(clean(tempArr[6]));
					p.setProcessingCode(clean(tempArr[7]));
					p.setCurrencyCode(clean(tempArr[8]));
					p.seteCommerceIndicator(clean(tempArr[9]));
					p.setAmountTransaction(clean(tempArr[10]));
					p.setAmountAdditional(clean(tempArr[11]));
					p.setSettlementAmountTransaction(clean(tempArr[12]));
					p.setSettlementAmountAdditional(clean(tempArr[13]));
					p.setApprovalCode(clean(tempArr[14]));
					p.setOriginatorPoint(clean(tempArr[15]));
					p.setPosEntryMode(clean(tempArr[16]));
					p.setPosConditionCode(clean(tempArr[17]));
					p.setAcquirerInstitutionIdCode(clean(tempArr[18]));
					p.setTransactionOriginatorInstitutionIdCode(clean(tempArr[19]));
					p.setAcquirerNameAndCountry(clean(tempArr[20]));
					p.setIssuerInstitutionIdCode(clean(tempArr[21]));
					p.setTransactionDestinationInstitutionIdCode(clean(tempArr[22]));
					p.setIssuerNameAndCountry(clean(tempArr[23]));
					p.setCardType(clean(tempArr[24]));
					p.setCardBrand(clean(tempArr[25]));
					p.setCardAcceptorTerminalId(clean(tempArr[26]));
					p.setCardAcceptorName(clean(tempArr[27]));
					p.setCardAcceptorLocation(clean(tempArr[28]));
					p.setCardAcceptorCountryCode(clean(tempArr[29]));
					p.setCardAcceptorBusinessCode(clean(tempArr[30]));
					p.setCardAcceptorIdCode(clean(tempArr[31]));
					p.setCardAcceptorStateName(clean(tempArr[32]));
					p.setCardAcceptorCity(clean(tempArr[33]));
					p.setDaysAged(clean(tempArr[34]));
					p.setMti(clean(tempArr[35]));
					p.setFileDate(fileDate);
					p.setCycle(Cycle);
					p.setFilename(file.getOriginalFilename());

					batchList.add(p);

					// Execute batch every 1000 records
					if (batchList.size() == batchSize) {
						batchNumber++;

						rupayPrasentmentRawDataRepository.saveAll(batchList);

						logger.info("Batch Executed " + batchNumber);
						batchList.clear();
					}

				}

			}

			if (!batchList.isEmpty()) {

				rupayPrasentmentRawDataRepository.saveAll(batchList);
				batchList.clear();

			}

			if (!OPbatchList.isEmpty()) {

				rupayOfflinePrasentmentRawDataRepository.saveAll(OPbatchList);
				OPbatchList.clear();

			}
			if (file.getOriginalFilename().startsWith("OfflinePresentment")) {

				int updateStatus = commonDataValidationRepository.updateStatusByFileTypeOrFiledate(
						"RUPAY INT OFFLINE PRESENTMENT CYCLE " + Cycle, fileDate, file.getOriginalFilename(),
						String.valueOf(Count), "Y");
				logger.error(updateStatus > 0 ? "Upload Status updated" : "unable to update");

			} else {

				int updateStatus = commonDataValidationRepository.updateStatusByFileTypeOrFiledate(
						"RUPAY INT PRESENTMENT CYCLE " + Cycle, fileDate, file.getOriginalFilename(),
						String.valueOf(Count), "Y");
				logger.error(updateStatus > 0 ? "Upload Status updated" : "unable to update");

			}

			logger.info("Successfully Inserted ");
			return true;

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error processing CSV at line: " + Count);
			return false;
		}

	}

	private String clean(String value) {
		if (value == null)
			return "";
		return value.replace('"', ' ').replaceAll("'", "").trim();
	}

}
