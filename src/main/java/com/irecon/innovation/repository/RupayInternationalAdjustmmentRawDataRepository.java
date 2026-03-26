package com.irecon.innovation.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.Rupay01DomesticRawData;
import com.irecon.innovation.entity.RupayInternationalAdjustmentRawData;


@Repository
public interface RupayInternationalAdjustmmentRawDataRepository extends JpaRepository<RupayInternationalAdjustmentRawData, Long>{

	List<RupayInternationalAdjustmentRawData> findByFilenameAndFileDate(String filename, String fileDate);
}
