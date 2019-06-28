package com.test.crm.controller;

import com.test.crm.entity.Product;
import com.test.crm.service.GroupServiceImpl;
import com.test.crm.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
        mav.addObject("product", new Product());
        mav.addObject("errorGroupId", groupId);
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
    public ModelAndView saveProduct(@ModelAttribute("product") Product product, Model model) {
        productService.createProduct(product);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("edit_product");
        mav.addObject("product", product);
        return mav;
    }
    @RequestMapping(value = "/crmproducts", method = RequestMethod.POST)
    public ModelAndView createProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult,
                                @ModelAttribute("errorGroupId") String groupId, Model model) {
        ModelAndView mav = new ModelAndView();
        if(bindingResult.hasErrors()) {
            mav.setViewName("crmproducts");
            mav.addObject("groupId", groupId);
            return mav;
        }
        if (product.getArticle() != null && !product.getArticle().isEmpty()
                && !productService.findAllByArticle(product.getArticle()).isEmpty()) {
            FieldError fieldError = new FieldError("product", "article", "not unique article");
            bindingResult.addError(fieldError);
            mav.setViewName("crmproducts");
            mav.addObject("groupId", groupId);
            return mav;
        }

        if (product.getGroupId() != null
                && groupService.findById(product.getGroupId().getId()) == null) {
            FieldError fieldError = new FieldError("product", "groupId", "not valid groupId");
            bindingResult.addError(fieldError);
            mav.setViewName("crmproducts");
            mav.addObject("groupId", groupId);
            return mav;
        }
        productService.createProduct(product);
        mav.setViewName("redirect:/crmproducts?groupId=" + product.getGroupId().getId());
        return mav;
    }
}
