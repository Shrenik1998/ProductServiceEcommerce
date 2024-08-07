package com.scaler.backendprojectecomm.controllers;

import com.scaler.backendprojectecomm.dtos.FakeStoreDto;
import com.scaler.backendprojectecomm.exceptions.CategoryNotFound;
import com.scaler.backendprojectecomm.exceptions.ProductNotFound;
import com.scaler.backendprojectecomm.models.Category;
import com.scaler.backendprojectecomm.models.Product;
import com.scaler.backendprojectecomm.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/products")
public class ProductController {
    //ProductService is an interface
    private ProductService productService;


    //ProductController class is dependent on the productService
    //object of interface cannot be created
    //spring will search the class that has implemented ProductService interface
    //spring will inject the class that has implemented this interface in this case FakeStoreProductService
    //spring will create FakeStoreProductService object and insert it in the below constructor
    //productService is parent class, hence it can point to the child class in this case FakeStoreProductService
    //the process of creating dependent obejct and injecting is called dependency injection
    ProductController(@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
    }

    //get the product based on id
    //this function will catch "ProductNotFound" exception object thrown by the product service
    //and throw it to the controller advice
    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") long id) throws ProductNotFound {

        ResponseEntity<Product> response = new ResponseEntity<>(
                productService.getSingleProduct(id), HttpStatus.OK
        );
        return response;
    }

    //get all products
    //localhost:8080/products?pageNumber=0&pageSize=3
    @GetMapping()
    public Page<Product> getAllProducts(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
        return productService.getAllProducts(pageNumber, pageSize);
    }

    //limit the number of products from the result
    @GetMapping("/limit={limit}")
    public List<Product> getLimitedProducts(@PathVariable("limit") int limit) {
        return productService.LimitProducts(limit);
    }

    //get categories of all the products
    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return productService.getAllCategories();
    }

    //get products by category
    //get the product based on id
    //this function will catch "ProductNotFound" exception object thrown by the product service
    //and throw it to the controller advice
    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable("category") String category) throws CategoryNotFound {
        return productService.getProductsByCategory(category);
    }

    //update product by id
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") long id,@RequestBody Product product) throws ProductNotFound, CategoryNotFound {
        return productService.updateProduct(id, product);
    }

    //replace product by id
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") long id,@RequestBody Product product) throws ProductNotFound, CategoryNotFound {
        return productService.replaceProduct(id, product);
    }

    //delete product by id
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") long id)
    {
        productService.deleteProduct(id);
    }

    @PostMapping()
    public Product addNewProduct(@RequestBody Product product)
    {
        return productService.addProduct(product);
    }


}
