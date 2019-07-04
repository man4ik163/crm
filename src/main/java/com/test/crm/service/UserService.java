package com.test.crm.service;

import com.test.crm.model.User;

import java.util.List;

public interface UserService {

    void createUser(User user);

    User findByName(String name);
}
