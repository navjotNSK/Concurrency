package com.navjot.Concurrency.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JacksonXmlRootElement
public class Order {

    private int productId;
    private String name;
    private String productType;
    private int qty;
    private double price;
    private String trackingId;
}