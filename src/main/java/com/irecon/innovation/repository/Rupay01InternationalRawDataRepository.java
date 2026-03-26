package com.irecon.innovation.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.Rupay01InternationalRawData;


@Repository
public interface Rupay01InternationalRawDataRepository extends JpaRepository<Rupay01InternationalRawData, Long>{

	List<Rupay01InternationalRawData> findByFilenameAndFileDate(String filename, String fileDate);
}
