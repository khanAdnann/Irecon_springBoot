package com.irecon.innovation.services;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface IcdNtslRawDataService {

	 public boolean SaveJcbNtslFile(MultipartFile file, String Date);
}
