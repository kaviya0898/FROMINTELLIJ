package com.example.Inventory_Service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class ProductDetailsDto {

    private String productName;

    private double price;

    public ProductDetailsDto(String productName, double price) {
        this.productName=productName;
        this.price=price;
    }

    public ProductDetailsDto() {

    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
