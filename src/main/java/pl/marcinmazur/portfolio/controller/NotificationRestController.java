package pl.marcinmazur.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.marcinmazur.portfolio.service.NotificationService;

@RestController
@RequestMapping("/administrator-panel/notifications")
public class NotificationRestController {

	private NotificationService notificationService;

	@Autowired
	public NotificationRestController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	@RequestMapping("/delete-notification")
	void deleteNotification(@RequestParam(name = "notificationId") int notificationId) {
		notificationService.deleteNotification(notificationId);
	}
}
