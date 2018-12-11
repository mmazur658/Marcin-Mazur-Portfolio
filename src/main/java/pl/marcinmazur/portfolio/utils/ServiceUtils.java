package pl.marcinmazur.portfolio.utils;

import pl.marcinmazur.portfolio.entity.Comment;
import pl.marcinmazur.portfolio.entity.ContactFormMessage;
import pl.marcinmazur.portfolio.entity.ProjectVisitingHistory;
import pl.marcinmazur.portfolio.entity.Task;

/**
 * Interface used to perform operations for service classes.
 * 
 * @author Marcin Mazur
 *
 */
public interface ServiceUtils {

	/**
	 * Creates and returns a ContactFormMessage with given parameters.
	 * 
	 * @param senderEmail
	 *            The String containing the email of the sender
	 * @param senderName
	 *            The String containing the name of the sender
	 * @param messageText
	 *            The String containing the text of the message
	 * @param messageSubject
	 *            The String containing the subject of the message
	 * @return A ContactFormMessage representing the ContactFormMessage with given
	 *         parameters
	 */
	ContactFormMessage createContactFormMessage(String senderEmail, String senderName, String messageText,
			String messageSubject);

	/**
	 * Returns the HQL Statement with given parameters. <br>
	 * The hqlType should contain "FROM" word, the name of the entity and the space
	 * char as a last character of the string.
	 * 
	 * @param hqlType
	 *            The String containing the begging of the HQL
	 * @param listType
	 *            The String containing the type of the list
	 * @return A String representing the created HQL
	 */
	String prepareHqlDependsOnListType(String hqlType, String listType);

	/**
	 * Creates and returns the Comment with given text and associated
	 * ContactFormMessage.
	 * 
	 * @param commentText
	 *            The String containing the text of the Comment
	 * @param contactFormMessage
	 *            The ContactFormMessage associated with the Comment
	 * @return A Comment representing the Comment with given text and associated
	 *         ContactFormMessage
	 */
	Comment createComment(String commentText, ContactFormMessage contactFormMessage);

	/**
	 * Changes the isActive and isReaded status of given ContactFormMessage
	 * 
	 * @param contactFormMessage
	 *            The ContactFormMessage object
	 */
	void changeIsActiveAndIsReadedStatus(ContactFormMessage contactFormMessage);

	/**
	 * Changes the isReplied status of given ContactFormMessage
	 * 
	 * @param contactFormMessage
	 *            The ContactFormMessage object
	 */
	void changeIsRepliedStatus(ContactFormMessage contactFormMessage);

	/**
	 * Changes the IsRead status of given ContactFormMessage
	 * 
	 * @param contactFormMessage
	 *            The ContactFormMessage object
	 */
	void changeIsReadedStatus(ContactFormMessage contactFormMessage);

	/**
	 * Creates and returns a ProjectVisitingHistory with given name of the project.
	 * 
	 * @param projectName
	 *            The String containing the of the
	 * @return A ProjectVisitingHistory representing the ProjectVisitingHistory
	 */
	ProjectVisitingHistory createProjectVisitingHistory(String projectName);

	/**
	 * Creates and returns a Task with given parameters
	 * 
	 * @param taskName
	 *            The String containing the name of the
	 * @param taskCategory
	 *            The String containing the category of the
	 * @param taskDeadline
	 *            The String containing the date of deadline of the
	 * @param taskDescription
	 *            The String containing the of the
	 * @return A Task representing the Task with given parameters
	 */
	Task createNewTask(String taskName, String taskCategory, String taskDeadline, String taskDescription);

	/**
	 * Updates the Task with given parameters
	 * 
	 * @param task
	 *            The Task containing the task to be updated
	 * @param taskName
	 *            The String containing the name of the Task
	 * @param taskDescription
	 *            The String containing the description of the Task
	 * @param taskIsActive
	 *            The boolean containing the isActive status of the Task
	 * @param taskCategory
	 *            The String containing the category of the Task
	 * @param taskDate
	 *            The String containing the date of added of the Task
	 * @param taskDeadline
	 *            The String containing the date of deadline of the Task
	 * @param taskIsCompleted
	 *            The boolean containing the isCompleted status of the Task
	 */
	void updateTask(Task task, String taskName, String taskDescription, boolean taskIsActive, String taskCategory,
			String taskDate, String taskDeadline, boolean taskIsCompleted);

}
