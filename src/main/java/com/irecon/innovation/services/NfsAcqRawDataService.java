
package com.irecon.innovation.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public interface NfsAcqRawDataService {

	

	public boolean SaveNfsAcqRawData(MultipartFile file, String Date);


}
