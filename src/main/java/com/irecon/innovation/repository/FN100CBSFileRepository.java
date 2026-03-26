package com.irecon.innovation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.FN100CBSRawData;
import com.irecon.innovation.entity.SwitchTLFRawData;

@Repository
public interface FN100CBSFileRepository extends JpaRepository<FN100CBSRawData, Long> {
	List<FN100CBSRawData> findByFileNameAndFileDate(String fileName, String fileDate);
}