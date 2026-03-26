package com.irecon.innovation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.IcdNtslRawData;
import com.irecon.innovation.entity.JcbNtslRawData;



@Repository
public interface IcdNtslRawDataRepository extends JpaRepository<IcdNtslRawData, Long> {
	List<IcdNtslRawData> findByFileNameAndFileDate(String fileName, String fileDate);
}