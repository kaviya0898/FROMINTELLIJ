package com.example.Inventory_Service.service;

import com.example.Inventory_Service.dto.OrderRequestDto;
import com.example.Inventory_Service.model.InventoryProducts;
import com.example.Inventory_Service.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateInventoryService {

    @Autowired
    InventoryRepository inventoryRepository;


    public void updateInventoryProducts(OrderRequestDto orderRequestDto) {

        InventoryProducts inventoryProducts=new InventoryProducts();
        inventoryProducts.setProductName(orderRequestDto.getProductName());
        inventoryProducts.setPrice(orderRequestDto.getPrice());
        inventoryProducts.setQuantity(orderRequestDto.getQuantity());
        inventoryRepository.save(inventoryProducts);
    }
}
