package com.irecon.innovation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.DfsIssRawData;

@Repository
public interface DfsIssRawDataRepository extends JpaRepository<DfsIssRawData, Long>{

	List<DfsIssRawData> findByFilenameAndFileDate(String filename, String fileDate);
}
