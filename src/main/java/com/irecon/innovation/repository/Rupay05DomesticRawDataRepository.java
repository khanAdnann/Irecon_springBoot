package com.irecon.innovation.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.Rupay05DomesticRawData;

@Repository
public interface Rupay05DomesticRawDataRepository extends JpaRepository<Rupay05DomesticRawData, Long>{

	List<Rupay05DomesticRawData> findByFilenameAndFileDate(String filename, String fileDate);
}
