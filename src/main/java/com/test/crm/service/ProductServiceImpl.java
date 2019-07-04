package com.test.crm.service;

import com.test.crm.model.Group;
import com.test.crm.model.Product;
import com.test.crm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

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

    @Transactional
    public Product findByArticle(String article){
        return productRepository.findByArticle(article);
    }

    @Transactional
    public Page<Product> findAllPages(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    @Transactional
    public Page<Product> findAllPages(Group groupId, Pageable pageable){
        return productRepository.findAllByGroupId(groupId, pageable);
    }
}
