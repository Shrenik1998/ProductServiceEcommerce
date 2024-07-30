package com.scaler.backendprojectecomm.repository;

import com.scaler.backendprojectecomm.models.Product;
import com.scaler.backendprojectecomm.projections.ProductWithIdAndTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);

    List<Product> findAll();

    List<Product> findByCategoryName(String categoryName);

    Product save(Product product);

    void deleteById(Long id);

    //HQL
    @Query("select p.id as id, p.title as title from Product p where p.id = :x")
    List<ProductWithIdAndTitle> randomSearchMethod(Long x);

    //SQL
    @Query(value = "select p.id as id, p.title as title from product p where p.id = :productId", nativeQuery = true)
    List<ProductWithIdAndTitle> randomSearchMethod2(Long productId);
}
