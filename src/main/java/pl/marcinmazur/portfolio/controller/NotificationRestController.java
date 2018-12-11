package pl.marcinmazur.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.marcinmazur.portfolio.service.NotificationService;

/**
 * The rest controller class is used to perform actions depending on the user
 * request.
 * 
 * @author Marcin Mazur
 *
 */
@RestController
@RequestMapping("/administrator-panel/notifications")
public class NotificationRestController {

	/**
	 * The NotificationService interface
	 */
	private NotificationService notificationService;

	/**
	 * Constructs a NotificationRestController with the NotificationService.
	 * 
	 * @param notificationService
	 *            The NotificationService interface
	 */
	@Autowired
	public NotificationRestController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	/**
	 * Deletes the notification with the given id.
	 * 
	 * @param notificationId
	 *            The int containing the id of the notification
	 */
	@RequestMapping("/delete-notification")
	void deleteNotification(@RequestParam(name = "notificationId") int notificationId) {
		notificationService.deleteNotification(notificationId);
	}
}
