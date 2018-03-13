package org.glsid.web;





import javax.validation.Valid;

import org.glsid.dao.ContactRepository;
import org.glsid.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/Contacts")
public class ContactController {
	@Autowired
	private ContactRepository contactRepository;
	
	
	@RequestMapping(value="/Index") //http://localhost:8080/Index
	public String index(Model model, 
			@RequestParam(name="page", defaultValue="0") int p,
			@RequestParam(name="size", defaultValue="5") int s,
			@RequestParam(name="motCle", defaultValue="") String mc) {
		
		Page<Contact> pageContacts = contactRepository.getNames("%"+mc+"%",new PageRequest(p, s));
		model.addAttribute("pageContacts", pageContacts);
		
		int totalPages = pageContacts.getTotalPages();
		
		int [] pagesCount = new int[totalPages];
		for(int i=0; i<totalPages; i++) {
			pagesCount[i] = i;
		}
		model.addAttribute("pagesCount", pagesCount);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);
		return "contacts";
	}
	
	
	@RequestMapping(value="/Adding", method=RequestMethod.GET)
	public String adding(Model model) {
		Contact contact = new Contact();
		model.addAttribute("contact", contact);
		return "adding";
	}
	
	@RequestMapping(value="/Added", method= RequestMethod.POST)
	public String added(@Valid Contact contact, BindingResult bindingResult ) {
		if(bindingResult.hasErrors()) {
			return "adding";
		}else {
			contactRepository.save(contact);
			return "redirect:Index";
		}
		
	}
	
	
	@RequestMapping(value="/Delete")
	public String delete(@RequestParam(name="id") Long id) {
		
		contactRepository.delete(id);
		return "redirect:Index";
	}
	
	@RequestMapping(value="/Updating")
	public String updating(Model model, Long id) {
		
		Contact contact = contactRepository.getOne(id);
		model.addAttribute("contact", contact);
		return "updating";
	}
	
	@RequestMapping(value="/Updated")
	public String updating(Model model, Contact contact) {
		
	    contactRepository.save(contact);
		
		return "redirect:Index";
	}
}
