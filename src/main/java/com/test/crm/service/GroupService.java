package com.test.crm.service;

import com.test.crm.entity.Group;
import com.test.crm.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GroupService {

    List<Group> findAll();

    List<Group> findAllByName(String name);

    void createGroup(Group group);

    Group getGroupById(Long id);
}
