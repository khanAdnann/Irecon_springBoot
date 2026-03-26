package com.irecon.innovation.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface SwitchTLFRawDataService {

	public boolean SaveSwitchTlfFile(MultipartFile file, String Date);
}
