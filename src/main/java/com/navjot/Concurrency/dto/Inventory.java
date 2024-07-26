package com.navjot.Concurrency.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory {

    private Integer productId;
    private String productName;
    private String productType;
    private int stockQty;
    private double pricePerUnit;
    private String location;

}

