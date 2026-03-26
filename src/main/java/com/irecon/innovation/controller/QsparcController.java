package com.irecon.innovation.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.irecon.innovation.repository.CommonDataValidationRepository;
import com.irecon.innovation.services.QsparcDomesticAdjustmentRawDataService;
import com.irecon.innovation.services.QsparcDomesticPresentmentRawDataService;
import com.irecon.innovation.services.QsparcInternationalAdjustmentRawDataService;
import com.irecon.innovation.services.QsparcInternationalPresentmentRawDataService;
import com.irecon.innovation.services.RupayDomesticAdjustmentRawDataService;
import com.irecon.innovation.services.RupayDomesticPresentmentRawDataService;
import com.irecon.innovation.services.RupayInternationalAdjustmentRawDataService;
import com.irecon.innovation.services.RupayInternationalPresentmentRawDataService;

@Controller
public class QsparcController {
	private static final Logger logger = LoggerFactory.getLogger(QsparcController.class);

	@Autowired
	private CommonDataValidationRepository commonDataValidationRepository;

	@Autowired
	private QsparcDomesticAdjustmentRawDataService domesticAdjustmentRawDataService;
	@Autowired
	private QsparcDomesticPresentmentRawDataService domesticPrsentmentRawDataService ;
	@Autowired
	private QsparcInternationalPresentmentRawDataService internationalPrasentmentRawDataService ;


	@Autowired
	private QsparcInternationalAdjustmentRawDataService InternationalAdjustmentRawDataService;

	@GetMapping("/qsparcPresentmentFileUpload")
	public String PresentmentFileUpload() {
		logger.info("GET METHOD PresentmentFileUpload  "  );
		return "QsparcPresentmentUpload";
	}
	
	@GetMapping("/qsparcAdjustmentUpload")
	public String AdjustmentFileUpload() {
		logger.info("GET METHOD AdjustmentFileUpload  "  );
		return "QsparcAdjustmentUpload";
	}
	
	@GetMapping("/qpsarc_settlement_file_upload")
	public String SettlementFileUpload() {
		logger.info("GET METHOD SettlementFileUpload  "  );
		return "QSPARC_Settlement_File_Upload";
	}
	
	@GetMapping("/qsparc_settlement_process")
	public String SettlementProcess() {
		
		logger.info("GET METHOD SettlementProcess  "  );
		return "QSPARC_Settlement_Process";
	}

	
	
	@PostMapping("/qsparcAdjustmentUpload")
	public ResponseEntity<?> qsparcAdjustmentUpload(@RequestParam MultipartFile[] files, @RequestParam String fileDate,
			@RequestParam String cycle, @RequestParam String category) {
		logger.info("Post method qsparcAdjustmentUpload  " + fileDate + " " + cycle + " " + category);
		List<String> resultData = new ArrayList<>();
		boolean allFilesUploadedSuccessfully = true;

		for (MultipartFile file : files) {
			try {

				boolean fileExists = !commonDataValidationRepository
						.findByFilenameAndFileDate(file.getOriginalFilename(), fileDate).isEmpty();
				if (fileExists) {
					resultData.add("File already exists: " + file.getOriginalFilename());
					allFilesUploadedSuccessfully = false;
					continue;
				}
				boolean result = category.equalsIgnoreCase("Domestic")
						? domesticAdjustmentRawDataService.SaveQsparcDomesticAdjustmentRawData(file, fileDate, cycle)
						: InternationalAdjustmentRawDataService.SaveQsparcInternationalAdjustmentRawData(file,
								fileDate, cycle);

				allFilesUploadedSuccessfully = !result ? false : allFilesUploadedSuccessfully;
				resultData.add(!result ? "Failed to Upload File: " + file.getOriginalFilename()
						: "File Uploaded successfully: " + file.getOriginalFilename());
			} catch (Exception e) {
				allFilesUploadedSuccessfully = false;
				resultData.add("Failed to upload: " + file.getOriginalFilename());

				logger.info("Failed to upload: " + fileDate + " "+ file.getOriginalFilename());
			}
		}
		return new ResponseEntity<>(resultData, allFilesUploadedSuccessfully ? HttpStatus.OK : HttpStatus.CONFLICT);

	}
	
	
	

	
	@PostMapping("/qsparcPresentmentFileUpload")
	public ResponseEntity<?> qsparcPresentmentFileUpload(@RequestParam MultipartFile[] files, @RequestParam String fileDate,
			@RequestParam String cycle, @RequestParam String category) {
		logger.info("Post method qsparcPresentmentFileUpload  " + fileDate + " " + cycle + " " + category);
		List<String> resultData = new ArrayList<>();
		boolean allFilesUploadedSuccessfully = true;

		for (MultipartFile file : files) {
			try {

				boolean fileExists = !commonDataValidationRepository
						.findByFilenameAndFileDate(file.getOriginalFilename(), fileDate).isEmpty();
				if (fileExists) {
					resultData.add("File already exists: " + file.getOriginalFilename());
					allFilesUploadedSuccessfully = false;
					continue;
				}
				
				
				boolean result = category.equalsIgnoreCase("Domestic")
						? domesticPrsentmentRawDataService.SaveQsparcDomesticPresetmentRawData(file, fileDate, cycle)
						: internationalPrasentmentRawDataService.SaveQsparcInernationalPresetmentRawData(file,
								fileDate, cycle);

				
				allFilesUploadedSuccessfully = !result ? false : allFilesUploadedSuccessfully;
				
				resultData.add(!result ? "Failed to Upload File: " + file.getOriginalFilename()
						: "File Uploaded successfully: " + file.getOriginalFilename());
			} catch (Exception e) {
				allFilesUploadedSuccessfully = false;
				resultData.add("Failed to upload: " + file.getOriginalFilename());

				logger.info("Failed to upload: " + fileDate + " ");
			}
		}
		return new ResponseEntity<>(resultData, allFilesUploadedSuccessfully ? HttpStatus.OK : HttpStatus.CONFLICT);

	}

	
	
	
}
