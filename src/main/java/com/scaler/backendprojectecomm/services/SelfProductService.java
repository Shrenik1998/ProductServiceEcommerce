package com.scaler.backendprojectecomm.services;

import com.scaler.backendprojectecomm.exceptions.CategoryNotFound;
import com.scaler.backendprojectecomm.exceptions.ProductNotFound;
import com.scaler.backendprojectecomm.models.Category;
import com.scaler.backendprojectecomm.models.Product;
import com.scaler.backendprojectecomm.repository.CategoryRepository;
import com.scaler.backendprojectecomm.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SelfProductService implements ProductService {
    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    //Dependency Injection by the IOC(Inversion of control) container
    public SelfProductService(ProductRepository productRepository,CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(long id) throws ProductNotFound {
        Optional<Product> productBucket= productRepository.findById(id);

        if(productBucket.isEmpty())
        {
            throw new ProductNotFound(id,"Product with id="+id+" not found");
        }

        return productBucket.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> LimitProducts(int limitNo) {
        return List.of();
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) throws CategoryNotFound {

        List<Product> products =  productRepository.findByCategoryName(category);

        if(products.isEmpty())
        {
            throw new CategoryNotFound(category,category+" category does not exist");
        }

        return products;
    }

    //Patch
    @Override
    public Product updateProduct(long id, Product product) throws ProductNotFound, CategoryNotFound {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            throw new ProductNotFound(id,"Product with id : " + id + " doesn't exist");
        }

        Product productInDB = optionalProduct.get();

        if (product.getTitle() != null) {
            productInDB.setTitle(product.getTitle());
        }
        if (product.getPrice() != 0.0) {
            productInDB.setPrice(product.getPrice());
        }
        if (product.getDescription() != null) {
            productInDB.setDescription(product.getDescription());
        }
        if(product.getQty() != 0)
        {
            productInDB.setQty(product.getQty());
        }

        //update category if id is present
        if (product.getCategory() != null && product.getId()!=0) {
            Optional<Category> optionalCategory = categoryRepository.findById(product.getCategory().getId());

            if (optionalCategory.isEmpty()) {
                throw new CategoryNotFound("","Category with id "+product.getCategory().getId()+
                        " does not exist");
            }

            Category categoryInDB = optionalCategory.get();

            if(product.getCategory().getDescription()!=null)
            {
                categoryInDB.setDescription(product.getCategory().getDescription());
            }

            if(product.getCategory().getName()!=null)
            {
                categoryInDB.setName(product.getCategory().getName());
            }
            productInDB.setCategory(categoryInDB);
        }


        return productRepository.save(productInDB);
    }

    //Put
    @Override
    public Product replaceProduct(long id, Product product) throws ProductNotFound, CategoryNotFound {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            throw new ProductNotFound(id,"Product with id : " + id + " doesn't exist");
        }

        Product productInDB = optionalProduct.get();

        productInDB.setTitle(product.getTitle());
        productInDB.setPrice(product.getPrice());
        productInDB.setQty(product.getQty());
        productInDB.setDescription(product.getDescription());

        //replace category if id is present
        if(product.getCategory() != null)
        {
            Optional<Category> optionalCategory = categoryRepository.findById(product.getCategory().getId());

            if (optionalCategory.isEmpty()) {
                throw new CategoryNotFound("","Category with id "+product.getCategory().getId()+
                        " does not exist");
            }
            Category category = product.getCategory();
            if(category.getId() != 0)
            {
                category = categoryRepository.save(category);
                product.setCategory(category);
            }

        }
        productInDB.setCategory(product.getCategory());

        return productRepository.save(productInDB);
    }

    //Delete
    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    //Post
    @Override
    public Product addProduct(Product product) {
        Category category = product.getCategory();

        if(category!=null && category.getId() == 0)
        {
            category = categoryRepository.save(category);
            product.setCategory(category);
        }

        return productRepository.save(product);
    }
}
