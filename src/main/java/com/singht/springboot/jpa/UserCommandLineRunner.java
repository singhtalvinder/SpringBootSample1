package com.singht.springboot.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// Invoked as soon as the sb app launches.
@Component
public class UserCommandLineRunner implements CommandLineRunner {
	
	// using logger.
	private static final Logger log = LoggerFactory.getLogger(UserCommandLineRunner.class);

    @Autowired
	private UserRepository repository1;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("UserCommandLineRunner");
		if(repository1 != null) 
		{
			repository1.save(new User("Ashmeet", "Admin"));
			repository1.save(new User("Sukhleen", "User"));
			repository1.save(new User("Harpreet", "Admin"));
			repository1.save(new User("Mandeep", "User"));
			
			for(User user : repository1.findAll()) {
				log.info(user.toString());
			}
			
			log.info("Admin users are" );
			for(User admin : repository1.findByRole("Admin")) {
				log.info("*******  " +admin.toString());
			}
		}
	}
}
