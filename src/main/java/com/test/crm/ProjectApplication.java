package com.test.crm;

import com.test.crm.entity.Group;
import com.test.crm.entity.Product;
import com.test.crm.service.GroupService;
import com.test.crm.service.GroupServiceImpl;
import com.test.crm.service.ProductService;
import com.test.crm.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Date;


@EnableAutoConfiguration
@SpringBootApplication
public class ProjectApplication {
    @Autowired
    private final GroupService groupService;

    @Autowired
    private final ProductService productService;

    public ProjectApplication(GroupServiceImpl groupService, ProductServiceImpl productService) {
        this.productService = productService;
        this.groupService = groupService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    private void testJpaMethods() {
//        Product product = new Product();
//        product.setName("name1");
//        groupService.findAll().forEach(System.out::println);
    }

}
