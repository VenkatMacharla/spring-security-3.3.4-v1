package com.mvn.jwt.api.service;

import com.mvn.jwt.api.dto.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {

    List<Product> productList = null;
    List<Product> productList1 = null;

    @PostConstruct
    public void loadProductsFromDB() {
        productList = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Product.builder()
                        .productId(i)
                        .name("product " + i)
                        .qty(new Random().nextInt(10))
                        .price(new Random().nextInt(5000)).build()
                ).collect(Collectors.toList());

        productList1 = IntStream.rangeClosed(1,50)
                .mapToObj(i-> Product.builder()
                        .productId(i)
                        .name("product "+i)
                        .qty(new Random().nextInt(10))
                        .price(new Random().nextInt(3000))
                        .build()).collect(Collectors.toList());
    }

    public List<Product> getProducts() {
        return productList1;
    }

    public Product getProduct(int id) {
        return productList.stream()
                .filter(product -> product.getProductId() == id)
                .findAny()
                .orElseThrow(() -> new RuntimeException("product " + id + " not found"));
    }

}
