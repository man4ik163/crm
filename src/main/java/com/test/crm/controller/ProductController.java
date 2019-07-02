package com.test.crm.controller;

import com.test.crm.messaging.Producer;
import com.test.crm.model.Group;
import com.test.crm.model.Product;
import com.test.crm.model.ProductStorage;
import com.test.crm.service.GroupServiceImpl;
import com.test.crm.service.ProductServiceImpl;
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

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private final ProductServiceImpl productService;

    @Autowired
    private final GroupServiceImpl groupService;

    @Autowired
    private final ProductStorage productStorage;

    @Autowired
    private final Producer producer;

    public ProductController(ProductServiceImpl productService, GroupServiceImpl groupService, ProductStorage productStorage, Producer producer) {
        this.productService = productService;
        this.groupService = groupService;
        this.productStorage = productStorage;
        this.producer = producer;
    }

    @RequestMapping(value = "/crmproducts/{groupId}", method = RequestMethod.GET)
//    @RequestMapping(value = "/crmproducts/", method = RequestMethod.GET)
    public String listGroups(@PathVariable Long groupId, Model model, Pageable pageable) {
        Page<Product> productPage = productService.findAllPages(groupService.findById(groupId), pageable);
        PageWrapper<Product> page = new PageWrapper<Product>(productPage, "/crmproducts");
        model.addAttribute("crmproducts", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("groupId", groupId);
        return "crmproducts";
    }

    @RequestMapping(value = "/crmproducts/delete/{id}")
    public String deleteGroup(@PathVariable Long id, RedirectAttributes redirectAttributes, Model model) {
        Product product = productService.findById(id);
        Long groupId = product.getGroupId().getId();
        try {
            model.addAttribute("groupId", groupId);
            redirectAttributes.addFlashAttribute("groupId", groupId);
            productService.deleteProduct(product);
        } catch (Exception ex) {
            System.err.println("Delete error:" + ex.getMessage());
            redirectAttributes.addFlashAttribute("message", "Error delete.");

        }
        return "redirect:/crmproducts/" + groupId;
    }

    @RequestMapping(value = "crmproducts/send_products_report/{groupId}", method = RequestMethod.POST)
    public String sendGroupsReport(RedirectAttributes redirectAttributes, @PathVariable Long groupId) {
        List<Product> products = productService.findAllByGroupId(groupService.findById(groupId));
        if (products != null && !products.isEmpty()) {
            productStorage.addAll(products);
            producer.send(productStorage);
            productStorage.clear();
            redirectAttributes.addFlashAttribute("message", "Report sent.");
            redirectAttributes.addFlashAttribute("groupId", groupId);
        }
        return "redirect:crmproducts";
    }
}
