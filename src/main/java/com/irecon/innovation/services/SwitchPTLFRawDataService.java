package com.irecon.innovation.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface SwitchPTLFRawDataService {

	public boolean SaveSwitchPtlfFile(MultipartFile file, String Date);
}
