package com.nxp.test_spring_secur.utils;

import com.nxp.test_spring_secur.entity.Account;
import com.nxp.test_spring_secur.entity.Product;
import com.nxp.test_spring_secur.repo.AccountRepository;
import com.nxp.test_spring_secur.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Migrate {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ProductRepository productRepository;


    public void dataSeeding() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        List<Account> a = accountRepository.findAll();
        if (a.size() == 0) {

            List<Account> accList = new ArrayList<>();

            Account account1 = new Account();
            account1.setStatus(1);
            account1.setFullName("Abc");
            account1.setRole(1);
            account1.setUsername("admin");
            account1.setPasswordHash(bCryptPasswordEncoder.encode("admin"));
            accList.add(account1);

            Account account2 = new Account();
            account2.setStatus(1);
            account2.setFullName("Abc");
            account2.setRole(0);
            account2.setUsername("user");
            account2.setPasswordHash(bCryptPasswordEncoder.encode("user"));
            accList.add(account2);

            accountRepository.saveAll(accList);
        }

        if (productRepository.findAll().size() == 0) {

            List<Product> accProd = new ArrayList<>();

            Product product1 = new Product();
            product1.setPrice(100000L);
            product1.setName("Ao");
            accProd.add(product1);

            Product product2 = new Product();
            product2.setPrice(120000L);
            product2.setName("Quan");
            accProd.add(product2);

            Product product3 = new Product();
            product3.setPrice(140000L);
            product3.setName("Giay");
            accProd.add(product3);

            Product product4 = new Product();
            product4.setPrice(100100L);
            product4.setName("Dep");
            accProd.add(product4);

            Product product5 = new Product();
            product5.setPrice(800000L);
            product5.setName("Mu");
            accProd.add(product5);

            Product product6 = new Product();
            product6.setPrice(3100000L);
            product6.setName("Kinh");
            accProd.add(product6);

            Product product7 = new Product();
            product7.setPrice(102000L);
            product7.setName("Laptop");
            accProd.add(product7);

            Product product8 = new Product();
            product8.setPrice(110000L);
            product8.setName("Tivi");
            accProd.add(product8);

            Product product9 = new Product();
            product9.setPrice(142000L);
            product9.setName("Nuoc");
            accProd.add(product9);

            Product product10 = new Product();
            product10.setPrice(1002340L);
            product10.setName("May say");
            accProd.add(product10);

            productRepository.saveAll(accProd);
        }
    }
}