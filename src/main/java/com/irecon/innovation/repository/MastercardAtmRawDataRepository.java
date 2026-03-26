package com.irecon.innovation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.MastercardAtmRawData;
import com.irecon.innovation.entity.SwitchTLFRawData;

@Repository
public interface MastercardAtmRawDataRepository extends JpaRepository<MastercardAtmRawData, Long> {
	List<MastercardAtmRawData> findByFileNameAndFileDate(String fileName, String fileDate);
}