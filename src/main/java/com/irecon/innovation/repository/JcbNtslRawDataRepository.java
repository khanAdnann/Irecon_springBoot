package com.irecon.innovation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import com.irecon.innovation.entity.JcbNtslRawData;



@Repository
public interface JcbNtslRawDataRepository extends JpaRepository<JcbNtslRawData, Long> {
	List<JcbNtslRawData> findByFileNameAndFileDate(String fileName, String fileDate);
}