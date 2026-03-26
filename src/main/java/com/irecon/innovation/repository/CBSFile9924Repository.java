package com.irecon.innovation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.CBSFile9924RawData;


@Repository
public interface CBSFile9924Repository extends JpaRepository<CBSFile9924RawData, Long> {
	List<CBSFile9924RawData> findByFileNameAndFileDate(String fileName, String fileDate);
}