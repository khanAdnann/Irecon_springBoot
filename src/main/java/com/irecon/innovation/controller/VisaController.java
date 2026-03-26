package com.irecon.innovation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VisaController {

	@GetMapping("/ep_file_upload")
	public String EPFileUpload() {
		return "Visa_EP_File_Upload";
	}
	
	@GetMapping("/settlement_process")
	public String SettlementProcess() {
		return "Visa_Settlement_Process";
	}
	
	@GetMapping("/update_dollar_rate")
	public String UpdateDollarRate() {
		return "Visa_Update_Dollar";
	}
	
	
	@GetMapping("/adjustment_upload")
	public String AdjustmentUpload() {
		return "Visa_Adjustment_Upload";
	}
}
