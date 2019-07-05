package com.test.crm.service;

import com.test.crm.model.User;

public interface UserService {

    void createUser(User user);

    User findByName(String name);
}
