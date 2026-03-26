package com.irecon.innovation.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface DfsIssRawDataService {

	public  boolean SaveDfsIssRawData(MultipartFile file, String fileDate);
}
