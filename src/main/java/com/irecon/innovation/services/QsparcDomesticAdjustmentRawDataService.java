package com.irecon.innovation.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface QsparcDomesticAdjustmentRawDataService {

	public boolean SaveQsparcDomesticAdjustmentRawData(MultipartFile file, String Date,String cycle);

}
