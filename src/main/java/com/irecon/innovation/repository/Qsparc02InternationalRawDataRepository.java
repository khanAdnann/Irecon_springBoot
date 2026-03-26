package com.irecon.innovation.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.Qsparc02InternationalRawData;


@Repository
public interface Qsparc02InternationalRawDataRepository extends JpaRepository<Qsparc02InternationalRawData, Long>{

	List<Qsparc02InternationalRawData> findByFilenameAndFileDate(String filename, String fileDate);
}
