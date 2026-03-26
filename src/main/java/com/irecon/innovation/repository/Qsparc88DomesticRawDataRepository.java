package com.irecon.innovation.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.Qsparc88DomesticRawData;


@Repository
public interface Qsparc88DomesticRawDataRepository extends JpaRepository<Qsparc88DomesticRawData, Long>{

	List<Qsparc88DomesticRawData> findByFilenameAndFileDate(String filename, String fileDate);
}
