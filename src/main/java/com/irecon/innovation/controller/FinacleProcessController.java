package com.irecon.innovation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FinacleProcessController {

	@GetMapping("/suspect_file_upload")
	public String SuspectFileUpload() {
		return "Suspect_File_Upload";
	}
	
	@GetMapping("/finacle_ttum")
	public String FinacleTTUM() {
		return "Finacle_TTM_Process";
	}
}
