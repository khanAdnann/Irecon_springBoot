package com.irecon.innovation.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.Rupay02DomesticRawData;

@Repository
public interface Rupay02DomesticRawDataRepository extends JpaRepository<Rupay02DomesticRawData, Long>{

	List<Rupay02DomesticRawData> findByFilenameAndFileDate(String filename, String fileDate);
}
