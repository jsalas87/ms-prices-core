package com.rfsc.prices.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@Entity
@Table(name="prices")
@AllArgsConstructor
@NoArgsConstructor
public class Price implements Serializable {

    @Id
    @GeneratedValue(strategy= javax.persistence.GenerationType.IDENTITY)
    Integer id;
    @Column(name="brand_id")
    Integer brandId;
    @Column(name="start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    Date startDate;
    @Column(name="end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    Date endDate;
    @Column(name="price_list")
    Integer priceList;
    @Column(name="product_id")
    Integer productId;
    @Column(name="priority")
    Integer priority;
    @Column(name="price")
    Double priceValue;
    String curr;
}
