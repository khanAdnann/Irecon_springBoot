package com.irecon.innovation.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.irecon.innovation.entity.Common_data_validation;
import com.irecon.innovation.repository.CommonDataValidationRepository;
import com.irecon.innovation.services.CommonDataValidationService;

@Controller
public class DashboardController {

	private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

	@Autowired
	private CommonDataValidationRepository commonDataValidationRepository;
	@Autowired
	private CommonDataValidationService commonDataValidationService;

	@GetMapping("/index")
	public String uploadFile() {
		logger.info("GET DashboardController ");
		return "index";
	}

	@GetMapping("/dashboard")
	public String showDashboardPage(Model model) {
		logger.info("GET DashboardController ");
		Map<String, String> acquirerReconStatus = new HashMap<>();
		Map<String, String> issuerReconStatus = new HashMap<>();
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String formattedDate = today.format(formatter);

		logger.info("Today's Date: " + formattedDate);
		commonDataValidationService.SaveAllFileTypeValues(formattedDate);

		List<Common_data_validation> uploadDtl = null;
		List<Common_data_validation> mappinDetails = null;
		uploadDtl = commonDataValidationRepository.findByFileDate(formattedDate);
		mappinDetails = commonDataValidationRepository.findMappingDataByFileDate(formattedDate);
		Set<String> uniqueFileTypes = uploadDtl.stream().map(Common_data_validation::getFileType)
				.filter(Objects::nonNull).collect(Collectors.toCollection(TreeSet::new));
		acquirerReconStatus = commonDataValidationService.getAcqReconSatusValues(formattedDate);
		issuerReconStatus = commonDataValidationService.getIssReconSatusValues(formattedDate);
		model.addAttribute("acquirerReconStatus", acquirerReconStatus);
		model.addAttribute("issuerReconStatus", issuerReconStatus);
		model.addAttribute("fileTypes", uniqueFileTypes);
		
		model.addAttribute("Date", formattedDate);
		model.addAttribute("uploadDtl", uploadDtl);
		model.addAttribute("mappinDetails", mappinDetails);

		return "Dashboard";
	}

	@GetMapping("/dashboardFilter")
	public String dashboardFilter(@RequestParam("fileDate") String fileDate, Model model) {
		logger.info("GET dashboardFilter ");
		Map<String, String> acquirerReconStatus = new HashMap<>();
		Map<String, String> issuerReconStatus = new HashMap<>();
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String formattedDate = today.format(formatter);
		fileDate = fileDate.replaceAll("-", "/");
		logger.info("Today's Date: " + fileDate);
		commonDataValidationService.SaveAllFileTypeValues(fileDate);

		List<Common_data_validation> uploadDtl = null;
		List<Common_data_validation> mappinDetails = null;
		
		uploadDtl = commonDataValidationRepository.findByFileDate(fileDate);
		mappinDetails = commonDataValidationRepository.findMappingDataByFileDate(fileDate);
		acquirerReconStatus = commonDataValidationService.getAcqReconSatusValues(fileDate);
		issuerReconStatus = commonDataValidationService.getIssReconSatusValues(fileDate);
		model.addAttribute("acquirerReconStatus", acquirerReconStatus);
		model.addAttribute("issuerReconStatus", issuerReconStatus);
		model.addAttribute("Date", fileDate);
		model.addAttribute("uploadDtl", uploadDtl);
		model.addAttribute("mappinDetails", mappinDetails);
		return "Dashboard";
	}

	@GetMapping("/dashboardFilter2")
	public String dashboardFilter2(@RequestParam("fileDate") String fileDate, Model model) {
		logger.info("GET dashboardFilter2 ");
		Map<String, String> acquirerReconStatus = new HashMap<>();
		Map<String, String> issuerReconStatus = new HashMap<>();
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String formattedDate = today.format(formatter);
		fileDate = fileDate.replaceAll("-", "/");
		logger.info("Today's Date: " + fileDate);
		commonDataValidationService.SaveAllFileTypeValues(fileDate);
		List<Common_data_validation> mappinDetails = null;

		List<Common_data_validation> uploadDtl = null;
		uploadDtl = commonDataValidationRepository.findByFileDate(fileDate);
		mappinDetails = commonDataValidationRepository.findMappingDataByFileDate(fileDate);
		acquirerReconStatus = commonDataValidationService.getAcqReconSatusValues(fileDate);
		issuerReconStatus = commonDataValidationService.getIssReconSatusValues(fileDate);
		model.addAttribute("acquirerReconStatus", acquirerReconStatus);
		model.addAttribute("issuerReconStatus", issuerReconStatus);
		model.addAttribute("Date", fileDate);
		model.addAttribute("mappinDetails", mappinDetails);
		model.addAttribute("uploadDtl", uploadDtl);

		return "Dashboard";
	}

	@GetMapping("/dashboardFilter3")
	public String dashboardFilter3(@RequestParam("fileDate") String fileDate, Model model) {
		logger.info("GET dashboardFilter3 ");
		Map<String, String> acquirerReconStatus = new HashMap<>();
		Map<String, String> issuerReconStatus = new HashMap<>();
		fileDate = fileDate.replaceAll("-", "/");
		logger.info("Date: " + fileDate);
		commonDataValidationService.SaveAllFileTypeValues(fileDate);
		List<Common_data_validation> mappinDetails = null;

		List<Common_data_validation> uploadDtl = null;
		List<Common_data_validation> reconDtl = null;
		uploadDtl = commonDataValidationRepository.findByFileDate(fileDate);
		mappinDetails = commonDataValidationRepository.findMappingDataByFileDate(fileDate);
		mappinDetails = commonDataValidationRepository.findMappingDataByFileDate(fileDate);
		acquirerReconStatus = commonDataValidationService.getAcqReconSatusValues(fileDate);
		issuerReconStatus = commonDataValidationService.getIssReconSatusValues(fileDate);
		model.addAttribute("acquirerReconStatus", acquirerReconStatus);
		model.addAttribute("issuerReconStatus", issuerReconStatus);
		model.addAttribute("Date", fileDate);
		model.addAttribute("mappinDetails", mappinDetails);
		model.addAttribute("reconDtl", reconDtl);
		model.addAttribute("uploadDtl", uploadDtl);

		return "Dashboard";
	}

	@GetMapping("/dashboardFileTypeFilter")
	public String dashboardFileTypeFilter(@RequestParam("fileType") String fileType,
			@RequestParam("fileDate") String fileDate, Model model) {
		logger.info("GET dashboardFileTypeFilter ");
		Map<String, String> acquirerReconStatus = new HashMap<>();
		Map<String, String> issuerReconStatus = new HashMap<>();

		List<Common_data_validation> uploadDtl = null;
		List<Common_data_validation> mappinDetails = null;
		uploadDtl = commonDataValidationRepository.findByFileTypeAndFileDate(fileType, fileDate);
		mappinDetails = commonDataValidationRepository.findMappingDataByFileDate(fileDate);
		acquirerReconStatus = commonDataValidationService.getAcqReconSatusValues(fileDate);
		issuerReconStatus = commonDataValidationService.getIssReconSatusValues(fileDate);
		model.addAttribute("acquirerReconStatus", acquirerReconStatus);
		model.addAttribute("issuerReconStatus", issuerReconStatus);
		model.addAttribute("Date", fileDate);
		model.addAttribute("mappinDetails", mappinDetails);
		model.addAttribute("uploadDtl", uploadDtl);

		return "Dashboard";
	}
}
