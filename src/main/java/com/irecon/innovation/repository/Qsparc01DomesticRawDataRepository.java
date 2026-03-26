package com.irecon.innovation.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.Qsparc01DomesticRawData;


@Repository
public interface Qsparc01DomesticRawDataRepository extends JpaRepository<Qsparc01DomesticRawData, Long>{

	List<Qsparc01DomesticRawData> findByFilenameAndFileDate(String filename, String fileDate);
}
