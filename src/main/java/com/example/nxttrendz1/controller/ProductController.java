/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.web.bind.annotation.*;
 * import java.util.ArrayList;
 * 
 */

// Write your code here
package com.example.nxttrendz1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

import com.example.nxttrendz1.service.ProductJpaService;
import com.example.nxttrendz1.model.*;

@RestController

public class ProductController {

    @Autowired
    public ProductJpaService productService;

    @GetMapping("/products")
    public ArrayList<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    @GetMapping("/products/{productId}")
    public Product getProductById(@PathVariable("productId") int productId) {
        return productService.getProductById(productId);
    }
    
    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }
    
    @PutMapping("/products/{productId}")
    public Product updateProduct(@PathVariable("productId") int productId,@RequestBody Product product){
        return productService.updateProduct(productId,product);
    }
    
    @DeleteMapping("/products/{productId}")
    public void deleteProduct(@PathVariable("productId") int productId){
        productService.deleteProduct(productId);
    }
    

}