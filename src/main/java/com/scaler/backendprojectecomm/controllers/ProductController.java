package com.scaler.backendprojectecomm.controllers;

import com.scaler.backendprojectecomm.models.Product;
import com.scaler.backendprojectecomm.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    //ProductService is an interface
    private ProductService productService;


    //ProductController class is dependent on the productService
    //object of interface cannot be created
    //spring will search the class that has implemented ProductService interface
    //spring will inject the class that has implemented this inteface in this case FakeStoreProductService
    //spring will create FakeStoreProductService object and insert it in the below constructor
    //productService is parent class, hence it can point to the child class in this case FakeStoreProductService
    //the process of creating dependent obejct and injecting is called dependency injection
    ProductController(ProductService productService) {
        this.productService = productService;
    }

    //get the product based on id
    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable("id") long id){
        return productService.getSingleProduct(id);
    }

    //get all products
    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    //limit the number of products from the result
    @GetMapping("/limit={limit}")
    public List<Product> getLimitedProducts(@PathVariable("limit") int limit) {
        return productService.LimitProducts(limit);
    }

    //get products by category
    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable("category") String category) {
        return productService.getProductsByCategory(category);
    }
}
