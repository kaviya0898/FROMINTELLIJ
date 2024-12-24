package com.example.Inventory_Service.service;

import com.example.Inventory_Service.dto.ProductDetailsDto;
import com.example.Inventory_Service.model.InventoryProducts;
import com.example.Inventory_Service.repository.InventoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RetriveAllProducts {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    ModelMapper modelMapper;


    public List<ProductDetailsDto> getAllProducts() {

        List<InventoryProducts> getAllProducts=inventoryRepository.findAll();

        return getAllProducts.stream().map(product->

        modelMapper.map(product, ProductDetailsDto.class))
                .collect(Collectors.toList());
    }
}
