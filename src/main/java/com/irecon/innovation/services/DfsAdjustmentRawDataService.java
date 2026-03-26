package com.irecon.innovation.services;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface DfsAdjustmentRawDataService {

	 public boolean SaveDfsAdjustmentFile(MultipartFile file, String Date);
}
