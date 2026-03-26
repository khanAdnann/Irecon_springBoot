package com.irecon.innovation.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import com.irecon.innovation.entity.SwitchPTLFRawData;


@Repository
public interface SwitchPTLFRawDataRepository extends JpaRepository<SwitchPTLFRawData, Long>{

	List<SwitchPTLFRawData> findByFilenameAndFileDate(String filename, String fileDate);
}
