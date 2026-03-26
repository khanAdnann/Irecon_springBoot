package com.irecon.innovation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.irecon.innovation.entity.CbsAllRawData1;


@Repository
public interface CBSAllRawData1Repository extends JpaRepository<CbsAllRawData1, Long> {
	List<CbsAllRawData1> findByFileNameAndFileDate(String fileName, String fileDate);
}