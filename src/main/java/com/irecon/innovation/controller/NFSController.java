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
import com.irecon.innovation.services.CBSFileService;
import com.irecon.innovation.services.NfsAdjustmentRawDataService;
import com.irecon.innovation.services.NfsNtslRawDataService;

@Controller
public class NFSController {
	private static final Logger logger = LoggerFactory.getLogger(NFSController.class);

	@Autowired
	private CommonDataValidationRepository commonDataValidationRepository;


	@Autowired
	private NfsNtslRawDataService nfsNtslRawDataService;
	
	@Autowired
	private NfsAdjustmentRawDataService nfsAdjustmentRawDataService;
	
	
	@GetMapping("/nfsNtslUpload")
	public String NtslUpload() {
		return "NfsNtslUpload";
	}

	@GetMapping("/nfsAdjustmentUpload")
	public String NfsAdjustmentUpload() {
		return "NfsAdjustmentUpload";
	}

	@GetMapping("/nfs_settlement_process")
	public String SettlementProcess() {
		return "Nfs_Settlement_Process";
	}

	@PostMapping("/nfsNtslFilesUpload")
	public ResponseEntity<?> ntslFilesUpload(@RequestParam("files") MultipartFile[] files,
			@RequestParam("fileDate") String fileDate) {
		logger.info("Post method ntslFilesUpload  " + fileDate + " " );
		List<String> resultData = new ArrayList<>();
		boolean allFilesUploadedSuccessfully = true;

		for (MultipartFile file : files) {
			try {
				boolean result = false;

				if (!commonDataValidationRepository.findByFilenameAndFileDate(file.getOriginalFilename(), fileDate)
						.isEmpty()) {
						resultData.add("File already exists: " + file.getOriginalFilename());
						allFilesUploadedSuccessfully = false;
						continue;
					}
				
			
					result = nfsNtslRawDataService.SaveNfsNtslFile(file, fileDate);
				

				if (!result) {
					allFilesUploadedSuccessfully = false;
					resultData.add("Failed to Upload " + " File: " + file.getOriginalFilename());
				} else {
					resultData.add("File Uploaded successfully: " + file.getOriginalFilename());
				}
			} catch (Exception e) {
				allFilesUploadedSuccessfully = false;
				resultData.add("Failed to upload: " + file.getOriginalFilename());
				
				logger.info("Failed to upload: " + fileDate + " ");
			}
		}

		if (!allFilesUploadedSuccessfully) {
			return new ResponseEntity<>(resultData, HttpStatus.CONFLICT);
		}

		return new ResponseEntity<>(resultData, HttpStatus.OK);
	}
	
	@PostMapping("/nfsAdustmentFilesUpload")
	public ResponseEntity<?> nfsAdustmentFilesUpload(@RequestParam("files") MultipartFile[] files,
			@RequestParam("fileDate") String fileDate) {
		logger.info("Post method ntslFilesUpload  " + fileDate + " " );
		List<String> resultData = new ArrayList<>();
		boolean allFilesUploadedSuccessfully = true;

		for (MultipartFile file : files) {
			try {
				boolean result = false;

				if (!commonDataValidationRepository.findByFilenameAndFileDate(file.getOriginalFilename(), fileDate)
						.isEmpty()) {
						resultData.add("File already exists: " + file.getOriginalFilename());
						allFilesUploadedSuccessfully = false;
						continue;
					}
				
			
					result = nfsAdjustmentRawDataService.SaveNfsAdjustmentFile(file, fileDate);
				

				if (!result) {
					allFilesUploadedSuccessfully = false;
					resultData.add("Failed to Upload " + " File: " + file.getOriginalFilename());
				} else {
					resultData.add("File Uploaded successfully: " + file.getOriginalFilename());
				}
			} catch (Exception e) {
				allFilesUploadedSuccessfully = false;
				resultData.add("Failed to upload: " + file.getOriginalFilename());
				
				logger.info("Failed to upload: " + fileDate + " ");
			}
		}

		if (!allFilesUploadedSuccessfully) {
			return new ResponseEntity<>(resultData, HttpStatus.CONFLICT);
		}

		return new ResponseEntity<>(resultData, HttpStatus.OK);
	}

}
