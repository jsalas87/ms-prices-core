package com.rfsc.prices.application.port.out;

import com.rfsc.prices.domain.Price;

import java.util.Date;
import java.util.List;

public interface PriceRepository {

    List<Price> findByValues(Date appDate, Integer brandId, Integer productId);
}
