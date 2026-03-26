package com.irecon.innovation.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface QsparcInternationalRawDataService {

	public  boolean SaveQsparcInternationalRawData(MultipartFile file, String fileDate);
	
	
	
	
}
