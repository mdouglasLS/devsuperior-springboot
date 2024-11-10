package com.devsuperior_springboot.services;

import com.devsuperior_springboot.dto.ProductDTO;
import com.devsuperior_springboot.dto.ProductMinDTO;
import com.devsuperior_springboot.entities.Product;
import com.devsuperior_springboot.repositories.ProductRepository;
import com.devsuperior_springboot.services.exceptions.DatabaseException;
import com.devsuperior_springboot.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<ProductMinDTO> findAll(String name, Pageable pageable) {
        Page<Product> products = repository.searchByName(name, pageable);
        repository.findProductsCategories(products.getContent());
        return products.map(product -> modelMapper.map(product, ProductMinDTO.class));
    }

    public ProductDTO findById(Long id) {
        Product product = repository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Recurso não encontrado.");
        });
        return modelMapper.map(product, ProductDTO.class);
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product product = new Product();
        copyDtoToEntity(dto, product);
        product = repository.save(product);
        return modelMapper.map(product, ProductDTO.class);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product product = repository.getReferenceById(id);
            copyDtoToEntity(dto, product);
            product = repository.save(product);
            return modelMapper.map(product, ProductDTO.class);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product product) {
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setImgUrl(dto.getImgUrl());
    }
}
