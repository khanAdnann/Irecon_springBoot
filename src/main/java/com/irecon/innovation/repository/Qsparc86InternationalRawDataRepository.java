package com.irecon.innovation.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.Qsparc86DomesticRawData;
import com.irecon.innovation.entity.Qsparc86InternationalRawData;



@Repository
public interface Qsparc86InternationalRawDataRepository extends JpaRepository<Qsparc86InternationalRawData, Long>{

	List<Qsparc86InternationalRawData> findByFilenameAndFileDate(String filename, String fileDate);
}
