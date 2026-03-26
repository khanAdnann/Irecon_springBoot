package com.irecon.innovation.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.Rupay03InternationalRawData;

@Repository
public interface Rupay03InternationalRawDataRepository extends JpaRepository<Rupay03InternationalRawData, Long>{

	List<Rupay03InternationalRawData> findByFilenameAndFileDate(String filename, String fileDate);
}
