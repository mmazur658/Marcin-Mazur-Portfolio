package pl.marcinmazur.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.marcinmazur.portfolio.entity.Notification;
import pl.marcinmazur.portfolio.service.NotificationService;

@Controller
@RequestMapping("/administrator-panel/notifications")
public class NotificationController {

	private NotificationService notificationService;

	@Autowired
	public NotificationController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	@RequestMapping("/notification-list")
	public String showNotificationModal(Model theModel) {

		List<Notification> notificationList = notificationService.getActiveNotifications();
		theModel.addAttribute("notificationList", notificationList);

		return "parts/notification-modal";
	}

}
