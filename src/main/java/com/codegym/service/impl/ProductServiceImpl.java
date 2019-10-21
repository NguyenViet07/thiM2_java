package com.codegym.service.impl;

import com.codegym.model.Product;
import com.codegym.model.Type;
import com.codegym.repository.ProductRepository;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.delete(id);
    }

    @Override
    public Page<Product> findAllByTitleContaining(String name, Pageable pageable) {
        return productRepository.findAllByNameContaining(name,pageable);
    }

    @Override
    public List<Integer> getNumberPage(Page<Product> products) {
        int productPage = products.getTotalPages();
        List<Integer> productPages = new ArrayList<>();
        for (int i = 0; i < productPage; i++) {
            productPages.add(i);
        }
        return productPages;
    }

    @Override
    public Iterable<Product> findAllByType(Type type) {
        return productRepository.findAllByType(type);
    }

}
