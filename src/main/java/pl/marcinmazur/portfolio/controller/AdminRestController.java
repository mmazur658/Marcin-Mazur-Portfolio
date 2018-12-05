package pl.marcinmazur.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.marcinmazur.portfolio.service.AdminService;
import pl.marcinmazur.portfolio.service.ContactFormMessageService;
import pl.marcinmazur.portfolio.service.NotificationService;

@RestController
@RequestMapping("/administrator-panel/admin-action")
public class AdminRestController {

	private ContactFormMessageService contactFormMessageService;
	private AdminService adminService;
	private NotificationService notificationService;

	@Autowired
	public AdminRestController(ContactFormMessageService contactFormMessageService, AdminService adminService,
			NotificationService notificationService) {
		this.contactFormMessageService = contactFormMessageService;
		this.adminService = adminService;
		this.notificationService = notificationService;

	}

	@RequestMapping("/get-number-of-notification")
	public long getNumberOfNotification() {
		return notificationService.getNumberOfActiveNotification();
	}

	@RequestMapping("/get-number-of-contact-form-messages")
	public long getNumberOfContactFormMessages() {
		return contactFormMessageService.getNumberOfUnreadContactFormMessage();
	}

	@RequestMapping("/delete-contact-form-message")
	void deleteContactFormMessage(@RequestParam(name = "contactFormMessageId") long contactFormMessageId) {
		contactFormMessageService.deleteContactFormMessage(contactFormMessageId);
	}

	@RequestMapping("/change-contact-form-message-read-status")
	void changeContactFormMessageReadStatus(@RequestParam(name = "selectedCheckboxValue") long selectedCheckboxValue) {
		contactFormMessageService.changeContactFormMessageReadStatus(selectedCheckboxValue);
	}

	@RequestMapping("/change-contact-form-message-replied-status")
	void changeContactFormMessageRepliedStatus(
			@RequestParam(name = "selectedCheckboxValue") long selectedCheckboxValue) {
		contactFormMessageService.changeContactFormMessageRepliedStatus(selectedCheckboxValue);
	}

	@RequestMapping("/change-contact-form-message-read-status-to-true")
	void setContactFormMessageReadStatusTrue(@RequestParam(name = "selectedCheckboxValue") long selectedCheckboxValue) {
		contactFormMessageService.setContactFormMessageReadStatusTrue(selectedCheckboxValue);
	}

	@RequestMapping("/add-comment")
	void addComment(@RequestParam(name = "messageId") long messageId,
			@RequestParam(name = "commentText") String commentText) {
		contactFormMessageService.addComment(messageId, commentText);
	}

	@RequestMapping("/delete-comment")
	void deleteComment(@RequestParam(name = "contactFormCommentId") long commentId) {
		contactFormMessageService.deleteComment(commentId);
	}

	@RequestMapping("check-password")
	boolean isPasswordCorrect(@RequestParam(name = "oldPassword") String oldPassword,
			@RequestParam(name = "username") String username) {
		return adminService.checkPassword(oldPassword, username);
	}

	@RequestMapping("change-password")
	void changePassword(@RequestParam(name = "newPassword") String newPassword,
			@RequestParam(name = "username") String username) {
		adminService.changePassword(newPassword, username);

	}

}
