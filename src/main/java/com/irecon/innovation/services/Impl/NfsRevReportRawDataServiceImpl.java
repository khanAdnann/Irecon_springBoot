package com.irecon.innovation.services.Impl;

import com.irecon.innovation.entity.NfsAcqRawData;
import com.irecon.innovation.entity.NfsAdjustmentRawData;
import com.irecon.innovation.entity.NfsNtslRawData;
import com.irecon.innovation.entity.NfsRevAcqReportRawData;
import com.irecon.innovation.repository.CommonDataValidationRepository;
import com.irecon.innovation.repository.NfsAdjustmentRawDataRepository;
import com.irecon.innovation.repository.NfsNtslRawDataRepository;
import com.irecon.innovation.repository.NfsRevReportRawDataRepository;
import com.irecon.innovation.services.NfsAdjustmentRawDataService;
import com.irecon.innovation.services.NfsNtslRawDataService;
import com.irecon.innovation.services.NfsRevReportRawDataService;

import org.apache.commons.compress.archivers.ar.ArArchiveEntry;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class NfsRevReportRawDataServiceImpl implements NfsRevReportRawDataService {
	private static final Logger logger = LoggerFactory.getLogger(NfsRevReportRawDataServiceImpl.class);

	@Autowired
	private CommonDataValidationRepository commonDataValidationRepository;

	@Autowired
	private NfsRevReportRawDataRepository nfsRevReportRawDataRepository;

	public boolean SaveNfsRevReportFile(MultipartFile file, String fileDate) {
		Map<String, Object> result = new HashMap<>();
		int totalCount = 0, count = 0;
		String cycle = "";
		try {
			String[] fileNames = file.getOriginalFilename().split("_");
			if (fileNames.length > 1) {
				cycle = fileNames[1].substring(0, 1);

			}
			FormulaEvaluator formulaEvaluate = null;
			List<NfsRevAcqReportRawData> listNfsRevAcqReportRawDatas = new ArrayList<NfsRevAcqReportRawData>();
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

			sheet = wb.getSheetAt(0);
			this.logger.info("Sheet rows: " + sheet.getLastRowNum());

			if (sheet.getLastRowNum() == 0) {

				this.logger.info("Uploaded file is blank!" + sheet.getLastRowNum());

				return false;
			}

			Row headerRow = sheet.getRow(1);
			if (headerRow == null) {

				this.logger.info("No header found in file!");

				return false;
			}

			DataFormatter dataFormatter = new DataFormatter();
			SimpleDateFormat sdf = new SimpleDateFormat("H:mm:ss");

			int startRow = (headerRow.getCell(0).getStringCellValue().contains("Verification Report")) ? 3 : 1;

			for (int i = startRow; i <= sheet.getLastRowNum(); i++) {
			//	System.out.println("datqa "+ sheet.getRow(i));
				Row row = sheet.getRow(i);
				if (row == null)
					continue;
				

				NfsRevAcqReportRawData entity = new NfsRevAcqReportRawData();
				System.out.println("data "+ getStringCell(row, 0));
				entity.setTransType(getStringCell(row, 0));
				entity.setRespCode(getStringCell(row, 1));

				String cardNo = getStringCell(row, 2).replace("`", "").trim();
				entity.setCardno(cardNo);

				entity.setRrn(getStringCell(row, 3));
				entity.setStanNo(getStringCell(row, 4));
				entity.setAcq(getStringCell(row, 5));
				entity.setIss(getStringCell(row, 6));

				String transDate = getFormattedCellValue(row.getCell(7), formulaEvaluate);
				entity.setTrasnDate(transDate);

				String transTimeRaw = getFormattedCellValue(row.getCell(8), formulaEvaluate);
				if (transTimeRaw != null && !transTimeRaw.isEmpty()) {
					Date dateObj = sdf.parse(transTimeRaw.replaceAll("\u00A0", ""));
					entity.setTransTime(new SimpleDateFormat("Kmmss").format(dateObj));
				}

				entity.setAtmId(getFormattedCellValue(row.getCell(9), formulaEvaluate));
				entity.setSettleDate(getFormattedCellValue(row.getCell(10), formulaEvaluate));

				entity.setRequestAmt(getStringCell(row, 11));

				String receivedAmt;
				if (row.getCell(12).getCellType() == CellType.NUMERIC) {
					receivedAmt = NumberToTextConverter.toText(row.getCell(12).getNumericCellValue());
				} else {
					receivedAmt = getStringCell(row, 12);
				}
				entity.setReceivedAmt(receivedAmt);

				entity.setStatus(getStringCell(row, 13));
				entity.setDcrsRemarks("UNMATCHED");

				entity.setFileDate(fileDate);
				entity.setCycle(cycle);

				entity.setMerchantType(""); // Empty
				entity.setFpan(cardNo);
				entity.setFilename(file.getOriginalFilename());
				listNfsRevAcqReportRawDatas.add(entity);
				count++;
				// Save Entity

			}

			// Update upload table

			nfsRevReportRawDataRepository.saveAll(listNfsRevAcqReportRawDatas);
			System.out.println("NFS nfsRevReportRawDataRepository CYCLE " + cycle);
			int updateStatus = commonDataValidationRepository.updateStatusByFileTypeOrFiledate(
					"NFS REV REPORT CYCLE "+cycle, fileDate, file.getOriginalFilename(), String.valueOf(count), "Y");
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

	private void setEntityValue(NfsAdjustmentRawData data, int index, String value) {
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
			// Optionally, round the number to a certain precision, for example to 2 decimal
			// places
			value = bd.setScale(2, RoundingMode.HALF_UP).toPlainString();
		}

		// Further processing if needed, for example rounding to 2 decimal places
		else if (value.indexOf('.') != -1) {
			// Ensure the value has a maximum of 2 decimal places
			BigDecimal bd = new BigDecimal(value);
			value = bd.setScale(2, RoundingMode.HALF_UP).toPlainString();
		}

		// If it's a very large number, you might want to truncate or round further
		if (value.length() > 15) {
			BigDecimal bd = new BigDecimal(value);
			value = bd.setScale(2, RoundingMode.HALF_UP).toPlainString();
		}

		return value;
	}

	private String getStringCell(Row row, int cellNum) {
		Cell cell = row.getCell(cellNum);
		if (cell == null)
			return "";
		return cell.getCellType() == CellType.STRING ? cell.getStringCellValue().replace("'", "").trim() : "";
	}

	private String getFormattedCellValue(Cell cell, FormulaEvaluator evaluator) {
		if (cell == null)
			return "";
		evaluator.evaluate(cell);
		DataFormatter dataFormatter = new DataFormatter();
		return dataFormatter.formatCellValue(cell, evaluator).replace("\u00A0", "").replace("'", "").trim();
	}

}
