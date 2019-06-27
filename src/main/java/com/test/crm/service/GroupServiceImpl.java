package com.test.crm.service;

import com.test.crm.entity.Group;
import com.test.crm.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Transactional
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Transactional
    public List<Group> findAllByName(String name) {
        return groupRepository.findAllByName(name);
    }

    @Transactional
    public void createGroup(Group group) {
        groupRepository.saveAndFlush(group);
    }

    @Transactional
    public Group getGroupById(Long id){
        return groupRepository.getOne(id);
    }
}
