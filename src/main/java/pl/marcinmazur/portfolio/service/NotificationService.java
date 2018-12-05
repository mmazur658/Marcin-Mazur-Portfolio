package pl.marcinmazur.portfolio.service;

import java.util.List;

import pl.marcinmazur.portfolio.entity.Notification;

public interface NotificationService {
	
	List<Notification> getActiveNotifications();

	long getNumberOfActiveNotification();

	void deleteNotification(int notificationId);

	void createNotificationAfterFirstCodeUsage(String accessCodeValue);

}
