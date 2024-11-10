package com.devsuperior_springboot.services;

import com.devsuperior_springboot.dto.CategoryDTO;
import com.devsuperior_springboot.entities.Category;
import com.devsuperior_springboot.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public List<CategoryDTO> findAll() {
        List<Category> categories = repository.findAll();
        return categories.stream().map(cat -> modelMapper.map(cat, CategoryDTO.class)).toList();
    }

}
