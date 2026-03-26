package com.irecon.innovation.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface MastercardAtmRawDataService {

	public boolean SaveMastercardAtmFile(MultipartFile file, String Date);
}
