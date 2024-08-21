/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */

// Write your code here
package com.example.nxttrendz1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import com.example.nxttrendz1.repository.*;
import com.example.nxttrendz1.model.*;

@Service
public class ProductJpaService implements ProductRepository {

    @Autowired
    private ProductJpaRepository productRepository;

    @Override
    public ArrayList<Product> getAllProduct() {
        List<Product> productList = productRepository.findAll();
        ArrayList<Product> products = new ArrayList<>(productList);
        return products;
    }

    @Override
    public Product getProductById(int productId) {
        try {
            Product product = productRepository.findById(productId).get();
            return product;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Product addProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    @Override
    public Product updateProduct(int productId, Product product) {
        try {
            Product newProduct = productRepository.findById(productId).get();
            if (product.getProductName() != null) {
                newProduct.setProductName(product.getProductName());
            }
            if (product.getPrice() != 0.0) {
                newProduct.setPrice(product.getPrice());
            }
            productRepository.save(newProduct);
            return newProduct;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteProduct(int productId) {
        try {
            productRepository.deleteById(productId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);

    }

}
