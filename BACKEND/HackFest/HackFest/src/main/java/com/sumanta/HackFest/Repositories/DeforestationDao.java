package com.sumanta.HackFest.Repositories;

import com.sumanta.HackFest.Entities.DeforestationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeforestationDao extends JpaRepository<DeforestationData, Integer> {
    List<DeforestationData> findByGstNumberAndLocationIdAndStartYearBetweenAndEndYearBetween(String gstNumber, String locationId, int startYearAfter, int startYearBefore, int endYearAfter, int endYearBefore);
}
