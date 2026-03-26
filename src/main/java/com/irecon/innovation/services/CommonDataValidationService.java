package com.irecon.innovation.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface CommonDataValidationService {

	public boolean SaveAllFileTypeValues(String Date);
	
	public boolean UpdateFileStatus(String fileType, String filedate, String filename, String count, String uploadStatus);


	public Map<String, String> getAcqReconSatusValues(String Date);

	public Map<String, String> getIssReconSatusValues(String Date);
	

}
