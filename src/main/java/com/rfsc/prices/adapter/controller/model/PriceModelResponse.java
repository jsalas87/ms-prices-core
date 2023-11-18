package com.rfsc.prices.adapter.controller.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rfsc.prices.domain.Price;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PriceModelResponse {

    @JsonProperty("identificador_de_producto")
    Integer productId;
    @JsonProperty("identificador_de_cadena")
    Integer brandId;
    @JsonProperty("tarifa_a_aplicar")
    Integer priceList;
    @JsonProperty("fecha_inicio_aplicacion")
    @JsonFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    Date startDate;
    @JsonProperty("fecha_fin_aplicacion")
    @JsonFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    Date endDate;
    @JsonProperty("precio_final")
    Double priceValue;
    @JsonProperty("moneda")
    String curr;

    public static PriceModelResponse of(Price price) {
        return PriceModelResponse.builder()
                .brandId(price.getBrandId())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .priceList(price.getPriceList())
                .productId(price.getProductId())
                .priceValue(price.getPriceValue())
                .curr(price.getCurr())
                .build();
    }

}
