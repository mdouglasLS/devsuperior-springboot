package com.devsuperior_springboot.repositories;

import com.devsuperior_springboot.entities.Category;
import com.devsuperior_springboot.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
