package pl.marcinmazur.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.marcinmazur.portfolio.service.ContactFormMessageService;

/**
 * The rest controller class is used to perform actions depending on the user
 * request.
 * 
 * @author Marcin Mazur
 *
 */
@RestController
@RequestMapping("/contact-form-message")
public class ContactFormMessageRestController {

	/**
	 * The ContactFormMessageService interface
	 */
	private ContactFormMessageService contactFormMessageService;

	/**
	 * Constructs a ContactFormMessageRestController with the
	 * ContactFormMessageService.
	 * 
	 * @param contactFormMessageService
	 *            The ContactFormMessageService interface
	 */
	@Autowired
	public ContactFormMessageRestController(ContactFormMessageService contactFormMessageService) {
		this.contactFormMessageService = contactFormMessageService;
	}

	/**
	 * Saves the contact form message with the given parameters.
	 * 
	 * @param senderName
	 *            The String containing the name of the user
	 * @param senderEmail
	 *            The String containing the email of the user
	 * @param messageSubject
	 *            The String containing the subject of the message
	 * @param messageText
	 *            The String containing the text of the message
	 */
	@RequestMapping("/sent-message")
	public void createContactFormMessage(@RequestParam(name = "senderName") String senderName,
			@RequestParam(name = "senderEmail") String senderEmail,
			@RequestParam(name = "messageSubject") String messageSubject,
			@RequestParam(name = "messageText") String messageText) {

		contactFormMessageService.sendMessage(senderEmail, senderName, messageText, messageSubject);

	}
}
