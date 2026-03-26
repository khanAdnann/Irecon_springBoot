package com.irecon.innovation.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface QsparcInternationalAdjustmentRawDataService {

	public boolean SaveQsparcInternationalAdjustmentRawData(MultipartFile file, String Date,String Cycle);

}
