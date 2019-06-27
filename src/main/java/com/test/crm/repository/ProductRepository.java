package com.test.crm.repository;

import com.test.crm.entity.Group;
import com.test.crm.entity.Product;
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
}
