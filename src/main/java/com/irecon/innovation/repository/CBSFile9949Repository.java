package com.irecon.innovation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.irecon.innovation.entity.CbsFile9949RawData;


@Repository
public interface CBSFile9949Repository extends JpaRepository<CbsFile9949RawData, Long> {
	List<CbsFile9949RawData> findByFileNameAndFileDate(String fileName, String fileDate);
}