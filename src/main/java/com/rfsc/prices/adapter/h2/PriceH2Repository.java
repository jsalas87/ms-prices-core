package com.rfsc.prices.adapter.h2;

import com.rfsc.prices.domain.Price;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface PriceH2Repository extends CrudRepository<Price, Integer> {

    @Query("SELECT P FROM Price P WHERE ?1 BETWEEN P.startDate AND P.endDate AND P.brandId = ?2 AND P.productId = ?3")
    List<Price> findByDateAndBrandAndProduct(Date appDate, Integer brandId, Integer productId);
}
