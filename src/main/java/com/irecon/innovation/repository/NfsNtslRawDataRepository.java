package com.irecon.innovation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.irecon.innovation.entity.CbsAllRawData1;
import com.irecon.innovation.entity.NfsNtslRawData;


@Repository
public interface NfsNtslRawDataRepository extends JpaRepository<NfsNtslRawData, Long> {
	List<NfsNtslRawData> findByFileNameAndFileDate(String fileName, String fileDate);
}