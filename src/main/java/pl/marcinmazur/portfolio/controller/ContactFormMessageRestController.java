package pl.marcinmazur.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.marcinmazur.portfolio.service.ContactFormMessageService;

@RestController
@RequestMapping("/contact-form-message")
public class ContactFormMessageRestController {

	private ContactFormMessageService contactFormMessageService;

	@Autowired
	public ContactFormMessageRestController(ContactFormMessageService contactFormMessageService) {
		this.contactFormMessageService = contactFormMessageService;
	}

	@RequestMapping("/sent-message")
	public void createContactFormMessage(@RequestParam(name = "senderName") String senderName,
			@RequestParam(name = "senderEmail") String senderEmail,
			@RequestParam(name = "messageSubject") String messageSubject,
			@RequestParam(name = "messageText") String messageText) {

		contactFormMessageService.sendMessage(senderEmail, senderName, messageText, messageSubject);

	}
}
