package com.example.Inventory_Service.controller;

import com.example.Inventory_Service.dto.ApiResponse;
import com.example.Inventory_Service.dto.OrderRequestDto;
import com.example.Inventory_Service.dto.ProductDetailsDto;
import com.example.Inventory_Service.service.CheckInventory;
import com.example.Inventory_Service.service.RetriveAllProducts;
import com.example.Inventory_Service.service.UpdateInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    UpdateInventoryService updateInventoryService;

    @Autowired
    CheckInventory checkInventory;

    @Autowired
    RetriveAllProducts retriveAllProducts;

    @PostMapping("/updateproducts")
    public ResponseEntity<?> addProducts(@RequestBody OrderRequestDto orderRequestDto)
    {
        updateInventoryService.updateInventoryProducts(orderRequestDto);
        return ResponseEntity.ok("product updated");
    }

    @GetMapping("/check-product")
    public ResponseEntity<ApiResponse<ProductDetailsDto>> checkProduct(@RequestParam("productName") String productName)
    {
        ProductDetailsDto productDetails = checkInventory.checkProductIsInStock(productName);
        System.out.println(productDetails.getProductName());

        Optional<ProductDetailsDto> productDto = Optional.ofNullable(productDetails);

        if(productDto.isPresent())
            {
                ApiResponse<ProductDetailsDto> response=new ApiResponse<>("Product is available",productDto.get());

                return ResponseEntity.ok(response);
            }
            else {
                ApiResponse<ProductDetailsDto> response=new ApiResponse<>("Product is not available");

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }

        @GetMapping("/retrivre-products")
        public ResponseEntity<ApiResponse<List<ProductDetailsDto>>> getAllProducts()
        {
              List<ProductDetailsDto> allProductsList=retriveAllProducts.getAllProducts();

              if(allProductsList.isEmpty())
              {
                  return ResponseEntity.status(HttpStatus.NO_CONTENT)
                          .body(new ApiResponse<>(null,"Inventory is empty",false));
              }
              else {
                  return ResponseEntity.ok(new ApiResponse<>(allProductsList,"Retrived products",true));
              }
        }

    @GetMapping("/retrivre-products/{productName}")
    public ResponseEntity<ApiResponse<ProductDetailsDto>> getProductDetails(@PathVariable String productName)
    {
        ProductDetailsDto productDetailsDto=checkInventory.findProductDetails(productName);

        if(productDetailsDto!=null)
        {
            return ResponseEntity.ok(new ApiResponse<>("Details of the product",productDetailsDto));
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Product not found",null));
        }
    }

    }

