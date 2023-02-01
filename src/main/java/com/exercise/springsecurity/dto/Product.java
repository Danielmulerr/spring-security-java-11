package com.exercise.springsecurity.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class Product {
    private int productId;
    @NotNull(message = "Name can't be null")
    private String name;
    @NotNull(message = "Quantity can't be null")
    private int quantity;
    @NotNull(message = "Price can't be null")
    private float price;
}
