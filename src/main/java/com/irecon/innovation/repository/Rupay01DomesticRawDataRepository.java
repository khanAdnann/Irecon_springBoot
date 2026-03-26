package com.irecon.innovation.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.Rupay01DomesticRawData;


@Repository
public interface Rupay01DomesticRawDataRepository extends JpaRepository<Rupay01DomesticRawData, Long>{

	List<Rupay01DomesticRawData> findByFilenameAndFileDate(String filename, String fileDate);
}
