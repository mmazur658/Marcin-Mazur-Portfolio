package pl.marcinmazur.portfolio.service;

/**
 * Interface for managing reports and reminders.
 * 
 * @author Marcin Mazur
 *
 */
public interface RaportAndReminderService {

	/**
	 * Returns the number of all messages for given day
	 * 
	 * @param stringCurrentDate
	 *            The String containing the date yyyy-MM-dd
	 * @return A long representing the number of all messages
	 */
	long getNumberOfMessagesForGivenDate(String stringCurrentDate);

	/**
	 * Returns the number of the unread messages for given day
	 * 
	 * @param stringCurrentDate
	 *            The String containing the date yyyy-MM-dd
	 * @return A long representing the number of the unread messages
	 */
	long getNumberOfUnreadMessagesForGivenDate(String stringCurrentDate);

	/**
	 * Creates the notification with the daily message report for given day
	 * 
	 * @param stringCurrentDate
	 *            The String containing the date yyyy-MM-dd mm:ss
	 */
	void createDailyMessageRaport(String stringCurrentDate);

	/**
	 * Creates the notification if the message is unread longer than 1 hour
	 * 
	 * @param stringCurrentDate
	 *            The String containing the date yyyy-MM-dd mm:ss
	 */
	void lookForUnreadMessages(String stringCurrentDate);

}
