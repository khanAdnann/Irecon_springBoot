package com.irecon.innovation.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.irecon.innovation.entity.Qsparc01InternationalRawData;


@Repository
public interface Qsparc01InternationalRawDataRepository extends JpaRepository<Qsparc01InternationalRawData, Long>{

	List<Qsparc01InternationalRawData> findByFilenameAndFileDate(String filename, String fileDate);
}
