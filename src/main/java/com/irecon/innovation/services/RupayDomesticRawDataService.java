package com.irecon.innovation.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface RupayDomesticRawDataService {

	public  boolean SaveRupayDomesticRawData(MultipartFile file, String fileDate);
	
	
	
	
}
