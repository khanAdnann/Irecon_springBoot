package com.irecon.innovation.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface RupayDomesticPresentmentRawDataService {

	public boolean SaveRupayDomesticPresetmentRawData(MultipartFile file, String Date,String cycle);

}
