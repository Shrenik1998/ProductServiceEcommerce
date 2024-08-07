package com.scaler.backendprojectecomm.repository;

import com.scaler.backendprojectecomm.models.Product;
import com.scaler.backendprojectecomm.projections.ProductWithIdAndTitle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository                                           //<Object,Primary Key data type Of Object>
public interface ProductRepository extends JpaRepository<Product, Long> {

    //Declared Query
    Optional<Product> findById(Long id);

    //Declared Query
    Page<Product> findAll(Pageable pageable);

    //Declared Query
    List<Product> findByCategoryName(String categoryName);

    //save is used to insert and update
    //if id is given, and it exists in database data will be updated, else new data will be inserted
    //Declared Query
    Product save(Product product);

    //Declared Query
    void deleteById(Long id);

    //HQL(Hibernate query language)
    @Query("select p.id as id, p.title as title from Product p where p.id = :x")
    List<ProductWithIdAndTitle> randomSearchMethod(Long x);

    //SQL
    @Query(value = "select p.id as id, p.title as title from product p where p.id = :productId", nativeQuery = true)
    List<ProductWithIdAndTitle> randomSearchMethod2(Long productId);
}
