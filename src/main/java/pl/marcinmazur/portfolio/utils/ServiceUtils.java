package pl.marcinmazur.portfolio.utils;

import pl.marcinmazur.portfolio.entity.Comment;
import pl.marcinmazur.portfolio.entity.ContactFormMessage;
import pl.marcinmazur.portfolio.entity.ProjectVisitingHistory;
import pl.marcinmazur.portfolio.entity.Task;

public interface ServiceUtils {

	ContactFormMessage createContactFormMessage(String senderEmail, String senderName, String messageText,
			String messageSubject);

	String prepareHqlDependsOnListType(String hqlType, String listType);

	Comment createComment(String commentText, ContactFormMessage contactFormMessage);

	void changeIsActiveAndIsReadedStatus(ContactFormMessage contactFormMessage);

	void changeIsRepliedStatus(ContactFormMessage contactFormMessage);

	void changeIsReadedStatus(ContactFormMessage contactFormMessage);

	ProjectVisitingHistory createProjectVisitingHistory(String projectName);

	Task createNewTask(String taskName, String taskCategory, String taskDeadline, String taskDescription);

	void updateTask(Task task, String taskName, String taskDescription, boolean taskIsActive, String taskCategory,
			String taskDate, String taskDeadline, boolean taskIsCompleted);

}
