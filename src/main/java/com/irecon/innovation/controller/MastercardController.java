package com.irecon.innovation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MastercardController {
	
	@GetMapping("/mastercard_cross_recon")
	public String CrossReconProcess() {
		return "Mastercard_Cross_Recon_Process";
	}

	@GetMapping("/mastercard_settlement_upload")
	public String SettlementUpload() {
		return "Mastercard_Settlement_Upload";
	}
	
	@GetMapping("/mastercard_t057_file_upload")
	public String T057FileUpload() {
		return "Mastercard_T057_File_Upload";
	}
	
	@GetMapping("/mastercard_invoice_file_upload")
	public String InvoiveFileUpload() {
		return "Mastercard_Invoice_File_Upload";
	}
	
	@GetMapping("/mastercard_settlement_process")
	public String SettlementProcess() {
		return "Mastercard_Settlement_Process";
	}
}
