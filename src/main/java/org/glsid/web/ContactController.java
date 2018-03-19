package org.glsid.web;

import java.io.File;
import java.io.FileInputStream;


import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.glsid.dao.ContactRepository;
import org.glsid.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller

public class ContactController {
	
	@Autowired
	private ContactRepository contactRepository;
	@Value("${images}")
	private String images;
	
	@RequestMapping(value="/Index") //http://localhost:8080/Index
	public String index(Model model, 
			@RequestParam(name="page", defaultValue="0") int p,
			@RequestParam(name="size", defaultValue="4") int s,
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
	public String added(@Valid Contact contact, BindingResult bindingResult,
			@RequestParam(name="pic") MultipartFile file) throws Exception{
		if(bindingResult.hasErrors()) {
			return "adding";
		}
		if(!(file.isEmpty())) {
			contact.setPhoto(file.getOriginalFilename());
			contactRepository.save(contact);
			file.transferTo(new File(images+contact.getId()));
		}else {
			contactRepository.save(contact);
		}
		return "redirect:Index";
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
	
	@RequestMapping(value="/Updated", method=RequestMethod.POST)
	public String updating(Model model,@Valid Contact contact, 
			BindingResult bindingResult,
		 @RequestParam(name="pic")MultipartFile file) throws Exception {
		if(bindingResult.hasErrors()) {
			return "updating";
		}
		if((!file.isEmpty())) {
			file.transferTo(new File(images+contact.getId()));
		}
		
		contactRepository.save(contact);
			return "redirect:Index";
	}
	
	@RequestMapping(value="/getPhoto", produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto(Long id) throws Exception {
		File f = new File(images+id);
		if(f.exists()) {
			return IOUtils.toByteArray(new FileInputStream(f));
		}else {
			return null;
		}
	}
	
	
//	@RequestMapping(value="/")
//	public String home() {
//		return "redirect:Index";
//	}
	
	@RequestMapping(value="/403")
	public String AccessDeneid() {
		return "403";
	}
	

}
