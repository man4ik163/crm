package com.test.crm.service;

import com.test.crm.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GroupService {

    List<Group> findAll();

    List<Group> findAllByName(String name);

    void createGroup(Group group);

    Group findById(Long id);

    void deleteGroup(Group group);

    Page<Group> findAllPages(Pageable pageable);

}