package com.nxp.test_spring_secur.service;

import com.nxp.test_spring_secur.entity.Product;
import com.nxp.test_spring_secur.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getListProd(String token){
        return productRepository.findAll();
    }

    public Product getInfoProduct(Long id) {
        return  productRepository.getOne(id);
    }
}
