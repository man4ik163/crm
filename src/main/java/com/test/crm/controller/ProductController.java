package com.test.crm.controller;

import com.test.crm.entity.Product;
import com.test.crm.service.GroupServiceImpl;
import com.test.crm.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {
    @Autowired
    private final ProductServiceImpl productService;

    @Autowired
    private final GroupServiceImpl groupService;

    public ProductController(ProductServiceImpl productService, GroupServiceImpl groupService) {
        this.productService = productService;
        this.groupService = groupService;
    }

    @RequestMapping(value = "/crmproducts", method = RequestMethod.GET)
    public ModelAndView crmProducts(@RequestParam(name="groupId")String groupId) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("crmproducts");
        mav.addObject("productsList", productService.findAllByGroupId(groupService.findById(Long.valueOf(groupId))));
        return mav;
    }

    @RequestMapping(value = "/delete_product", method = RequestMethod.GET)
    public String deleteProduct(@RequestParam(name="productId")String productId) {
        Product product = productService.findById(Long.valueOf(productId));
        Long groupId = product.getGroupId().getId();
        productService.deleteProduct(product);
        return "redirect:/crmproducts?groupId=" + groupId;
    }

    @RequestMapping(value = "/edit_product", method = RequestMethod.GET)
    public ModelAndView editProduct(@RequestParam(name="productId")String productId) {
        Product product = productService.findById(Long.valueOf(productId));
        ModelAndView mav = new ModelAndView();
        mav.setViewName("edit_product");
        mav.addObject("product", product);
        return mav;
    }

    @RequestMapping(value = "/save_product", method = RequestMethod.POST)
    public ModelAndView saveProduct(@ModelAttribute Product product, Model model) {
        productService.createProduct(product);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("edit_product");
        mav.addObject("product", product);
        return mav;
    }
}
