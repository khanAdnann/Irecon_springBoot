package com.irecon.innovation.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.Qsparc05DomesticRawData;


@Repository
public interface Qsparc05DomesticRawDataRepository extends JpaRepository<Qsparc05DomesticRawData, Long>{

	List<Qsparc05DomesticRawData> findByFilenameAndFileDate(String filename, String fileDate);
}
