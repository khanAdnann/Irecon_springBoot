package com.irecon.innovation.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.irecon.innovation.entity.Rupay86DomesticRawData;


@Repository
public interface Rupay86DomesticRawDataRepository extends JpaRepository<Rupay86DomesticRawData, Long>{

	List<Rupay86DomesticRawData> findByFilenameAndFileDate(String filename, String fileDate);
}
