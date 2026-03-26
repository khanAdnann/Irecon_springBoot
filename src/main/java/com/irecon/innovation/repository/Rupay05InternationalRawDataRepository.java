package com.irecon.innovation.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.Rupay05InternationalRawData;

@Repository
public interface Rupay05InternationalRawDataRepository extends JpaRepository<Rupay05InternationalRawData, Long>{

	List<Rupay05InternationalRawData> findByFilenameAndFileDate(String filename, String fileDate);
}
