package com.irecon.innovation.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface RupayInternationalRawDataService {

	public  boolean SaveRupayIntrnationalRawData(MultipartFile file, String fileDate);
	
	
	
	
}
