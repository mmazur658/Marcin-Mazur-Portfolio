package pl.marcinmazur.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.marcinmazur.portfolio.entity.Notification;
import pl.marcinmazur.portfolio.service.NotificationService;

/**
 * The controller class is used to return the view depending on the user
 * request. This controller contains the views of: <br>
 * <ul>
 * <li>"parts/notification-modal"</li>
 * </ul>
 * 
 * @author Marcin Mazur
 *
 */
@Controller
@RequestMapping("/administrator-panel/notifications")
public class NotificationController {

	/*
	 * The NotificationService interface
	 */
	private NotificationService notificationService;

	/**
	 * Constructs a NotificationController with the NotificationService.
	 * 
	 * @param notificationService
	 *            The NotificationService interface
	 */
	@Autowired
	public NotificationController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	/**
	 * Returns the view of "parts/notification-modal" with model attribute:<br>
	 * <ul>
	 * <li>notificationList - The list of all active Notifications</li>
	 * </ul>
	 * 
	 * @param theModel
	 *            The Model containing the data passed to the view
	 * @return The String representing the name of the view
	 */
	@RequestMapping("/notification-list")
	public String showNotificationModal(Model theModel) {

		List<Notification> notificationList = notificationService.getActiveNotifications();
		theModel.addAttribute("notificationList", notificationList);

		return "parts/notification-modal";
	}

}
