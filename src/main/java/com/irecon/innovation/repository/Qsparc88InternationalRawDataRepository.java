package com.irecon.innovation.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.Qsparc88DomesticRawData;
import com.irecon.innovation.entity.Qsparc88InternationalRawData;


@Repository
public interface Qsparc88InternationalRawDataRepository extends JpaRepository<Qsparc88InternationalRawData, Long>{

	List<Qsparc88InternationalRawData> findByFilenameAndFileDate(String filename, String fileDate);
}
