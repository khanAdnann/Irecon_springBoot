package com.irecon.innovation.services.Impl;

import com.irecon.innovation.entity.JcbAdjustmentRawData;
import com.irecon.innovation.entity.NfsAcqRawData;
import com.irecon.innovation.entity.NfsAdjustmentRawData;
import com.irecon.innovation.entity.NfsNtslRawData;
import com.irecon.innovation.repository.CommonDataValidationRepository;
import com.irecon.innovation.repository.JcbAdjustmentRawDataRepository;
import com.irecon.innovation.repository.NfsAdjustmentRawDataRepository;
import com.irecon.innovation.repository.NfsNtslRawDataRepository;
import com.irecon.innovation.services.JcbAdjustmentRawDataService;
import com.irecon.innovation.services.NfsAdjustmentRawDataService;
import com.irecon.innovation.services.NfsNtslRawDataService;

import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class JcbAdjustmentRawDataServiceImpl implements JcbAdjustmentRawDataService {
	private static final Logger logger = LoggerFactory.getLogger(JcbAdjustmentRawDataServiceImpl.class);

	@Autowired
	private CommonDataValidationRepository commonDataValidationRepository;

	@Autowired
	private JcbAdjustmentRawDataRepository jcbAdjustmentRawDataRepository;

	public boolean SaveJcbAdjustmentFile(MultipartFile file, String fileDate) {
		Map<String, Object> result = new HashMap<>();
		int totalCount = 0, count = 0;
String cycle="";
		try {
			
		   
			FormulaEvaluator formulaEvaluate = null;
			Workbook wb = null;
			Sheet sheet = null;
			int extn = file.getOriginalFilename().indexOf(".");
			if (file.getOriginalFilename().substring(extn).equalsIgnoreCase(".XLS")) {

				wb = new HSSFWorkbook(file.getInputStream());
				sheet = wb.getSheetAt(0);
				formulaEvaluate = new HSSFFormulaEvaluator((HSSFWorkbook) wb);

			} else if (file.getOriginalFilename().substring(extn).equalsIgnoreCase(".XLSX")) {

				// book = new XSSFWorkbook(file.getInputStream());
				wb = new XSSFWorkbook(file.getInputStream());
				sheet = wb.getSheetAt(0);
				formulaEvaluate = new XSSFFormulaEvaluator((XSSFWorkbook) wb);
			}
			List<JcbAdjustmentRawData> rowDataList = new ArrayList<>();
			for (Row r : sheet) {
				if (r.getRowNum() > 0) {
					JcbAdjustmentRawData data = new JcbAdjustmentRawData();
					int colIndex = 0;

					for (Cell c : r) {
						if (colIndex < 44) {
							String value = "";

							switch (c.getCellType()) {
							case STRING:
								value = c.getStringCellValue().replace("'", "");
								break;
							case NUMERIC:
								value = processNumeric(c.getNumericCellValue());
								break;
							default:
								value = "default"; 
								break;
							}

							setEntityValue(data, colIndex, value);
							colIndex++;
						}
					}

					data.setCreatedBy("INT12016");
					data.setFileDate(fileDate);
					data.setFileName(file.getOriginalFilename());

					rowDataList.add(data);

					count++;
				}
			}
			jcbAdjustmentRawDataRepository.saveAll(rowDataList);
			
			int updateStatus = commonDataValidationRepository.updateStatusByFileTypeOrFiledate("JCB ADJUSTMENT",
					fileDate, file.getOriginalFilename(), String.valueOf(count), "Y");
			if (updateStatus > 0) {
				logger.error("Upload Status updated");
			} else {

				logger.error("unabele to update ");
			}

			logger.info("Success");
			
		} catch (Exception e) {
			result.put("result", false);
			result.put("error", e.getMessage());
			return false;
		}

		return true;
	}

	private void setEntityValue(JcbAdjustmentRawData data, int index, String value) {
		switch (index) {
		case 0:
			data.setTxnuid(value);
			break;
		case 1:
			data.setTxnType(value);
			break;
		case 2:
			data.setuId(value);
			break;
		case 3:
			data.setAdjDate(value);
			break;
		case 4:
			data.setAdjType(value);
			break;
		case 5:
			data.setAcq(value);
			break;
		case 6:
			data.setIsr(value);
			break;
		case 7:
			data.setResponse(value);
			break;
		case 8:
			data.setTxnDate(value);
			break;
		case 9:
			data.setTxnTime(value);
			break;
		case 10:
			data.setRrn(value);
			break;
		case 11:
			data.setAtmid(value);
			break;
		case 12:
			data.setCardNo(value);
			break;
		case 13:
			data.setChbDate(value);
			break;
		case 14:
			data.setChbRef(value);
			break;
		case 15:
			data.setTxnAmount(value);
			break;
		case 16:
			data.setAdjAmount(value);
			break;
		case 17:
			data.setAcqFee(value);
			break;
		case 18:
			data.setIssFee(value);
			break;
		case 19:
			data.setIssFeeSW(value);
			break;
		case 20:
			data.setNpciFee(value);
			break;
		case 21:
			data.setAcqFeeTax(value);
			break;
		case 22:
			data.setIssFeeTax(value);
			break;
		case 23:
			data.setNpciTAX(value);
			break;
		case 24:
			data.setAdjRef(value);
			break;
		case 25:
			data.setBankAdjRef(value);
			break;
		case 26:
			data.setAdjProof(value);
			break;
		case 27:
			data.setReasonDesc(value);
			break;
		case 28:
			data.setPincode(value);
			break;
		case 29:
			data.setAtmLocation(value);
			break;
		case 30:
			data.setMultiDisputeGroup(value);
			break;
		case 31:
			data.setFcqm(value);
			break;
		case 32:
			data.setAdjSettlementDate(value);
			break;
		case 33:
			data.setCustomerPenalty(value);
			break;
		case 34:
			data.setAdjTime(value);
			break;
		case 35:
			data.setCycle(value);
			break;
		case 36:
			data.setTatExpiryDate(value);
			break;
		case 37:
			data.setAcqSTLAmount(value);
			break;
		case 38:
			data.setAcqCC(value);
			break;
		case 39:
			data.setPanEntryMode(value);
			break;
		case 40:
			data.setServiceCode(value);
			break;
		case 41:
			data.setCardDatInputCapability(value);
			break;
		case 42:
			data.setMccCode(value);
			break;
		case 43:
			data.setCreatedBy(value);
			break;
		default:
			// Optionally log if the index is out of range or unknown
			System.out.println("Unknown index: " + index);
		}
	}

	private String processNumeric(double numericValue) {
		// Convert the numeric value to a string
		String value = String.valueOf(numericValue);

		// Handle scientific notation (E notation)
		if (value.contains("E")) {
			BigDecimal bd = new BigDecimal(numericValue);
			
			value = bd.setScale(2, RoundingMode.HALF_UP).toPlainString();
		}

		
		else if (value.indexOf('.') != -1) {
			// Ensure the value has a maximum of 2 decimal places
			BigDecimal bd = new BigDecimal(value);
			value = bd.setScale(2, RoundingMode.HALF_UP).toPlainString();
		}

		
		if (value.length() > 15) {
			BigDecimal bd = new BigDecimal(value);
			value = bd.setScale(2, RoundingMode.HALF_UP).toPlainString();
		}

		return value;
	}

}
