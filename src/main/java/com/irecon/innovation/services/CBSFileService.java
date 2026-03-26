package com.irecon.innovation.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface CBSFileService {
	public boolean findByFileNameAndFileDate(String fileName, String fileDate);

	public boolean SaveCBSFile(MultipartFile file, String Date);
}
