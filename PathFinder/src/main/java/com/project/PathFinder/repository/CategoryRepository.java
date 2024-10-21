package com.project.PathFinder.repository;

import com.project.PathFinder.entity.Category;
import com.project.PathFinder.entity.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Set<Category> findByNameIn(Set<CategoryName> categories);
}
