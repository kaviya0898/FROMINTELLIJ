package com.example.Inventory_Service.service;

import com.example.Inventory_Service.dto.ProductDetailsDto;
import com.example.Inventory_Service.exceptions.ProductNotFoundException;
import com.example.Inventory_Service.model.InventoryProducts;
import com.example.Inventory_Service.repository.InventoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CheckInventory {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    ModelMapper modelMapper;


    public ProductDetailsDto checkProductIsInStock(String productName) {

       return inventoryRepository.findByProductName(productName)
                .map(inventoryProducts -> new ProductDetailsDto(
                        inventoryProducts.getProductName(),
                        inventoryProducts.getPrice()

                )).orElseThrow(()->new ProductNotFoundException("Product unavailable"));

    }

    public ProductDetailsDto findProductDetails(String productName) {

       Optional<InventoryProducts> products=inventoryRepository.findByProductName(productName);

       return products.map(product->modelMapper.map(product, ProductDetailsDto.class))
               .orElse(null);
    }
}
