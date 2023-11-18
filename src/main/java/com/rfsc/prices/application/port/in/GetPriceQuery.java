package com.rfsc.prices.application.port.in;

import com.rfsc.prices.domain.Price;

import java.util.Date;

public interface GetPriceQuery {

    Price execute(Date appDate, Integer brandId, Integer productId);
}
