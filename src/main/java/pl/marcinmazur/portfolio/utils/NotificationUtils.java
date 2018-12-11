package pl.marcinmazur.portfolio.utils;

import pl.marcinmazur.portfolio.entity.AccessCode;
import pl.marcinmazur.portfolio.entity.Notification;

public interface NotificationUtils {

	/**
	 * Creates and returns a Notification after first code using.
	 * 
	 * @param theAccessCode
	 *            The AccessCode containing the AccessCode
	 * @return A Notification representing the Notification with the message
	 */
	Notification createNotificationAfterFirstCodeUsage(AccessCode theAccessCode);

	/**
	 * Create and returns a Notification with daily report
	 * 
	 * @param numberOfMessages
	 *            The long containing the number of messages
	 * @param numberOfUnreadMessages
	 *            The long containing the number of unread messages
	 * @param stringCurrentDate
	 *            The String containing the date of yesterday
	 * @return A Notification representing the Notification with the report
	 */
	Notification createDailyMessageRaport(long numberOfMessages, long numberOfUnreadMessages, String stringCurrentDate);

	/**
	 * Create and returns a Notification if there are messages unread longer than 1
	 * hour.
	 * 
	 * @param numberOfUnreadMessage
	 *            The long containing the number of unread messages
	 * @return A Notification representing the Notification with the message
	 */
	Notification createUnreadMessagesNotification(long numberOfUnreadMessage);
}
