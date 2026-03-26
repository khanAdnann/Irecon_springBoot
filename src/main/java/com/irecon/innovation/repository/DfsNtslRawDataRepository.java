package com.irecon.innovation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.DfsNtslRawData;
import com.irecon.innovation.entity.JcbNtslRawData;



@Repository
public interface DfsNtslRawDataRepository extends JpaRepository<DfsNtslRawData, Long> {
	List<DfsNtslRawData> findByFileNameAndFileDate(String fileName, String fileDate);
}