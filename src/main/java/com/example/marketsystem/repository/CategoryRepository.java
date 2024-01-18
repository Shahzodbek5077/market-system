package com.example.marketsystem.repository;

import com.example.marketsystem.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByNameEqualsIgnoreCase(String name);
    Page<Category> findAllBy(Pageable pageable);
}
