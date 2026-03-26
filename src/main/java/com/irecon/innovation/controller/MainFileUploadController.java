package com.irecon.innovation.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.irecon.innovation.entity.NfsAcqRawData;
import com.irecon.innovation.exception.FailedToLoadCSVFile;
import com.irecon.innovation.repository.CommonDataValidationRepository;
import com.irecon.innovation.repository.DfsAcqRawDataRepository;
import com.irecon.innovation.repository.DfsIssRawDataRepository;
import com.irecon.innovation.repository.IcdAcqRawDataRepository;
import com.irecon.innovation.repository.IcdIssRawDataRepository;
import com.irecon.innovation.repository.JcbAcqRawDataRepository;
import com.irecon.innovation.repository.JcbIssRawDataRepository;
import com.irecon.innovation.repository.NfsAcqRawDataRepository;
import com.irecon.innovation.repository.NfsIssRawDataRepository;
import com.irecon.innovation.services.CommonDataValidationService;
import com.irecon.innovation.services.DfsAcqRawDataService;
import com.irecon.innovation.services.DfsIssRawDataService;
import com.irecon.innovation.services.IcdAcqRawDataService;
import com.irecon.innovation.services.IcdIssRawDataService;
import com.irecon.innovation.services.JcbAcqRawDataService;
import com.irecon.innovation.services.JcbIssRawDataService;
import com.irecon.innovation.services.MastercardAtmRawDataService;
import com.irecon.innovation.services.NfsAcqRawDataService;
import com.irecon.innovation.services.NfsIssRawDataService;
import com.irecon.innovation.services.NfsRevReportRawDataService;
import com.irecon.innovation.services.QsparcDomesticRawDataService;
import com.irecon.innovation.services.QsparcInternationalRawDataService;
import com.irecon.innovation.services.RupayDomesticRawDataService;
import com.irecon.innovation.services.RupayInternationalRawDataService;
import com.irecon.innovation.services.Impl.JcbIssRawDataServiceImpl;
import com.irecon.innovation.utility.ReadMastercardPosUtil;
import com.irecon.innovation.utility.ReadVisaFileUtil;

@Controller
public class MainFileUploadController {
	private static final Logger logger = LoggerFactory.getLogger(MainFileUploadController.class);

	/*
	 * @Autowired private FileService fileService;
	 */
	@Autowired
	private CommonDataValidationRepository commonDataValidationRepository;

	@Autowired
	private CommonDataValidationService commonDataValidationService;

	@Autowired
	private NfsAcqRawDataService nfsAcqRawDataService;

	@Autowired
	private NfsIssRawDataService nfsIssRawDataService;
	@Autowired
	private NfsRevReportRawDataService nfsRevReportRawDataService;

	@Autowired
	private IcdAcqRawDataService icdAcqRawDataService;
	@Autowired
	private IcdIssRawDataService icdIssRawDataService;

	@Autowired
	private DfsAcqRawDataService dfsAcqRawDataService;

	@Autowired
	private DfsIssRawDataService dfsIssRawDataService;

	@Autowired
	private JcbAcqRawDataService jcbAcqRawDataService;

	@Autowired
	private JcbIssRawDataService jcbIssRawDataService;

	@Autowired
	private RupayDomesticRawDataService rupayDomesticRawDataService;

	@Autowired
	private RupayInternationalRawDataService rupayInternationalRawDataService;

	@Autowired
	private QsparcInternationalRawDataService qsparcInternationalRawDataService;

	@Autowired
	private QsparcDomesticRawDataService qsparcDomesticRawDataService;

	@Autowired
	private MastercardAtmRawDataService mastercardAtmRawDataService;

	@GetMapping("/mainFileUpload")
	public String mainFileUpload() {
		logger.info("GET METHOD mainFileUpload  "  );
		return "MainFileUpload";
	}

	@SuppressWarnings("static-access")
	@PostMapping("/mainFileUpload")
	public ResponseEntity<?> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,
			@RequestParam("fileDate") String fileDate, @RequestParam("category") String category,
			@RequestParam("subcategory") String subcategory) {
		logger.info("POST METHOD uploadMultipleFiles  "  );
		List<String> resultData = new ArrayList<>();
		boolean allFilesUploadedSuccessfully = true;

		for (MultipartFile file : files) {
			try {
				boolean result = false;

				if (!commonDataValidationRepository.findByFilenameAndFileDate(file.getOriginalFilename(), fileDate)
						.isEmpty()) {

					resultData.add("File already exists:" + file.getOriginalFilename());
					allFilesUploadedSuccessfully = false;
					continue;

				} else {

					if (category.equalsIgnoreCase("NFS") && subcategory.equalsIgnoreCase("ACQUIRER")) {
						result = nfsAcqRawDataService.SaveNfsAcqRawData(file, fileDate);

					} else if (category.equalsIgnoreCase("NFS") && subcategory.equalsIgnoreCase("ISSUER")) {

						result = nfsIssRawDataService.SaveNfsIssRawData(file, fileDate);

					} else if (category.equalsIgnoreCase("NFS") && subcategory.equalsIgnoreCase("REV_REPORT")) {

						result = nfsRevReportRawDataService.SaveNfsRevReportFile(file, fileDate);

					} else if (category.equalsIgnoreCase("ICD") && subcategory.equalsIgnoreCase("ACQUIRER")) {

						result = icdAcqRawDataService.SaveIcdAcqRawData(file, fileDate);
					} else if (category.equalsIgnoreCase("ICD") && subcategory.equalsIgnoreCase("ISSUER")) {

						result = icdIssRawDataService.SaveIcdIssRawData(file, fileDate);
					} else if (category.equalsIgnoreCase("DFS") && subcategory.equalsIgnoreCase("ACQUIRER")) {

						result = dfsAcqRawDataService.SaveDfsAcqRawData(file, fileDate);
					} else if (category.equalsIgnoreCase("DFS") && subcategory.equalsIgnoreCase("ISSUER")) {

						result = dfsIssRawDataService.SaveDfsIssRawData(file, fileDate);
					} else if (category.equalsIgnoreCase("JCB") && subcategory.equalsIgnoreCase("ACQUIRER")) {

						result = jcbAcqRawDataService.SaveJcbAcqRawData(file, fileDate);
					} else if (category.equalsIgnoreCase("JCB") && subcategory.equalsIgnoreCase("ISSUER")) {

						result = jcbIssRawDataService.SaveJcbIssRawData(file, fileDate);
					} else if (category.equalsIgnoreCase("RUPAY") && subcategory.equalsIgnoreCase("DOMESTIC")) {

						result = rupayDomesticRawDataService.SaveRupayDomesticRawData(file, fileDate);

					} else if (category.equalsIgnoreCase("RUPAY") && subcategory.equalsIgnoreCase("INTERNATIONAL")) {
						result = rupayInternationalRawDataService.SaveRupayIntrnationalRawData(file, fileDate);
					} else if (category.equalsIgnoreCase("QSPARC") && subcategory.equalsIgnoreCase("DOMESTIC")) {
						result = qsparcDomesticRawDataService.SaveQsparcDomesticRawData(file, fileDate);

					} else if (category.equalsIgnoreCase("QSPARC") && subcategory.equalsIgnoreCase("INTERNATIONAL")) {
						result = qsparcInternationalRawDataService.SaveQsparcInternationalRawData(file, fileDate);
					} else if (category.equalsIgnoreCase("MASTERCARD") && subcategory.equalsIgnoreCase("ATM")) {
						result = mastercardAtmRawDataService.SaveMastercardAtmFile(file, fileDate);
					} else if (category.equalsIgnoreCase("MASTERCARD") && subcategory.equalsIgnoreCase("POS")) {

						ReadMastercardPosUtil readmas_pos = new ReadMastercardPosUtil();
						result = readmas_pos.read_method(fileDate, file);

					} else if (category.equalsIgnoreCase("VISA") && subcategory.equalsIgnoreCase("ACQUIRER")
							|| subcategory.equalsIgnoreCase("ISSUER")) {

						ReadVisaFileUtil readRupay = new ReadVisaFileUtil();
						HashMap<String, Object> output = readRupay.readData(fileDate, subcategory, file);
						if (((Boolean) output.get("result")).booleanValue()) {
							System.out.println(" " + output.get("count").toString());
							if (subcategory.equalsIgnoreCase("ACQUIRER")) {
								result = commonDataValidationService.UpdateFileStatus("VISA ACQUIRER", fileDate,
										file.getOriginalFilename(), output.get("count").toString(), "Y");

							} else {
								result = commonDataValidationService.UpdateFileStatus("VISA ISSUER", fileDate,
										file.getOriginalFilename(), output.get("count").toString(), "Y");

							}

						}

					} else {

						result = nfsAcqRawDataService.SaveNfsAcqRawData(file, fileDate);
					}

				}

				if (!result) {
					allFilesUploadedSuccessfully = false;
					resultData.add("Failed to Upload " + " File: " + file.getOriginalFilename());
				} else {
					resultData.add("File Uploaded successfully: " + file.getOriginalFilename());
				}
			} catch (Exception e) {
				allFilesUploadedSuccessfully = false;
				resultData.add("Failed to upload: " + file.getOriginalFilename());
			}
		}

		if (!allFilesUploadedSuccessfully) {
			return new ResponseEntity<>(resultData, HttpStatus.CONFLICT);
		}

		return new ResponseEntity<>(resultData, HttpStatus.OK);
	}

}
