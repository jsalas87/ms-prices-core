package com.rfsc.prices.adapter.h2;

import com.rfsc.prices.application.port.out.PriceRepository;
import com.rfsc.prices.domain.Price;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class PriceH2Adapter implements PriceRepository {

    private final PriceH2Repository priceH2Repository;

    public PriceH2Adapter(PriceH2Repository priceH2Repository) {
        this.priceH2Repository = priceH2Repository;
    }

    @Override
    @SneakyThrows
    public List<Price> findByValues(Date appDate, Integer brandId, Integer productId) {
        return Optional.ofNullable(priceH2Repository.findByDateAndBrandAndProduct(appDate, brandId,productId))
                .orElse(List.of());

    }
}
