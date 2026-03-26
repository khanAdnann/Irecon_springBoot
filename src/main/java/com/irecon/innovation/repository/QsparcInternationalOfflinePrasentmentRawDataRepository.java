package com.irecon.innovation.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.QsparcInternationalOfflinePresentmentRawData;
import com.irecon.innovation.entity.Rupay01DomesticRawData;
import com.irecon.innovation.entity.RupayDomesticAdjustmentRawData;
import com.irecon.innovation.entity.RupayDomesticPresentmentRawData;
import com.irecon.innovation.entity.RupayInternationalAdjustmentRawData;
import com.irecon.innovation.entity.RupayInternationalOfflinePresentmentRawData;
import com.irecon.innovation.entity.RupayInternationalPresentmentRawData;


@Repository
public interface QsparcInternationalOfflinePrasentmentRawDataRepository extends JpaRepository<QsparcInternationalOfflinePresentmentRawData, Long>{

	List<QsparcInternationalOfflinePresentmentRawData> findByFilenameAndFileDate(String filename, String fileDate);
}
