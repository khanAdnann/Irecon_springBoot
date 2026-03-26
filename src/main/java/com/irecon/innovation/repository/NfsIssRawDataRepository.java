package com.irecon.innovation.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.NfsIssRawData;

@Repository
public interface NfsIssRawDataRepository extends JpaRepository<NfsIssRawData, Long> {
    List<NfsIssRawData> findByFilenameAndFileDate(String filename, String fileDate);
    boolean existsByFilenameAndFileDate(String filename, String fileDate);
}