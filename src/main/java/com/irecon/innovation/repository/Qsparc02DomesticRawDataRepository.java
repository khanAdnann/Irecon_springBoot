package com.irecon.innovation.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.Qsparc02DomesticRawData;

@Repository
public interface Qsparc02DomesticRawDataRepository extends JpaRepository<Qsparc02DomesticRawData, Long>{

	List<Qsparc02DomesticRawData> findByFilenameAndFileDate(String filename, String fileDate);
}
