package com.scaler.backendprojectecomm.services;
import com.scaler.backendprojectecomm.models.Product;
import java.util.*;

public interface ProductService {
    Product getSingleProduct(long id);

    List<Product> getAllProducts();

    List<Product> LimitProducts(int limitNo);

    List<Product> getProductsByCategory(String category);
}
