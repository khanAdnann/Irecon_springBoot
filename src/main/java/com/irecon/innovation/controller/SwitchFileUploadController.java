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
import com.irecon.innovation.services.SwitchPTLFRawDataService;
import com.irecon.innovation.services.SwitchTLFRawDataService;
import com.irecon.innovation.services.Impl.SwitchTLFRawDataServiceImpl;

@Controller
public class SwitchFileUploadController {
	private static final Logger logger = LoggerFactory.getLogger(SwitchFileUploadController.class);



	@Autowired
	private SwitchTLFRawDataService servicSwitchTLFService;
	@Autowired
	private SwitchPTLFRawDataService servicSwitchPTLFService;

	@Autowired
	private CommonDataValidationRepository commonDataValidationRepository;

	@GetMapping("/switchFileUpload")
	public String SwitchFileUpload() {
		logger.info("GET method Switch  ");
		return "SwitchFileUpload";
	}

	@PostMapping("/switchFileUpload")
	public ResponseEntity<?> switchFileUpload(@RequestParam("files") MultipartFile[] files,
			@RequestParam("fileDate") String fileDate, @RequestParam("fileType") String fileType) {
		logger.info("Post method Switch TLF " + fileDate + " " + fileType);
		List<String> resultData = new ArrayList<>();
		boolean allFilesUploadedSuccessfully = true;

		for (MultipartFile file : files) {
			try {
				boolean result = false;
				if (!commonDataValidationRepository.findByFilenameAndFileDate(file.getOriginalFilename(), fileDate)
						.isEmpty()) {
					resultData.add("file already exists: " + file.getOriginalFilename());
					allFilesUploadedSuccessfully = false;
					continue;
				} else {

					if (fileType.equalsIgnoreCase("TLF")) {

					
						result = servicSwitchTLFService.SaveSwitchTlfFile(file, fileDate);

					} else {
						
						result = servicSwitchPTLFService.SaveSwitchPtlfFile(file, fileDate);
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
