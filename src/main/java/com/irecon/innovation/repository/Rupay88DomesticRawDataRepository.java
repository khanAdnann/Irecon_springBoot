package com.irecon.innovation.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.irecon.innovation.entity.Rupay88DomesticRawData;

@Repository
public interface Rupay88DomesticRawDataRepository extends JpaRepository<Rupay88DomesticRawData, Long>{

	List<Rupay88DomesticRawData> findByFilenameAndFileDate(String filename, String fileDate);
}
