package org.glsid;

import java.util.Date;


import org.glsid.dao.ContactRepository;
import org.glsid.entities.Contact;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class myContacts {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(myContacts.class, args);
		ContactRepository contactRepository = ctx.getBean(ContactRepository.class);
		contactRepository.save(new Contact("rachid", "assouani", new Date(), "rachid@gmail.com","Sale N45"));
		contactRepository.save(new Contact("zaki", "ios", new Date(), "zaki@gmail.com","Sale N45"));
		contactRepository.save(new Contact("haitam", "hamdan", new Date(), "haitam@gmail.com","Sale N45"));
			
	}
}
