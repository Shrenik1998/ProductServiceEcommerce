package com.scaler.backendprojectecomm.services;

import com.scaler.backendprojectecomm.dtos.FakeStoreDto;
import com.scaler.backendprojectecomm.models.Category;
import com.scaler.backendprojectecomm.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(long productId)
    {
        FakeStoreDto fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreDto.class
        );

        //Convert FakeStoreProductDto into Product.
        return converToProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreDto[] fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreDto[].class
        );

        //Convert FakeStoreProductDto into Product.
        List<Product> products = new ArrayList<>();
        for (FakeStoreDto fakeStoreProductDto1 : fakeStoreProductDto) {
            products.add(converToProduct(fakeStoreProductDto1));
        }
        return products;
    }

    //get no of products
    @Override
    public List<Product> LimitProducts(int limitNo){
        FakeStoreDto[] fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products?limit="+limitNo,
                FakeStoreDto[].class
        );

        List<Product> products = new ArrayList<>();

        if(limitNo >= fakeStoreProductDto.length){
            for (FakeStoreDto fakeStoreProductDto1 : fakeStoreProductDto) {
                products.add(converToProduct(fakeStoreProductDto1));
            }
        }
        else{
            for(int i=0;i<limitNo;i++)
            {
                products.add(converToProduct(fakeStoreProductDto[i]));
            }
        }
        return products;
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        FakeStoreDto[] fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/category/"+category,
                FakeStoreDto[].class
        );

        //Convert FakeStoreProductDto into Product.
        List<Product> products = new ArrayList<>();
        for (FakeStoreDto fakeStoreProductDto1 : fakeStoreProductDto) {
            products.add(converToProduct(fakeStoreProductDto1));
        }
        return products;
    }


    private Product converToProduct(FakeStoreDto fakeStoreDto) {
        Product product = new Product();
        product.setId(fakeStoreDto.getId());
        product.setTitle(fakeStoreDto.getTitle());
        product.setDescription(fakeStoreDto.getDescription());
        Category category = new Category();

        category.setDescription(fakeStoreDto.getCategory());
        product.setCategory(category);
        return product;
    }
}
