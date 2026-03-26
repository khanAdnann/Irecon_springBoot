package com.irecon.innovation.services.Impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import com.irecon.innovation.entity.Common_data_validation;
import com.irecon.innovation.entity.SwitchTLFRawData;
import com.irecon.innovation.repository.CommonDataValidationRepository;
import com.irecon.innovation.repository.SwitchTLFRawDataRepository;
import com.irecon.innovation.services.CommonDataValidationService;
import com.irecon.innovation.services.SwitchTLFRawDataService;

@Service
public class CommonDataValidationServiceImpl implements CommonDataValidationService {
	private static final Logger logger = LoggerFactory.getLogger(CommonDataValidationServiceImpl.class);
	@Autowired
	private WebClient.Builder webClientBuilder;

	@Autowired
	private CommonDataValidationRepository commonDataValidationRepository;

	public boolean SaveAllFileTypeValues(String fileDate) {
		logger.info("Save SaveAllFileTypeValues " + fileDate);

		try {

			List<String> filelist = new ArrayList<String>();
			filelist.add("SWITCH ATM");
			filelist.add("SWITCH POS");
			filelist.add("NFS ACQUIRER");
			filelist.add("NFS ISSUER");

			filelist.add("NFS NTSL CYCLE 1");
			filelist.add("NFS NTSL CYCLE 2");
			filelist.add("NFS NTSL CYCLE 3");
			filelist.add("NFS NTSL CYCLE 4");
			filelist.add("NFS ADJUSTMENT CYCLE 1");
			filelist.add("NFS ADJUSTMENT CYCLE 2");
			filelist.add("NFS ADJUSTMENT CYCLE 3");
			filelist.add("NFS ADJUSTMENT CYCLE 4");
			filelist.add("NFS REV REPORT CYCLE 1");
			filelist.add("NFS REV REPORT CYCLE 2");
			filelist.add("NFS REV REPORT CYCLE 3");
			filelist.add("NFS REV REPORT CYCLE 4");
			filelist.add("JCB NTSL");
			filelist.add("ICD NTSL");
			filelist.add("DFS NTSL");
			filelist.add("JCB ADJUSTMENT");
			filelist.add("ICD ADJUSTMENT");
			filelist.add("DFS ADJUSTMENT");
			filelist.add("ICD ACQUIRER");
			filelist.add("ICD ISSUER");
			filelist.add("DFS ACQUIRER");
			filelist.add("DFS ISSUER");
			filelist.add("JCB ACQUIRER");
			filelist.add("JCB ISSUER");
			filelist.add("VISA ACQUIRER");
			filelist.add("VISA ISSUER");
			filelist.add("MASTERCARD POS");
			filelist.add("MASTERCARD ATM");
			filelist.add("RUPAY DOM");
			filelist.add("RUPAY INT");
			filelist.add("RUPAY 01 DOM");
			filelist.add("RUPAY 02 DOM");
			filelist.add("RUPAY 03 DOM");
			filelist.add("RUPAY 05 DOM");
			filelist.add("RUPAY 88 DOM");
			filelist.add("RUPAY 86 DOM");
			filelist.add("RUPAY 01 INT");
			filelist.add("RUPAY 02 INT");
			filelist.add("RUPAY 03 INT");
			filelist.add("RUPAY 05 INT");
			filelist.add("RUPAY 88 INT");
			filelist.add("RUPAY 86 INT");
			filelist.add("QSPARC 01 DOM");
			filelist.add("QSPARC 02 DOM");
			filelist.add("QSPARC 03 DOM");
			filelist.add("QSPARC 05 DOM");
			filelist.add("QSPARC 88 DOM");
			filelist.add("QSPARC 86 DOM");
			filelist.add("QSPARC 01 INT");
			filelist.add("QSPARC 02 INT");
			filelist.add("QSPARC 03 INT");
			filelist.add("QSPARC 05 INT");
			filelist.add("QSPARC 88 INT");
			filelist.add("QSPARC 86 INT");
			filelist.add("RUPAY INT ADJUSTMENT CYCLE 1");
			filelist.add("RUPAY DOM ADJUSTMENT CYCLE 1");
			filelist.add("RUPAY DOM ADJUSTMENT CYCLE 2");
			filelist.add("RUPAY DOM ADJUSTMENT CYCLE 3");
			filelist.add("RUPAY DOM ADJUSTMENT CYCLE 4");

			filelist.add("QSPARC INT ADJUSTMENT CYCLE 1");
			filelist.add("QSPARC DOM ADJUSTMENT CYCLE 1");
			filelist.add("QSPARC DOM ADJUSTMENT CYCLE 2");
			filelist.add("QSPARC DOM ADJUSTMENT CYCLE 3");
			filelist.add("QSPARC DOM ADJUSTMENT CYCLE 4");
			filelist.add("RUPAY INT PRESENTMENT CYCLE 1");
			filelist.add("RUPAY DOM PRESENTMENT CYCLE 1");
			filelist.add("RUPAY DOM PRESENTMENT CYCLE 2");
			filelist.add("RUPAY DOM PRESENTMENT CYCLE 3");
			filelist.add("RUPAY DOM PRESENTMENT CYCLE 4");

			filelist.add("QSPARC INT PRESENTMENT CYCLE 1");
			filelist.add("QSPARC DOM PRESENTMENT CYCLE 1");
			filelist.add("QSPARC DOM PRESENTMENT CYCLE 2");
			filelist.add("QSPARC DOM PRESENTMENT CYCLE 3");
			filelist.add("QSPARC DOM PRESENTMENT CYCLE 4");
			
			filelist.add("RUPAY INT OFFLINE PRESENTMENT CYCLE 1");
			filelist.add("RUPAY DOM OFFLINE PRESENTMENT CYCLE 1");
			filelist.add("RUPAY DOM OFFLINE PRESENTMENT CYCLE 2");
			filelist.add("RUPAY DOM OFFLINE PRESENTMENT CYCLE 3");
			filelist.add("RUPAY DOM OFFLINEPRESENTMENT CYCLE 4");

			filelist.add("QSPARC INT OFFLINE PRESENTMENT CYCLE 1");
			filelist.add("QSPARC DOM OFFLINE PRESENTMENT CYCLE 1");
			filelist.add("QSPARC DOM OFFLINE PRESENTMENT CYCLE 2");
			filelist.add("QSPARC DOM OFFLINE PRESENTMENT CYCLE 3");
			filelist.add("QSPARC DOM OFFLINE PRESENTMENT CYCLE 4");
			filelist.add("CBS FN100");
			filelist.add("CBS 5165005811356");
			filelist.add("CBS 5165005139968");
			filelist.add("CBS 5165005139948");
			filelist.add("CBS 5165005712453");
			filelist.add("CBS 5165005811354");
			filelist.add("CBS 5165005139949");
			filelist.add("CBS 5165005811351");
			filelist.add("CBS 5165005811352");
			filelist.add("CBS 5165005712451");
			filelist.add("CBS 5165005139950");
			filelist.add("CBS 5165005139951");
			filelist.add("CBS 5165005139924");
			filelist.add("CBS 5165005139926");
			filelist.add("CBS 5165005811355");
			filelist.add("CBS 5165003182550");
			filelist.add("CBS 5165005139954");
			filelist.add("CBS 152281");
			filelist.add("CBS 5165003182549");
			filelist.add("CBS 5165005139931");
			filelist.add("CBS 5165005139933");
			filelist.add("CBS 5165005139934");
			filelist.add("CBS 5165005139938");
			filelist.add("CBS 5165005139970");
			filelist.add("CBS 5165005139971");
			filelist.add("CBS 5165005139932");
			filelist.add("CBS 5165005139936");
			filelist.add("MASTERCARD ISS");
			filelist.add("MASTERCARD ACQ DOM");
			filelist.add("MASTERCARD ACQ INT");
			filelist.add("MAPPING1");
			filelist.add("MAPPING2");
			filelist.add("CTC ACQ");
			filelist.add("CTC ISS");
			filelist.add("ICCW ACQ");
			filelist.add("VISA ACQ INT");
			filelist.add("VISA ISS");
			filelist.add("VISA ACQ DOM");
			filelist.add("RUPAY DOM");
			filelist.add("RUPAY INT");
			filelist.add("NFS ACQ");
			filelist.add("NFS ISS");
			filelist.add("DFS ACQ");
			filelist.add("DFS ISS");
			filelist.add("ICD ACQ");
			filelist.add("ICD ISS");
			filelist.add("JCB ACQ");
			filelist.add("JCB ISS");

			for (int i = 0; i < filelist.size(); i++) {
				Common_data_validation cdv = new Common_data_validation();

				if (!commonDataValidationRepository.findByFileTypeAndFileDate(filelist.get(i), fileDate).isEmpty()) {
					continue;
				} else {
					if (filelist.get(i).equalsIgnoreCase("RUPAY INT") || filelist.get(i).equalsIgnoreCase("RUPAY DOM")
							|| filelist.get(i).equalsIgnoreCase("VISA ACQ DOM")
							|| filelist.get(i).equalsIgnoreCase("VISA ACQ INT")
							|| filelist.get(i).equalsIgnoreCase("MAPPING1")
							|| filelist.get(i).equalsIgnoreCase("MAPPING2")
							|| filelist.get(i).equalsIgnoreCase("CTC ACQ")
							|| filelist.get(i).equalsIgnoreCase("CTC ISS")
							|| filelist.get(i).equalsIgnoreCase("ICCW ACQ")
							|| filelist.get(i).equalsIgnoreCase("VISA ISS")
							|| filelist.get(i).equalsIgnoreCase("MASTERCARD ACQ INT")
							|| filelist.get(i).equalsIgnoreCase("MASTERCARD ACQ DOM")
							|| filelist.get(i).equalsIgnoreCase("MASTERCARD ISS")
							|| filelist.get(i).equalsIgnoreCase("NFS ACQ")
							|| filelist.get(i).equalsIgnoreCase("NFS ISS")
							|| filelist.get(i).equalsIgnoreCase("DFS ACQ")
							|| filelist.get(i).equalsIgnoreCase("DFS ISS")
							|| filelist.get(i).equalsIgnoreCase("ICD ACQ")
							|| filelist.get(i).equalsIgnoreCase("ICD ISS")
							|| filelist.get(i).equalsIgnoreCase("JCB ACQ")
							|| filelist.get(i).equalsIgnoreCase("JCB ISS")) {
						cdv.setFileType(filelist.get(i));
						cdv.setCategory("RECON ENTRY");
						cdv.setFiledate(fileDate);

					} else {
						cdv.setFileType(filelist.get(i));
						cdv.setCategory("FILE ENTRY");
						cdv.setFiledate(fileDate);
					}

					commonDataValidationRepository.save(cdv);
				}

			}

			logger.error("FileType Inserted Succesfully");
			return true;
		} catch (Exception e) {
			logger.error("Error processing file", e);
			return false;
		}
	}

	public Map<String, String> getAcqReconSatusValues(String Date) {

		Map<String, String> acquirerReconStatus = new HashMap<>();

		String[] networkList = { "NFS", "JCB", "DFS", "ICD", "CTC", "ICCW", "VISA", "RUPAY", "MASTERCARD" };

		for (String network : networkList) {

			String acqStatus = "";
			if (network.equalsIgnoreCase("VISA") || network.equalsIgnoreCase("MASTERCARD")) {

				String acqStatus1 = commonDataValidationRepository.findReconStatusByFileDate(network + " ACQ DOM",
						Date);

				String acqStatus2 = commonDataValidationRepository.findReconStatusByFileDate(network + " ACQ INT",
						Date);

				acqStatus = acqStatus1 + "," + acqStatus2;

			} else if (network.equalsIgnoreCase("RUPAY")) {

				String acqStatus1 = commonDataValidationRepository.findReconStatusByFileDate(network + " DOM", Date);

				acqStatus = acqStatus1;

			} else {

				acqStatus = commonDataValidationRepository.findReconStatusByFileDate(network + " ACQ", Date);

			}

			acquirerReconStatus.put(network, acqStatus);

		}

		return acquirerReconStatus;

	}

	public Map<String, String> getIssReconSatusValues(String Date) {

		Map<String, String> issuerReconStatus = new HashMap<>();

		String[] networkList = { "NFS", "JCB", "DFS", "ICD", "C2C", "ICCW", "VISA", "RUPAY", "MASTERCARD" };

		for (String network : networkList) {
			String issStatus = "";
			if (network.equalsIgnoreCase("RUPAY")) {

				String issStatus1 = commonDataValidationRepository.findReconStatusByFileDate(network + " INT", Date);

				issStatus = issStatus1;

			} else {

				issStatus = commonDataValidationRepository.findReconStatusByFileDate(network + " ISS", Date);

			}

			issuerReconStatus.put(network, issStatus);
		}

		return issuerReconStatus;

	}

	public boolean UpdateFileStatus(String fileType, String filedate, String filename, String count,
			String uploadStatus) {

		int updateStatus = commonDataValidationRepository.updateStatusByFileTypeOrFiledate(fileType, filedate, filename,
				count, uploadStatus);
		if (updateStatus > 0) {
			logger.error("Upload Status updated");
			return true;
		} else {
			logger.error("unabele to update ");
			return false;

		}

	}

}
