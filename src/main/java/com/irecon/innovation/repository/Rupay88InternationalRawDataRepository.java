package com.irecon.innovation.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.irecon.innovation.entity.Rupay88DomesticRawData;
import com.irecon.innovation.entity.Rupay88InternationalRawData;

@Repository
public interface Rupay88InternationalRawDataRepository extends JpaRepository<Rupay88InternationalRawData, Long>{

	List<Rupay88InternationalRawData> findByFilenameAndFileDate(String filename, String fileDate);
}
