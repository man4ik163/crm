package com.test.crm.service;

import com.test.crm.entity.Group;
import com.test.crm.entity.Product;
import com.test.crm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public List<Product> findAllByName(String name) {
        return productRepository.findAllByName(name);
    }

    @Transactional
    public void createProduct(Product product) {
        productRepository.saveAndFlush(product);
    }

    @Transactional
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public List<Product> findAllByGroupId(Group group) {
        return productRepository.findAllByGroupId(group);
    }

    @Transactional
    public void deleteProduct(Product product){
        productRepository.delete(product);
    }

    @Transactional
    public Product findById(Long id){
        return productRepository.getOne(id);
    }

    @Transactional
    public List<Product> findAllByArticle(String article){
        return productRepository.findAllByArticle(article);
    }
}
