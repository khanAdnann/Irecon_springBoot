package com.irecon.innovation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.irecon.innovation.entity.CbsAllRawData1;
import com.irecon.innovation.entity.IcdAdjustmentRawData;
import com.irecon.innovation.entity.NfsAdjustmentRawData;
import com.irecon.innovation.entity.NfsNtslRawData;


@Repository
public interface IcdAdjustmentRawDataRepository extends JpaRepository<IcdAdjustmentRawData, Long> {
	List<IcdAdjustmentRawData> findByFileNameAndFileDate(String fileName, String fileDate);
}