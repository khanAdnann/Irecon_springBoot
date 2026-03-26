package com.irecon.innovation.services.Impl;

import java.io.BufferedReader;
import java.io.IOException;
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

import com.irecon.innovation.dao.SwitchTLFRawDataJdbcDao;
import com.irecon.innovation.entity.Common_data_validation;
import com.irecon.innovation.entity.SwitchTLFRawData;
import com.irecon.innovation.repository.CommonDataValidationRepository;
import com.irecon.innovation.repository.SwitchTLFRawDataRepository;
import com.irecon.innovation.services.SwitchTLFRawDataService;

@Service
public class SwitchTLFRawDataServiceImpl implements SwitchTLFRawDataService {
	private static final Logger logger = LoggerFactory.getLogger(SwitchTLFRawDataServiceImpl.class);

	@Autowired
	private SwitchTLFRawDataRepository switchTLFRepository;
	@Autowired
	private SwitchTLFRawDataJdbcDao switchTLFJdbcDao;

	@Autowired
	private CommonDataValidationRepository commonDataValidationRepository;

	@Override
	public boolean SaveSwitchTlfFile(MultipartFile file, String fileDate) {
		ExecutorService executor = Executors.newFixedThreadPool(8); // Adjust threads based on your CPU/db capacity
		List<Future<?>> futures = new ArrayList<>();

		logger.info("Save method Switch TLF " + fileDate);
		int batchNumber = 0, batchSize = 100000;
		int batchCount = 0;

		List<SwitchTLFRawData> rawDataSet = new ArrayList<>();
		BufferedReader reader = null;
		Long count = 0L;

		try {
			reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
			String thisLine;

			while ((thisLine = reader.readLine()) != null) {
				if (thisLine.contains("FTTLF")) break;

				if (count == 1 && thisLine.trim().length() == 1045) {
					String pat = "[^\\w ]";
					thisLine = thisLine.trim().replaceAll(pat, " ");
					thisLine = thisLine.trim().replaceAll(thisLine.substring(3, 157), "");
				}

				if (thisLine.trim().isEmpty() ||
					List.of(5, 91, 92, 106, 176).contains(thisLine.trim().length())) continue;

				if (List.of(891, 896, 904, 1024, 1284).contains(thisLine.trim().length())) {

					SwitchTLFRawData switchTLF = new SwitchTLFRawData();
					switchTLF.setDateTime(thisLine.substring(14, 33).trim());
					switchTLF.setRecType(thisLine.substring(33, 35).trim());
					switchTLF.setAuthPpd(thisLine.substring(35, 39).trim());
					switchTLF.setTermLn(thisLine.substring(39, 43).trim());
					switchTLF.setTermFiid(thisLine.substring(43, 47).trim());
					switchTLF.setTermTermId(thisLine.substring(47, 63).trim());
					switchTLF.setCrdLn(thisLine.substring(63, 67).trim());
					switchTLF.setCrdFiid(thisLine.substring(67, 71).trim());
					switchTLF.setCrdPan(thisLine.substring(71, 77).trim());
					switchTLF.setCrdMbrNum(thisLine.substring(77, 82).trim());
					switchTLF.setBrchId(thisLine.substring(82, 92).trim());
					switchTLF.setRegnId(thisLine.substring(92, 102).trim());
					switchTLF.setUserFld1x(thisLine.substring(102, 112).trim());
					switchTLF.setTypCde(thisLine.substring(112, 118).trim());
					switchTLF.setTyp(thisLine.substring(118, 128).trim());
					switchTLF.setRteStat(thisLine.substring(128, 138).trim());
					switchTLF.setOriginator(thisLine.substring(138, 148).trim());
					switchTLF.setResponder(thisLine.substring(148, 158).trim());
					switchTLF.setEntryTime(thisLine.substring(158, 168).trim());
					switchTLF.setExitTime(thisLine.substring(168, 178).trim());
					switchTLF.setReEntryTime(thisLine.substring(178, 188).trim());
					switchTLF.setTranDate(thisLine.substring(188, 198).trim());
					switchTLF.setTranTim(thisLine.substring(198, 208).trim());
					switchTLF.setPostDat(thisLine.substring(208, 218).trim());
					switchTLF.setAcqIchgSetlDat(thisLine.substring(218, 228).trim());
					switchTLF.setIssIchgSetlDat(thisLine.substring(228, 238).trim());
					switchTLF.setTermTyp(thisLine.substring(238, 248).trim());
					switchTLF.setTimOfst(thisLine.substring(248, 258).trim());
					switchTLF.setAcqInstIdNum(thisLine.substring(258, 268).trim());
					switchTLF.setRcvInstIdNum(thisLine.substring(268, 278).trim());
					switchTLF.setTranCde(thisLine.substring(278, 288).trim());
					switchTLF.setFromAcct(thisLine.substring(288, 298).trim());
					switchTLF.setUserFld1(thisLine.substring(298, 308).trim());
					switchTLF.setToAcct(thisLine.substring(308, 318).trim());
					switchTLF.setMultAcct(thisLine.substring(318, 328).trim());
					switchTLF.setAmt1(thisLine.substring(328, 338).trim());
					switchTLF.setAmt2(thisLine.substring(338, 348).trim());
					switchTLF.setAmt3(thisLine.substring(348, 358).trim());
					switchTLF.setDepBalCr(thisLine.substring(358, 368).trim());
					switchTLF.setDepTyp(thisLine.substring(368, 378).trim());
					switchTLF.setRespCde(thisLine.substring(378, 388).trim());
					switchTLF.setTermNameLoc(thisLine.substring(388, 398).trim());
					switchTLF.setTermOwnerName(thisLine.substring(398, 408).trim());
					switchTLF.setTermCity(thisLine.substring(408, 418).trim());
					switchTLF.setTermSt(thisLine.substring(418, 428).trim());
					switchTLF.setTermCntry(thisLine.substring(428, 438).trim());
					switchTLF.setOrigOseqNum(thisLine.substring(438, 448).trim());
					switchTLF.setOrigOtranDat(thisLine.substring(448, 458).trim());
					switchTLF.setOrigOtranTim(thisLine.substring(458, 468).trim());
					switchTLF.setOrigB24Post(thisLine.substring(468, 478).trim());
					switchTLF.setOrigCrncyCde(thisLine.substring(478, 488).trim());
					switchTLF.setMultCrncyAuthCrncyCde(thisLine.substring(488, 498).trim());
					switchTLF.setMultCrncyAuthConvRate(thisLine.substring(498, 508).trim());
					switchTLF.setMultCrncySetlCrncyCde(thisLine.substring(508, 518).trim());
					switchTLF.setMultCrncySetlConvRate(thisLine.substring(518, 528).trim());
					switchTLF.setMultCrncyConvDatTim(thisLine.substring(528, 538).trim());
					switchTLF.setRvslRsn(thisLine.substring(538, 548).trim());
					switchTLF.setPinOfst(thisLine.substring(548, 558).trim());
					switchTLF.setShrgGrp(thisLine.substring(558, 568).trim());
					switchTLF.setDestOrder(thisLine.substring(568, 578).trim());
					switchTLF.setAuthIdResp(thisLine.substring(578, 588).trim());
					switchTLF.setRefrImpInd(thisLine.substring(588, 598).trim());
					switchTLF.setRefrAvailImp(thisLine.substring(598, 608).trim());
					switchTLF.setRefrLedgImp(thisLine.substring(608, 618).trim());
					switchTLF.setRefrHldAmtImp(thisLine.substring(618, 628).trim());
					switchTLF.setRefrCafRefrInd(thisLine.substring(628, 638).trim());
					switchTLF.setRefrUserFld3(thisLine.substring(638, 648).trim());
					switchTLF.setDepSetlImpFlg(thisLine.substring(648, 658).trim());
					switchTLF.setAdjSetlImpFlg(thisLine.substring(658, 668).trim());
					switchTLF.setRefrInd(thisLine.substring(668, 678).trim());
					switchTLF.setUserFld4(thisLine.substring(678, 688).trim());
					switchTLF.setFrwdInstIdNum(thisLine.substring(688, 698).trim());
					switchTLF.setCrdAccptIdNum(thisLine.substring(698, 708).trim());
					switchTLF.setCrdIssIdNum(thisLine.substring(708, 718).trim());
					switchTLF.setUserFld6(thisLine.substring(718, 728).trim());
					switchTLF.setDcrsRemarks(thisLine.substring(728, 738).trim());

					switchTLF.setFileDate(fileDate);
					switchTLF.setFileName(file.getOriginalFilename());

					rawDataSet.add(switchTLF);
					count++;

					if (++batchCount % batchSize == 0) {
						batchNumber++;
						logger.info("Batch Executed is " + batchNumber);
						List<SwitchTLFRawData> toSave = new ArrayList<>(rawDataSet);
						
						futures.add(executor.submit(() -> switchTLFJdbcDao.batchInsert(toSave)));
						rawDataSet.clear();
					}
				}
			}

			// Final leftover batch
			if (!rawDataSet.isEmpty()) {
				List<SwitchTLFRawData> toSave = new ArrayList<>(rawDataSet);
				futures.add(executor.submit(() -> switchTLFJdbcDao.batchInsert(toSave)));
			}

			// Wait for all tasks to finish
			for (Future<?> f : futures) f.get(); // Waits for each batch to finish

			executor.shutdown();

			int updateStatus = commonDataValidationRepository.updateStatusByFileTypeOrFiledate(
				"SWITCH ATM", fileDate, file.getOriginalFilename(), String.valueOf(count), "Y");

			logger.info(updateStatus > 0 ? "Upload status updated." : "Unable to update status.");

			logger.info("Successfully Uploaded!");
			return true;

		} catch (Exception e) {
			logger.error("Error processing file", e);
			return false;
		} finally {
			try {
				if (reader != null) reader.close();
			} catch (IOException e) {
				logger.error("Error closing reader", e);
			}
		}

	}
}
