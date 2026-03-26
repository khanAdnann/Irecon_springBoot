
package com.irecon.innovation.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.NfsAcqRawData;

@Repository
public interface NfsAcqRawDataRepository extends JpaRepository<NfsAcqRawData, Long> {
	/* public int findByFileNameAndFileUplDate(String fileName, String date); */
	List<NfsAcqRawData> findByFilenameAndFileDate(String filename, String fileDate);

	boolean existsByFilenameAndFileDate(String filename, String fileDate);

}
