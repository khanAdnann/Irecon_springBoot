package com.irecon.innovation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.SwitchTLFRawData;

@Repository
public interface SwitchTLFRawDataRepository extends JpaRepository<SwitchTLFRawData, Long> {
	List<SwitchTLFRawData> findByFileNameAndFileDate(String fileName, String fileDate);
}