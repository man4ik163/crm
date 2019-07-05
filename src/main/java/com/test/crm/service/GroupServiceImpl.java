package com.test.crm.service;

import com.test.crm.model.Group;
import com.test.crm.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

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
    public Group findById(Long id) {
        return groupRepository.getOne(id);
    }

    @Transactional
    public void deleteGroup(Group group) {
        groupRepository.delete(group);
    }

    @Transactional
    public Page<Group> findAllPages(Pageable pageable) {
        return groupRepository.findAll(pageable);
    }
}
