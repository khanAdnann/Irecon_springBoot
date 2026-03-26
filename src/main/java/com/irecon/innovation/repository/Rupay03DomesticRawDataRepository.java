package com.irecon.innovation.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.Rupay03DomesticRawData;

@Repository
public interface Rupay03DomesticRawDataRepository extends JpaRepository<Rupay03DomesticRawData, Long>{

	List<Rupay03DomesticRawData> findByFilenameAndFileDate(String filename, String fileDate);
}
