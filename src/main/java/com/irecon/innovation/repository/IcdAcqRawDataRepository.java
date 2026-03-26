package com.irecon.innovation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.IcdAcqRawData;

@Repository
public interface IcdAcqRawDataRepository extends JpaRepository<IcdAcqRawData, Long>{
	
	List<IcdAcqRawData> findByFilenameAndFileDate(String filename, String fileDate);

}
