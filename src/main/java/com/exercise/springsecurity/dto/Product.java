package com.exercise.springsecurity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private int productId;
    private String name;
    private int quantity;
    private float price;
}
