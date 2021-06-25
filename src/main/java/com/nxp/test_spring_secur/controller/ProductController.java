package com.nxp.test_spring_secur.controller;

import com.nxp.test_spring_secur.entity.Product;
import com.nxp.test_spring_secur.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/nxp/v1/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/getProducts", method = RequestMethod.POST)
    public List<Product> login(@RequestParam String token) {
        return productService.getListProd(token);
    }

    @RequestMapping(value = "/getInfoProduct/{id}", method = RequestMethod.GET)
    public Product login(@PathVariable Long id) {
        return productService.getInfoProduct(id);
    }
}
