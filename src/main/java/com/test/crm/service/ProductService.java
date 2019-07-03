package com.test.crm.service;

import com.test.crm.model.Group;
import com.test.crm.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    List<Product> findAllByName(String name);

    void createProduct(Product product);

    List<Product> findAll();

    List<Product> findAllByGroupId(Group group);

    void deleteProduct(Product product);

    Product findById(Long id);

    List<Product> findAllByArticle(String article);

    Product findByArticle(String article);

    Page<Product> findAllPages(Pageable pageable);
}
