package com.irecon.innovation.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface IcdIssRawDataService {

	public  boolean SaveIcdIssRawData(MultipartFile file, String fileDate);
	
}
