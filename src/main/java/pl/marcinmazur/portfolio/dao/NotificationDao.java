package pl.marcinmazur.portfolio.dao;

import java.util.List;

import pl.marcinmazur.portfolio.entity.Notification;

public interface NotificationDao {
	
	Notification getNotificationById(int notificationId);
	
	List<Notification> getListOfNotificationsForGivenDate(String startDate, String endDate);
	
	long getSumOfActiveNotifications();
	
	void saveNotification(Notification notification);

	List<Notification> getListOfActiveNotifications();
	

}
