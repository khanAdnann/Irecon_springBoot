package com.irecon.innovation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.irecon.innovation.entity.CbsFile9948RawData;


@Repository
public interface CBSFile9948Repository extends JpaRepository<CbsFile9948RawData, Long> {
	List<CbsFile9948RawData> findByFileNameAndFileDate(String fileName, String fileDate);
}