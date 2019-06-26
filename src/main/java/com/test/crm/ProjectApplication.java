package com.test.crm;

import com.test.crm.entity.Group;
import com.test.crm.service.GroupService;
import com.test.crm.service.ProductService;
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

	public ProjectApplication(GroupService groupService, ProductService productService) {
		this.productService = productService;
		this.groupService = groupService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	private void testJpaMethods(){
		groupService.createGroup(new Group("name", new Date()));
		groupService.findAll().forEach(System.out::println);
	}

}
