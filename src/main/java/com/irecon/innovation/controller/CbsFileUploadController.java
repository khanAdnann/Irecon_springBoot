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
import com.irecon.innovation.repository.SwitchTLFRawDataRepository;
import com.irecon.innovation.services.CBSFileService;

@Controller
public class CbsFileUploadController {
	private static final Logger logger = LoggerFactory.getLogger(SwitchFileUploadController.class);
	@Autowired
	private CommonDataValidationRepository commonDataValidationRepository;


	@Autowired
	private CBSFileService cbsFileService;
	
	
	
	@GetMapping("/cbsFilesUpload")
	public String CbsFileUpload() {
		logger.info("GET method CBS File Upoad " );
		return "CbsFileUpload";
	}
	

	@PostMapping("/cbsFilesUpload")
	public ResponseEntity<?> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,
			@RequestParam("fileDate") String fileDate) {
		logger.info("Post method CBS File Upload " + fileDate+" ");
		List<String> resultData = new ArrayList<>();
		boolean allFilesUploadedSuccessfully = true;

		for (MultipartFile file : files) {
			try {
				boolean result = false;

			
					// Default to acquirer if category/subcategory not matched
				
				
				if (!commonDataValidationRepository.findByFilenameAndFileDate(file.getOriginalFilename(), fileDate)
						.isEmpty()) {
						resultData.add("File already exists: " + file.getOriginalFilename());
						allFilesUploadedSuccessfully = false;
						continue;
					}
				
			
					result = cbsFileService.SaveCBSFile(file, fileDate);
				

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
