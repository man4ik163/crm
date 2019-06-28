package com.test.crm.controller;

import com.test.crm.entity.Group;
import com.test.crm.service.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class GroupController {
    @Autowired
    private final GroupServiceImpl groupService;

    public GroupController(GroupServiceImpl groupService) {
        this.groupService = groupService;
    }

    @RequestMapping(value = {"/crmgroups", "/"}, method = RequestMethod.GET)
    public ModelAndView crmGroups() {
        List<Group> groups = groupService.findAll();
        ModelAndView mav = new ModelAndView();
        mav.setViewName("crmgroups");
        if(groups != null && !groups.isEmpty()) mav.addObject("groupsList", groups);
        mav.addObject("group", new Group());
        return mav;
    }

    @RequestMapping(value = "/delete_group", method = RequestMethod.GET)
    public String deleteGroup(@RequestParam(name="groupId")String groupId) {
        Group group = groupService.findById(Long.valueOf(groupId));
        groupService.deleteGroup(group);
        return "redirect:/crmgroups";
    }

    @RequestMapping(value = "/edit_group", method = RequestMethod.GET)
    public ModelAndView editGroup(@RequestParam(name="groupId")String groupId) {
        Group group = groupService.findById(Long.valueOf(groupId));
        ModelAndView mav = new ModelAndView();
        mav.setViewName("edit_group");
        mav.addObject("group", group);
        return mav;
    }

    @RequestMapping(value = "/save_group", method = RequestMethod.POST)
    public ModelAndView saveGroup(@ModelAttribute Group group, Model model) {
        groupService.createGroup(group);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("edit_group");
        mav.addObject("group", group);
        return mav;
    }

    @RequestMapping(value = "/crmgroups", method = RequestMethod.POST)
    public ModelAndView createGroup(@ModelAttribute @Valid Group group, BindingResult bindingResult, Model model) {
        ModelAndView mav = new ModelAndView();
        if(bindingResult.hasErrors()) {
            mav.setViewName("crmgroups");
            return mav;
        }
        groupService.createGroup(group);
        mav.setViewName("crmgroups");
        mav.addObject("groupsList", groupService.findAll());
        return mav;
    }
}