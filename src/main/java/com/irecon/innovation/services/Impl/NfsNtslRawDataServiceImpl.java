package com.irecon.innovation.services.Impl;

import com.irecon.innovation.entity.NfsAcqRawData;
import com.irecon.innovation.entity.NfsNtslRawData;
import com.irecon.innovation.repository.CommonDataValidationRepository;
import com.irecon.innovation.repository.NfsNtslRawDataRepository;
import com.irecon.innovation.services.NfsNtslRawDataService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class NfsNtslRawDataServiceImpl implements NfsNtslRawDataService {
	private static final Logger logger = LoggerFactory.getLogger(NfsNtslRawDataServiceImpl.class);

	@Autowired
	private CommonDataValidationRepository commonDataValidationRepository;

	@Autowired
	private NfsNtslRawDataRepository nfsNtslRawDataRepository;

	public boolean SaveNfsNtslFile(MultipartFile file, String fileDate) {
		Map<String, Object> result = new HashMap<>();
		int totalCount = 0, count = 0;
		
		  Set<NfsNtslRawData> rawDataSet = new HashSet<>();
		try {
			String bankname = "" ,cycle="";
			String[] fileNames = file.getOriginalFilename().split("_");
			if (fileNames.length > 1) {
				 cycle = fileNames[1].substring(0, 1);
				//logger.info("Cycle is: " + cycle);
		
			}
			File tempFile = File.createTempFile("upload-", ".html");
			file.transferTo(tempFile);

			Document doc = Jsoup.parse(tempFile, "UTF-8");
			Elements rows = doc.select("tbody tr");

			for (Element row : rows) {
				Elements cells2 = row.select("th");
				Elements cells = row.select("td");

				if (cells2.text().contains("Daily Settlement")) {
					bankname = cells2.text();
					//System.out.println("bankname " + bankname);
				}
				//System.out.println("ss " + cells2.text());
				if (cells.size() >= 4) {
					count++;
					//System.out.println("cells.get(0).text() " + cells.get(0).text());
					NfsNtslRawData data = new NfsNtslRawData();
					data.setDescription(cells.get(0).text());
					data.setNoOfTxns(cells.get(1).text());
					data.setDebit(cells.get(2).text());
					data.setCredit(cells.get(3).text());
					data.setCycle(cycle);
					data.setFileDate(fileDate);
					data.setCreatedBy("1000");
					data.setCreatedDate(fileDate);
					data.setSrNo(++totalCount);
					data.setBankName(bankname); // TODO: extract from file
					data.setFileName(file.getOriginalFilename());
					rawDataSet.add(data);
					
				}
			}
			nfsNtslRawDataRepository.saveAll(rawDataSet);
			int updateStatus = commonDataValidationRepository.updateStatusByFileTypeOrFiledate("NFS NTSL CYCLE "+cycle,
					fileDate, file.getOriginalFilename(), String.valueOf(count), "Y");
			logger.info(updateStatus > 0 ? "Upload status updated." : "Unable to update status.");
			logger.info("Successfully uploaded file.");
			tempFile.delete();
		} catch (Exception e) {
			result.put("result", false);
			result.put("error", e.getMessage());
			return false;
		}

		return true;
	}
}
