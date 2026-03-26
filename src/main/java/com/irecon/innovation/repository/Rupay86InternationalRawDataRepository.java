package com.irecon.innovation.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.Rupay86InternationalRawData;


@Repository
public interface Rupay86InternationalRawDataRepository extends JpaRepository<Rupay86InternationalRawData, Long>{

	List<Rupay86InternationalRawData> findByFilenameAndFileDate(String filename, String fileDate);
}
