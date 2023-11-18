package com.rfsc.prices.application.usecase;

import com.rfsc.prices.application.port.out.PriceRepository;
import com.rfsc.prices.application.usecase.exception.NotFoundException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetPriceUseCaseTest {

    @Mock
    private  PriceRepository priceRepository;
    @InjectMocks
    private GetPriceUseCase getPriceUseCase;

    @Test
    @DisplayName("Dependencias inyectadas")
    void allDependenciesFulfilled() {
        assertThat(priceRepository).isNotNull();
        assertThat(getPriceUseCase).isNotNull();
    }

    @Test
    void executeTestSuccess() {
        var object = getObject("/response/30_5.json");
        when(priceRepository.findByValues(any(), any(), any())).thenReturn(List.of(object));
        var evt = getPriceUseCase.execute(getDate("2020-06-15-10.00.00"), 1, 35455);
        assertEquals(object, evt);
    }

    @Test
    void executeTestFailed() {
        when(priceRepository.findByValues(any(), any(), any())).thenReturn(List.of());
        assertThrows(NotFoundException.class, () -> getPriceUseCase.execute(getDate("2020-06-15-10.00.00"), 1, 35455));
    }

}
