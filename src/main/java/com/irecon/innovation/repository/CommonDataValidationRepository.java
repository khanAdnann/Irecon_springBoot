package com.irecon.innovation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.Common_data_validation;

import jakarta.transaction.Transactional;

@Repository
public interface CommonDataValidationRepository extends JpaRepository<Common_data_validation, Long> {

	@Query(value = "SELECT * FROM common_data_validation WHERE LOWER(fileName) = LOWER(?1) AND LOWER(fileDate) = LOWER(?2)", nativeQuery = true)
	List<Common_data_validation> findByFilenameAndFileDate(String filename, String filedate);

	@Query(value = "SELECT * FROM common_data_validation WHERE  LOWER(file_type) = LOWER(?1) AND LOWER(fileDate) = LOWER(?2)", nativeQuery = true)
	List<Common_data_validation> findByFileTypeAndFileDate(String fileType, String filedate);
	@Query(value = "SELECT * FROM common_data_validation WHERE LOWER(fileDate) = LOWER(?1) and category='FILE ENTRY'", nativeQuery = true)
	List<Common_data_validation> findByFileDate(String filedate);
	@Query(value = "SELECT * FROM common_data_validation WHERE LOWER(fileDate) = LOWER(?1)  and file_type in( 'MAPPING1', 'MAPPING2')  ", nativeQuery = true)
	List<Common_data_validation> findMappingDataByFileDate(String filedate);
	@Query(value = "SELECT recon_status FROM common_data_validation WHERE LOWER(file_type) = LOWER(?1) and LOWER(fileDate) = LOWER(?2)  ", nativeQuery = true)
	String findReconStatusByFileDate(String fileType,String filedate);

	@Modifying
	@Transactional
	@Query(value = "UPDATE common_data_validation SET filename = ?3, count = ?4, upload_status = ?5 WHERE file_type = ?1 AND filedate = ?2", nativeQuery = true)
	int updateStatusByFileTypeOrFiledate(String fileType, String filedate, String filename, String count, String uploadStatus);

}
