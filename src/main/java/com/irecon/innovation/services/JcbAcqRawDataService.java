package com.irecon.innovation.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface JcbAcqRawDataService {
	
	public boolean SaveJcbAcqRawData(MultipartFile file, String Date);

}
