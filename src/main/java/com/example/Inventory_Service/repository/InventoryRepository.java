package com.example.Inventory_Service.repository;

import com.example.Inventory_Service.dto.ProductDetailsDto;
import com.example.Inventory_Service.model.InventoryProducts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<InventoryProducts,Long> {

    boolean existsByProductName(String productName);

    Optional<InventoryProducts> findByProductName(String productName);


}
