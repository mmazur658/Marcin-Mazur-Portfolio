package pl.marcinmazur.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.marcinmazur.portfolio.service.AdminService;
import pl.marcinmazur.portfolio.service.ContactFormMessageService;
import pl.marcinmazur.portfolio.service.NotificationService;

/**
 * The rest controller class is used to perform actions depending on the user
 * request.
 * 
 * @author Marcin Mazur
 *
 */
@RestController
@RequestMapping("/administrator-panel/admin-action")
public class AdminRestController {

	/**
	 * The ContactFormMessageService interface
	 */
	private ContactFormMessageService contactFormMessageService;

	/**
	 * The AdminService interface
	 */
	private AdminService adminService;

	/**
	 * The NotificationService interface
	 */
	private NotificationService notificationService;

	/**
	 * Constructs a AdminRestController with the ContactFormMessageService,
	 * AdminService and NotificationService.
	 * 
	 * @param contactFormMessageService
	 *            The ContactFormMessageService interface
	 * @param adminService
	 *            The AdminService interface
	 * @param notificationService
	 *            The NotificationService interface
	 */
	@Autowired
	public AdminRestController(ContactFormMessageService contactFormMessageService, AdminService adminService,
			NotificationService notificationService) {
		this.contactFormMessageService = contactFormMessageService;
		this.adminService = adminService;
		this.notificationService = notificationService;

	}

	/**
	 * Returns the number of active notifications.
	 * 
	 * @return A long representing the number of active notifications.
	 */
	@RequestMapping("/get-number-of-notification")
	public long getNumberOfNotification() {
		return notificationService.getNumberOfActiveNotification();
	}

	/**
	 * Returns the number of unread contact form messages.
	 * 
	 * @return A long representing the number of unread messages
	 */
	@RequestMapping("/get-number-of-contact-form-messages")
	public long getNumberOfContactFormMessages() {
		return contactFormMessageService.getNumberOfUnreadContactFormMessage();
	}

	/**
	 * Deletes the contact form messages with given id
	 * 
	 * @param contactFormMessageId
	 *            The long containing the id of the message to be deleted
	 */
	@RequestMapping("/delete-contact-form-message")
	void deleteContactFormMessage(@RequestParam(name = "contactFormMessageId") long contactFormMessageId) {
		contactFormMessageService.deleteContactFormMessage(contactFormMessageId);
	}

	/**
	 * Changes the isRead status of the message with the given id.
	 * 
	 * @param contactFormMessageId
	 *            The long containing the id of the message
	 */
	@RequestMapping("/change-contact-form-message-read-status")
	void changeContactFormMessageReadStatus(@RequestParam(name = "selectedCheckboxValue") long contactFormMessageId) {
		contactFormMessageService.changeContactFormMessageIsReadStatus(contactFormMessageId);
	}

	/**
	 * Changes the isReplied status of the message with the given id.
	 * 
	 * @param contactFormMessageId
	 *            The long containing the id of the message
	 */
	@RequestMapping("/change-contact-form-message-replied-status")
	void changeContactFormMessageRepliedStatus(
			@RequestParam(name = "selectedCheckboxValue") long contactFormMessageId) {
		contactFormMessageService.changeContactFormMessageRepliedStatus(contactFormMessageId);
	}

	/**
	 * Changes the isRead status of the message with the given id on TRUE.
	 * 
	 * @param contactFormMessageId
	 *            The long containing the id of the message
	 */
	@RequestMapping("/change-contact-form-message-read-status-to-true")
	void setContactFormMessageReadStatusTrue(@RequestParam(name = "selectedCheckboxValue") long contactFormMessageId) {
		contactFormMessageService.setContactFormMessageReadStatusTrue(contactFormMessageId);
	}

	/**
	 * Adds a Comment with the given text to the message with the given id.
	 * 
	 * @param messageId
	 *            The long containing the id of the message
	 * @param commentText
	 *            The String containing the text of the comment
	 */
	@RequestMapping("/add-comment")
	void addComment(@RequestParam(name = "messageId") long messageId,
			@RequestParam(name = "commentText") String commentText) {
		contactFormMessageService.addComment(messageId, commentText);
	}

	/**
	 * Deletes the Comment with the given id.
	 * 
	 * @param commentId
	 *            The long containing the id of the comment to be deleted
	 */
	@RequestMapping("/delete-comment")
	void deleteComment(@RequestParam(name = "contactFormCommentId") long commentId) {
		contactFormMessageService.deleteComment(commentId);
	}

	/**
	 * Returns TRUE if the given password is correct.
	 * 
	 * @param oldPassword
	 *            The String containing the old password
	 * @param username
	 *            The String containing the name of the user
	 * @return A boolean representing the result
	 */
	@RequestMapping("check-password")
	boolean isPasswordCorrect(@RequestParam(name = "oldPassword") String oldPassword,
			@RequestParam(name = "username") String username) {
		return adminService.checkPassword(oldPassword, username);
	}

	/**
	 * Changes the user's password.
	 * 
	 * @param newPassword
	 *            The String containing the new password
	 * @param username
	 *            The String containing the name of the user
	 */
	@RequestMapping("change-password")
	void changePassword(@RequestParam(name = "newPassword") String newPassword,
			@RequestParam(name = "username") String username) {
		adminService.changePassword(newPassword, username);

	}

}
