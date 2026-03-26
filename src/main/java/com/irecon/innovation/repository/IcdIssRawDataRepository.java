package com.irecon.innovation.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.IcdIssRawData;
import com.irecon.innovation.entity.NfsIssRawData;
@Repository
public interface IcdIssRawDataRepository extends JpaRepository<IcdIssRawData, Long>{
	List<IcdIssRawData> findByFilenameAndFileDate(String filename, String fileDate);

	 

}
