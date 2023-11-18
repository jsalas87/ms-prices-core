package com.rfsc.prices.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.rfsc.prices.domain.Price;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static  Price getObject(String file) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            return  mapper.readValue(new File(file), Price.class);
        } catch (IOException e) {
            return Price.builder().build();
        }
    }

    public static  Date getDate(String date) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
        try {
            return formato.parse(date);
        } catch (ParseException e) {
            return new Date();
        }
    }
}
