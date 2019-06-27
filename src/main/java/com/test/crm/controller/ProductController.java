package com.test.crm.controller;

import com.test.crm.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {
    @Autowired
    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/crmproducts", method = RequestMethod.GET)
    public ModelAndView crmproducts() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("crmproducts");
        mav.addObject("productsList", productService.findAll());
        return mav;
    }
    //TODO доделать метод
    @RequestMapping(value = "/crmproductsredirect", method = RequestMethod.GET)
    public ModelAndView crmproductsRedirect(@ModelAttribute String postId) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("crmproducts");
        mav.addObject("productsList", productService.findAllByGroupId(Long.valueOf(postId)));
        return mav;
    }
}
