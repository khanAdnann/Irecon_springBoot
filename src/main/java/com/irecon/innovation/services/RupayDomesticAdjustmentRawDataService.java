package com.irecon.innovation.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface RupayDomesticAdjustmentRawDataService {

	public boolean SaveRupayDomesticAdjustmentRawData(MultipartFile file, String Date,String cycle);

}
