package com.example.ecomappdemo.controller;

import com.example.ecomappdemo.model.Product;
import com.example.ecomappdemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
    @GetMapping
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(String.valueOf(id)).orElse(null); // Handle not found
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product existingProduct = productRepository.findById(String.valueOf(id)).orElse(null);
        if (existingProduct != null) {
            // Update fields of existingProduct with values from product
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            // ... update other fields
            return productRepository.save(existingProduct);
        }
        return null; // Or throw an exception
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(String.valueOf(id));
    }
}