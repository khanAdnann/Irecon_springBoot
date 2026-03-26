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
import com.irecon.innovation.services.DfsAdjustmentRawDataService;
import com.irecon.innovation.services.DfsNtslRawDataService;
import com.irecon.innovation.services.JcbNtslRawDataService;

@Controller
public class DFSController {
	
	private static final Logger logger = LoggerFactory.getLogger(DFSController.class);

	@Autowired
	private CommonDataValidationRepository commonDataValidationRepository;


	@Autowired
	private DfsNtslRawDataService dfsNtslRawDataService;
	@Autowired
	private DfsAdjustmentRawDataService dfsAdjustmentRawDataService;
	
	
	@GetMapping("/dfsNtslUpload")
	public String NtslUpload() {
		return "DfsNtslUpload";
	}

	@GetMapping("/dfs_adjustment_upload")
	public String AdjustmentUpload() {
		return "Dfs_Adjustment_File_Upload";
	}

	@GetMapping("/dfs_settlement_process")
	public String SettlementProcess() {
		return "Dfs_Settlement_Process";
	}
	
	

	@PostMapping("/dfsNtslFilesUpload")
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
				
			
					result = dfsNtslRawDataService.SaveDfsNtslFile(file, fileDate);
				

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
	
	@PostMapping("/dfsAdustmentFilesUpload")
	public ResponseEntity<?> dfsAdustmentFilesUpload(@RequestParam("files") MultipartFile[] files,
			@RequestParam("fileDate") String fileDate) {
		logger.info("Post method dfsAdustmentFilesUpload  " + fileDate + " " );
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
				
			
					result =dfsAdjustmentRawDataService.SaveDfsAdjustmentFile(file, fileDate);
				

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
