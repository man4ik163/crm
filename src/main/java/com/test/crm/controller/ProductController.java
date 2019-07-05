package com.test.crm.controller;

import com.test.crm.messaging.Producer;
import com.test.crm.model.Product;
import com.test.crm.model.ProductStorage;
import com.test.crm.service.GroupService;
import com.test.crm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private ProductStorage productStorage;

    @Autowired
    private Producer producer;

    @RequestMapping(value = "/crmproducts/{groupId}", method = RequestMethod.GET)
    public String listGroups(@PathVariable Long groupId, Model model, Pageable pageable) {
        Page<Product> productPage = productService.findAllPages(groupService.findById(groupId), pageable);
        PageWrapper<Product> page = new PageWrapper<Product>(productPage, "/crmproducts");
        model.addAttribute("crmproducts", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("groupId", groupId);
        return "crmproducts";
    }

    @RequestMapping(value = "/crmproduct/delete/{id}")
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
        return "redirect:/crmproducts/" + groupId;
    }

    @RequestMapping("crmproduct/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("groupId", product.getGroupId().getId());
        return "product_edit_form";
    }

    @RequestMapping("crmproduct/create/{groupId}")
    public String createProduct(@PathVariable Long groupId, Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("groupId", groupId);
        return "product_create_form";
    }

    @RequestMapping("save_product/{groupId}")
    public String saveProduct(@Valid Product product, BindingResult bindingResult,@PathVariable Long groupId, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("product", product);
            model.addAttribute("groupId", groupId);
            return getMapping(product);
        }
        if(groupService.findById(product.getGroupId().getId()) == null){
            FieldError fieldError = new FieldError("product", "groupId", "not valid groupId");
            bindingResult.addError(fieldError);
            return getMapping(product);
        }
        if(product.getArticle() != null &&
            !productService.findAllByArticle(product.getArticle()).isEmpty() &&
            !product.getId().equals(productService.findByArticle(product.getArticle()).getId())){
            FieldError fieldError = new FieldError("product", "article", "not unique article");
            bindingResult.addError(fieldError);
            return getMapping(product);
        }
        productService.createProduct(product);
        return "redirect:/crmproducts/" + product.getGroupId().getId();
    }

    private String getMapping(Product product){
        if(product.getId() != null){
            return "product_edit_form";
        }
        if(product.getId() == null){
            return "product_create_form";
        }
        return "redirect:crmgroups/";
    }
}

