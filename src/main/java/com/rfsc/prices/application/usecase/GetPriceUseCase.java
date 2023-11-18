package com.rfsc.prices.application.usecase;

import com.rfsc.prices.application.port.in.GetPriceQuery;
import com.rfsc.prices.application.port.out.PriceRepository;
import com.rfsc.prices.application.usecase.exception.NotFoundException;
import com.rfsc.prices.config.ErrorCode;
import com.rfsc.prices.domain.Price;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Date;

@Component
public class GetPriceUseCase implements GetPriceQuery {

    private final PriceRepository priceRepository;

    public GetPriceUseCase(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price execute(Date appDate, Integer brandId, Integer productId) {
        return priceRepository.findByValues(appDate, brandId, productId)
                .stream()
                .max(Comparator.comparing(Price::getPriority))
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUNT));
    }
}
