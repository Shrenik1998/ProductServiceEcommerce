package com.scaler.backendprojectecomm.repository;

import com.scaler.backendprojectecomm.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAll();

    Category save(Category category);
}
