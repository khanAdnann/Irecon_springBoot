package com.irecon.innovation.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface DfsAcqRawDataService {

	public boolean SaveDfsAcqRawData(MultipartFile file, String Date);
}
