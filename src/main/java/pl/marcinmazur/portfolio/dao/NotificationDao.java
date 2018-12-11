package pl.marcinmazur.portfolio.dao;

import java.util.List;

import pl.marcinmazur.portfolio.entity.Notification;

/**
 * Interface for performing database operations on Notification objects.
 * 
 * @author Marcin Mazur
 *
 */
public interface NotificationDao {

	/**
	 * Returns the notification with the given id
	 * 
	 * @param notificationId
	 *            The int containing the id of notification
	 * @return A Notification representing the notification with the given id
	 */
	Notification getNotificationById(int notificationId);

	/**
	 * Returns the list of Notification for given date range
	 * 
	 * @param startDate
	 *            The String containing the first day of the range
	 * @param endDate
	 *            The String containing the last day of the range
	 * @return A List&lt;Notification&gt; representing the list of notification for
	 *         given date range
	 */
	List<Notification> getListOfNotificationsForGivenDate(String startDate, String endDate);

	/**
	 * Returns the number of all active notifications
	 * 
	 * @return A long representing the number of all active notifications
	 */
	long getSumOfActiveNotifications();

	/**
	 * Saves Notification in the database
	 * 
	 * @param notification
	 *            The Notification to be saved.
	 */
	void saveNotification(Notification notification);

	/**
	 * Returns the list of all active notifications
	 * 
	 * @return A List&lt;Notification&gt; representing the list of all active
	 *         notifications
	 */
	List<Notification> getListOfActiveNotifications();

}
