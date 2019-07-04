package com.test.crm.controller;

import com.test.crm.messaging.Producer;
import com.test.crm.model.Group;
import com.test.crm.model.GroupStorage;
import com.test.crm.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private Producer producer;

    @Autowired
    private GroupStorage groupStorage;

    @RequestMapping(value = {"/crmgroups", "/"}, method = RequestMethod.GET)
    public String listGroups(Model model, Pageable pageable) {
        Page<Group> groupPage = groupService.findAllPages(pageable);
        PageWrapper<Group> page = new PageWrapper<Group>(groupPage, "/crmgroups");
        model.addAttribute("crmgroups", page.getContent());
        model.addAttribute("page", page);
        return "crmgroups";
    }

    @RequestMapping(value = {"/crmgroups", "/"}, method = RequestMethod.POST)
    public String listGroups(Model model) {
        Pageable pageable = PageRequest.of(0, 20);
        Page<Group> groupPage = groupService.findAllPages(pageable);
        PageWrapper<Group> page = new PageWrapper<Group>(groupPage, "/crmgroups");
        model.addAttribute("crmgroups", page.getContent());
        model.addAttribute("page", page);
        return "crmgroups";
    }

    @RequestMapping(value = "crmgroups/send_groups_report", method = RequestMethod.POST)
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

    @RequestMapping("crmgroup/edit/{id}")
    public String editGroup(@PathVariable Long id, Model model) {
        model.addAttribute("group", groupService.findById(id));
        return "group_edit_form";
    }

    @RequestMapping("crmgroup/create/")
    public String createGroup(Model model) {
        model.addAttribute("group", new Group());
        return "group_create_form";
    }

    @RequestMapping("save_group")
    public String saveGroup(@Valid Group group, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            if(group.getId() != null){
                return "group_edit_form";
            }
            if(group.getId() == null){
                return "group_create_form";
            }
        }
        groupService.createGroup(group);
        return "redirect:/crmgroups";
    }

}