package com.test.familytree;

import com.test.familytree.service.UserInteractionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FamilytreeApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext =SpringApplication.run(FamilytreeApplication.class, args);

		UserInteractionService userInteractionService = applicationContext.getBean(UserInteractionService.class);
		userInteractionService.interactWithUser();
	}

}
