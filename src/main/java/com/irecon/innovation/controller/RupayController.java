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
import com.irecon.innovation.repository.RupayInternationalAdjustmmentRawDataRepository;
import com.irecon.innovation.services.CommonDataValidationService;
import com.irecon.innovation.services.RupayDomesticAdjustmentRawDataService;
import com.irecon.innovation.services.RupayDomesticPresentmentRawDataService;
import com.irecon.innovation.services.RupayInternationalAdjustmentRawDataService;
import com.irecon.innovation.services.RupayInternationalPresentmentRawDataService;
import com.irecon.innovation.services.RupayInternationalRawDataService;

@Controller
public class RupayController {
	private static final Logger logger = LoggerFactory.getLogger(RupayController.class);

	@Autowired
	private CommonDataValidationRepository commonDataValidationRepository;


	@Autowired
	private RupayDomesticAdjustmentRawDataService domesticAdjustmentRawDataService;
	@Autowired
	private RupayDomesticPresentmentRawDataService domesticPrsentmentRawDataService ;
	@Autowired
	private RupayInternationalPresentmentRawDataService internationalPrasentmentRawDataService ;
	@Autowired
	private RupayInternationalAdjustmentRawDataService rupayInternationalAdjustmentRawDataService;

	@GetMapping("/rupay_settlement_process")
	public String SettlementProcess() {
		logger.info("GET METHOD SettlementProcess  "  );
		return "Rupay_Settlement_Process";
	}

	@GetMapping("/rupayPresentmentFileUpload")
	public String PresentmentFile() {
		logger.info("GET METHOD PresentmentFile  "  );
		return "RupayPresentmentUpload";
	}

	@GetMapping("/rupayAdjustmentUpload")
	public String AdjustmentFile() {
		logger.info("GET METHOD AdjustmentFile  "  );
		return "RupayAdjustmentUpload";
	}

	@GetMapping("/rupay_settlement_file_upload")
	public String SettlementFile() {
		logger.info("GET METHOD SettlementFile  "  );
		return "Rupay_Settlement_Upload";
	}

	@PostMapping("/rupayAdjustmentUpload")
	public ResponseEntity<?> rupayAdjustmentUpload(@RequestParam MultipartFile[] files, @RequestParam String fileDate,
			@RequestParam String cycle, @RequestParam String category) {
		logger.info("Post method rupayAdjustmentUpload  " + fileDate + " " + cycle + " " + category);
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
						? domesticAdjustmentRawDataService.SaveRupayDomesticAdjustmentRawData(file, fileDate, cycle)
						: rupayInternationalAdjustmentRawDataService.SaveRupayInternationalAdjustmentRawData(file,
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

	
	@PostMapping("/rupayPresentmentFileUpload")
	public ResponseEntity<?> rupayPresentmentFileUpload(@RequestParam MultipartFile[] files, @RequestParam String fileDate,
			@RequestParam String cycle, @RequestParam String category) {
		logger.info("Post method rupayPresentmentFileUpload  " + fileDate + " " + cycle + " " + category);
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
						? domesticPrsentmentRawDataService.SaveRupayDomesticPresetmentRawData(file, fileDate, cycle)
						: internationalPrasentmentRawDataService.SaveRupayInernationalPresetmentRawData(file,
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
