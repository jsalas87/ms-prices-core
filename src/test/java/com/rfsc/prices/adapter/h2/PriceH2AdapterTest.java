package com.rfsc.prices.adapter.h2;

import com.rfsc.prices.config.Utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.rfsc.prices.config.Utils.getDate;
import static com.rfsc.prices.config.Utils.getObject;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceH2AdapterTest {

    @Mock
    private PriceH2Repository priceH2Repository;

    @InjectMocks
    private PriceH2Adapter priceH2Adapter;

    @Test
    @DisplayName("Dependencias inyectadas")
    void allDependenciesFulfilled() {
        assertThat(priceH2Repository).isNotNull();
        assertThat(priceH2Adapter).isNotNull();
    }

    @Test
    void executeTestSuccess() {
        var object = getObject("/response/30_5.json");
        when(priceH2Repository.findByDateAndBrandAndProduct(any(), any(), any())).thenReturn(List.of(object));
        var evt = priceH2Adapter.findByValues(getDate("2020-06-15-10.00.00"), 1, 35455);
        assertEquals(List.of(object), evt);
    }

    @Test
    void executeTestFailed() {
        when(priceH2Repository.findByDateAndBrandAndProduct(any(), any(), any())).thenReturn(null);
        var evt = priceH2Adapter.findByValues(getDate("2020-06-15-10.00.00"), 1, 35455);
        assertEquals(List.of(), evt);
    }
}
