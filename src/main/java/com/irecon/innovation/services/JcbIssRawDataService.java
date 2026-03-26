package com.irecon.innovation.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface JcbIssRawDataService {

	public  boolean SaveJcbIssRawData(MultipartFile file, String fileDate);
}
