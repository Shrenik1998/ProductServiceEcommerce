package com.scaler.backendprojectecomm.services;

import com.scaler.backendprojectecomm.dtos.FakeStoreDto;
import com.scaler.backendprojectecomm.exceptions.ProductNotFound;
import com.scaler.backendprojectecomm.models.Category;
import com.scaler.backendprojectecomm.models.Product;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(long productId) throws ProductNotFound {
        FakeStoreDto fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreDto.class
        );

        if(fakeStoreProductDto == null)
        {
            throw new ProductNotFound(productId,"Product with id " +productId+ " doesn't exist");
        }

        //Convert FakeStoreProductDto into Product
        return convertToProduct(fakeStoreProductDto);
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
            products.add(convertToProduct(fakeStoreProductDto1));
        }
        return products;
    }

    //get no of products
    @Override
    public List<Product> LimitProducts(int limitNo){
        FakeStoreDto[] fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreDto[].class
        );

        List<Product> products = new ArrayList<>();

        if(limitNo >= fakeStoreProductDto.length){
            for (FakeStoreDto fakeStoreProductDto1 : fakeStoreProductDto) {
                products.add(convertToProduct(fakeStoreProductDto1));
            }
        }
        else{
            for(int i=0;i<limitNo;i++)
            {
                products.add(convertToProduct(fakeStoreProductDto[i]));
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
            products.add(convertToProduct(fakeStoreProductDto1));
        }
        return products;
    }

    //partial update
    //Patch
    @Override
    public Product updateProduct(long id, Product product) {
        FakeStoreDto fakeStoreProductDto = new FakeStoreDto();
        if(product.getTitle()!=null)
        {
            fakeStoreProductDto.setTitle(product.getTitle());
        }
        if(product.getDescription()!=null)
        {
            fakeStoreProductDto.setDescription(product.getDescription());
        }
        if(product.getCategory().getName()!=null)
        {
            fakeStoreProductDto.setCategory(product.getCategory().getName());
        }

        fakeStoreProductDto.setPrice(product.getPrice());


        FakeStoreDto fakeStoreDto = restTemplate.patchForObject("https://fakestoreapi.com/products/" + id,
                fakeStoreProductDto, FakeStoreDto.class);
        return convertToProduct(fakeStoreDto);
    }

    //Full update
    //Put
    @Override
    public Product replaceProduct(long id, Product product) {
        FakeStoreDto fakeStoreProductDto = new FakeStoreDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setCategory(product.getCategory().getName());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreDto.class);
        HttpMessageConverterExtractor<FakeStoreDto> responseExtractor = new HttpMessageConverterExtractor<>(FakeStoreDto.class,
                restTemplate.getMessageConverters());
        FakeStoreDto response = restTemplate.execute("https://fakestoreapi.com/products/" + id,
                HttpMethod.PUT, requestCallback, responseExtractor);
        return convertToProduct(response);
    }

    @Override
    public Product deleteProduct(long id,Product product) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreDto.class);
        HttpMessageConverterExtractor<FakeStoreDto> responseExtractor = new HttpMessageConverterExtractor<>(FakeStoreDto.class,
                restTemplate.getMessageConverters());
        FakeStoreDto response = restTemplate.execute("https://fakestoreapi.com/products/" + id,
                HttpMethod.DELETE, requestCallback, responseExtractor);
        return convertToProduct(response);
    }

    @Override
    public Product addProduct(Product product){
        FakeStoreDto fakeStoreProductDto = new FakeStoreDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setCategory(product.getCategory().getName());

        FakeStoreDto fakeStoreDto = restTemplate.postForObject("https://fakestoreapi.com/products/",
                fakeStoreProductDto, FakeStoreDto.class);

        return convertToProduct(fakeStoreDto);
    }


    private Product convertToProduct(FakeStoreDto fakeStoreDto) {
        Product product = new Product();
        product.setId(fakeStoreDto.getId());
        product.setTitle(fakeStoreDto.getTitle());
        product.setDescription(fakeStoreDto.getDescription());
        product.setPrice(fakeStoreDto.getPrice());
        Category category = new Category();

        category.setName(fakeStoreDto.getCategory());
        product.setCategory(category);
        return product;
    }
}
