package com.irecon.innovation.services.Impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.irecon.innovation.entity.Common_data_validation;
import com.irecon.innovation.entity.Qsparc01InternationalRawData;
import com.irecon.innovation.entity.Qsparc02InternationalRawData;
import com.irecon.innovation.entity.Qsparc03InternationalRawData;
import com.irecon.innovation.entity.Qsparc05InternationalRawData;
import com.irecon.innovation.entity.Qsparc86InternationalRawData;
import com.irecon.innovation.entity.Qsparc88InternationalRawData;
import com.irecon.innovation.repository.CommonDataValidationRepository;
import com.irecon.innovation.repository.Qsparc01InternationalRawDataRepository;
import com.irecon.innovation.repository.Qsparc02InternationalRawDataRepository;
import com.irecon.innovation.repository.Qsparc03InternationalRawDataRepository;
import com.irecon.innovation.repository.Qsparc05InternationalRawDataRepository;
import com.irecon.innovation.repository.Qsparc86InternationalRawDataRepository;
import com.irecon.innovation.repository.Qsparc88InternationalRawDataRepository;
import com.irecon.innovation.services.QsparcInternationalRawDataService;

import com.irecon.innovation.utility.QsparcHeaderUtil;
import com.irecon.innovation.utility.QsparcUtilBean;

@Service
public class QsparcInternationalRawDataServiceImpl implements QsparcInternationalRawDataService {

	private static final Logger logger = LoggerFactory.getLogger(QsparcInternationalRawDataServiceImpl.class);

	@Autowired
	private Qsparc88InternationalRawDataRepository qsparc88InternationalRawDataRepository;

	
	@Autowired
	private Qsparc01InternationalRawDataRepository qsparc01InternationalRawDataRepository;
	@Autowired
	private Qsparc02InternationalRawDataRepository qsparc02InternationalRawDataRepository;
	@Autowired
	private Qsparc03InternationalRawDataRepository qsparc03InternationalRawDataRepository;
	@Autowired
	private Qsparc05InternationalRawDataRepository qsparc05InternationalRawDataRepository;


	@Autowired
	private Qsparc86InternationalRawDataRepository qsparc86InternationalRawDataRepository;

	@Autowired
	private CommonDataValidationRepository commonDataValidationRepository;


	@Override
	public boolean SaveQsparcInternationalRawData(MultipartFile file, String fileDate) {


		logger.info("Save method SaveQsparcInternationalRawData " + fileDate);
		BufferedReader reader = null;
		Long DataCount = 0L;
		// Check if the file already exists with the same name and date

		try {
			
				if (file.getOriginalFilename().substring(0, 2).equalsIgnoreCase("88")) {

					Set<Qsparc88InternationalRawData> rawDataSet = new HashSet<>();
					Pattern TAG_REGEX = Pattern.compile(">(.+?)</");
					Pattern node_REGEX = Pattern.compile("<(.+?)>");
					QsparcHeaderUtil headerUtil = new QsparcHeaderUtil();
					QsparcUtilBean utilBean = new QsparcUtilBean();
					int batchNumber = 0, batchSize = 50000;
					int batchCount = 0;
					reader = new BufferedReader(new InputStreamReader(file.getInputStream()));

					int feesize = 1;
					@SuppressWarnings("unused")
					String thisLine, hdr = "", trl = "", trl_nFunCd = "";
					@SuppressWarnings("unused")
					String trl_nRecNum = null, transactions_count = null;
					logger.info("Process started" + (new Date()).getTime());
					while ((thisLine = reader.readLine()) != null) {

						Matcher nodeMatcher = node_REGEX.matcher(thisLine);
						nodeMatcher.find();
						if (!nodeMatcher.group(1).equalsIgnoreCase("Txn")) {
							if (nodeMatcher.group(1).equalsIgnoreCase("Hdr")) {
								hdr = "hdr";
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("/Hdr")) {
								hdr = "";
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nDtTmFlGen")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnDtTmFlGen(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nMemInstCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnMemInstCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nUnFlNm")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnUnFlNm(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nProdCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnProdCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nSetBIN")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnSetBIN(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFlCatg")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnFlCatg(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nVerNum")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnVerNum(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nAcqInstCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnAcqInstCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nAmtSet")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								double amtSet = Integer.parseInt(matcher.group(1));
								amtSet /= 100.0D;
								utilBean.setnAmtSet(String.valueOf(amtSet));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nAmtTxn")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								double amtTxn = Double.parseDouble(matcher.group(1));
								amtTxn /= 100.0D;
								utilBean.setnAmtTxn(String.valueOf(amtTxn));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nApprvlCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnApprvlCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nRRN")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnARD(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCcyCdSet")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCcyCdSet(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCcyCdTxn")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCcyCdTxn(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nConvRtSet")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnConvRtSet(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCrdAcpAddAdrs")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCrdAcpAddAdrs(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCrdAcptTrmId")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCrdAcptTrmId(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCrdAcpZipCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCrdAcpZipCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nDtSet")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								if (hdr.equalsIgnoreCase("hdr")) {
									headerUtil.setnDtSet(matcher.group(1));
									continue;
								}
								utilBean.setnDtSet(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nDtTmLcTxn")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnDtTmLcTxn(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFunCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								if (hdr.equalsIgnoreCase("hdr")) {
									headerUtil.setnFunCd(matcher.group(1));
									continue;
								}
								if (hdr.equalsIgnoreCase("Trl")) {
									trl_nFunCd = matcher.group(1);
									logger.info(trl_nFunCd);
									continue;
								}
								utilBean.setnFunCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nLtPrsntInd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnLtPrsntInd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nMTI")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								if (hdr.equalsIgnoreCase("hdr")) {
									headerUtil.setnMTI(matcher.group(1));
									continue;
								}
								utilBean.setnMTI(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nPAN")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnPAN(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nRecNum")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								if (hdr.equalsIgnoreCase("hdr")) {
									headerUtil.setnRecNum(matcher.group(1));
									continue;
								}
								if (hdr.equalsIgnoreCase("Trl")) {
									headerUtil.setTrl_nRecNum(matcher.group(1));
									trl_nRecNum = matcher.group(1);
									continue;
								}
								utilBean.setnRecNum(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nRGCSRcvdDt")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnRGCSRcvdDt(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nSetDCInd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnSetDCInd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nTxnDesInstCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnTxnDesInstCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCrdAcpBussCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCrdAcpBussCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCrdAcpNm")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCrdAcpNm(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nTxnOrgInstCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnTxnOrgInstCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nUID")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnUID(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFeeDCInd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								switch (feesize) {
								case 1:
									utilBean.setnFeeDCInd1(matcher.group(1));
									continue;
								case 2:
									utilBean.setnFeeDCInd2(matcher.group(1));
									continue;
								case 3:
									utilBean.setnFeeDCInd3(matcher.group(1));
									continue;
								case 4:
									utilBean.setnFeeDCInd4(matcher.group(1));
									continue;
								case 5:
									utilBean.setnFeeDCInd5(matcher.group(1));
									continue;
								}
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFeeAmt")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								switch (feesize) {
								case 1:
									utilBean.setnFeeAmt1(matcher.group(1));
									continue;
								case 2:
									utilBean.setnFeeAmt2(matcher.group(1));
									continue;
								case 3:
									utilBean.setnFeeAmt3(matcher.group(1));
									continue;
								case 4:
									utilBean.setnFeeAmt4(matcher.group(1));
									continue;
								case 5:
									utilBean.setFeeAmt5(matcher.group(1));
									continue;
								}
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFeeCcy")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								switch (feesize) {
								case 1:
									utilBean.setnFeeCcy1(matcher.group(1));
									continue;
								case 2:
									utilBean.setnFeeCcy2(matcher.group(1));
									continue;
								case 3:
									utilBean.setnFeeCcy3(matcher.group(1));
									continue;
								case 4:
									utilBean.setnFeeCcy4(matcher.group(1));
									continue;
								case 5:
									utilBean.setnFeeCcy5(matcher.group(1));
									continue;
								}
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFeeTpCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								switch (feesize) {
								case 1:
									utilBean.setnFeeTpCd1(matcher.group(1));
									continue;
								case 2:
									utilBean.setnFeeTpCd2(matcher.group(1));
									continue;
								case 3:
									utilBean.setnFeeTpCd3(matcher.group(1));
									continue;
								case 4:
									utilBean.setnFeeTpCd4(matcher.group(1));
									continue;
								case 5:
									utilBean.setnFeeTpCd5(matcher.group(1));
									continue;
								}
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nIntrchngCtg")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								switch (feesize) {
								case 1:
									utilBean.setnIntrchngCtg1(matcher.group(1));
									continue;
								case 2:
									utilBean.setnIntrchngCtg2(matcher.group(1));
									continue;
								case 3:
									utilBean.setnIntrchngCtg3(matcher.group(1));
									continue;
								case 4:
									utilBean.setnIntrchngCtg4(matcher.group(1));
									continue;
								case 5:
									utilBean.setnIntrchngCtg5(matcher.group(1));
									continue;
								}
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("/Fee")) {
								feesize++;
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCaseNum")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCaseNum(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nContNum")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnContNum(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFulParInd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnFulParInd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nProcCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnProdCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nAmtBil")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnAmtBil(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCcyCdBil")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCcyCdBil(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nConvRtBil")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnConvRtBil(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nMsgRsnCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnMsgRsnCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nRnTtlAmt")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnRnTtlAmt(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nTxnCnt")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnTxnCnt(matcher.group(1));
								transactions_count = matcher.group(1);
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("Trl")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								hdr = "Trl";
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("/Trl")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								hdr = "";
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("/Txn")) {
								feesize = 1;

								Qsparc88InternationalRawData entity = new Qsparc88InternationalRawData();

								// Header fields
								entity.setMti(headerUtil.getnMTI());
								entity.setFunctionCode(headerUtil.getnFunCd());
								entity.setRecordNumber(headerUtil.getnRecNum());
								entity.setMemberInstitutionIdCode(headerUtil.getnMemInstCd());
								entity.setUniqueFileName(headerUtil.getnUnFlNm());
								entity.setProductCode(headerUtil.getnProdCd());
								entity.setSettlementBIN(headerUtil.getnSetBIN());
								entity.setFileCategory(headerUtil.getnFlCatg());
								entity.setVersionNumber(headerUtil.getnVerNum());
								entity.setTransactionsCount(headerUtil.getnTxnCnt());
								entity.setRunTotalAmount(headerUtil.getnRnTtlAmt());
								entity.setTrlFunctionCode(headerUtil.getTrl_nFunCd());
								entity.setTrlRecordNumber(headerUtil.getTrl_nRecNum());

								// Date parsing for settlement date
								entity.setDateSettlement(headerUtil.getnDtSet()); // format: yyyyMMdd
								entity.setFileDate(fileDate); // format: yyyy-MM-dd

								// utilBeanBean fields
								entity.setAcquirerInstitutionIdCode(utilBean.getnAcqInstCd());
								entity.setAmountSettlement(utilBean.getnAmtSet());
								entity.setAmountTransaction(utilBean.getnAmtTxn());
								entity.setApprovalCode(utilBean.getnApprvlCd());
								entity.setAcquirerReferenceData(utilBean.getnARD());
								entity.setCaseNumber(utilBean.getnCaseNum());
								entity.setCurrencyCodeSettlement(utilBean.getnCcyCdSet());
								entity.setCurrencyCodeTransaction(utilBean.getnCcyCdTxn());
								entity.setConversionRateSettlement(utilBean.getnConvRtSet());
								entity.setCardAcceptorAddiAddr(utilBean.getnCrdAcpAddAdrs());
								entity.setCardAcceptorTerminalID(utilBean.getnCrdAcptTrmId());
								entity.setCardAcceptorZipCode(utilBean.getnCrdAcpZipCd());
								entity.setDateAndTimeLocalTransaction(utilBean.getnDtTmLcTxn());
								entity.setTxnFunctionCode(utilBean.getnFunCd());
								entity.setLatePresentmentIndicator(utilBean.getnLtPrsntInd());
								entity.setTxnMTI(utilBean.getnMTI());

								String pan = utilBean.getnPAN().trim();
								String Update_Pan = "";
								if (pan.length() <= 16 && pan != null && pan.trim() != "" && pan.length() > 0) {
									Update_Pan = String.valueOf(pan.substring(0, 6)) + "XXXXXX"
											+ pan.substring(pan.length() - 4);
								} else if (pan.length() >= 16 && pan != null && pan.trim() != "" && pan.length() > 0) {
									Update_Pan = String.valueOf(pan.substring(0, 6)) + "XXXXXXXXX"
											+ pan.substring(pan.length() - 4);
								} else {
									Update_Pan = null;
								}
								entity.setPrimaryAccountNumber(Update_Pan);
								entity.setTxnRecordNumber(utilBean.getnRecNum());
								entity.setRgcsReceivedDate(utilBean.getnRGCSRcvdDt());
								entity.setSettlementDRCRIndicator(utilBean.getnSetDCInd());
								entity.setTxnDestiInstiIdCode(utilBean.getnTxnDesInstCd());
								entity.setTxnOriginInstiIdCode(utilBean.getnTxnOrgInstCd());
								entity.setCardHolderUID(utilBean.getnUID());
								entity.setAmountBilling(utilBean.getnAmtBil());
								entity.setCurrencyCodeBilling(utilBean.getnCcyCdBil());
								entity.setConversionRateBilling(utilBean.getnConvRtBil());
								entity.setMessageReasonCode(utilBean.getnMsgRsnCd());

								// Fees
								entity.setFeeDRCRIndicator1(utilBean.getnFeeDCInd1());
								entity.setFeeAmount1(utilBean.getnFeeAmt1());
								entity.setFeeCurrency1(utilBean.getnFeeCcy1());
								entity.setFeeTypeCode1(utilBean.getnFeeTpCd1());
								entity.setInterchangeCategory1(utilBean.getnIntrchngCtg1());

								entity.setFeeDRCRIndicator2(utilBean.getnFeeDCInd2());
								entity.setFeeAmount2(utilBean.getnFeeAmt2());
								entity.setFeeCurrency2(utilBean.getnFeeCcy2());
								entity.setFeeTypeCode2(utilBean.getnFeeTpCd2());
								entity.setInterchangeCategory2(utilBean.getnIntrchngCtg2());

								entity.setFeeDRCRIndicator3(utilBean.getnFeeDCInd3());
								entity.setFeeAmount3(utilBean.getnFeeAmt3());
								entity.setFeeCurrency3(utilBean.getnFeeCcy3());
								entity.setFeeTypeCode3(utilBean.getnFeeTpCd3());
								entity.setInterchangeCategory3(utilBean.getnIntrchngCtg3());

								entity.setFeeDRCRIndicator4(utilBean.getnFeeDCInd4());
								entity.setFeeAmount4(utilBean.getnFeeAmt4());
								entity.setFeeCurrency4(utilBean.getnFeeCcy4());
								entity.setFeeTypeCode4(utilBean.getnFeeTpCd4());
								entity.setInterchangeCategory4(utilBean.getnIntrchngCtg4());

								entity.setFeeDRCRIndicator5(utilBean.getnFeeDCInd5());
								entity.setFeeAmount5(utilBean.getFeeAmt5());
								entity.setFeeCurrency5(utilBean.getnFeeCcy5());
								entity.setFeeTypeCode5(utilBean.getnFeeTpCd5());
								entity.setInterchangeCategory5(utilBean.getnIntrchngCtg5());

								// Other
								entity.setPan(pan);
								entity.setRrn(utilBean.getnARD());
								entity.setFilename(file.getOriginalFilename());
								entity.setFlag("I");
								entity.setCreatedDate(new Date()); // current timestamp
								rawDataSet.add(entity);
								DataCount++;
								utilBean = new QsparcUtilBean(); // Reset for next record

								thisLine = null;

								if (++batchCount % batchSize == 0) {

									batchNumber++;

									logger.info("Batch Executed is " + batchNumber);
									qsparc88InternationalRawDataRepository.saveAll(rawDataSet);
									rawDataSet = new HashSet<Qsparc88InternationalRawData>();

								} else {

									qsparc88InternationalRawDataRepository.saveAll(rawDataSet);
								}
							}
						}
					}
					int updateStatus = commonDataValidationRepository.updateStatusByFileTypeOrFiledate("QSPARC 88 INT", fileDate, file.getOriginalFilename(), String.valueOf(DataCount), "Y");
					if(updateStatus>0) {
						logger.error("Upload Status updated");
					}else {
						
						logger.error("unabele to update ");
					}



				}else if (file.getOriginalFilename().substring(0, 2).equalsIgnoreCase("01")) {

					Set<Qsparc01InternationalRawData> rawDataSet = new HashSet<>();
					Pattern TAG_REGEX = Pattern.compile(">(.+?)</");
					Pattern node_REGEX = Pattern.compile("<(.+?)>");
					QsparcHeaderUtil headerUtil = new QsparcHeaderUtil();
					QsparcUtilBean utilBean = new QsparcUtilBean();
					int batchNumber = 0, batchSize = 50000;
					int batchCount = 0;
					reader = new BufferedReader(new InputStreamReader(file.getInputStream()));

					int feesize = 1;
					@SuppressWarnings("unused")
					String thisLine, hdr = "", trl = "", trl_nFunCd = "";
					@SuppressWarnings("unused")
					String trl_nRecNum = null, transactions_count = null;
					logger.info("Process started" + (new Date()).getTime());
					while ((thisLine = reader.readLine()) != null) {

						Matcher nodeMatcher = node_REGEX.matcher(thisLine);
						nodeMatcher.find();
						if (!nodeMatcher.group(1).equalsIgnoreCase("Txn")) {
							if (nodeMatcher.group(1).equalsIgnoreCase("Hdr")) {
								hdr = "hdr";
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("/Hdr")) {
								hdr = "";
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nDtTmFlGen")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnDtTmFlGen(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nMemInstCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnMemInstCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nUnFlNm")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnUnFlNm(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nProdCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnProdCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nSetBIN")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnSetBIN(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFlCatg")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnFlCatg(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nVerNum")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnVerNum(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nAcqInstCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnAcqInstCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nAmtSet")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								double amtSet = Integer.parseInt(matcher.group(1));
								amtSet /= 100.0D;
								utilBean.setnAmtSet(String.valueOf(amtSet));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nAmtTxn")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								double amtTxn = Double.parseDouble(matcher.group(1));
								amtTxn /= 100.0D;
								utilBean.setnAmtTxn(String.valueOf(amtTxn));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nApprvlCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnApprvlCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nRRN")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnARD(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCcyCdSet")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCcyCdSet(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCcyCdTxn")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCcyCdTxn(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nConvRtSet")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnConvRtSet(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCrdAcpAddAdrs")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCrdAcpAddAdrs(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCrdAcptTrmId")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCrdAcptTrmId(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCrdAcpZipCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCrdAcpZipCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nDtSet")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								if (hdr.equalsIgnoreCase("hdr")) {
									headerUtil.setnDtSet(matcher.group(1));
									continue;
								}
								utilBean.setnDtSet(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nDtTmLcTxn")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnDtTmLcTxn(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFunCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								if (hdr.equalsIgnoreCase("hdr")) {
									headerUtil.setnFunCd(matcher.group(1));
									continue;
								}
								if (hdr.equalsIgnoreCase("Trl")) {
									trl_nFunCd = matcher.group(1);
									logger.info(trl_nFunCd);
									continue;
								}
								utilBean.setnFunCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nLtPrsntInd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnLtPrsntInd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nMTI")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								if (hdr.equalsIgnoreCase("hdr")) {
									headerUtil.setnMTI(matcher.group(1));
									continue;
								}
								utilBean.setnMTI(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nPAN")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnPAN(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nRecNum")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								if (hdr.equalsIgnoreCase("hdr")) {
									headerUtil.setnRecNum(matcher.group(1));
									continue;
								}
								if (hdr.equalsIgnoreCase("Trl")) {
									headerUtil.setTrl_nRecNum(matcher.group(1));
									trl_nRecNum = matcher.group(1);
									continue;
								}
								utilBean.setnRecNum(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nRGCSRcvdDt")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnRGCSRcvdDt(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nSetDCInd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnSetDCInd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nTxnDesInstCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnTxnDesInstCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCrdAcpBussCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCrdAcpBussCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCrdAcpNm")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCrdAcpNm(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nTxnOrgInstCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnTxnOrgInstCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nUID")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnUID(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFeeDCInd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								switch (feesize) {
								case 1:
									utilBean.setnFeeDCInd1(matcher.group(1));
									continue;
								case 2:
									utilBean.setnFeeDCInd2(matcher.group(1));
									continue;
								case 3:
									utilBean.setnFeeDCInd3(matcher.group(1));
									continue;
								case 4:
									utilBean.setnFeeDCInd4(matcher.group(1));
									continue;
								case 5:
									utilBean.setnFeeDCInd5(matcher.group(1));
									continue;
								}
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFeeAmt")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								switch (feesize) {
								case 1:
									utilBean.setnFeeAmt1(matcher.group(1));
									continue;
								case 2:
									utilBean.setnFeeAmt2(matcher.group(1));
									continue;
								case 3:
									utilBean.setnFeeAmt3(matcher.group(1));
									continue;
								case 4:
									utilBean.setnFeeAmt4(matcher.group(1));
									continue;
								case 5:
									utilBean.setFeeAmt5(matcher.group(1));
									continue;
								}
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFeeCcy")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								switch (feesize) {
								case 1:
									utilBean.setnFeeCcy1(matcher.group(1));
									continue;
								case 2:
									utilBean.setnFeeCcy2(matcher.group(1));
									continue;
								case 3:
									utilBean.setnFeeCcy3(matcher.group(1));
									continue;
								case 4:
									utilBean.setnFeeCcy4(matcher.group(1));
									continue;
								case 5:
									utilBean.setnFeeCcy5(matcher.group(1));
									continue;
								}
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFeeTpCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								switch (feesize) {
								case 1:
									utilBean.setnFeeTpCd1(matcher.group(1));
									continue;
								case 2:
									utilBean.setnFeeTpCd2(matcher.group(1));
									continue;
								case 3:
									utilBean.setnFeeTpCd3(matcher.group(1));
									continue;
								case 4:
									utilBean.setnFeeTpCd4(matcher.group(1));
									continue;
								case 5:
									utilBean.setnFeeTpCd5(matcher.group(1));
									continue;
								}
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nIntrchngCtg")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								switch (feesize) {
								case 1:
									utilBean.setnIntrchngCtg1(matcher.group(1));
									continue;
								case 2:
									utilBean.setnIntrchngCtg2(matcher.group(1));
									continue;
								case 3:
									utilBean.setnIntrchngCtg3(matcher.group(1));
									continue;
								case 4:
									utilBean.setnIntrchngCtg4(matcher.group(1));
									continue;
								case 5:
									utilBean.setnIntrchngCtg5(matcher.group(1));
									continue;
								}
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("/Fee")) {
								feesize++;
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCaseNum")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCaseNum(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nContNum")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnContNum(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFulParInd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnFulParInd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nProcCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnProdCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nAmtBil")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnAmtBil(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCcyCdBil")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCcyCdBil(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nConvRtBil")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnConvRtBil(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nMsgRsnCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnMsgRsnCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nRnTtlAmt")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnRnTtlAmt(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nTxnCnt")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnTxnCnt(matcher.group(1));
								transactions_count = matcher.group(1);
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("Trl")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								hdr = "Trl";
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("/Trl")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								hdr = "";
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("/Txn")) {
								feesize = 1;

								Qsparc01InternationalRawData entity = new Qsparc01InternationalRawData();

								// Header fields
								entity.setMti(headerUtil.getnMTI());
								entity.setFunctionCode(headerUtil.getnFunCd());
								entity.setRecordNumber(headerUtil.getnRecNum());
								entity.setMemberInstitutionIdCode(headerUtil.getnMemInstCd());
								entity.setUniqueFileName(headerUtil.getnUnFlNm());
								entity.setProductCode(headerUtil.getnProdCd());
								entity.setSettlementBIN(headerUtil.getnSetBIN());
								entity.setFileCategory(headerUtil.getnFlCatg());
								entity.setVersionNumber(headerUtil.getnVerNum());
								entity.setTransactionsCount(headerUtil.getnTxnCnt());
								entity.setRunTotalAmount(headerUtil.getnRnTtlAmt());
								entity.setTrlFunctionCode(headerUtil.getTrl_nFunCd());
								entity.setTrlRecordNumber(headerUtil.getTrl_nRecNum());

								// Date parsing for settlement date
								entity.setDateSettlement(headerUtil.getnDtSet()); // format: yyyyMMdd
								entity.setFileDate(fileDate); // format: yyyy-MM-dd

								// utilBeanBean fields
								entity.setAcquirerInstitutionIdCode(utilBean.getnAcqInstCd());
								entity.setAmountSettlement(utilBean.getnAmtSet());
								entity.setAmountTransaction(utilBean.getnAmtTxn());
								entity.setApprovalCode(utilBean.getnApprvlCd());
								entity.setAcquirerReferenceData(utilBean.getnARD());
								entity.setCaseNumber(utilBean.getnCaseNum());
								entity.setCurrencyCodeSettlement(utilBean.getnCcyCdSet());
								entity.setCurrencyCodeTransaction(utilBean.getnCcyCdTxn());
								entity.setConversionRateSettlement(utilBean.getnConvRtSet());
								entity.setCardAcceptorAddiAddr(utilBean.getnCrdAcpAddAdrs());
								entity.setCardAcceptorTerminalID(utilBean.getnCrdAcptTrmId());
								entity.setCardAcceptorZipCode(utilBean.getnCrdAcpZipCd());
								entity.setDateAndTimeLocalTransaction(utilBean.getnDtTmLcTxn());
								entity.setTxnFunctionCode(utilBean.getnFunCd());
								entity.setLatePresentmentIndicator(utilBean.getnLtPrsntInd());
								entity.setTxnMTI(utilBean.getnMTI());

								String pan = utilBean.getnPAN().trim();
								String Update_Pan = "";
								if (pan.length() <= 16 && pan != null && pan.trim() != "" && pan.length() > 0) {
									Update_Pan = String.valueOf(pan.substring(0, 6)) + "XXXXXX"
											+ pan.substring(pan.length() - 4);
								} else if (pan.length() >= 16 && pan != null && pan.trim() != "" && pan.length() > 0) {
									Update_Pan = String.valueOf(pan.substring(0, 6)) + "XXXXXXXXX"
											+ pan.substring(pan.length() - 4);
								} else {
									Update_Pan = null;
								}
								entity.setPrimaryAccountNumber(Update_Pan);
								entity.setTxnRecordNumber(utilBean.getnRecNum());
								entity.setRgcsReceivedDate(utilBean.getnRGCSRcvdDt());
								entity.setSettlementDRCRIndicator(utilBean.getnSetDCInd());
								entity.setTxnDestiInstiIdCode(utilBean.getnTxnDesInstCd());
								entity.setTxnOriginInstiIdCode(utilBean.getnTxnOrgInstCd());
								entity.setCardHolderUID(utilBean.getnUID());
								entity.setAmountBilling(utilBean.getnAmtBil());
								entity.setCurrencyCodeBilling(utilBean.getnCcyCdBil());
								entity.setConversionRateBilling(utilBean.getnConvRtBil());
								entity.setMessageReasonCode(utilBean.getnMsgRsnCd());

								// Fees
								entity.setFeeDRCRIndicator1(utilBean.getnFeeDCInd1());
								entity.setFeeAmount1(utilBean.getnFeeAmt1());
								entity.setFeeCurrency1(utilBean.getnFeeCcy1());
								entity.setFeeTypeCode1(utilBean.getnFeeTpCd1());
								entity.setInterchangeCategory1(utilBean.getnIntrchngCtg1());

								entity.setFeeDRCRIndicator2(utilBean.getnFeeDCInd2());
								entity.setFeeAmount2(utilBean.getnFeeAmt2());
								entity.setFeeCurrency2(utilBean.getnFeeCcy2());
								entity.setFeeTypeCode2(utilBean.getnFeeTpCd2());
								entity.setInterchangeCategory2(utilBean.getnIntrchngCtg2());

								entity.setFeeDRCRIndicator3(utilBean.getnFeeDCInd3());
								entity.setFeeAmount3(utilBean.getnFeeAmt3());
								entity.setFeeCurrency3(utilBean.getnFeeCcy3());
								entity.setFeeTypeCode3(utilBean.getnFeeTpCd3());
								entity.setInterchangeCategory3(utilBean.getnIntrchngCtg3());

								entity.setFeeDRCRIndicator4(utilBean.getnFeeDCInd4());
								entity.setFeeAmount4(utilBean.getnFeeAmt4());
								entity.setFeeCurrency4(utilBean.getnFeeCcy4());
								entity.setFeeTypeCode4(utilBean.getnFeeTpCd4());
								entity.setInterchangeCategory4(utilBean.getnIntrchngCtg4());

								entity.setFeeDRCRIndicator5(utilBean.getnFeeDCInd5());
								entity.setFeeAmount5(utilBean.getFeeAmt5());
								entity.setFeeCurrency5(utilBean.getnFeeCcy5());
								entity.setFeeTypeCode5(utilBean.getnFeeTpCd5());
								entity.setInterchangeCategory5(utilBean.getnIntrchngCtg5());

								// Other
								entity.setPan(pan);
								entity.setRrn(utilBean.getnARD());
								entity.setFilename(file.getOriginalFilename());
								entity.setFlag("I");
								entity.setCreatedDate(new Date()); // current timestamp
								rawDataSet.add(entity);
								DataCount++;
								utilBean = new QsparcUtilBean(); // Reset for next record

								thisLine = null;

								if (++batchCount % batchSize == 0) {

									batchNumber++;

									logger.info("Batch Executed is " + batchNumber);
									qsparc01InternationalRawDataRepository.saveAll(rawDataSet);
									rawDataSet = new HashSet<Qsparc01InternationalRawData>();

								} else {

									qsparc01InternationalRawDataRepository.saveAll(rawDataSet);
								}
							}
						}
					}
					int updateStatus = commonDataValidationRepository.updateStatusByFileTypeOrFiledate("QSPARC 01 INT", fileDate, file.getOriginalFilename(), String.valueOf(DataCount), "Y");
					if(updateStatus>0) {
						logger.error("Upload Status updated");
					}else {
						
						logger.error("unabele to update ");
					}



				}else if (file.getOriginalFilename().substring(0, 2).equalsIgnoreCase("02")) {

					Set<Qsparc02InternationalRawData> rawDataSet = new HashSet<>();
					Pattern TAG_REGEX = Pattern.compile(">(.+?)</");
					Pattern node_REGEX = Pattern.compile("<(.+?)>");
					QsparcHeaderUtil headerUtil = new QsparcHeaderUtil();
					QsparcUtilBean utilBean = new QsparcUtilBean();
					int batchNumber = 0, batchSize = 50000;
					int batchCount = 0;
					reader = new BufferedReader(new InputStreamReader(file.getInputStream()));

					int feesize = 1;
					@SuppressWarnings("unused")
					String thisLine, hdr = "", trl = "", trl_nFunCd = "";
					@SuppressWarnings("unused")
					String trl_nRecNum = null, transactions_count = null;
					logger.info("Process started" + (new Date()).getTime());
					while ((thisLine = reader.readLine()) != null) {

						Matcher nodeMatcher = node_REGEX.matcher(thisLine);
						nodeMatcher.find();
						if (!nodeMatcher.group(1).equalsIgnoreCase("Txn")) {
							if (nodeMatcher.group(1).equalsIgnoreCase("Hdr")) {
								hdr = "hdr";
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("/Hdr")) {
								hdr = "";
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nDtTmFlGen")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnDtTmFlGen(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nMemInstCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnMemInstCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nUnFlNm")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnUnFlNm(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nProdCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnProdCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nSetBIN")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnSetBIN(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFlCatg")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnFlCatg(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nVerNum")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnVerNum(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nAcqInstCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnAcqInstCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nAmtSet")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								double amtSet = Integer.parseInt(matcher.group(1));
								amtSet /= 100.0D;
								utilBean.setnAmtSet(String.valueOf(amtSet));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nAmtTxn")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								double amtTxn = Double.parseDouble(matcher.group(1));
								amtTxn /= 100.0D;
								utilBean.setnAmtTxn(String.valueOf(amtTxn));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nApprvlCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnApprvlCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nRRN")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnARD(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCcyCdSet")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCcyCdSet(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCcyCdTxn")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCcyCdTxn(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nConvRtSet")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnConvRtSet(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCrdAcpAddAdrs")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCrdAcpAddAdrs(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCrdAcptTrmId")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCrdAcptTrmId(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCrdAcpZipCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCrdAcpZipCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nDtSet")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								if (hdr.equalsIgnoreCase("hdr")) {
									headerUtil.setnDtSet(matcher.group(1));
									continue;
								}
								utilBean.setnDtSet(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nDtTmLcTxn")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnDtTmLcTxn(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFunCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								if (hdr.equalsIgnoreCase("hdr")) {
									headerUtil.setnFunCd(matcher.group(1));
									continue;
								}
								if (hdr.equalsIgnoreCase("Trl")) {
									trl_nFunCd = matcher.group(1);
									logger.info(trl_nFunCd);
									continue;
								}
								utilBean.setnFunCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nLtPrsntInd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnLtPrsntInd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nMTI")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								if (hdr.equalsIgnoreCase("hdr")) {
									headerUtil.setnMTI(matcher.group(1));
									continue;
								}
								utilBean.setnMTI(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nPAN")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnPAN(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nRecNum")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								if (hdr.equalsIgnoreCase("hdr")) {
									headerUtil.setnRecNum(matcher.group(1));
									continue;
								}
								if (hdr.equalsIgnoreCase("Trl")) {
									headerUtil.setTrl_nRecNum(matcher.group(1));
									trl_nRecNum = matcher.group(1);
									continue;
								}
								utilBean.setnRecNum(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nRGCSRcvdDt")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnRGCSRcvdDt(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nSetDCInd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnSetDCInd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nTxnDesInstCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnTxnDesInstCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCrdAcpBussCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCrdAcpBussCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCrdAcpNm")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCrdAcpNm(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nTxnOrgInstCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnTxnOrgInstCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nUID")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnUID(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFeeDCInd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								switch (feesize) {
								case 1:
									utilBean.setnFeeDCInd1(matcher.group(1));
									continue;
								case 2:
									utilBean.setnFeeDCInd2(matcher.group(1));
									continue;
								case 3:
									utilBean.setnFeeDCInd3(matcher.group(1));
									continue;
								case 4:
									utilBean.setnFeeDCInd4(matcher.group(1));
									continue;
								case 5:
									utilBean.setnFeeDCInd5(matcher.group(1));
									continue;
								}
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFeeAmt")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								switch (feesize) {
								case 1:
									utilBean.setnFeeAmt1(matcher.group(1));
									continue;
								case 2:
									utilBean.setnFeeAmt2(matcher.group(1));
									continue;
								case 3:
									utilBean.setnFeeAmt3(matcher.group(1));
									continue;
								case 4:
									utilBean.setnFeeAmt4(matcher.group(1));
									continue;
								case 5:
									utilBean.setFeeAmt5(matcher.group(1));
									continue;
								}
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFeeCcy")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								switch (feesize) {
								case 1:
									utilBean.setnFeeCcy1(matcher.group(1));
									continue;
								case 2:
									utilBean.setnFeeCcy2(matcher.group(1));
									continue;
								case 3:
									utilBean.setnFeeCcy3(matcher.group(1));
									continue;
								case 4:
									utilBean.setnFeeCcy4(matcher.group(1));
									continue;
								case 5:
									utilBean.setnFeeCcy5(matcher.group(1));
									continue;
								}
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFeeTpCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								switch (feesize) {
								case 1:
									utilBean.setnFeeTpCd1(matcher.group(1));
									continue;
								case 2:
									utilBean.setnFeeTpCd2(matcher.group(1));
									continue;
								case 3:
									utilBean.setnFeeTpCd3(matcher.group(1));
									continue;
								case 4:
									utilBean.setnFeeTpCd4(matcher.group(1));
									continue;
								case 5:
									utilBean.setnFeeTpCd5(matcher.group(1));
									continue;
								}
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nIntrchngCtg")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								switch (feesize) {
								case 1:
									utilBean.setnIntrchngCtg1(matcher.group(1));
									continue;
								case 2:
									utilBean.setnIntrchngCtg2(matcher.group(1));
									continue;
								case 3:
									utilBean.setnIntrchngCtg3(matcher.group(1));
									continue;
								case 4:
									utilBean.setnIntrchngCtg4(matcher.group(1));
									continue;
								case 5:
									utilBean.setnIntrchngCtg5(matcher.group(1));
									continue;
								}
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("/Fee")) {
								feesize++;
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCaseNum")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCaseNum(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nContNum")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnContNum(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFulParInd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnFulParInd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nProcCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnProdCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nAmtBil")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnAmtBil(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCcyCdBil")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCcyCdBil(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nConvRtBil")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnConvRtBil(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nMsgRsnCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnMsgRsnCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nRnTtlAmt")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnRnTtlAmt(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nTxnCnt")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnTxnCnt(matcher.group(1));
								transactions_count = matcher.group(1);
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("Trl")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								hdr = "Trl";
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("/Trl")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								hdr = "";
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("/Txn")) {
								feesize = 1;

								Qsparc02InternationalRawData entity = new Qsparc02InternationalRawData();

								// Header fields
								entity.setMti(headerUtil.getnMTI());
								entity.setFunctionCode(headerUtil.getnFunCd());
								entity.setRecordNumber(headerUtil.getnRecNum());
								entity.setMemberInstitutionIdCode(headerUtil.getnMemInstCd());
								entity.setUniqueFileName(headerUtil.getnUnFlNm());
								entity.setProductCode(headerUtil.getnProdCd());
								entity.setSettlementBIN(headerUtil.getnSetBIN());
								entity.setFileCategory(headerUtil.getnFlCatg());
								entity.setVersionNumber(headerUtil.getnVerNum());
								entity.setTransactionsCount(headerUtil.getnTxnCnt());
								entity.setRunTotalAmount(headerUtil.getnRnTtlAmt());
								entity.setTrlFunctionCode(headerUtil.getTrl_nFunCd());
								entity.setTrlRecordNumber(headerUtil.getTrl_nRecNum());

								// Date parsing for settlement date
								entity.setDateSettlement(headerUtil.getnDtSet()); // format: yyyyMMdd
								entity.setFileDate(fileDate); // format: yyyy-MM-dd

								// utilBeanBean fields
								entity.setAcquirerInstitutionIdCode(utilBean.getnAcqInstCd());
								entity.setAmountSettlement(utilBean.getnAmtSet());
								entity.setAmountTransaction(utilBean.getnAmtTxn());
								entity.setApprovalCode(utilBean.getnApprvlCd());
								entity.setAcquirerReferenceData(utilBean.getnARD());
								entity.setCaseNumber(utilBean.getnCaseNum());
								entity.setCurrencyCodeSettlement(utilBean.getnCcyCdSet());
								entity.setCurrencyCodeTransaction(utilBean.getnCcyCdTxn());
								entity.setConversionRateSettlement(utilBean.getnConvRtSet());
								entity.setCardAcceptorAddiAddr(utilBean.getnCrdAcpAddAdrs());
								entity.setCardAcceptorTerminalID(utilBean.getnCrdAcptTrmId());
								entity.setCardAcceptorZipCode(utilBean.getnCrdAcpZipCd());
								entity.setDateAndTimeLocalTransaction(utilBean.getnDtTmLcTxn());
								entity.setTxnFunctionCode(utilBean.getnFunCd());
								entity.setLatePresentmentIndicator(utilBean.getnLtPrsntInd());
								entity.setTxnMTI(utilBean.getnMTI());

								String pan = utilBean.getnPAN().trim();
								String Update_Pan = "";
								if (pan.length() <= 16 && pan != null && pan.trim() != "" && pan.length() > 0) {
									Update_Pan = String.valueOf(pan.substring(0, 6)) + "XXXXXX"
											+ pan.substring(pan.length() - 4);
								} else if (pan.length() >= 16 && pan != null && pan.trim() != "" && pan.length() > 0) {
									Update_Pan = String.valueOf(pan.substring(0, 6)) + "XXXXXXXXX"
											+ pan.substring(pan.length() - 4);
								} else {
									Update_Pan = null;
								}
								entity.setPrimaryAccountNumber(Update_Pan);
								entity.setTxnRecordNumber(utilBean.getnRecNum());
								entity.setRgcsReceivedDate(utilBean.getnRGCSRcvdDt());
								entity.setSettlementDRCRIndicator(utilBean.getnSetDCInd());
								entity.setTxnDestiInstiIdCode(utilBean.getnTxnDesInstCd());
								entity.setTxnOriginInstiIdCode(utilBean.getnTxnOrgInstCd());
								entity.setCardHolderUID(utilBean.getnUID());
								entity.setAmountBilling(utilBean.getnAmtBil());
								entity.setCurrencyCodeBilling(utilBean.getnCcyCdBil());
								entity.setConversionRateBilling(utilBean.getnConvRtBil());
								entity.setMessageReasonCode(utilBean.getnMsgRsnCd());

								// Fees
								entity.setFeeDRCRIndicator1(utilBean.getnFeeDCInd1());
								entity.setFeeAmount1(utilBean.getnFeeAmt1());
								entity.setFeeCurrency1(utilBean.getnFeeCcy1());
								entity.setFeeTypeCode1(utilBean.getnFeeTpCd1());
								entity.setInterchangeCategory1(utilBean.getnIntrchngCtg1());

								entity.setFeeDRCRIndicator2(utilBean.getnFeeDCInd2());
								entity.setFeeAmount2(utilBean.getnFeeAmt2());
								entity.setFeeCurrency2(utilBean.getnFeeCcy2());
								entity.setFeeTypeCode2(utilBean.getnFeeTpCd2());
								entity.setInterchangeCategory2(utilBean.getnIntrchngCtg2());

								entity.setFeeDRCRIndicator3(utilBean.getnFeeDCInd3());
								entity.setFeeAmount3(utilBean.getnFeeAmt3());
								entity.setFeeCurrency3(utilBean.getnFeeCcy3());
								entity.setFeeTypeCode3(utilBean.getnFeeTpCd3());
								entity.setInterchangeCategory3(utilBean.getnIntrchngCtg3());

								entity.setFeeDRCRIndicator4(utilBean.getnFeeDCInd4());
								entity.setFeeAmount4(utilBean.getnFeeAmt4());
								entity.setFeeCurrency4(utilBean.getnFeeCcy4());
								entity.setFeeTypeCode4(utilBean.getnFeeTpCd4());
								entity.setInterchangeCategory4(utilBean.getnIntrchngCtg4());

								entity.setFeeDRCRIndicator5(utilBean.getnFeeDCInd5());
								entity.setFeeAmount5(utilBean.getFeeAmt5());
								entity.setFeeCurrency5(utilBean.getnFeeCcy5());
								entity.setFeeTypeCode5(utilBean.getnFeeTpCd5());
								entity.setInterchangeCategory5(utilBean.getnIntrchngCtg5());

								// Other
								entity.setPan(pan);
								entity.setRrn(utilBean.getnARD());
								entity.setFilename(file.getOriginalFilename());
								entity.setFlag("I");
								entity.setCreatedDate(new Date()); // current timestamp
								rawDataSet.add(entity);
								DataCount++;
								utilBean = new QsparcUtilBean(); // Reset for next record

								thisLine = null;

								if (++batchCount % batchSize == 0) {

									batchNumber++;

									logger.info("Batch Executed is " + batchNumber);
									qsparc02InternationalRawDataRepository.saveAll(rawDataSet);
									rawDataSet = new HashSet<Qsparc02InternationalRawData>();

								} else {

									qsparc02InternationalRawDataRepository.saveAll(rawDataSet);
								}
							}
						}
					}
					int updateStatus = commonDataValidationRepository.updateStatusByFileTypeOrFiledate("QSPARC 02 INT", fileDate, file.getOriginalFilename(), String.valueOf(DataCount), "Y");
					if(updateStatus>0) {
						logger.error("Upload Status updated");
					}else {
						
						logger.error("unabele to update ");
					}



				}else if (file.getOriginalFilename().substring(0, 2).equalsIgnoreCase("03")) {

					Set<Qsparc03InternationalRawData> rawDataSet = new HashSet<>();
					Pattern TAG_REGEX = Pattern.compile(">(.+?)</");
					Pattern node_REGEX = Pattern.compile("<(.+?)>");
					QsparcHeaderUtil headerUtil = new QsparcHeaderUtil();
					QsparcUtilBean utilBean = new QsparcUtilBean();
					int batchNumber = 0, batchSize = 50000;
					int batchCount = 0;
					reader = new BufferedReader(new InputStreamReader(file.getInputStream()));

					int feesize = 1;
					@SuppressWarnings("unused")
					String thisLine, hdr = "", trl = "", trl_nFunCd = "";
					@SuppressWarnings("unused")
					String trl_nRecNum = null, transactions_count = null;
					logger.info("Process started" + (new Date()).getTime());
					while ((thisLine = reader.readLine()) != null) {

						Matcher nodeMatcher = node_REGEX.matcher(thisLine);
						nodeMatcher.find();
						if (!nodeMatcher.group(1).equalsIgnoreCase("Txn")) {
							if (nodeMatcher.group(1).equalsIgnoreCase("Hdr")) {
								hdr = "hdr";
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("/Hdr")) {
								hdr = "";
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nDtTmFlGen")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnDtTmFlGen(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nMemInstCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnMemInstCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nUnFlNm")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnUnFlNm(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nProdCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnProdCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nSetBIN")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnSetBIN(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFlCatg")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnFlCatg(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nVerNum")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnVerNum(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nAcqInstCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnAcqInstCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nAmtSet")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								double amtSet = Integer.parseInt(matcher.group(1));
								amtSet /= 100.0D;
								utilBean.setnAmtSet(String.valueOf(amtSet));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nAmtTxn")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								double amtTxn = Double.parseDouble(matcher.group(1));
								amtTxn /= 100.0D;
								utilBean.setnAmtTxn(String.valueOf(amtTxn));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nApprvlCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnApprvlCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nRRN")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnARD(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCcyCdSet")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCcyCdSet(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCcyCdTxn")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCcyCdTxn(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nConvRtSet")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnConvRtSet(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCrdAcpAddAdrs")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCrdAcpAddAdrs(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCrdAcptTrmId")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCrdAcptTrmId(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCrdAcpZipCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCrdAcpZipCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nDtSet")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								if (hdr.equalsIgnoreCase("hdr")) {
									headerUtil.setnDtSet(matcher.group(1));
									continue;
								}
								utilBean.setnDtSet(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nDtTmLcTxn")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnDtTmLcTxn(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFunCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								if (hdr.equalsIgnoreCase("hdr")) {
									headerUtil.setnFunCd(matcher.group(1));
									continue;
								}
								if (hdr.equalsIgnoreCase("Trl")) {
									trl_nFunCd = matcher.group(1);
									logger.info(trl_nFunCd);
									continue;
								}
								utilBean.setnFunCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nLtPrsntInd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnLtPrsntInd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nMTI")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								if (hdr.equalsIgnoreCase("hdr")) {
									headerUtil.setnMTI(matcher.group(1));
									continue;
								}
								utilBean.setnMTI(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nPAN")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnPAN(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nRecNum")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								if (hdr.equalsIgnoreCase("hdr")) {
									headerUtil.setnRecNum(matcher.group(1));
									continue;
								}
								if (hdr.equalsIgnoreCase("Trl")) {
									headerUtil.setTrl_nRecNum(matcher.group(1));
									trl_nRecNum = matcher.group(1);
									continue;
								}
								utilBean.setnRecNum(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nRGCSRcvdDt")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnRGCSRcvdDt(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nSetDCInd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnSetDCInd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nTxnDesInstCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnTxnDesInstCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCrdAcpBussCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCrdAcpBussCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCrdAcpNm")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCrdAcpNm(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nTxnOrgInstCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnTxnOrgInstCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nUID")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnUID(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFeeDCInd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								switch (feesize) {
								case 1:
									utilBean.setnFeeDCInd1(matcher.group(1));
									continue;
								case 2:
									utilBean.setnFeeDCInd2(matcher.group(1));
									continue;
								case 3:
									utilBean.setnFeeDCInd3(matcher.group(1));
									continue;
								case 4:
									utilBean.setnFeeDCInd4(matcher.group(1));
									continue;
								case 5:
									utilBean.setnFeeDCInd5(matcher.group(1));
									continue;
								}
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFeeAmt")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								switch (feesize) {
								case 1:
									utilBean.setnFeeAmt1(matcher.group(1));
									continue;
								case 2:
									utilBean.setnFeeAmt2(matcher.group(1));
									continue;
								case 3:
									utilBean.setnFeeAmt3(matcher.group(1));
									continue;
								case 4:
									utilBean.setnFeeAmt4(matcher.group(1));
									continue;
								case 5:
									utilBean.setFeeAmt5(matcher.group(1));
									continue;
								}
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFeeCcy")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								switch (feesize) {
								case 1:
									utilBean.setnFeeCcy1(matcher.group(1));
									continue;
								case 2:
									utilBean.setnFeeCcy2(matcher.group(1));
									continue;
								case 3:
									utilBean.setnFeeCcy3(matcher.group(1));
									continue;
								case 4:
									utilBean.setnFeeCcy4(matcher.group(1));
									continue;
								case 5:
									utilBean.setnFeeCcy5(matcher.group(1));
									continue;
								}
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFeeTpCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								switch (feesize) {
								case 1:
									utilBean.setnFeeTpCd1(matcher.group(1));
									continue;
								case 2:
									utilBean.setnFeeTpCd2(matcher.group(1));
									continue;
								case 3:
									utilBean.setnFeeTpCd3(matcher.group(1));
									continue;
								case 4:
									utilBean.setnFeeTpCd4(matcher.group(1));
									continue;
								case 5:
									utilBean.setnFeeTpCd5(matcher.group(1));
									continue;
								}
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nIntrchngCtg")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								switch (feesize) {
								case 1:
									utilBean.setnIntrchngCtg1(matcher.group(1));
									continue;
								case 2:
									utilBean.setnIntrchngCtg2(matcher.group(1));
									continue;
								case 3:
									utilBean.setnIntrchngCtg3(matcher.group(1));
									continue;
								case 4:
									utilBean.setnIntrchngCtg4(matcher.group(1));
									continue;
								case 5:
									utilBean.setnIntrchngCtg5(matcher.group(1));
									continue;
								}
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("/Fee")) {
								feesize++;
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCaseNum")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCaseNum(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nContNum")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnContNum(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFulParInd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnFulParInd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nProcCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnProdCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nAmtBil")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnAmtBil(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCcyCdBil")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCcyCdBil(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nConvRtBil")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnConvRtBil(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nMsgRsnCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnMsgRsnCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nRnTtlAmt")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnRnTtlAmt(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nTxnCnt")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnTxnCnt(matcher.group(1));
								transactions_count = matcher.group(1);
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("Trl")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								hdr = "Trl";
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("/Trl")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								hdr = "";
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("/Txn")) {
								feesize = 1;

								Qsparc03InternationalRawData entity = new Qsparc03InternationalRawData();

								// Header fields
								entity.setMti(headerUtil.getnMTI());
								entity.setFunctionCode(headerUtil.getnFunCd());
								entity.setRecordNumber(headerUtil.getnRecNum());
								entity.setMemberInstitutionIdCode(headerUtil.getnMemInstCd());
								entity.setUniqueFileName(headerUtil.getnUnFlNm());
								entity.setProductCode(headerUtil.getnProdCd());
								entity.setSettlementBIN(headerUtil.getnSetBIN());
								entity.setFileCategory(headerUtil.getnFlCatg());
								entity.setVersionNumber(headerUtil.getnVerNum());
								entity.setTransactionsCount(headerUtil.getnTxnCnt());
								entity.setRunTotalAmount(headerUtil.getnRnTtlAmt());
								entity.setTrlFunctionCode(headerUtil.getTrl_nFunCd());
								entity.setTrlRecordNumber(headerUtil.getTrl_nRecNum());

								// Date parsing for settlement date
								entity.setDateSettlement(headerUtil.getnDtSet()); // format: yyyyMMdd
								entity.setFileDate(fileDate); // format: yyyy-MM-dd

								// utilBeanBean fields
								entity.setAcquirerInstitutionIdCode(utilBean.getnAcqInstCd());
								entity.setAmountSettlement(utilBean.getnAmtSet());
								entity.setAmountTransaction(utilBean.getnAmtTxn());
								entity.setApprovalCode(utilBean.getnApprvlCd());
								entity.setAcquirerReferenceData(utilBean.getnARD());
								entity.setCaseNumber(utilBean.getnCaseNum());
								entity.setCurrencyCodeSettlement(utilBean.getnCcyCdSet());
								entity.setCurrencyCodeTransaction(utilBean.getnCcyCdTxn());
								entity.setConversionRateSettlement(utilBean.getnConvRtSet());
								entity.setCardAcceptorAddiAddr(utilBean.getnCrdAcpAddAdrs());
								entity.setCardAcceptorTerminalID(utilBean.getnCrdAcptTrmId());
								entity.setCardAcceptorZipCode(utilBean.getnCrdAcpZipCd());
								entity.setDateAndTimeLocalTransaction(utilBean.getnDtTmLcTxn());
								entity.setTxnFunctionCode(utilBean.getnFunCd());
								entity.setLatePresentmentIndicator(utilBean.getnLtPrsntInd());
								entity.setTxnMTI(utilBean.getnMTI());

								String pan = utilBean.getnPAN().trim();
								String Update_Pan = "";
								if (pan.length() <= 16 && pan != null && pan.trim() != "" && pan.length() > 0) {
									Update_Pan = String.valueOf(pan.substring(0, 6)) + "XXXXXX"
											+ pan.substring(pan.length() - 4);
								} else if (pan.length() >= 16 && pan != null && pan.trim() != "" && pan.length() > 0) {
									Update_Pan = String.valueOf(pan.substring(0, 6)) + "XXXXXXXXX"
											+ pan.substring(pan.length() - 4);
								} else {
									Update_Pan = null;
								}
								entity.setPrimaryAccountNumber(Update_Pan);
								entity.setTxnRecordNumber(utilBean.getnRecNum());
								entity.setRgcsReceivedDate(utilBean.getnRGCSRcvdDt());
								entity.setSettlementDRCRIndicator(utilBean.getnSetDCInd());
								entity.setTxnDestiInstiIdCode(utilBean.getnTxnDesInstCd());
								entity.setTxnOriginInstiIdCode(utilBean.getnTxnOrgInstCd());
								entity.setCardHolderUID(utilBean.getnUID());
								entity.setAmountBilling(utilBean.getnAmtBil());
								entity.setCurrencyCodeBilling(utilBean.getnCcyCdBil());
								entity.setConversionRateBilling(utilBean.getnConvRtBil());
								entity.setMessageReasonCode(utilBean.getnMsgRsnCd());

								// Fees
								entity.setFeeDRCRIndicator1(utilBean.getnFeeDCInd1());
								entity.setFeeAmount1(utilBean.getnFeeAmt1());
								entity.setFeeCurrency1(utilBean.getnFeeCcy1());
								entity.setFeeTypeCode1(utilBean.getnFeeTpCd1());
								entity.setInterchangeCategory1(utilBean.getnIntrchngCtg1());

								entity.setFeeDRCRIndicator2(utilBean.getnFeeDCInd2());
								entity.setFeeAmount2(utilBean.getnFeeAmt2());
								entity.setFeeCurrency2(utilBean.getnFeeCcy2());
								entity.setFeeTypeCode2(utilBean.getnFeeTpCd2());
								entity.setInterchangeCategory2(utilBean.getnIntrchngCtg2());

								entity.setFeeDRCRIndicator3(utilBean.getnFeeDCInd3());
								entity.setFeeAmount3(utilBean.getnFeeAmt3());
								entity.setFeeCurrency3(utilBean.getnFeeCcy3());
								entity.setFeeTypeCode3(utilBean.getnFeeTpCd3());
								entity.setInterchangeCategory3(utilBean.getnIntrchngCtg3());

								entity.setFeeDRCRIndicator4(utilBean.getnFeeDCInd4());
								entity.setFeeAmount4(utilBean.getnFeeAmt4());
								entity.setFeeCurrency4(utilBean.getnFeeCcy4());
								entity.setFeeTypeCode4(utilBean.getnFeeTpCd4());
								entity.setInterchangeCategory4(utilBean.getnIntrchngCtg4());

								entity.setFeeDRCRIndicator5(utilBean.getnFeeDCInd5());
								entity.setFeeAmount5(utilBean.getFeeAmt5());
								entity.setFeeCurrency5(utilBean.getnFeeCcy5());
								entity.setFeeTypeCode5(utilBean.getnFeeTpCd5());
								entity.setInterchangeCategory5(utilBean.getnIntrchngCtg5());

								// Other
								entity.setPan(pan);
								entity.setRrn(utilBean.getnARD());
								entity.setFilename(file.getOriginalFilename());
								entity.setFlag("I");
								entity.setCreatedDate(new Date()); // current timestamp
								rawDataSet.add(entity);
								DataCount++;
								utilBean = new QsparcUtilBean(); // Reset for next record

								thisLine = null;

								if (++batchCount % batchSize == 0) {

									batchNumber++;

									logger.info("Batch Executed is " + batchNumber);
									qsparc03InternationalRawDataRepository.saveAll(rawDataSet);
									rawDataSet = new HashSet<Qsparc03InternationalRawData>();

								} else {

									qsparc03InternationalRawDataRepository.saveAll(rawDataSet);
								}
							}
						}
					}
					int updateStatus = commonDataValidationRepository.updateStatusByFileTypeOrFiledate("QSPARC 03 INT", fileDate, file.getOriginalFilename(), String.valueOf(DataCount), "Y");
					if(updateStatus>0) {
						logger.error("Upload Status updated");
					}else {
						
						logger.error("unabele to update ");
					}



				}else if (file.getOriginalFilename().substring(0, 2).equalsIgnoreCase("05")) {

					Set<Qsparc05InternationalRawData> rawDataSet = new HashSet<>();
					Pattern TAG_REGEX = Pattern.compile(">(.+?)</");
					Pattern node_REGEX = Pattern.compile("<(.+?)>");
					QsparcHeaderUtil headerUtil = new QsparcHeaderUtil();
					QsparcUtilBean utilBean = new QsparcUtilBean();
					int batchNumber = 0, batchSize = 50000;
					int batchCount = 0;
					reader = new BufferedReader(new InputStreamReader(file.getInputStream()));

					int feesize = 1;
					@SuppressWarnings("unused")
					String thisLine, hdr = "", trl = "", trl_nFunCd = "";
					@SuppressWarnings("unused")
					String trl_nRecNum = null, transactions_count = null;
					logger.info("Process started" + (new Date()).getTime());
					while ((thisLine = reader.readLine()) != null) {

						Matcher nodeMatcher = node_REGEX.matcher(thisLine);
						nodeMatcher.find();
						if (!nodeMatcher.group(1).equalsIgnoreCase("Txn")) {
							if (nodeMatcher.group(1).equalsIgnoreCase("Hdr")) {
								hdr = "hdr";
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("/Hdr")) {
								hdr = "";
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nDtTmFlGen")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnDtTmFlGen(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nMemInstCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnMemInstCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nUnFlNm")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnUnFlNm(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nProdCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnProdCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nSetBIN")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnSetBIN(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFlCatg")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnFlCatg(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nVerNum")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnVerNum(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nAcqInstCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnAcqInstCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nAmtSet")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								double amtSet = Integer.parseInt(matcher.group(1));
								amtSet /= 100.0D;
								utilBean.setnAmtSet(String.valueOf(amtSet));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nAmtTxn")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								double amtTxn = Double.parseDouble(matcher.group(1));
								amtTxn /= 100.0D;
								utilBean.setnAmtTxn(String.valueOf(amtTxn));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nApprvlCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnApprvlCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nRRN")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnARD(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCcyCdSet")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCcyCdSet(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCcyCdTxn")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCcyCdTxn(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nConvRtSet")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnConvRtSet(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCrdAcpAddAdrs")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCrdAcpAddAdrs(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCrdAcptTrmId")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCrdAcptTrmId(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCrdAcpZipCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCrdAcpZipCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nDtSet")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								if (hdr.equalsIgnoreCase("hdr")) {
									headerUtil.setnDtSet(matcher.group(1));
									continue;
								}
								utilBean.setnDtSet(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nDtTmLcTxn")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnDtTmLcTxn(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFunCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								if (hdr.equalsIgnoreCase("hdr")) {
									headerUtil.setnFunCd(matcher.group(1));
									continue;
								}
								if (hdr.equalsIgnoreCase("Trl")) {
									trl_nFunCd = matcher.group(1);
									logger.info(trl_nFunCd);
									continue;
								}
								utilBean.setnFunCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nLtPrsntInd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnLtPrsntInd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nMTI")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								if (hdr.equalsIgnoreCase("hdr")) {
									headerUtil.setnMTI(matcher.group(1));
									continue;
								}
								utilBean.setnMTI(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nPAN")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnPAN(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nRecNum")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								if (hdr.equalsIgnoreCase("hdr")) {
									headerUtil.setnRecNum(matcher.group(1));
									continue;
								}
								if (hdr.equalsIgnoreCase("Trl")) {
									headerUtil.setTrl_nRecNum(matcher.group(1));
									trl_nRecNum = matcher.group(1);
									continue;
								}
								utilBean.setnRecNum(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nRGCSRcvdDt")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnRGCSRcvdDt(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nSetDCInd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnSetDCInd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nTxnDesInstCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnTxnDesInstCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCrdAcpBussCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCrdAcpBussCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCrdAcpNm")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCrdAcpNm(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nTxnOrgInstCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnTxnOrgInstCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nUID")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnUID(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFeeDCInd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								switch (feesize) {
								case 1:
									utilBean.setnFeeDCInd1(matcher.group(1));
									continue;
								case 2:
									utilBean.setnFeeDCInd2(matcher.group(1));
									continue;
								case 3:
									utilBean.setnFeeDCInd3(matcher.group(1));
									continue;
								case 4:
									utilBean.setnFeeDCInd4(matcher.group(1));
									continue;
								case 5:
									utilBean.setnFeeDCInd5(matcher.group(1));
									continue;
								}
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFeeAmt")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								switch (feesize) {
								case 1:
									utilBean.setnFeeAmt1(matcher.group(1));
									continue;
								case 2:
									utilBean.setnFeeAmt2(matcher.group(1));
									continue;
								case 3:
									utilBean.setnFeeAmt3(matcher.group(1));
									continue;
								case 4:
									utilBean.setnFeeAmt4(matcher.group(1));
									continue;
								case 5:
									utilBean.setFeeAmt5(matcher.group(1));
									continue;
								}
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFeeCcy")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								switch (feesize) {
								case 1:
									utilBean.setnFeeCcy1(matcher.group(1));
									continue;
								case 2:
									utilBean.setnFeeCcy2(matcher.group(1));
									continue;
								case 3:
									utilBean.setnFeeCcy3(matcher.group(1));
									continue;
								case 4:
									utilBean.setnFeeCcy4(matcher.group(1));
									continue;
								case 5:
									utilBean.setnFeeCcy5(matcher.group(1));
									continue;
								}
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFeeTpCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								switch (feesize) {
								case 1:
									utilBean.setnFeeTpCd1(matcher.group(1));
									continue;
								case 2:
									utilBean.setnFeeTpCd2(matcher.group(1));
									continue;
								case 3:
									utilBean.setnFeeTpCd3(matcher.group(1));
									continue;
								case 4:
									utilBean.setnFeeTpCd4(matcher.group(1));
									continue;
								case 5:
									utilBean.setnFeeTpCd5(matcher.group(1));
									continue;
								}
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nIntrchngCtg")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								switch (feesize) {
								case 1:
									utilBean.setnIntrchngCtg1(matcher.group(1));
									continue;
								case 2:
									utilBean.setnIntrchngCtg2(matcher.group(1));
									continue;
								case 3:
									utilBean.setnIntrchngCtg3(matcher.group(1));
									continue;
								case 4:
									utilBean.setnIntrchngCtg4(matcher.group(1));
									continue;
								case 5:
									utilBean.setnIntrchngCtg5(matcher.group(1));
									continue;
								}
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("/Fee")) {
								feesize++;
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCaseNum")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCaseNum(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nContNum")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnContNum(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nFulParInd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnFulParInd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nProcCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnProdCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nAmtBil")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnAmtBil(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nCcyCdBil")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnCcyCdBil(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nConvRtBil")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnConvRtBil(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nMsgRsnCd")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								utilBean.setnMsgRsnCd(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nRnTtlAmt")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnRnTtlAmt(matcher.group(1));
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("nTxnCnt")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								headerUtil.setnTxnCnt(matcher.group(1));
								transactions_count = matcher.group(1);
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("Trl")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								hdr = "Trl";
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("/Trl")) {
								Matcher matcher = TAG_REGEX.matcher(thisLine);
								matcher.find();
								hdr = "";
								continue;
							}
							if (nodeMatcher.group(1).equalsIgnoreCase("/Txn")) {
								feesize = 1;

								Qsparc05InternationalRawData entity = new Qsparc05InternationalRawData();

								// Header fields
								entity.setMti(headerUtil.getnMTI());
								entity.setFunctionCode(headerUtil.getnFunCd());
								entity.setRecordNumber(headerUtil.getnRecNum());
								entity.setMemberInstitutionIdCode(headerUtil.getnMemInstCd());
								entity.setUniqueFileName(headerUtil.getnUnFlNm());
								entity.setProductCode(headerUtil.getnProdCd());
								entity.setSettlementBIN(headerUtil.getnSetBIN());
								entity.setFileCategory(headerUtil.getnFlCatg());
								entity.setVersionNumber(headerUtil.getnVerNum());
								entity.setTransactionsCount(headerUtil.getnTxnCnt());
								entity.setRunTotalAmount(headerUtil.getnRnTtlAmt());
								entity.setTrlFunctionCode(headerUtil.getTrl_nFunCd());
								entity.setTrlRecordNumber(headerUtil.getTrl_nRecNum());

								// Date parsing for settlement date
								entity.setDateSettlement(headerUtil.getnDtSet()); // format: yyyyMMdd
								entity.setFileDate(fileDate); // format: yyyy-MM-dd

								// utilBeanBean fields
								entity.setAcquirerInstitutionIdCode(utilBean.getnAcqInstCd());
								entity.setAmountSettlement(utilBean.getnAmtSet());
								entity.setAmountTransaction(utilBean.getnAmtTxn());
								entity.setApprovalCode(utilBean.getnApprvlCd());
								entity.setAcquirerReferenceData(utilBean.getnARD());
								entity.setCaseNumber(utilBean.getnCaseNum());
								entity.setCurrencyCodeSettlement(utilBean.getnCcyCdSet());
								entity.setCurrencyCodeTransaction(utilBean.getnCcyCdTxn());
								entity.setConversionRateSettlement(utilBean.getnConvRtSet());
								entity.setCardAcceptorAddiAddr(utilBean.getnCrdAcpAddAdrs());
								entity.setCardAcceptorTerminalID(utilBean.getnCrdAcptTrmId());
								entity.setCardAcceptorZipCode(utilBean.getnCrdAcpZipCd());
								entity.setDateAndTimeLocalTransaction(utilBean.getnDtTmLcTxn());
								entity.setTxnFunctionCode(utilBean.getnFunCd());
								entity.setLatePresentmentIndicator(utilBean.getnLtPrsntInd());
								entity.setTxnMTI(utilBean.getnMTI());

								String pan = utilBean.getnPAN().trim();
								String Update_Pan = "";
								if (pan.length() <= 16 && pan != null && pan.trim() != "" && pan.length() > 0) {
									Update_Pan = String.valueOf(pan.substring(0, 6)) + "XXXXXX"
											+ pan.substring(pan.length() - 4);
								} else if (pan.length() >= 16 && pan != null && pan.trim() != "" && pan.length() > 0) {
									Update_Pan = String.valueOf(pan.substring(0, 6)) + "XXXXXXXXX"
											+ pan.substring(pan.length() - 4);
								} else {
									Update_Pan = null;
								}
								entity.setPrimaryAccountNumber(Update_Pan);
								entity.setTxnRecordNumber(utilBean.getnRecNum());
								entity.setRgcsReceivedDate(utilBean.getnRGCSRcvdDt());
								entity.setSettlementDRCRIndicator(utilBean.getnSetDCInd());
								entity.setTxnDestiInstiIdCode(utilBean.getnTxnDesInstCd());
								entity.setTxnOriginInstiIdCode(utilBean.getnTxnOrgInstCd());
								entity.setCardHolderUID(utilBean.getnUID());
								entity.setAmountBilling(utilBean.getnAmtBil());
								entity.setCurrencyCodeBilling(utilBean.getnCcyCdBil());
								entity.setConversionRateBilling(utilBean.getnConvRtBil());
								entity.setMessageReasonCode(utilBean.getnMsgRsnCd());

								// Fees
								entity.setFeeDRCRIndicator1(utilBean.getnFeeDCInd1());
								entity.setFeeAmount1(utilBean.getnFeeAmt1());
								entity.setFeeCurrency1(utilBean.getnFeeCcy1());
								entity.setFeeTypeCode1(utilBean.getnFeeTpCd1());
								entity.setInterchangeCategory1(utilBean.getnIntrchngCtg1());

								entity.setFeeDRCRIndicator2(utilBean.getnFeeDCInd2());
								entity.setFeeAmount2(utilBean.getnFeeAmt2());
								entity.setFeeCurrency2(utilBean.getnFeeCcy2());
								entity.setFeeTypeCode2(utilBean.getnFeeTpCd2());
								entity.setInterchangeCategory2(utilBean.getnIntrchngCtg2());

								entity.setFeeDRCRIndicator3(utilBean.getnFeeDCInd3());
								entity.setFeeAmount3(utilBean.getnFeeAmt3());
								entity.setFeeCurrency3(utilBean.getnFeeCcy3());
								entity.setFeeTypeCode3(utilBean.getnFeeTpCd3());
								entity.setInterchangeCategory3(utilBean.getnIntrchngCtg3());

								entity.setFeeDRCRIndicator4(utilBean.getnFeeDCInd4());
								entity.setFeeAmount4(utilBean.getnFeeAmt4());
								entity.setFeeCurrency4(utilBean.getnFeeCcy4());
								entity.setFeeTypeCode4(utilBean.getnFeeTpCd4());
								entity.setInterchangeCategory4(utilBean.getnIntrchngCtg4());

								entity.setFeeDRCRIndicator5(utilBean.getnFeeDCInd5());
								entity.setFeeAmount5(utilBean.getFeeAmt5());
								entity.setFeeCurrency5(utilBean.getnFeeCcy5());
								entity.setFeeTypeCode5(utilBean.getnFeeTpCd5());
								entity.setInterchangeCategory5(utilBean.getnIntrchngCtg5());

								// Other
								entity.setPan(pan);
								entity.setRrn(utilBean.getnARD());
								entity.setFilename(file.getOriginalFilename());
								entity.setFlag("I");
								entity.setCreatedDate(new Date()); // current timestamp
								rawDataSet.add(entity);
								DataCount++;
								utilBean = new QsparcUtilBean(); // Reset for next record

								thisLine = null;

								if (++batchCount % batchSize == 0) {

									batchNumber++;

									logger.info("Batch Executed is " + batchNumber);
									qsparc05InternationalRawDataRepository.saveAll(rawDataSet);
									rawDataSet = new HashSet<Qsparc05InternationalRawData>();

								} else {

									qsparc05InternationalRawDataRepository.saveAll(rawDataSet);
								}
							}
						}
					}
					int updateStatus = commonDataValidationRepository.updateStatusByFileTypeOrFiledate("QSPARC 05 INT", fileDate, file.getOriginalFilename(), String.valueOf(DataCount), "Y");
					if(updateStatus>0) {
						logger.error("Upload Status updated");
					}else {
						
						logger.error("unabele to update ");
					}



				} else if (file.getOriginalFilename().substring(0, 2).equalsIgnoreCase("86")) {

					Set<Qsparc86InternationalRawData> rawDataSet = new HashSet<>();

					int batchNumber = 0, batchSize = 50000;
					int batchCount = 0;
					reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
					String thisLine = "";
					logger.info("Process started " + (new Date()).getTime());
					while ((thisLine = reader.readLine()) != null) {
						Qsparc86InternationalRawData entity = new Qsparc86InternationalRawData();

						if (thisLine.startsWith("01")) {
							thisLine = thisLine.replaceAll("\\s", "");

							entity.setMessageType(thisLine.substring(0, 2).trim());
							entity.setProductId(thisLine.substring(2, 5).trim());
							entity.setTransactionType(thisLine.substring(5, 7).trim());
							entity.setFromAccountType(thisLine.substring(7, 9).trim());
							entity.setToAccountType(thisLine.substring(9, 11).trim());
							entity.setActionCode(thisLine.substring(11, 12).trim());
							entity.setResponseCode(thisLine.substring(12, 14).trim());
							entity.setPanNumber(thisLine.substring(14, 33).trim());
							entity.setApprovalNumber(thisLine.substring(33, 39).trim());
							entity.setRetrivalReference(thisLine.substring(39, 51).trim());
							entity.setTransactionDate(thisLine.substring(51, 58).trim());
							entity.setTransactionTime(thisLine.substring(58, 64).trim());
							entity.setMerchantCatagoryCode(thisLine.substring(64, 68).trim());
							entity.setCardAcceptorId(thisLine.substring(68, 83).trim());
							entity.setCardAcceptorTerminalId(thisLine.substring(83, 91).trim());
							entity.setCardAcceptorTerminalLocation(thisLine.substring(91, 131).trim());
							entity.setAcquirerId(thisLine.substring(131, 142).trim());
							entity.setTransactionCurrencyCode(thisLine.substring(142, 145).trim());
							entity.setTransactionAmount(thisLine.substring(145, 160).trim());
							entity.setCardHolderBillingCurrency(thisLine.substring(160, 163).trim());
							entity.setCardHolderBillingAmount(thisLine.substring(163, 178).trim());
							entity.setPanEntryMode(thisLine.substring(178, 180).trim());
							entity.setPanEntryCapability(thisLine.substring(180, 181).trim());
							entity.setPinCaptureCode(thisLine.substring(181, 183).trim());
							entity.setAcquirerCountryCode(thisLine.substring(183, 186).trim());
							entity.setAdditionalAmount(thisLine.substring(186, 201).trim());
							entity.setRupayProduct(thisLine.substring(201, 206).trim());
							entity.setCvd2MatchResult(thisLine.substring(206, 207).trim());
							entity.setCvdICVDMatchResult(thisLine.substring(207, 208).trim());
							entity.setReccuringPaymentIndicator(thisLine.substring(208, 210).trim());
							entity.setEciIndicator(thisLine.substring(210, 212).trim());
							entity.setIcs1ResultCode(thisLine.substring(212, 214).trim());
							entity.setFraudScore(thisLine.substring(214, 219).trim());
							entity.setEmiAmount(thisLine.substring(219, 245).trim());
							entity.setArqcAuthorization(thisLine.substring(245, 246).trim());
							entity.setTransactionId(thisLine.substring(246, 276).trim());
							entity.setLoyaltyPoint(thisLine.substring(276, 282).trim());
							entity.setIcs2ResultCode(thisLine.substring(282, 283).trim());
							entity.setCustMobileNumber(thisLine.substring(283, 295).trim());
							entity.setImageCode(thisLine.substring(295, 300).trim());
							entity.setPersonalPhase(thisLine.substring(300, 305).trim());
							entity.setUidNumber(thisLine.substring(305, 317).trim());
							entity.setCardDataInputCapability(thisLine.substring(317, 318).trim());
							entity.setCardHolderAuthCapability(thisLine.substring(318, 319).trim());
							entity.setCardCaptureCapability(thisLine.substring(319, 320).trim());
							entity.setTerminalOperatingEnviroment(thisLine.substring(320, 321).trim());
							entity.setCardHolderPresentData(thisLine.substring(321, 322).trim());
							entity.setCardPresentData(thisLine.substring(322, 323).trim());
							entity.setCardDataInputMode(thisLine.substring(323, 324).trim());
							entity.setCardHolderAuthMode(thisLine.substring(324, 325).trim());
							entity.setCardHolderAuthEntity(thisLine.substring(325, 326).trim());
							entity.setCardDataOpCapability(thisLine.substring(326, 327).trim());
							entity.setTerminalDataOpCapability(thisLine.substring(327, 328).trim());
							entity.setPinCaptureCapability(thisLine.substring(328, 329).trim());
							entity.setZipCode(thisLine.substring(329, 338).trim());
							entity.setAdviceReasonCode(thisLine.substring(338, 345).trim());
							entity.setItPan(thisLine.substring(345, 355).trim());
							entity.setIntrauthnw(thisLine.substring(355, 370).trim());
							entity.setOtpIndicator(thisLine.substring(370, 371).trim());
							entity.setIcsTxnId(thisLine.substring(371, 386).trim());
							entity.setNwData(thisLine.substring(386, 398).trim());
							entity.setServiceCode(thisLine.substring(398, 401).trim());
							entity.setCurrencyCodeActualTxnAmt(thisLine.substring(401, 404).trim());
							entity.setActualTxnAmt(thisLine.substring(404, 419).trim());
							entity.setFileDate(fileDate);
							entity.setFilename(file.getOriginalFilename());
							rawDataSet.add(entity);
							DataCount++;
							// Reset for next record

							thisLine = null;

							if (++batchCount % batchSize == 0) {

								batchNumber++;

								logger.info("Batch Executed is " + batchNumber);
								qsparc86InternationalRawDataRepository.saveAll(rawDataSet);
								rawDataSet = new HashSet<Qsparc86InternationalRawData>();

							} else {

								qsparc86InternationalRawDataRepository.saveAll(rawDataSet);
							}
							// You can now persist this entity using your repository
							// rupay86RawDataRepository.save(entity);
						}
					}
					int updateStatus = commonDataValidationRepository.updateStatusByFileTypeOrFiledate("QSPARC 86 INT", fileDate, file.getOriginalFilename(), String.valueOf(DataCount), "Y");
					if(updateStatus>0) {
						logger.error("Upload Status updated");
					}else {
						
						logger.error("unabele to update ");
					}


				} else {

				}
			
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

	

	}}
