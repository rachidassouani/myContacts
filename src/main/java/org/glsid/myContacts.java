package org.glsid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class myContacts {

	public static void main(String[] args) {
		SpringApplication.run(myContacts.class, args);
		
//		ApplicationContext ctx = 
//		ContactRepository contactRepository = ctx.getBean(ContactRepository.class);
//		contactRepository.save(new Contact("rachid", "assouani", new Date(), "rachid@gmail.com","Sale N45","rachid.png"));
//		contactRepository.save(new Contact("zaki", "ios", new Date(), "zaki@gmail.com","Sale N45", "zaki.png"));
//		contactRepository.save(new Contact("haitam", "hamdan", new Date(), "haitam@gmail.com","Sale N45", "haitam.png"));
			
	}
}
