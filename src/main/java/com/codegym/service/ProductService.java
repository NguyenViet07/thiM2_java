package com.codegym.service;

import com.codegym.model.Product;
import com.codegym.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService extends GeneralService<Product> {
    Page<Product> findAllByTitleContaining(String name, Pageable pageable);
    List<Integer> getNumberPage(Page<Product> products);
    Iterable<Product> findAllByType(Type country);
}
