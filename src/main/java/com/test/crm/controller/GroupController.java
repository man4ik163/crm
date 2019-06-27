package com.test.crm.controller;

import com.test.crm.service.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GroupController {
    @Autowired
    private final GroupServiceImpl groupService;

    public GroupController(GroupServiceImpl groupService) {
        this.groupService = groupService;
    }

    @RequestMapping(value = {"/crmgroups", "/"}, method = RequestMethod.GET)
    public ModelAndView crmgroups() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("crmgroups");
        mav.addObject("groupsList", groupService.findAll());
        return mav;
    }
}