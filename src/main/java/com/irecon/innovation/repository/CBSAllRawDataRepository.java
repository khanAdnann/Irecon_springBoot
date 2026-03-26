package com.irecon.innovation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.CbsAllRawData;


@Repository
public interface CBSAllRawDataRepository extends JpaRepository<CbsAllRawData, Long> {
	List<CbsAllRawData> findByFileNameAndFileDate(String fileName, String fileDate);
}