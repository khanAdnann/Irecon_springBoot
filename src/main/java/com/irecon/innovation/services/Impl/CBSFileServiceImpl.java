package com.irecon.innovation.services.Impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.irecon.innovation.entity.CBSFile9924RawData;
import com.irecon.innovation.entity.CbsAllRawData;
import com.irecon.innovation.entity.CbsAllRawData1;
import com.irecon.innovation.entity.CbsFile9948RawData;
import com.irecon.innovation.entity.CbsFile9949RawData;
import com.irecon.innovation.entity.Common_data_validation;
import com.irecon.innovation.entity.FN100CBSRawData;
import com.irecon.innovation.entity.SwitchTLFRawData;
import com.irecon.innovation.repository.CBSAllRawData1Repository;
import com.irecon.innovation.repository.CBSAllRawDataRepository;
import com.irecon.innovation.repository.CBSFile9924Repository;
import com.irecon.innovation.repository.CBSFile9948Repository;
import com.irecon.innovation.repository.CBSFile9949Repository;
import com.irecon.innovation.repository.CommonDataValidationRepository;
import com.irecon.innovation.repository.FN100CBSFileRepository;
import com.irecon.innovation.repository.SwitchTLFRawDataRepository;
import com.irecon.innovation.services.CBSFileService;
import com.irecon.innovation.services.SwitchTLFRawDataService;
import com.irecon.innovation.utility.ConnectionUtil;

@Service
public class CBSFileServiceImpl implements CBSFileService {
	private static final Logger logger = LoggerFactory.getLogger(CBSFileServiceImpl.class);

	@Autowired
	private FN100CBSFileRepository fn100cbsFileRepository;

	@Autowired
	private CBSFile9924Repository cbsFile9924Repository;

	@Autowired
	private CBSFile9948Repository cbsFile9948Repository;
	@Autowired
	private CBSFile9949Repository cbsFile9949Repository;
	@Autowired
	private CBSAllRawDataRepository cbsAllRawDataRepository;
	@Autowired
	private CBSAllRawData1Repository cbsAllRawData1Repository;

	@Autowired
	private CommonDataValidationRepository commonDataValidationRepository;

	@Override
	public boolean findByFileNameAndFileDate(String fileName, String fileDate) {
		// TODO Auto-generated method stub
		if (fileName.contains("FN100")) {
			List<FN100CBSRawData> existingFiles = fn100cbsFileRepository.findByFileNameAndFileDate(fileName, fileDate);
			if (!existingFiles.isEmpty()) {
				logger.info("File already exists with the same name and date: " + fileName);
				return false; // Return false to indicate the file already exists
			} else {
				return true;
			}

		} else {
			List<FN100CBSRawData> existingFiles = fn100cbsFileRepository.findByFileNameAndFileDate(fileName, fileDate);
			if (!existingFiles.isEmpty()) {
				logger.info("File already exists with the same name and date: " + fileName);
				return false; // Return false to indicate the file already exists
			} else {
				return true;
			}

		}

	}

	@Override
	public boolean SaveCBSFile(MultipartFile file, String fileDate) {

		logger.info("Save method CBS File " + file.getOriginalFilename() + " fileDate " + fileDate + "size "
				+ file.getSize());
		int batchNumber = 0, batchSize = 100000 ;
		int batchCount = 0;
		// Check if the file already exists with the same name and date

		BufferedReader reader = null;
		Long count = 0L;

		try {
			reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
			String thisLine, FileType = "";

			FileType = "";
			if (file.getOriginalFilename().contains("FN100")) {
				List<FN100CBSRawData> entityList = new ArrayList<>();

				while ((thisLine = reader.readLine()) != null) {
					if (!thisLine.toUpperCase().trim().contains("HDRCBS") && !thisLine.trim().contains("FTRCBS")) {
						count++;
						String[] splitData = thisLine.split("\\|", -1); // -1 to preserve empty strings

						if (splitData.length < 21) {
							System.err.println("Skipping malformed line " + count + ": " + thisLine);
							continue;
						}

						FN100CBSRawData entity = new FN100CBSRawData();
						entity.setAtmId(splitData[0]);
						entity.setRefNum(splitData[1]);
						entity.setCardNumber(splitData[2]);
						entity.setTranAmt(splitData[3]);
						entity.setTranDate(splitData[4]);
						entity.setRcreTime(splitData[5]);
						entity.setInitSolId(splitData[6]);
						entity.setValueDate(splitData[7]);
						entity.setPartTranType(splitData[8]);
						entity.setForacid(splitData[9]);
						entity.setBankId(splitData[10]);
						entity.setCummulativeBalance(splitData[11]);
						entity.setEntryUser(splitData[12]);
						entity.setTranParticular(splitData[13]);
						entity.setTranId(splitData[14]);
						entity.setContraForacid(splitData[15]);
						entity.setTranRemark(splitData[16]);
						entity.setPostedTime(splitData[17]);
						entity.setVerifiedTime(splitData[18]);
						entity.setTranPart1(splitData[19]);
						entity.setTranPart2(splitData[20]);

						entity.setFileDate(fileDate); // 22
						entity.setFileName(file.getOriginalFilename()); // 23
						entity.setCreatedDate(new Date()); // 24

						entityList.add(entity);

						// Optional: batch save if large file
						if (++batchCount % batchSize == 0) {
							batchNumber++;
							System.out.println("Batch Executed is " + batchNumber);
							fn100cbsFileRepository.saveAll(entityList);
							entityList.clear();
						}
					}
				}

				if (!entityList.isEmpty()) {
					fn100cbsFileRepository.saveAll(entityList);
				}

				int updateStatus = commonDataValidationRepository.updateStatusByFileTypeOrFiledate("CBS FN100",
						fileDate, file.getOriginalFilename(), String.valueOf(count), "Y");
				logger.info(updateStatus > 0 ? "Upload status updated." : "Unable to update status.");

			} else if (file.getOriginalFilename().contains("5165005139924")) {
				List<CBSFile9924RawData> entityList = new ArrayList<>();
				FileType = file.getOriginalFilename().substring(0, 13);
				while ((thisLine = reader.readLine()) != null) {
					if (!thisLine.toUpperCase().trim().contains("HDRCBS") && !thisLine.trim().contains("FTRCBS")) {
						count++;
						String[] splitData = thisLine.split("\\|", -1); // -1 to preserve empty strings

						if (splitData.length < 19) {
							System.err.println("Skipping malformed line " + count + ": " + thisLine);
							continue;
						}

						CBSFile9924RawData entity = new CBSFile9924RawData();
						entity.setTranParticular1(splitData[0]); // 1
						entity.setTranParticular2(splitData[1]); // 2
						entity.setTranParticular3(splitData[2]); // 3
						entity.setTranAmount(splitData[3]); // 4
						entity.setTranDate(splitData[4]); // 5
						entity.setRcreTime(splitData[5]); // 6
						entity.setInitSolId(splitData[6]); // 7
						entity.setValueDate(splitData[7]); // 8
						entity.setPartTranType(splitData[8]); // 9
						entity.setForacid(splitData[9]); // 10
						entity.setTranRemarks(splitData[10]); // 11
						entity.setCummulativeBalance(splitData[11]); // 12
						entity.setTranParticular(splitData[12]); // 13
						entity.setNarration(splitData[13]); // 14
						entity.setSolId(splitData[14]); // 15
						entity.setTranId(splitData[15]); // 16
						entity.setTranRemarks1(splitData[16]); // 17
						entity.setPostedTime(splitData[17]); // 18
						entity.setVerifiedTime(splitData[18]); // 19
						entity.setFileDate(fileDate); // 20
						entity.setFileName(file.getOriginalFilename()); // 21
						entity.setCreateddate(fileDate); // 22

						entityList.add(entity);

						// Optional: batch save if large file
						if (++batchCount % batchSize == 0) {
							batchNumber++;
							System.out.println("Batch Executed is " + batchNumber);
							cbsFile9924Repository.saveAll(entityList);
							entityList.clear();
						}
					}
				}

// Save any remaining data
				if (!entityList.isEmpty()) {
					cbsFile9924Repository.saveAll(entityList);
				}

				int updateStatus = commonDataValidationRepository.updateStatusByFileTypeOrFiledate("CBS " + FileType,
						fileDate, file.getOriginalFilename(), String.valueOf(count), "Y");
				logger.info(updateStatus > 0 ? "Upload status updated." : "Unable to update status.");
			} else if (file.getOriginalFilename().contains("5165005139949")
					|| file.getOriginalFilename().contains("5165005811352")
					|| file.getOriginalFilename().contains("5165005811351")
					|| file.getOriginalFilename().contains("5165005712451")) {
				List<CbsFile9949RawData> entityList = new ArrayList<>();
				FileType = file.getOriginalFilename().substring(0, 13);
				while ((thisLine = reader.readLine()) != null) {
					if (!thisLine.toUpperCase().trim().contains("HDRCBS") && !thisLine.trim().contains("FTRCBS")
							&& !thisLine.trim().contains("solid") && !thisLine.trim().contains("---------")
							&& thisLine.trim().length() != 0) {
						count++;
						CbsFile9949RawData entity = new CbsFile9949RawData();

						String[] splitData = thisLine.split("\\|", -1); // split line by pipe
						if (splitData.length < 19) {
							System.err.println("Skipping malformed line " + count + ": " + thisLine);
							continue;
						}
						entity.setTranParticular1(splitData[0]);
						entity.setSeqNum(splitData[1]);
						entity.setCardNo(splitData[2]);
						entity.setTranAmount(splitData[3]);
						entity.setValueDate(splitData[4]);
						entity.setRcreTime(splitData[5]);
						entity.setSolId(splitData[6]);
						entity.setTranDate(splitData[7]);
						entity.setPartTranType(splitData[8]);
						entity.setAtmGl(splitData[9]);
						entity.setTranRemarks(splitData[10]);
						entity.setCummulativeBalance(splitData[11]);
						entity.setTranParticular(splitData[12]);
						entity.setNarration(splitData[13]);
						entity.setTranId(splitData[14]);
						entity.setUid(splitData[15]);
						entity.setTranRemarks1(splitData[16]);
						entity.setPostedTime(splitData[17]);
						entity.setVerifiedTime(splitData[18]);

						// Special condition for specific file names
						if (file.getOriginalFilename().contains("5165005139949")) {
							entity.setApprovalCode("");
							entity.setReferenceNumber("");
						} else {
							// You can populate these from splitData[19], splitData[20] if present
							// Example (optional): entity.setApprovalCode(splitData[19]);
							// entity.setReferenceNumber(splitData[20]);
						}

						entity.setFileDate(fileDate);
						entity.setFileName(file.getOriginalFilename());
						entity.setCreateddate(fileDate);

						// Optional: batch save if large file
						if (++batchCount % batchSize == 0) {
							batchNumber++;
							System.out.println("Batch Executed is " + batchNumber);
							cbsFile9949Repository.saveAll(entityList);
							entityList.clear();
						}
					}
				}

				if (!entityList.isEmpty()) {
					cbsFile9949Repository.saveAll(entityList);
				}

				int updateStatus = commonDataValidationRepository.updateStatusByFileTypeOrFiledate("CBS " + FileType,
						fileDate, file.getOriginalFilename(), String.valueOf(count), "Y");
				logger.info(updateStatus > 0 ? "Upload status updated." : "Unable to update status.");

			} else if (file.getOriginalFilename().contains("5165005139948")
					|| file.getOriginalFilename().contains("5165005811354")
					|| file.getOriginalFilename().contains("5165005712453")) {
				FileType = file.getOriginalFilename().substring(0, 13);
				String InsertQuery9948 = "INSERT INTO  cbs_nfs_iss_rawdata (tran_date, narration,  dr_amount, cr_amount, GL_BALANCE,tran_date2, dummy, sys_man, tran_id, acc_number, narration2, FILEDATE,FILENAME,CREATEDDATE)          VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				ConnectionUtil cu = new ConnectionUtil();
				Connection con = cu.getconn();
				con.setAutoCommit(false);

				PreparedStatement ps9948 = con.prepareStatement(InsertQuery9948);

				int linecount = 0;
				while ((thisLine = reader.readLine()) != null) {
					linecount++;
					if (!thisLine.trim().contains("Balance B/F") && !thisLine.trim().contains("end of the report")
							&& !thisLine.trim().contains("-----------") && !thisLine.trim().contains("A/C")
							&& !thisLine.trim().contains("BO: Bombay")
							&& !thisLine.trim().contains("Punjab National Bank") && thisLine.trim().length() != 0)
						if (linecount == 100001 || linecount == 200001 || linecount == 300001 || linecount == 400001
								|| linecount == 500001 || linecount == 600001 || linecount == 700001
								|| linecount == 800001 || linecount == 900001 || linecount == 1000001
								|| linecount == 1100001 || linecount == 1200001 || linecount == 1300001
								|| linecount == 1400001 || linecount == 1500001 || linecount == 1600001
								|| linecount == 1700001 || linecount == 1800001 || linecount == 1900001
								|| linecount == 2000001) {
							count++;

							ps9948.setString(1, thisLine.substring(1, 11));
							ps9948.setString(2, thisLine.substring(12, 59));
							ps9948.setString(3, thisLine.substring(69, 96));
							ps9948.setString(4, thisLine.substring(96, 113));
							ps9948.setString(5, thisLine.substring(114, 143));
							ps9948.setString(6, thisLine.substring(160, 171));
							ps9948.setString(7, thisLine.substring(174, 197));
							ps9948.setString(8, thisLine.substring(197, 199));
							ps9948.setString(9, thisLine.substring(199, 208));
							ps9948.setString(10, thisLine.substring(208, 225));
							ps9948.setString(11, thisLine.substring(225, thisLine.trim().length()));
							ps9948.setString(12, fileDate);
							ps9948.setString(13, file.getOriginalFilename());
							ps9948.setString(14, fileDate);
							ps9948.addBatch();
						} else {
							count++;

							ps9948.setString(1, thisLine.substring(0, 10));
							ps9948.setString(2, thisLine.substring(11, 58));
							ps9948.setString(3, thisLine.substring(68, 95));
							ps9948.setString(4, thisLine.substring(95, 112));
							ps9948.setString(5, thisLine.substring(113, 142));
							ps9948.setString(6, thisLine.substring(159, 170));
							ps9948.setString(7, thisLine.substring(173, 196));
							ps9948.setString(8, thisLine.substring(196, 198));
							ps9948.setString(9, thisLine.substring(198, 207));
							ps9948.setString(10, thisLine.substring(207, 224));
							ps9948.setString(11, thisLine.substring(224, thisLine.trim().length()));
							ps9948.setString(12, fileDate);
							ps9948.setString(13, file.getOriginalFilename());
							ps9948.setString(14, fileDate);
							ps9948.addBatch();
						}
					if (++batchCount % batchSize == 0) {
						batchNumber++;
						System.out.println("Batch Executed is " + batchNumber);
						ps9948.executeBatch();
						con.commit();
					}
				}
				ps9948.executeBatch();
				int updateStatus = commonDataValidationRepository.updateStatusByFileTypeOrFiledate("CBS " + FileType,
						fileDate, file.getOriginalFilename(), String.valueOf(count), "Y");
				logger.info(updateStatus > 0 ? "Upload status updated." : "Unable to update status.");
				con.close();
			}

			else if (file.getOriginalFilename().contains("5165005139926")
					|| file.getOriginalFilename().contains("5165005811355")
					|| file.getOriginalFilename().contains("5165003182549")
					|| file.getOriginalFilename().contains("5165003182550")) {

				List<CbsAllRawData> entityList = new ArrayList<>();
				FileType = file.getOriginalFilename().substring(0, 13);
				while ((thisLine = reader.readLine()) != null) {
					if (file.getOriginalFilename().contains("5165005811355")) {

						if (thisLine.trim().length() != 11 && thisLine.trim().length() != 28
								&& !thisLine.toUpperCase().trim().contains("HDRCBS")
								&& !thisLine.trim().contains("FTRCBS")) {

							count++;
							String[] splitData = thisLine.split("\\|", -1); // -1 to preserve empty strings

							if (splitData.length < 17) {
								System.err.println("Skipping malformed line " + count + ": " + thisLine);
								continue;
							}
							CbsAllRawData entity = new CbsAllRawData();

							entity.setTranParticular(splitData[0]);
							entity.setTranAmount(splitData[1]);
							entity.setTranDate(splitData[2]);
							entity.setRcreTime(splitData[3]);
							entity.setInitSolId(splitData[4]);
							entity.setValueDate(splitData[5]);
							entity.setPartTranType(splitData[6]);
							entity.setForacid(splitData[7]);
							entity.setTranRemarks(splitData[8]);
							entity.setCummulativeBalance(splitData[9]);
							entity.setTranParticular1(splitData[10]);
							entity.setNarration(splitData[11]);
							entity.setSolId(splitData[12]);
							entity.setTranId(splitData[13]);
							entity.setTranRemarks1(splitData[14]);
							entity.setPostedTime(splitData[15]);
							entity.setApprovalCode(splitData[16]);

							entity.setFileDate(fileDate); // 20
							entity.setFileName(file.getOriginalFilename()); // 21
							entity.setCreateddate(fileDate); // 22

							// Add this entity to your list for batch saving, e.g.
							entityList.add(entity);

							// Optional: batch save if large file
							if (++batchCount % batchSize == 0) {
								batchNumber++;
								System.out.println("Batch Executed is " + batchNumber);
								cbsAllRawDataRepository.saveAll(entityList);
								entityList.clear();
							}

						}
					} else if (file.getOriginalFilename().contains("5165005811355")) {
						if (!thisLine.toUpperCase().trim().contains("HDRCBS") && !thisLine.trim().contains("FTRCBS")) {
							count++;
							String[] splitData = thisLine.split("\\|", -1); // -1 to preserve empty strings

							if (splitData.length < 17) {
								System.err.println("Skipping malformed line " + count + ": " + thisLine);
								continue;
							}
							CbsAllRawData entity = new CbsAllRawData();

							entity.setTranParticular(splitData[0]);
							entity.setTranAmount(splitData[1]);
							entity.setTranDate(splitData[2]);
							entity.setRcreTime(splitData[3]);
							entity.setInitSolId(splitData[4]);
							entity.setValueDate(splitData[5]);
							entity.setPartTranType(splitData[6]);
							entity.setForacid(splitData[7]);
							entity.setTranRemarks(splitData[8]);
							entity.setCummulativeBalance(splitData[9]);
							entity.setTranParticular1(splitData[10]);
							entity.setNarration(splitData[11]);
							entity.setSolId(splitData[12]);
							entity.setTranId(splitData[13]);
							entity.setTranRemarks1(splitData[14]);
							entity.setPostedTime(splitData[15]);
							entity.setApprovalCode(splitData[16]);

							entity.setFileDate(fileDate); // 20
							entity.setFileName(file.getOriginalFilename()); // 21
							entity.setCreateddate(fileDate); // 22

							// Add this entity to your list for batch saving, e.g.
							entityList.add(entity);

							// Optional: batch save if large file
							if (++batchCount % batchSize == 0) {
								batchNumber++;
								System.out.println("Batch Executed is " + batchNumber);
								cbsAllRawDataRepository.saveAll(entityList);
								entityList.clear();
							}
						}
					}
				}
// Save any remaining data
				if (!entityList.isEmpty()) {
					cbsAllRawDataRepository.saveAll(entityList);
				}

				int updateStatus = commonDataValidationRepository.updateStatusByFileTypeOrFiledate("CBS " + FileType,
						fileDate, file.getOriginalFilename(), String.valueOf(count), "Y");
				logger.info(updateStatus > 0 ? "Upload status updated." : "Unable to update status.");
			} else if (file.getOriginalFilename().contains("5165005139931")
					|| file.getOriginalFilename().contains("5165005139932")
					|| file.getOriginalFilename().contains("5165005139933")
					|| file.getOriginalFilename().contains("5165005139934")
					|| file.getOriginalFilename().contains("5165005139936")
					|| file.getOriginalFilename().contains("5165005139938")
					|| file.getOriginalFilename().contains("5165005139970")
					|| file.getOriginalFilename().contains("5165005139971")
					|| file.getOriginalFilename().contains("5165005139954")
					|| file.getOriginalFilename().contains("5165005811356")
					|| file.getOriginalFilename().contains("5165005139950")
					|| file.getOriginalFilename().contains("5165005139951")
					|| file.getOriginalFilename().contains("5165005139968")
					|| file.getOriginalFilename().contains("152281")) {

				List<CbsAllRawData1> entityList = new ArrayList<>();
				FileType = file.getOriginalFilename().substring(0, 13);
				while ((thisLine = reader.readLine()) != null) {

					if (!thisLine.toUpperCase().trim().contains("HDRCBS") && !thisLine.trim().contains("FTRCBS")
							&& !thisLine.trim().contains("solid") && !thisLine.trim().contains("FTRCBS")
							&& !thisLine.trim().contains("------") && thisLine.trim().length() != 46
							&& thisLine.trim().length() != 0 && thisLine.trim().length() != 1) {
						count++;

						String[] splitData = thisLine.split("\\|", -1);

						CbsAllRawData1 data = new CbsAllRawData1();

						int i = 0;
						data.setTranParticular1(splitData.length > i ? splitData[i++] : null);
						data.setSeqNum(splitData.length > i ? splitData[i++] : null);
						data.setCardNo(splitData.length > i ? splitData[i++] : null);
						data.setTranAmount(splitData.length > i ? splitData[i++] : null);
						data.setValueDate(splitData.length > i ? splitData[i++] : null);
						data.setRcreTime(splitData.length > i ? splitData[i++] : null);
						data.setSolId(splitData.length > i ? splitData[i++] : null);
						data.setTranDate(splitData.length > i ? splitData[i++] : null);
						data.setPartTranType(splitData.length > i ? splitData[i++] : null);
						data.setAtmGl(splitData.length > i ? splitData[i++] : null);
						data.setTranRemarks(splitData.length > i ? splitData[i++] : null);
						data.setCummulativeBalance(splitData.length > i ? splitData[i++] : null);
						data.setTranParticular(splitData.length > i ? splitData[i++] : null);
						data.setNarration(splitData.length > i ? splitData[i++] : null);
						data.setTranId(splitData.length > i ? splitData[i++] : null);
						data.setUid(splitData.length > i ? splitData[i++] : null);
						data.setTranRemarks1(splitData.length > i ? splitData[i++] : null);
						data.setPostedTime(splitData.length > i ? splitData[i++] : null);
						data.setVerifiedTime(splitData.length > i ? splitData[i++] : null);
						data.setApprovalCode(splitData.length > i ? splitData[i++] : null);
						data.setFlag(splitData.length > i ? splitData[i++] : null);

						// Set file info from setupBean
						data.setFileDate(fileDate); // 20
						data.setFileName(file.getOriginalFilename()); // 21
						data.setCreateddate(fileDate); // 22

						entityList.add(data);

						// Optional: batch save if large file
						if (++batchCount % batchSize == 0) {
							batchNumber++;
							System.out.println("Batch Executed is " + batchNumber);
							cbsAllRawData1Repository.saveAll(entityList);
							entityList.clear();
						}
					}

				}
// Save any remaining data
				if (!entityList.isEmpty()) {

					cbsAllRawData1Repository.saveAll(entityList);
					
				}
				int updateStatus = commonDataValidationRepository.updateStatusByFileTypeOrFiledate("CBS " + FileType,
						fileDate, file.getOriginalFilename(), String.valueOf(count), "Y");
				logger.info(updateStatus > 0 ? "Upload status updated." : "Unable to update status.");
			} else {
				logger.error("Invalid or Blank File !! ");
				return false;

			}

			logger.info("SuccessFully Uploaded!! ");
			return true;
		} catch (

		Exception e) {
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
