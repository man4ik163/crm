package com.test.crm.controller;

import com.test.crm.messaging.Producer;
import com.test.crm.model.Group;
import com.test.crm.model.GroupStorage;
import com.test.crm.service.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class GroupController {
    @Autowired
    private final GroupServiceImpl groupService;

    @Autowired
    private final Producer producer;

    @Autowired
    private final GroupStorage groupStorage;

    public GroupController(GroupServiceImpl groupService, Producer producer, GroupStorage groupStorage) {
        this.groupService = groupService;
        this.producer = producer;
        this.groupStorage = groupStorage;
    }

    @RequestMapping(value = "/crmgroups", method = RequestMethod.GET)
    public String listGroups(Model model, Pageable pageable) {
        Page<Group> groupPage = groupService.findAllPages(pageable);
        PageWrapper<Group> page = new PageWrapper<Group>(groupPage, "/crmgroups");
        model.addAttribute("crmgroups", page.getContent());
        model.addAttribute("page", page);
        return "crmgroups";
    }

    @RequestMapping(value = "crmgroup/send_groups_report", method = RequestMethod.POST)
    public String sendGroupsReport(RedirectAttributes redirectAttributes) {
        List<Group> groups = groupService.findAll();
        if(groups != null && !groups.isEmpty()) {
            groupStorage.addAll(groups);
            producer.send(groupStorage);
            groupStorage.clear();
            redirectAttributes.addFlashAttribute("message", "Report sent.");
        }
        return "redirect:/crmgroups/";
    }

    @RequestMapping(value = "crmgroup/delete/{id}")
    public String deleteGroup(@PathVariable Long id,  RedirectAttributes redirectAttributes){
        try{
            groupService.deleteGroup(groupService.findById(id));
        }catch(Exception ex){
            System.err.println("Delete error:"+ex.getMessage());
            redirectAttributes.addFlashAttribute("message", "This group have products.");

        }
        return "redirect:/crmgroups";
    }


}