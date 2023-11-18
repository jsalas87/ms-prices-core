package com.rfsc.prices.adapter.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureTestDatabase
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class PriceControllerAdapterTest {
    private static final String URL_GET_PRICE= "/api/v1.0/getprice";
    private static final String BRANDID_KEY = "brandId";
    private static final String PRODUCTID_KEY = "productId";
    private static final String APPDATE_KEY = "appDate";
    private static final Integer ONE = 1;
    private static final Integer PRODUCT = 35455;


    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Todas las dependencias estan inyectadas")
    void allDependenciesFulfilled() {
        Assertions.assertThat(mockMvc).isNotNull();
    }

    @Test
    @DisplayName("petición a las 10:00 del día 14 del producto 35455  para la brand 1 (ZARA), retorna producto con price 35.5")
    void whenGetCase1Success() throws Exception {

        successCases("2020-06-14-10.00.00", "/response/35_5.json");

    }

    @Test
    @DisplayName("petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA), retorna producto con price 25.45")
    void whenGetCase2Success() throws Exception {

        successCases("2020-06-14-16.00.00", "/response/25_45.json");

    }

    @Test
    @DisplayName("petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA), retorna producto con price 35.5")
    void whenGetCase3Success() throws Exception {

        successCases("2020-06-14-21.00.00", "/response/35_5.json");

    }

    @Test
    @DisplayName("petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA), retorna producto con price 30.50")
    void whenGetCase4Success() throws Exception {

        successCases("2020-06-15-10.00.00", "/response/30_5.json");

    }

    @Test
    @DisplayName("petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA), retorna producto con price 38.95")
    void whenGetCase5Success() throws Exception {

        successCases("2020-06-16-21.00.00", "/response/38_95.json");

    }

    @Test
    @DisplayName("petición a las 21:00 del día 13 del producto 35455   para la brand 1 (ZARA), retorna 404")
    void whenGetCase1Failed() throws Exception {

        failedCases(ONE, PRODUCT, "2020-06-13-21.00.00", status().is4xxClientError());

    }

    @Test
    @DisplayName("petición sin fecha del producto 35455 para la brand 1 (ZARA), retorna 400")
    void whenGetCase2Failed() throws Exception {

        this.mockMvc.perform(get(URL_GET_PRICE)
                        .header(BRANDID_KEY, ONE)
                        .header(PRODUCTID_KEY, PRODUCT)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }

    @Test
    @DisplayName("Si alguno de los campos es del tipo incorrecto, retorna 400")
    void whenGetCase3Failed() throws Exception {

        failedCases("ONE", PRODUCT, "2020-06-16-21.00.00", status().is4xxClientError());

    }

    @Test
    @DisplayName("Si la fecha esta en formato incorrecto, retorna 400")
    void whenGetCase4Failed() throws Exception {

        failedCases(ONE, PRODUCT, "2020-06-16", status().is4xxClientError());

    }

    private void successCases(String date, String file) throws Exception {

        var response = this.mockMvc.perform(get(URL_GET_PRICE)
                        .header(BRANDID_KEY, ONE)
                        .header(PRODUCTID_KEY, PRODUCT)
                        .header(APPDATE_KEY, date)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Resource resource = new ClassPathResource(file);
        assertThat(resource.getFile()).hasContent(response);

    }

    private void failedCases(Object brandId, Integer product, String appDate, ResultMatcher failedExpected) throws Exception {

        this.mockMvc.perform(get(URL_GET_PRICE)
                        .header(BRANDID_KEY, brandId)
                        .header(PRODUCTID_KEY, product)
                        .header(APPDATE_KEY, appDate)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(failedExpected);

    }
}
