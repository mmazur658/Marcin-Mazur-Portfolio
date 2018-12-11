package pl.marcinmazur.portfolio.service;

import java.util.List;

import pl.marcinmazur.portfolio.entity.Notification;

/**
 * Interface for managing notifications.
 * 
 * @author Marcin Mazur
 *
 */
public interface NotificationService {

	/**
	 * Returns the list of all Notifications
	 * 
	 * @return A List&lt;Notification&gt; representing the list of the notifications
	 */
	List<Notification> getActiveNotifications();

	/**
	 * Returns the number of all active notifications
	 * 
	 * @return A long representing the number of all active notifications
	 */
	long getNumberOfActiveNotification();

	/**
	 * Deletes the notification with given id <br>
	 * Changes the notification isActuve status to false
	 * 
	 * @param notificationId
	 *            The int containing the id of the notification
	 */
	void deleteNotification(int notificationId);

	/**
	 * Creates the notification after first access code using
	 * 
	 * @param accessCodeValue
	 *            The String containing the value of the access code
	 */
	void createNotificationAfterFirstCodeUsing(String accessCodeValue);

}
