package com.irecon.innovation.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.Qsparc05InternationalRawData;


@Repository
public interface Qsparc05InternationalRawDataRepository extends JpaRepository<Qsparc05InternationalRawData, Long>{

	List<Qsparc05InternationalRawData> findByFilenameAndFileDate(String filename, String fileDate);
}
