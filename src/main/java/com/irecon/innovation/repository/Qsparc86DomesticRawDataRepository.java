package com.irecon.innovation.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.Qsparc86DomesticRawData;



@Repository
public interface Qsparc86DomesticRawDataRepository extends JpaRepository<Qsparc86DomesticRawData, Long>{

	List<Qsparc86DomesticRawData> findByFilenameAndFileDate(String filename, String fileDate);
}
