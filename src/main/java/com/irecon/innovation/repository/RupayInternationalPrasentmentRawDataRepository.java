package com.irecon.innovation.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.Rupay01DomesticRawData;
import com.irecon.innovation.entity.RupayDomesticAdjustmentRawData;
import com.irecon.innovation.entity.RupayDomesticPresentmentRawData;
import com.irecon.innovation.entity.RupayInternationalAdjustmentRawData;
import com.irecon.innovation.entity.RupayInternationalPresentmentRawData;


@Repository
public interface RupayInternationalPrasentmentRawDataRepository extends JpaRepository<RupayInternationalPresentmentRawData, Long>{

	List<RupayInternationalPresentmentRawData> findByFilenameAndFileDate(String filename, String fileDate);
}
