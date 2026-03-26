
package com.irecon.innovation.services.Impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.irecon.innovation.entity.Common_data_validation;
import com.irecon.innovation.entity.SwitchPTLFRawData;
import com.irecon.innovation.entity.SwitchTLFRawData;
import com.irecon.innovation.repository.CommonDataValidationRepository;
import com.irecon.innovation.repository.SwitchPTLFRawDataRepository;
import com.irecon.innovation.repository.SwitchTLFRawDataRepository;
import com.irecon.innovation.services.SwitchPTLFRawDataService;

@Service
public class SwitchPTLFRawDataServiceImpl implements SwitchPTLFRawDataService {
	private static final Logger logger = LoggerFactory.getLogger(SwitchPTLFRawDataServiceImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Autowired
	private SwitchPTLFRawDataRepository switchPTLFRepository;

	@Autowired
	private CommonDataValidationRepository commonDataValidationRepository;

	
	private static final String INSERT_SQL = "  INSERT INTO  switch_pos_rawdata (DATE_TIME,REC_TYP,CRD_LN,CRD_FIID,CRAD_NUM,CRD_MBR_NUM,RETL_KEY_IN,RDF_KEY,RDF_KEY_GRP,RDF_KEY_REGN,RDF_KEY_ID,TERM_ID,SHIFT_NUM,BATCH_NUM,TERM_IN,TERM_FIID,TERM_ID2,TERM_TIME,TERM_ID3,TKEY_RKEY_REC_FRMT,TKEY_RKEY_RETAILER_ID,TKEY_RKEY_CLERK_ID,DATA_FLAG,TYPE,RTE_STAT,ORIGINATOR,RESPONDER,ISS_CDE,ENTRY_TIME,EXIT_TIME,RE_ENTRY_TIME,TRAN_DATE,TRAN_TIM,POST_DAT,ACQ_ICHG_SETL_DAT,ISS_ICHG_SETL_DAT,SEQ_NUM,TERM_NAME_LOC,TERM_OWNER_NAME,TERM_CITY,TERM_ST,TERM_CNTRY_CDE,BRCH_ID,USER_FID,TERM_TIM_OFST,ACQ_INST_ID_NUM,RCV_INST_ID_NUM,TERM_TYPE,CLERK_ID,CTR_AUTH,CTR_AUTH_GRP,CTR_AUTH_USER_ID,RETL_SIC_CDE,ORIG,DEST,TRAN_CDE,CRD_TYPE,ACCT,RESP_CDE,AMOUNT_1,AMOUNT_2,EXPIRY_DATE,TRACK2,PIN_OFST,PRE_AUTH_SEQ_NUM,INVOICE_NUM,ORIG_INVOICE_NUM,AUTHORIZER,AUTH_IND,SHIFT_NUM_2,BATCH_SEQ_NUM,APPRV_CODE,APPRV_CODE_LENGTH,ICHG_RESP,PSEUDO_TERM_ID,RFRL_PHONE,DUMMY_1,DFT_CAPTURE_FLAG,SELT_FLAG,RVRL_CODE,REA_FOR_CHRGBCK,NUM_OF_CHRGBCK,PT_SRV_COND_CODE,PT_SRV_ENTRY_MODE,AUTH_IND2,ORIG_CRNCY_CODE,MULTI_CRNY_AUTH_CRNCY_CODE,MULTY_CRNCY_AUTH_CONV_RATE,MULTI_CRNCY_SETL_CRNCY_CODE,MULTI_CRNCY_SETL_CONV_RATE,MULTI_CRNCY_CONV_DAT_TIME,REFR_IMP_IND,REFR_AVAIL_CR,REFR_CR_LMT,REFR_CR_BAL,REFR_TTL,REFR_CUR,ADJ_SETL_IMPACT_FLAG,REFR_IND,FRWD_INST_ID_NUM,CRD_ACCPT_ID_NUM,CRD_ISS_ID_NUM,ORIG_MSG_TYPE,ORIG_TRAN_TIM,ORIG_TRAN_DATE,ORIG_SEQ_NUM,ORIG_B24_POST_DATE,EXCP_RSN_CODE,OVRRDE_FLAG, ADDR,ZIP,ADDR_VERFY_STAT,PIN_IND,PIN_TRIES,PRE_AUTH_TS,PRE_AUTH_HLDS_STAT,USER_FID2,USER_DATA_D_LEN,USER_DATA_D_INFO,DCRS_REMARKS,FILEDATE,FILENAME) VALUES (" +
	        String.join(", ", Collections.nCopies(122, "?")) + ")";
	@Override
	public boolean SaveSwitchPtlfFile(MultipartFile file, String fileDate) {
		logger.info("Save method Switch PTLF " + fileDate);
		int batchNumber = 0, batchSize = 2000;
		int batchCount = 0;
		List<Object[]> batchArgs = new ArrayList<>();
		List<SwitchPTLFRawData> rawDataSet = new ArrayList<>();
		BufferedReader reader = null;
		Long count = 0L;

		try {
			reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
			
			
			String thisline, FIRST_DATA, Second_DATA;
		
			while ((thisline = reader.readLine()) != null) {
				SwitchPTLFRawData data = new SwitchPTLFRawData();
				if (count == 1 && thisline.trim().length() == 950) {
					String pat = "[^\\w ]";
					thisline = thisline.trim().replaceAll(pat, " ");
					thisline = thisline.trim().replaceAll(thisline.substring(5, 153), "");
				}
				if (count == 1 && thisline.trim().length() == 2774) {
					String pat = "[^\\w ]";
					thisline = thisline.trim().replaceAll(pat, " ");
					thisline = thisline.trim().replaceAll(thisline.substring(0, 150), "DD");
				}
				if (thisline.trim().length() == 0 || thisline.trim().length() == 192 || thisline.trim().length() == 5
						|| thisline.trim().length() == 241 || thisline.trim().length() == 848)
					continue;
				if (thisline.trim().length() == 898 || thisline.trim().length() == 802
						|| thisline.trim().length() == 2026) {
					FIRST_DATA = thisline.substring(8, 14).trim();
					if (FIRST_DATA.contains("1828DR")) {

						data.setDateTime(thisline.substring(14, 33).trim());
						data.setRecTyp(thisline.substring(33, 35).trim());
						data.setCrdLn(thisline.substring(35, 39).trim());
						data.setCrdFiid(thisline.substring(39, 43).trim());
						data.setCradNum(thisline.substring(43, 62).trim());
						data.setCrdMbrNum(thisline.substring(62, 65).trim());
						data.setRetlKeyIn(thisline.substring(65, 69).trim());
						data.setRdfKey(thisline.substring(65, 69).trim());
						data.setRdfKeyGrp(thisline.substring(73, 77).trim());
						data.setRdfKeyRegn(thisline.substring(77, 81).trim());
						data.setRdfKeyId(thisline.substring(81, 100).trim());
						data.setTermId(thisline.substring(100, 116).trim());
						data.setShiftNum(thisline.substring(116, 119).trim());
						data.setBatchNum(thisline.substring(119, 122).trim());
						data.setTermIn(thisline.substring(122, 126).trim());
						data.setTermFiid(thisline.substring(126, 130).trim());
						data.setTermId2(thisline.substring(130, 146).trim());
						data.setTermTime(thisline.substring(146, 154).trim());
						data.setTermId3(thisline.substring(154, 170).trim());
						data.setTkeyRkeyRecFrmt(thisline.substring(170, 171).trim());
						data.setTkeyRkeyRetailerId(thisline.substring(171, 190).trim());
						data.setTkeyRkeyClerkId(thisline.substring(190, 196).trim());
						data.setDataFlag(thisline.substring(196, 197).trim());
						data.setType(thisline.substring(197, 201).trim());
						data.setRteStat(thisline.substring(201, 203).trim());
						data.setOriginator(thisline.substring(203, 204).trim());
						data.setResponder(thisline.substring(204, 205).trim());
						data.setIssCde(thisline.substring(205, 207).trim());
						data.setEntryTime(thisline.substring(207, 226).trim());
						data.setExitTime(thisline.substring(226, 245).trim());
						data.setReEntryTime(thisline.substring(245, 264).trim());
						data.setTranDate(thisline.substring(264, 270).trim());
						data.setTranTim(thisline.substring(270, 278).trim());
						data.setPostDat(thisline.substring(278, 284).trim());
						data.setAcqIchgSetlDat(thisline.substring(284, 290).trim());
						data.setIssIchgSetlDat(thisline.substring(290, 296).trim());
						data.setSeqNum(thisline.substring(296, 308).trim());
						data.setTermNameLoc(thisline.substring(308, 333).trim());
						data.setTermOwnerName(thisline.substring(333, 355).trim());
						data.setTermCity(thisline.substring(355, 368).trim());
						data.setTermSt(thisline.substring(368, 371).trim());
						data.setTermCntryCde(thisline.substring(371, 373).trim());
						data.setBrchId(thisline.substring(373, 377).trim());
						data.setUserFid(thisline.substring(377, 380).trim());
						data.setTermTimOfst(thisline.substring(380, 385).trim());
						data.setAcqInstIdNum(thisline.substring(385, 396).trim());
						data.setRcvInstIdNum(thisline.substring(396, 407).trim());
						data.setTermType(thisline.substring(407, 409).trim());
						data.setClerkId(thisline.substring(409, 415).trim());
						data.setCtrAuth(thisline.substring(415, 427).trim());

						data.setCtrAuthGrp(thisline.substring(427, 431).trim());
						data.setCtrAuthUserId(thisline.substring(431, 435).trim());
						data.setRetlSicCde(thisline.substring(435, 439).trim());
						data.setOrig(thisline.substring(439, 445).trim());
						data.setDest(thisline.substring(445, 447).trim());
						data.setTranCde(thisline.substring(447, 466).trim());
						data.setCrdType(thisline.substring(466, 469).trim());
						data.setAcct(thisline.substring(469, 488).trim());
						data.setRespCde(thisline.substring(488, 507).trim());
						data.setAmount1(thisline.substring(507, 511).trim());
						data.setAmount2(thisline.substring(511, 551).trim());
						data.setExpiryDate(thisline.substring(551, 567).trim());
						data.setTrack2(thisline.substring(567, 579).trim());
						data.setPinOfst(thisline.substring(579, 589).trim());
						data.setPreAuthSeqNum(thisline.substring(589, 599).trim());
						data.setInvoiceNum(thisline.substring(599, 615).trim());
						data.setOrigInvoiceNum(thisline.substring(615, 616).trim());
						data.setAuthorizer(thisline.substring(616, 619).trim());
						data.setAuthInd(thisline.substring(619, 622).trim());
						data.setShiftNum2(thisline.substring(622, 630).trim());
						data.setBatchSeqNum(thisline.substring(630, 631).trim());
						data.setApprvCode(thisline.substring(631, 639).trim());
						data.setApprvCodeLength(thisline.substring(639, 643).trim());
						data.setIchgResp(thisline.substring(643, 663).trim());
						data.setPseudoTermId(thisline.substring(663, 666).trim());
						data.setRfrlPhone(thisline.substring(666, 667).trim());
						data.setDummy1(thisline.substring(667, 668).trim());
						data.setDftCaptureFlag(thisline.substring(668, 670).trim());
						data.setSeltFlag(thisline.substring(670, 672).trim());
						data.setRvrlCode(thisline.substring(672, 673).trim());
						data.setReaForChrgbck(thisline.substring(673, 675).trim());
						data.setNumOfChrgbck(thisline.substring(675, 678).trim());
						data.setPtSrvCondCode(thisline.substring(678, 679).trim());
						data.setPtSrvEntryMode(thisline.substring(679, 682).trim());
						data.setAuthInd2(thisline.substring(682, 685).trim());
						data.setOrigCrncyCode(thisline.substring(685, 693).trim());
						data.setMultiCrnyAuthCrncyCode(thisline.substring(693, 696).trim());
						data.setMultyCrncyAuthConvRate(thisline.substring(696, 704).trim());
						data.setMultiCrncySetlCrncyCode(thisline.substring(704, 723).trim());
						data.setMultiCrncySetlConvRate(thisline.substring(723, 724).trim());
						data.setMultiCrncyConvDatTime(thisline.substring(724, 725).trim());
						data.setRefrImpInd(thisline.substring(725, 726).trim());
						data.setRefrAvailCr(thisline.substring(726, 727).trim());
						data.setRefrCrLmt(thisline.substring(727, 728).trim());
						data.setRefrCrBal(thisline.substring(728, 729).trim());
						data.setRefrTtl(thisline.substring(729, 730).trim());
						data.setRefrCur(thisline.substring(730, 734).trim());
						data.setAdjSetlImpactFlag(thisline.substring(734, 750).trim());
						data.setRefrInd(thisline.substring(750, 761).trim());
						data.setFrwdInstIdNum(thisline.substring(761, 772).trim());
						data.setCrdAccptIdNum(thisline.substring(772, 776).trim());
						data.setCrdIssIdNum(thisline.substring(776, 784).trim());
						data.setOrigMsgType(thisline.substring(784, 790).trim());
						data.setOrigTranTim(thisline.substring(790, 802).trim());

						if (thisline.trim().length() == 802) {

							data.setOrigTranDate("");
							data.setExcpRsnCode("");
							data.setOvrrdeFlag("");
							data.setAddr("");
							data.setZip("");
							data.setAddrVerfyStat("");
							data.setPinInd("");
							data.setPinTries("");
							data.setPreAuthTs("");
							data.setPreAuthHldsStat("");
							data.setUserFid2("");
							data.setUserDataDLen("");
							data.setUserDataDInfo("");

						} else {

							data.setOrigTranDate(thisline.substring(802, 806).trim());
							data.setExcpRsnCode(thisline.substring(806, 809).trim());
							data.setOvrrdeFlag(thisline.substring(809, 810).trim());
							data.setAddr(thisline.substring(810, 830).trim());
							data.setZip(thisline.substring(830, 839).trim());
							data.setAddrVerfyStat(thisline.substring(839, 840).trim());
							data.setPinInd(thisline.substring(840, 841).trim());
							data.setPinTries(thisline.substring(841, 842).trim());
							data.setPreAuthTs(thisline.substring(842, 856).trim());
							data.setPreAuthHldsStat(thisline.substring(856, 857).trim());
							data.setUserFid2(thisline.substring(857, 890).trim());
							data.setUserDataDLen(thisline.substring(890, 894).trim());
							data.setUserDataDInfo(thisline.substring(894, 895).trim());
							data.setDcrsRemarks("");
							data.setFileDate(fileDate);
							data.setFilename(file.getOriginalFilename());
						}

					}

					rawDataSet.add(data);
					count++;
				} else {

					FIRST_DATA = thisline.substring(8, 14).trim();
					if (FIRST_DATA.contains("1828DR")) {

						data.setDateTime(thisline.substring(14, 33).trim());
						data.setRecTyp(thisline.substring(33, 35).trim());
						data.setCrdLn(thisline.substring(35, 39).trim());
						data.setCrdFiid(thisline.substring(39, 43).trim());
						data.setCradNum(thisline.substring(43, 62).trim());
						data.setCrdMbrNum(thisline.substring(62, 65).trim());
						data.setRetlKeyIn(thisline.substring(65, 69).trim());
						data.setRdfKey(thisline.substring(65, 69).trim());
						data.setRdfKeyGrp(thisline.substring(73, 77).trim());
						data.setRdfKeyRegn(thisline.substring(77, 81).trim());
						data.setRdfKeyId(thisline.substring(81, 100).trim());
						data.setTermId(thisline.substring(100, 116).trim());
						data.setShiftNum(thisline.substring(116, 119).trim());
						data.setBatchNum(thisline.substring(119, 122).trim());
						data.setTermIn(thisline.substring(122, 126).trim());
						data.setTermFiid(thisline.substring(126, 130).trim());
						data.setTermId2(thisline.substring(130, 146).trim());
						data.setTermTime(thisline.substring(146, 154).trim());
						data.setTermId3(thisline.substring(154, 170).trim());
						data.setTkeyRkeyRecFrmt(thisline.substring(170, 171).trim());
						data.setTkeyRkeyRetailerId(thisline.substring(171, 190).trim());
						data.setTkeyRkeyClerkId(thisline.substring(190, 196).trim());
						data.setDataFlag(thisline.substring(196, 197).trim());
						data.setType(thisline.substring(197, 201).trim());
						data.setRteStat(thisline.substring(201, 203).trim());
						data.setOriginator(thisline.substring(203, 204).trim());
						data.setResponder(thisline.substring(204, 205).trim());
						data.setIssCde(thisline.substring(205, 207).trim());
						data.setEntryTime(thisline.substring(207, 226).trim());
						data.setExitTime(thisline.substring(226, 245).trim());
						data.setReEntryTime(thisline.substring(245, 264).trim());
						data.setTranDate(thisline.substring(264, 270).trim());
						data.setTranTim(thisline.substring(270, 278).trim());
						data.setPostDat(thisline.substring(278, 284).trim());
						data.setAcqIchgSetlDat(thisline.substring(284, 290).trim());
						data.setIssIchgSetlDat(thisline.substring(290, 296).trim());
						data.setSeqNum(thisline.substring(296, 308).trim());
						data.setTermNameLoc(thisline.substring(308, 333).trim());
						data.setTermOwnerName(thisline.substring(333, 355).trim());
						data.setTermCity(thisline.substring(355, 368).trim());
						data.setTermSt(thisline.substring(368, 371).trim());
						data.setTermCntryCde(thisline.substring(371, 373).trim());
						data.setBrchId(thisline.substring(373, 377).trim());
						data.setUserFid(thisline.substring(377, 380).trim());
						data.setTermTimOfst(thisline.substring(380, 385).trim());
						data.setAcqInstIdNum(thisline.substring(385, 396).trim());
						data.setRcvInstIdNum(thisline.substring(396, 407).trim());
						data.setTermType(thisline.substring(407, 409).trim());
						data.setClerkId(thisline.substring(409, 415).trim());
						data.setCtrAuth(thisline.substring(415, 427).trim());

						data.setCtrAuthGrp(thisline.substring(427, 431).trim());
						data.setCtrAuthUserId(thisline.substring(431, 435).trim());
						data.setRetlSicCde(thisline.substring(435, 439).trim());
						data.setOrig(thisline.substring(439, 445).trim());
						data.setDest(thisline.substring(445, 447).trim());
						data.setTranCde(thisline.substring(447, 466).trim());
						data.setCrdType(thisline.substring(466, 469).trim());
						data.setAcct(thisline.substring(469, 488).trim());
						data.setRespCde(thisline.substring(488, 507).trim());
						data.setAmount1(thisline.substring(507, 511).trim());
						data.setAmount2(thisline.substring(511, 551).trim());
						data.setExpiryDate(thisline.substring(551, 567).trim());
						data.setTrack2(thisline.substring(567, 579).trim());
						data.setPinOfst(thisline.substring(579, 589).trim());
						data.setPreAuthSeqNum(thisline.substring(589, 599).trim());
						data.setInvoiceNum(thisline.substring(599, 615).trim());
						data.setOrigInvoiceNum(thisline.substring(615, 616).trim());
						data.setAuthorizer(thisline.substring(616, 619).trim());
						data.setAuthInd(thisline.substring(619, 622).trim());
						data.setShiftNum2(thisline.substring(622, 630).trim());
						data.setBatchSeqNum(thisline.substring(630, 631).trim());
						data.setApprvCode(thisline.substring(631, 639).trim());
						data.setApprvCodeLength(thisline.substring(639, 643).trim());
						data.setIchgResp(thisline.substring(643, 663).trim());
						data.setPseudoTermId(thisline.substring(663, 666).trim());
						data.setRfrlPhone(thisline.substring(666, 667).trim());
						data.setDummy1(thisline.substring(667, 668).trim());
						data.setDftCaptureFlag(thisline.substring(668, 670).trim());
						data.setSeltFlag(thisline.substring(670, 672).trim());
						data.setRvrlCode(thisline.substring(672, 673).trim());
						data.setReaForChrgbck(thisline.substring(673, 675).trim());
						data.setNumOfChrgbck(thisline.substring(675, 678).trim());
						data.setPtSrvCondCode(thisline.substring(678, 679).trim());
						data.setPtSrvEntryMode(thisline.substring(679, 682).trim());
						data.setAuthInd2(thisline.substring(682, 685).trim());
						data.setOrigCrncyCode(thisline.substring(685, 693).trim());
						data.setMultiCrnyAuthCrncyCode(thisline.substring(693, 696).trim());
						data.setMultyCrncyAuthConvRate(thisline.substring(696, 704).trim());
						data.setMultiCrncySetlCrncyCode(thisline.substring(704, 723).trim());
						data.setMultiCrncySetlConvRate(thisline.substring(723, 724).trim());
						data.setMultiCrncyConvDatTime(thisline.substring(724, 725).trim());
						data.setRefrImpInd(thisline.substring(725, 726).trim());
						data.setRefrAvailCr(thisline.substring(726, 727).trim());
						data.setRefrCrLmt(thisline.substring(727, 728).trim());
						data.setRefrCrBal(thisline.substring(728, 729).trim());
						data.setRefrTtl(thisline.substring(729, 730).trim());
						data.setRefrCur(thisline.substring(730, 734).trim());
						data.setAdjSetlImpactFlag(thisline.substring(734, 750).trim());
						data.setRefrInd(thisline.substring(750, 761).trim());
						data.setFrwdInstIdNum(thisline.substring(761, 772).trim());
						data.setCrdAccptIdNum(thisline.substring(772, 776).trim());
						data.setCrdIssIdNum(thisline.substring(776, 784).trim());
						data.setOrigMsgType(thisline.substring(784, 790).trim());
						data.setOrigTranTim(thisline.substring(790, 802).trim());

						data.setOrigTranDate(thisline.substring(802, 806).trim());
						data.setExcpRsnCode(thisline.substring(806, 809).trim());
						data.setOvrrdeFlag(thisline.substring(809, 810).trim());
						data.setAddr(thisline.substring(810, 830).trim());
						data.setZip(thisline.substring(830, 839).trim());
						data.setAddrVerfyStat(thisline.substring(839, 840).trim());
						data.setPinInd(thisline.substring(840, 841).trim());
						data.setPinTries(thisline.substring(841, 842).trim());
						data.setPreAuthTs(thisline.substring(842, 856).trim());
						data.setPreAuthHldsStat(thisline.substring(856, 857).trim());
						data.setUserFid2(thisline.substring(857, 890).trim());
						data.setUserDataDLen(thisline.substring(890, 894).trim());
						data.setUserDataDInfo(thisline.substring(894, 895).trim());
						data.setDcrsRemarks("");
						data.setFileDate(fileDate);
						data.setFilename(file.getOriginalFilename());
					}

					rawDataSet.add(data);
					count++;
				}

				Second_DATA = thisline.substring(1836, 1842).trim();
				if (Second_DATA.contains("1828DR")) {
					
					data.setDateTime(thisline.substring(1842, 1861).trim());
					data.setRecTyp(thisline.substring(1861, 1863).trim());
					data.setCrdLn(thisline.substring(1863, 1867).trim());
					data.setCrdFiid(thisline.substring(1867, 1871).trim());
					data.setCradNum(thisline.substring(1871, 1890).trim());
					data.setCrdMbrNum(thisline.substring(1890, 1893).trim());
					data.setRetlKeyIn(thisline.substring(1893, 1897).trim());
					data.setRdfKey(thisline.substring(1897, 1901).trim());
					data.setRdfKeyGrp(thisline.substring(1901, 1905).trim());
					data.setRdfKeyRegn(thisline.substring(1905, 1909).trim());
					data.setRdfKeyId(thisline.substring(1909, 1928).trim());
					data.setTermId(thisline.substring(1928, 1944).trim());
					data.setShiftNum(thisline.substring(1944, 1947).trim());
					data.setBatchNum(thisline.substring(1947, 1950).trim());
					data.setTermIn(thisline.substring(1950, 1954).trim());
					data.setTermFiid(thisline.substring(1954, 1958).trim());
					data.setTermId2(thisline.substring(1958, 1974).trim());
					data.setTermTime(thisline.substring(1974, 1982).trim());
					data.setTermId3(thisline.substring(1982, 1998).trim());
					data.setTkeyRkeyRecFrmt(thisline.substring(1998, 1999).trim());
					data.setTkeyRkeyRetailerId(thisline.substring(1999, 2018).trim());
					data.setTkeyRkeyClerkId(thisline.substring(2018, 2024).trim());
					data.setDataFlag(thisline.substring(2024, 2025).trim());
					data.setType(thisline.substring(2025, 2029).trim());
					data.setRteStat(thisline.substring(2029, 2031).trim());
					data.setOriginator(thisline.substring(2031, 2032).trim());
					data.setResponder(thisline.substring(2032, 2033).trim());
					data.setIssCde(thisline.substring(2033, 2035).trim());
					data.setEntryTime(thisline.substring(2035, 2054).trim());
					data.setExitTime(thisline.substring(2054, 2073).trim());
					data.setReEntryTime(thisline.substring(2073, 2092).trim());
					data.setTranDate(thisline.substring(2092, 2098).trim());
					data.setTranTim(thisline.substring(2098, 2106).trim());
					data.setPostDat(thisline.substring(2106, 2112).trim());
					data.setAcqIchgSetlDat(thisline.substring(2112, 2118).trim());
					data.setIssIchgSetlDat(thisline.substring(2118, 2124).trim());
					data.setSeqNum(thisline.substring(2124, 2136).trim());
					data.setTermNameLoc(thisline.substring(2136, 2161).trim());
					data.setTermOwnerName(thisline.substring(2161, 2183).trim());
					data.setTermCity(thisline.substring(2183, 2196).trim());
					data.setTermSt(thisline.substring(2196, 2199).trim());
					data.setTermCntryCde(thisline.substring(2199, 2201).trim());
					data.setBrchId(thisline.substring(2201, 2205).trim());
					data.setUserFid(thisline.substring(2205, 2208).trim());
					data.setTermTimOfst(thisline.substring(2208, 2213).trim());
					data.setAcqInstIdNum(thisline.substring(2213, 2224).trim());
					data.setRcvInstIdNum(thisline.substring(2224, 2235).trim());
					data.setTermType(thisline.substring(2235, 2237).trim());
					data.setClerkId(thisline.substring(2237, 2243).trim());
					data.setCtrAuth(thisline.substring(2243, 2255).trim());
					data.setRetlSicCde(thisline.substring(2255, 2259).trim());
					data.setOrig(thisline.substring(2259, 2263).trim());
					data.setDest(thisline.substring(2263, 2267).trim());
					data.setTranCde(thisline.substring(2267, 2273).trim());
					data.setCrdType(thisline.substring(2273, 2275).trim());
					data.setAcct(thisline.substring(2275, 2294).trim());
					data.setRespCde(thisline.substring(2294, 2297).trim());
					data.setAmount1(thisline.substring(2297, 2316).trim());
					data.setAmount2(thisline.substring(2316, 2335).trim());
					data.setExpiryDate(thisline.substring(2335, 2339).trim());
					data.setTrack2(thisline.substring(2339, 2379).trim());
					data.setPinOfst(thisline.substring(2379, 2395).trim());
					data.setPreAuthSeqNum(thisline.substring(2395, 2407).trim());
					data.setInvoiceNum(thisline.substring(2407, 2417).trim());
					data.setOrigInvoiceNum(thisline.substring(2417, 2427).trim());
					data.setAuthorizer(thisline.substring(2427, 2443).trim());
					data.setAuthInd(thisline.substring(2443, 2444).trim());
					data.setShiftNum2(thisline.substring(2444, 2447).trim());
					data.setBatchSeqNum(thisline.substring(2447, 2450).trim());
					data.setApprvCode(thisline.substring(2450, 2458).trim());
					data.setApprvCodeLength(thisline.substring(2458, 2459).trim());
					data.setIchgResp(thisline.substring(2459, 2467).trim());
					data.setPseudoTermId(thisline.substring(2467, 2471).trim());
					data.setRfrlPhone(thisline.substring(2471, 2491).trim());
					data.setDummy1(thisline.substring(2491, 2494).trim());
					data.setDftCaptureFlag(thisline.substring(2494, 2495).trim());
					data.setSeltFlag(thisline.substring(2495, 2496).trim());
					data.setRvrlCode(thisline.substring(2496, 2498).trim());
					data.setReaForChrgbck(thisline.substring(2498, 2500).trim());
					data.setNumOfChrgbck(thisline.substring(2500, 2501).trim());
					data.setPtSrvCondCode(thisline.substring(2501, 2503).trim());
					data.setPtSrvEntryMode(thisline.substring(2503, 2506).trim());
					data.setAuthInd2(thisline.substring(2506, 2507).trim());
					data.setOrigCrncyCode(thisline.substring(2507, 2510).trim());
					data.setMultiCrnyAuthCrncyCode(thisline.substring(2510, 2513).trim());
					data.setMultyCrncyAuthConvRate(thisline.substring(2513, 2521).trim());
					data.setMultiCrncySetlCrncyCode(thisline.substring(2521, 2525).trim());
					data.setMultiCrncySetlConvRate(thisline.substring(2525, 2533).trim());
					data.setMultiCrncyConvDatTime(thisline.substring(2533, 2552).trim());
					data.setRefrImpInd(thisline.substring(2552, 2553).trim());
					data.setRefrAvailCr(thisline.substring(2553, 2554).trim());
					data.setRefrCrLmt(thisline.substring(2554, 2555).trim());
					data.setRefrCrBal(thisline.substring(2555, 2556).trim());
					data.setRefrTtl(thisline.substring(2556, 2557).trim());
					data.setRefrCur(thisline.substring(2557, 2558).trim());
					data.setAdjSetlImpactFlag(thisline.substring(2558, 2559).trim());
					data.setRefrInd(thisline.substring(2559, 2563).trim());
					data.setFrwdInstIdNum(thisline.substring(2563, 2579).trim());
					data.setCrdAccptIdNum(thisline.substring(2579, 2590).trim());
					data.setCrdIssIdNum(thisline.substring(2590, 2601).trim());
					data.setOrigMsgType(thisline.substring(2601, 2605).trim());
					data.setOrigTranTim(thisline.substring(2605, 2613).trim());
					data.setOrigTranDate(thisline.substring(2613, 2619).trim());
					data.setOrigSeqNum(thisline.substring(2619, 2631).trim());
					data.setOrigB24PostDate(thisline.substring(2631, 2635).trim());
					data.setExcpRsnCode(thisline.substring(2635, 2638).trim());
					data.setOvrrdeFlag(thisline.substring(2638, 2639).trim());
					data.setAddr(thisline.substring(2639, 2659).trim());
					data.setZip(thisline.substring(2659, 2668).trim());
					data.setAddrVerfyStat(thisline.substring(2668, 2669).trim());
					data.setPinInd(thisline.substring(2669, 2670).trim());
					data.setPinTries(thisline.substring(2670, 2671).trim());

					// For the remaining fields
					if (thisline.trim().length() == 2676 || thisline.trim().length() == 2726) {
					    data.setPreAuthTs("");
					    data.setPreAuthHldsStat("");
					    data.setUserFid2("");
					    data.setUserDataDLen("");
					    data.setUserDataDInfo("");
					} else {
					    data.setPreAuthTs(thisline.substring(2671, 2785).trim());
					    data.setPreAuthHldsStat(thisline.substring(2785, 2786).trim());
					    data.setUserFid2(thisline.substring(2786, 2787).trim());
					    data.setUserDataDLen(thisline.substring(2787, 2791).trim());
					    data.setUserDataDInfo(thisline.substring(2791, 2792).trim());
					}
					data.setDcrsRemarks("");
					data.setFileDate(fileDate);
					data.setFilename(file.getOriginalFilename());
					rawDataSet.add(data);
					count++;
				}

				if (++batchCount % batchSize == 0) {

					batchNumber++;

					logger.info("Batch Executed is " + batchNumber);
					switchPTLFRepository.saveAll(rawDataSet);
					rawDataSet = new ArrayList<>();

				}else {
					
					switchPTLFRepository.saveAll(rawDataSet);
				}

			}

			int updateStatus = commonDataValidationRepository.updateStatusByFileTypeOrFiledate("SWITCH POS", fileDate,
					file.getOriginalFilename(), String.valueOf(count), "Y");

			logger.info(updateStatus > 0 ? "Upload status updated." : "Unable to update status.");
			logger.info("Successfully uploaded file.");

			return true;
		} catch (Exception e) {
			logger.error("Error processing file", e);
			return false;
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (Exception e) {
				logger.error("Error closing reader", e);
			}
		}
	}
}
