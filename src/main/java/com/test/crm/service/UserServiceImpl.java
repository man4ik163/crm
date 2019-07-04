package com.test.crm.service;

import com.test.crm.model.User;
import com.test.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void createUser(User user) {
        userRepository.saveAndFlush(user);
    }

    @Transactional
    public User findByName(String name){
        return userRepository.findByName(name);
    }
}
