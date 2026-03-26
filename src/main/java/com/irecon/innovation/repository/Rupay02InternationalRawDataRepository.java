package com.irecon.innovation.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.Rupay02InternationalRawData;

@Repository
public interface Rupay02InternationalRawDataRepository extends JpaRepository<Rupay02InternationalRawData, Long>{

	List<Rupay02InternationalRawData> findByFilenameAndFileDate(String filename, String fileDate);
}
