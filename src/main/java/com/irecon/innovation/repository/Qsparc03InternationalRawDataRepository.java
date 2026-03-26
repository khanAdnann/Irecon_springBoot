package com.irecon.innovation.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.Qsparc03InternationalRawData;


@Repository
public interface Qsparc03InternationalRawDataRepository extends JpaRepository<Qsparc03InternationalRawData, Long>{

	List<Qsparc03InternationalRawData> findByFilenameAndFileDate(String filename, String fileDate);
}
