package com.irecon.innovation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.irecon.innovation.entity.CbsAllRawData1;
import com.irecon.innovation.entity.NfsAdjustmentRawData;
import com.irecon.innovation.entity.NfsNtslRawData;
import com.irecon.innovation.entity.NfsRevAcqReportRawData;


@Repository
public interface NfsRevReportRawDataRepository extends JpaRepository<NfsRevAcqReportRawData, Long> {
	List<NfsRevAcqReportRawData> findByFileNameAndFileDate(String fileName, String fileDate);
}