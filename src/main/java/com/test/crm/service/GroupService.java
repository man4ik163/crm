package com.test.crm.service;

import com.test.crm.entity.Group;
import com.test.crm.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Transactional
    public List<Group> findAll(){
        return groupRepository.findAll();
    }

    @Transactional
    public List<Group> findAllByName(String name){
        return groupRepository.findAllByName(name);
    }

    @Transactional
    public void createGroup(Group group){
        groupRepository.saveAndFlush(group);
    }
}
