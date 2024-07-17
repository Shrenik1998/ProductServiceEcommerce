package com.scaler.backendprojectecomm.services;

import com.scaler.backendprojectecomm.exceptions.CategoryNotFound;
import com.scaler.backendprojectecomm.exceptions.ProductNotFound;
import com.scaler.backendprojectecomm.models.Category;
import com.scaler.backendprojectecomm.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelfProductService implements ProductService {
    @Override
    public Product getSingleProduct(long id) throws ProductNotFound {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public List<Product> LimitProducts(int limitNo) {
        return List.of();
    }

    @Override
    public List<Category> getAllCategories() {
        return List.of();
    }

    @Override
    public List<Product> getProductsByCategory(String category) throws CategoryNotFound {
        return List.of();
    }

    @Override
    public Product updateProduct(long id, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(long id, Product product) {
        return null;
    }

    @Override
    public Product deleteProduct(long id, Product product) {
        return null;
    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }
}
