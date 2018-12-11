package pl.marcinmazur.portfolio.dao;

import java.util.List;

import pl.marcinmazur.portfolio.entity.Comment;
import pl.marcinmazur.portfolio.entity.ContactFormMessage;

/**
 * Repository class for performing database operations on ContactFormMessage and
 * Comment objects.
 * 
 * @author Marcin Mazur
 *
 */
public interface ContactFormMessageDao {

	/**
	 * Saves the ContactFormMessage in the database
	 * 
	 * @param contactFormMessage
	 *            The ContactFormMessage to be saved
	 */
	void sendMessage(ContactFormMessage contactFormMessage);

	/**
	 * Saves the comment in the database
	 * 
	 * @param comment
	 *            The Comment to be saved
	 */
	void addComment(Comment comment);

	/**
	 * Deletes the Comment with the given id
	 * 
	 * @param commentId
	 *            The long containing the id of the comment to be deleted
	 */
	void deleteComment(long commentId);

	/**
	 * Return the ContactFormMessage with the given id
	 * 
	 * @param contactFormMessageId
	 *            The Long containing the id of the contact form messages
	 * @return A ContactFormMessage representing the contactFormMessage with the
	 *         given id
	 */
	ContactFormMessage getContactFormMessage(Long contactFormMessageId);

	/**
	 * Returns the list of ContactFormMessage for given type of the list
	 * 
	 * @param listType
	 *            A String containing the type of the list (new-archive-all)
	 * @param resultStartRange
	 *            An Integer containing the first returning index
	 * @param resultRange
	 *            An Integer containing the number of results
	 * @return A List&lt;ContactFormMessage&gt; representing the list of
	 *         contactFormMessages
	 */
	List<ContactFormMessage> getListOfContactFormMessages(String listType, Integer resultStartRange,
			Integer resultRange);

	/**
	 * Returns the number of all unread contact form messages
	 * 
	 * @return A long representing the number of all unread contact form messages
	 */
	long getNumberOfUnreadContactFormMessages();

	/**
	 * Returns the number of contact form messages for the given type of the list
	 * 
	 * @param listType
	 *            A String containing the type of the list (new-archive-all)
	 * @return A long representing the number of contact form messages
	 */
	long getNumberOfContactFormMessagesForGivenListTpe(String listType);

	/**
	 * Returns the number of all contact form messages from the given date range
	 * 
	 * @param startDate
	 *            The String containing the first day of the range
	 * @param endDate
	 *            The String containing the last day of the range
	 * @return A long representing the number of contact form messages
	 */
	long getNumberOfContactFormMessagesForGivenDate(String startDate, String endDate);

	/**
	 * Returns the number of active, unread contact form messages from the given
	 * date range
	 * 
	 * @param startDate
	 *            The String containing the first day of the range
	 * @param endDate
	 *            The String containing the last day of the range
	 * @return A long representing the number of contact form messages
	 */
	long getNumberOfUnreadContactFormMessagesForGivenDate(String startDate, String endDate);

	/**
	 * Returns the number of unread contact form messages from the given date to the
	 * present
	 * 
	 * @param stringCurrentDate
	 *            The String containing the start date of the range
	 * @return A long representing the number of contact form messages
	 */
	long getNumberOfUnreadContactFormMessagesForGivenDate(String stringCurrentDate);

	/**
	 * Returns the number of contact form messages with given date range
	 * 
	 * @param startDate
	 *            The String containing the first day of the range
	 * @param endDate
	 *            The String containing the last day of the range
	 * @return A long representing the number of contact form messages
	 */
	long getNumberOfContactFormMessageForGivenDate(String startDate, String endDate);

	/**
	 * Returns the date and the number of contact form messages as a list of
	 * Object[] for given date range
	 * 
	 * @param startDate
	 *            The String containing the first day of the month
	 * @param endDate
	 *            The String containing the last day of the month
	 * @return A List&lt;Object[]&gt; representing the list of date and number of
	 *         contact form messages
	 */
	List<Object[]> getMonthlyMessagesData(String startDate, String endDate);

}
