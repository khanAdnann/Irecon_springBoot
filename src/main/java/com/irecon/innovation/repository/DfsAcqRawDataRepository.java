package com.irecon.innovation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.DfsAcqRawData;

@Repository
public interface DfsAcqRawDataRepository extends JpaRepository<DfsAcqRawData, Long>{

	List<DfsAcqRawData> findByFilenameAndFileDate(String filename, String fileDate);
}
