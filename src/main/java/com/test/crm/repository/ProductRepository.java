package com.test.crm.repository;

import com.test.crm.model.Group;
import com.test.crm.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.name = :name")
    List<Product> findAllByName(@Param("name") String name);

    List<Product> findAllByGroupId(Group group);

    List<Product> findAllByArticle(String article);

    Page<Product> findAll(Pageable pageable);

    Page<Product> findAllByGroupId(Group groupId, Pageable pageable);
}
