package com.exercise.springsecurity.services;

import com.exercise.springsecurity.dto.Product;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {
    private List<Product> productList = null;

    @PostConstruct
    public void ProductService() {
        productList = IntStream.rangeClosed(1, 1000)
                .mapToObj(num -> Product.builder()
                        .productId(num)
                        .name("product " + num)
                        .quantity(new Random().nextInt(1000))
                        .price(new Random().nextFloat())
                        .build())
                .collect(Collectors.toList());
    }

    public List<Product> getAll() {
        return productList;
    }

    public Product getProduct(int productId) {
        return productList.stream().filter(product -> product.getProductId() == productId)
                .findAny()
                .orElseThrow(() -> new RuntimeException("Unable to find a product with id: " + productId));
    }
}
