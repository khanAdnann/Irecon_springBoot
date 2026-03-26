package com.irecon.innovation.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface QsparcDomesticRawDataService {

	public boolean SaveQsparcDomesticRawData(MultipartFile file, String fileDate);

}
