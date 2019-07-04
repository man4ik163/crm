package com.test.crm.service;

import com.test.crm.model.User;
import com.test.crm.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    public UserDetailsServiceImpl(UserService userService) {

    }


    @Override
    public UserDetails loadUserByUsername(String name){
        User user = userService.findByName(name);
        if (user == null) {
            throw new UsernameNotFoundException(name);
        }
        return new UserDetailsImpl(user);
    }
}
