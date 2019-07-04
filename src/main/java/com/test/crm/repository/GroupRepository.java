package com.test.crm.repository;

import com.test.crm.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findAllByName(String name);

    Page<Group> findAll(Pageable pageable);

    List<Group> findAll();
}