package com.test.crm.repository;

import com.test.crm.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("select g from Group g where g.name = :name")
    List<Group> findAllByName(@Param("name") String name);

    Page<Group> findAll(Pageable pageable);

    List<Group> findAll();
}