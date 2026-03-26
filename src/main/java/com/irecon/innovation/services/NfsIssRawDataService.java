package com.irecon.innovation.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface NfsIssRawDataService {
	
   public  boolean SaveNfsIssRawData(MultipartFile file, String fileDate);
}