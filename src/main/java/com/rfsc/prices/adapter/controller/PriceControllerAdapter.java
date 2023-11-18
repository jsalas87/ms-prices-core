package com.rfsc.prices.adapter.controller;

import com.rfsc.prices.adapter.controller.model.PriceModelResponse;
import com.rfsc.prices.application.port.in.GetPriceQuery;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/api/v1.0")
public class PriceControllerAdapter {

    private static final String GET_PRICE = "/getprice";

    private final GetPriceQuery getPriceQuery;

    public PriceControllerAdapter(GetPriceQuery getPriceQuery) {
        this.getPriceQuery = getPriceQuery;
    }

    @GetMapping(GET_PRICE)
    public PriceModelResponse getAvailableOffer(@RequestHeader Integer brandId,
                                                @RequestHeader Integer productId,
                                                @RequestHeader @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss") Date appDate) {
        var price = getPriceQuery.execute(appDate, brandId, productId);
        return PriceModelResponse.of(price);
    }
}
