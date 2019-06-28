package com.test.crm.service;

import com.test.crm.entity.Group;
import com.test.crm.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService {

    List<Product> findAllByName(String name);

    void createProduct(Product product);

    List<Product> findAll();

    List<Product> findAllByGroupId(Group group);

    void deleteProduct(Product product);

    Product findById(Long id);

    List<Product> findAllByArticle(String article);
}
