package com.test.crm.controller;

import com.test.crm.model.Role;
import com.test.crm.model.User;
import com.test.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Collections;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "registration_form";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "registration_form";
        }
        if (userService.findByName(user.getName()) != null) {
            FieldError fieldError = new FieldError("user", "name", "this user exist");
            bindingResult.addError(fieldError);
            return "registration_form";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        userService.createUser(user);
        return "redirect:/authorization_form";
    }

}
