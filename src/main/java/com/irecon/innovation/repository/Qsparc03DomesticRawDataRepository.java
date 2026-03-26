package com.irecon.innovation.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.Qsparc03DomesticRawData;


@Repository
public interface Qsparc03DomesticRawDataRepository extends JpaRepository<Qsparc03DomesticRawData, Long>{

	List<Qsparc03DomesticRawData> findByFilenameAndFileDate(String filename, String fileDate);
}
