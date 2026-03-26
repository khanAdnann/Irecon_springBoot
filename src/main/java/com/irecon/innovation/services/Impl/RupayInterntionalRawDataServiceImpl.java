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
import com.irecon.innovation.entity.Rupay01InternationalRawData;
import com.irecon.innovation.entity.Rupay02InternationalRawData;
import com.irecon.innovation.entity.Rupay03InternationalRawData;
import com.irecon.innovation.entity.Rupay05InternationalRawData;
import com.irecon.innovation.entity.Rupay86InternationalRawData;
import com.irecon.innovation.entity.Rupay88InternationalRawData;
import com.irecon.innovation.repository.CommonDataValidationRepository;
import com.irecon.innovation.repository.Rupay01InternationalRawDataRepository;
import com.irecon.innovation.repository.Rupay02InternationalRawDataRepository;
import com.irecon.innovation.repository.Rupay03InternationalRawDataRepository;
import com.irecon.innovation.repository.Rupay05InternationalRawDataRepository;
import com.irecon.innovation.repository.Rupay86InternationalRawDataRepository;
import com.irecon.innovation.repository.Rupay88InternationalRawDataRepository;

import com.irecon.innovation.services.RupayInternationalRawDataService;

import com.irecon.innovation.utility.RupayHeaderUtil;
import com.irecon.innovation.utility.RupayUtilBean;

@Service
public class RupayInterntionalRawDataServiceImpl implements RupayInternationalRawDataService {

	private static final Logger logger = LoggerFactory.getLogger(RupayInterntionalRawDataServiceImpl.class);

	@Autowired
	private Rupay88InternationalRawDataRepository rupay88InternationalRawDataRepository;
	@Autowired
	private Rupay01InternationalRawDataRepository rupay01InternationalRawDataRepository;
	@Autowired
	private Rupay02InternationalRawDataRepository rupay02InternationalRawDataRepository;
	@Autowired
	private Rupay03InternationalRawDataRepository rupay03InternationalRawDataRepository;
	@Autowired
	private Rupay05InternationalRawDataRepository rupay05InternationalRawDataRepository;

	@Autowired
	private Rupay86InternationalRawDataRepository rupay86InternationalRawDataRepository;

	@Autowired
	private CommonDataValidationRepository commonDataValidationRepository;

	@Override
	public boolean SaveRupayIntrnationalRawData(MultipartFile file, String fileDate) {
		logger.info("Save method SaveRupayInternationalRawData " + fileDate);
		BufferedReader reader = null;
		int batchNumber = 0, batchSize = 40000;
		Long DataCount = 0L;
		// Check if the file already exists with the same name and date
		try {

			if (file.getOriginalFilename().substring(0, 2).equalsIgnoreCase("88")) {

				Set<Rupay88InternationalRawData> rawDataSet = new HashSet<>();

				Pattern TAG_REGEX = Pattern.compile(">(.+?)</");
				Pattern node_REGEX = Pattern.compile("<(.+?)>");
				RupayHeaderUtil headerUtil = new RupayHeaderUtil();
				RupayUtilBean utilBean = new RupayUtilBean();

				int batchCount = 0;
				reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
				String thisLine, hdr = "", trl = "", trl_nFunCd = "", trl_nRecNum = "", transactions_count = null;
				int feesize = 1;

				logger.info("Process started " + (new Date()).getTime());
				while ((thisLine = reader.readLine()) != null) {
					// System.out.println("data "+ thisLine);
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

							Rupay88InternationalRawData entity = new Rupay88InternationalRawData();

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
							utilBean = new RupayUtilBean(); // Reset for next record

							thisLine = null;

							if (++batchCount % batchSize == 0) {

								batchNumber++;

								logger.info("Batch Executed is " + batchNumber);
								rupay88InternationalRawDataRepository.saveAll(rawDataSet);
								rawDataSet = new HashSet<Rupay88InternationalRawData>();

							} else {

								rupay88InternationalRawDataRepository.saveAll(rawDataSet);
							}
						}
					}
				}

				int updateStatus = commonDataValidationRepository.updateStatusByFileTypeOrFiledate("RUPAY 88 INT", fileDate, file.getOriginalFilename(), String.valueOf(DataCount), "Y");
				if(updateStatus>0) {
					logger.error("Upload Status updated");
				}else {
					
					logger.error("unabele to update ");
				}


			} else if (file.getOriginalFilename().substring(0, 2).equalsIgnoreCase("01")) {

				Set<Rupay01InternationalRawData> rawDataSet = new HashSet<>();
				Pattern TAG_REGEX = Pattern.compile(">(.+?)</");
				Pattern node_REGEX = Pattern.compile("<(.+?)>");
				RupayHeaderUtil headerUtil = new RupayHeaderUtil();
				RupayUtilBean utilBean = new RupayUtilBean();

				int batchCount = 0;
				reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
				String thisLine, hdr = "", trl = "", trl_nFunCd = "", trl_nRecNum = "", transactions_count = null;
				int feesize = 1;

				logger.info("Process started " + (new Date()).getTime());
				while ((thisLine = reader.readLine()) != null) {
					// System.out.println("data "+ thisLine);
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

							Rupay01InternationalRawData entity = new Rupay01InternationalRawData();

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
							utilBean = new RupayUtilBean(); // Reset for next record

							thisLine = null;

							if (++batchCount % batchSize == 0) {

								batchNumber++;

								logger.info("Batch Executed is " + batchNumber);
								rupay01InternationalRawDataRepository.saveAll(rawDataSet);
								rawDataSet = new HashSet<Rupay01InternationalRawData>();

							} else {

								rupay01InternationalRawDataRepository.saveAll(rawDataSet);
							}
						}
					}
				}

				int updateStatus = commonDataValidationRepository.updateStatusByFileTypeOrFiledate("RUPAY 01 INT", fileDate, file.getOriginalFilename(), String.valueOf(DataCount), "Y");
				if(updateStatus>0) {
					logger.error("Upload Status updated");
				}else {
					
					logger.error("unabele to update ");
				}


			} else if (file.getOriginalFilename().substring(0, 2).equalsIgnoreCase("02")) {

				Set<Rupay02InternationalRawData> rawDataSet = new HashSet<>();
				Pattern TAG_REGEX = Pattern.compile(">(.+?)</");
				Pattern node_REGEX = Pattern.compile("<(.+?)>");
				RupayHeaderUtil headerUtil = new RupayHeaderUtil();
				RupayUtilBean utilBean = new RupayUtilBean();

				int batchCount = 0;
				reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
				String thisLine, hdr = "", trl = "", trl_nFunCd = "", trl_nRecNum = "", transactions_count = null;
				int feesize = 1;

				logger.info("Process started " + (new Date()).getTime());
				while ((thisLine = reader.readLine()) != null) {
					// System.out.println("data "+ thisLine);
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

							Rupay02InternationalRawData entity = new Rupay02InternationalRawData();

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
							utilBean = new RupayUtilBean(); // Reset for next record

							thisLine = null;

							if (++batchCount % batchSize == 0) {

								batchNumber++;

								logger.info("Batch Executed is " + batchNumber);
								rupay02InternationalRawDataRepository.saveAll(rawDataSet);
								rawDataSet = new HashSet<Rupay02InternationalRawData>();

							} else {

								rupay02InternationalRawDataRepository.saveAll(rawDataSet);
							}
						}
					}
				}

				int updateStatus = commonDataValidationRepository.updateStatusByFileTypeOrFiledate("RUPAY 02 INT", fileDate, file.getOriginalFilename(), String.valueOf(DataCount), "Y");
				if(updateStatus>0) {
					logger.error("Upload Status updated");
				}else {
					
					logger.error("unabele to update ");
				}


			} else if (file.getOriginalFilename().substring(0, 2).equalsIgnoreCase("03")) {

				Set<Rupay03InternationalRawData> rawDataSet = new HashSet<>();
				Pattern TAG_REGEX = Pattern.compile(">(.+?)</");
				Pattern node_REGEX = Pattern.compile("<(.+?)>");
				RupayHeaderUtil headerUtil = new RupayHeaderUtil();
				RupayUtilBean utilBean = new RupayUtilBean();

				int batchCount = 0;
				reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
				String thisLine, hdr = "", trl = "", trl_nFunCd = "", trl_nRecNum = "", transactions_count = null;
				int feesize = 1;

				logger.info("Process started " + (new Date()).getTime());
				while ((thisLine = reader.readLine()) != null) {
					// System.out.println("data "+ thisLine);
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

							Rupay03InternationalRawData entity = new Rupay03InternationalRawData();

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
							utilBean = new RupayUtilBean(); // Reset for next record

							thisLine = null;

							if (++batchCount % batchSize == 0) {

								batchNumber++;

								logger.info("Batch Executed is " + batchNumber);
								rupay03InternationalRawDataRepository.saveAll(rawDataSet);
								rawDataSet = new HashSet<Rupay03InternationalRawData>();

							} else {

								rupay03InternationalRawDataRepository.saveAll(rawDataSet);
							}
						}
					}
				}

				int updateStatus = commonDataValidationRepository.updateStatusByFileTypeOrFiledate("RUPAY 03 INT", fileDate, file.getOriginalFilename(), String.valueOf(DataCount), "Y");
				logger.info(updateStatus > 0 ? "Upload status updated." : "Unable to update status.");
				


			} else if (file.getOriginalFilename().substring(0, 2).equalsIgnoreCase("05")) {

				Set<Rupay05InternationalRawData> rawDataSet = new HashSet<>();
				Pattern TAG_REGEX = Pattern.compile(">(.+?)</");
				Pattern node_REGEX = Pattern.compile("<(.+?)>");
				RupayHeaderUtil headerUtil = new RupayHeaderUtil();
				RupayUtilBean utilBean = new RupayUtilBean();

				int batchCount = 0;
				reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
				String thisLine, hdr = "", trl = "", trl_nFunCd = "", trl_nRecNum = "", transactions_count = null;
				int feesize = 1;

				logger.info("Process started " + (new Date()).getTime());
				while ((thisLine = reader.readLine()) != null) {
					// System.out.println("data "+ thisLine);
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

							Rupay05InternationalRawData entity = new Rupay05InternationalRawData();

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
							utilBean = new RupayUtilBean(); // Reset for next record

							thisLine = null;

							if (++batchCount % batchSize == 0) {

								batchNumber++;

								logger.info("Batch Executed is " + batchNumber);
								rupay05InternationalRawDataRepository.saveAll(rawDataSet);
								rawDataSet = new HashSet<Rupay05InternationalRawData>();

							} else {

								rupay05InternationalRawDataRepository.saveAll(rawDataSet);
							}
						}
					}
				}

				int updateStatus = commonDataValidationRepository.updateStatusByFileTypeOrFiledate("RUPAY 05 INT", fileDate, file.getOriginalFilename(), String.valueOf(DataCount), "Y");
				logger.info(updateStatus > 0 ? "Upload status updated." : "Unable to update status.");
			

			} else if (file.getOriginalFilename().substring(0, 2).equalsIgnoreCase("86")) {

				Set<Rupay86InternationalRawData> rawDataSet = new HashSet<>();
				Pattern TAG_REGEX = Pattern.compile(">(.+?)</");
				Pattern node_REGEX = Pattern.compile("<(.+?)>");
				RupayHeaderUtil headerUtil = new RupayHeaderUtil();
				RupayUtilBean utilBean = new RupayUtilBean();

				int batchCount = 0;
				reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
				String thisLine, hdr = "", trl = "", trl_nFunCd = "", trl_nRecNum = "", transactions_count = null;
				int feesize = 1;

				logger.info("Process started " + (new Date()).getTime());
				while ((thisLine = reader.readLine()) != null) {
					Rupay86InternationalRawData entity = new Rupay86InternationalRawData();

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
						utilBean = new RupayUtilBean(); // Reset for next record

						thisLine = null;

						if (++batchCount % batchSize == 0) {

							batchNumber++;

							logger.info("Batch Executed is " + batchNumber);
							rupay86InternationalRawDataRepository.saveAll(rawDataSet);
							rawDataSet = new HashSet<Rupay86InternationalRawData>();

						} else {

							rupay86InternationalRawDataRepository.saveAll(rawDataSet);
						}
						// You can now persist this entity using your repository
						// rupay86RawDataRepository.save(entity);
					}
				}
			
				int updateStatus = commonDataValidationRepository.updateStatusByFileTypeOrFiledate("RUPAY 86 INT", fileDate, file.getOriginalFilename(), String.valueOf(DataCount), "Y");
				logger.info(updateStatus > 0 ? "Upload status updated." : "Unable to update status.");
				logger.info("Successfully uploaded file.");

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
	}

}
