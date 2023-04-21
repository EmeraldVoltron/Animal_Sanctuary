package animal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import animal.beans.ContactForm;
import animal.repository.AdopterRepository;
import animal.repository.ContactMessageRepository;
import animal.beans.ContactMessage;

@Controller
public class ContactController {
	
	@Autowired
	private ContactMessageRepository contactMessageRepository;
    
	@GetMapping("/contact")
	public String showContactForm(Model model) {
	    model.addAttribute("contactForm", new ContactForm());
	    return "contact";
	}
	
	@PostMapping("/contact")
	public String submitContactForm(@ModelAttribute("contactForm") ContactForm contactForm, Model model) {
	    // validate the form input
	    if (contactForm.getName().isEmpty() || contactForm.getEmail().isEmpty() || contactForm.getMessage().isEmpty()) {
	        model.addAttribute("errorMsg", "Please fill in all the required fields.");
	        return "contact";
	    }
	    
	    // save message to database
	    ContactMessage contactMessage = new ContactMessage();
	    contactMessage.setName(contactForm.getName());
	    contactMessage.setEmail(contactForm.getEmail());
	    contactMessage.setReason(contactForm.getReason());
	    contactMessage.setAnimalName(contactForm.getAnimalName());
	    contactMessage.setMessage(contactForm.getMessage());
	    contactMessageRepository.save(contactMessage);
	    
	    model.addAttribute("successMsg", "Your message has been sent.");
	    return "contact";
	}
}
