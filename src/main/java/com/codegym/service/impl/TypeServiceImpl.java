package com.codegym.service.impl;

import com.codegym.model.Product;
import com.codegym.model.Type;
import com.codegym.repository.ProductRepository;
import com.codegym.repository.TypeRepository;
import com.codegym.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class TypeServiceImpl implements TypeService {
    @Autowired
    TypeRepository typeRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public Page<Type> findAll(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public Type findById(Long id) {
        return typeRepository.findOne(id);
    }

    @Override
    public void save(Type type) {
        typeRepository.save(type);
    }

    @Override
    public void remove(Long id) {
        Type type = findById(id);
        List<Product> products = (List<Product>) productRepository.findAllByType(type);
        for(int i=0; i<products.size(); i++){
            productRepository.delete(products.get(i));
        }
        typeRepository.delete(id);

    }

    @Override
    public Iterable<Type> findAll() {
        return typeRepository.findAll();
    }

}
