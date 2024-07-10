package com.scaler.backendprojectecomm.services;
import com.scaler.backendprojectecomm.exceptions.CategoryNotFound;
import com.scaler.backendprojectecomm.exceptions.ProductNotFound;
import com.scaler.backendprojectecomm.models.Category;
import com.scaler.backendprojectecomm.models.Product;
import java.util.*;

public interface ProductService {
    Product getSingleProduct(long id) throws ProductNotFound;

    List<Product> getAllProducts();

    List<Product> LimitProducts(int limitNo);

    List<Category> getAllCategories();

    List<Product> getProductsByCategory(String category) throws CategoryNotFound;

    Product updateProduct(long id,Product product);

    Product replaceProduct(long id,Product product);

    Product deleteProduct(long id,Product product);

    Product addProduct(Product product);
}
