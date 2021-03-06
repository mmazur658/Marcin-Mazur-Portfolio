package pl.marcinmazur.portfolio.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import pl.marcinmazur.portfolio.entity.Comment;
import pl.marcinmazur.portfolio.entity.ContactFormMessage;
import pl.marcinmazur.portfolio.entity.ProjectVisitingHistory;
import pl.marcinmazur.portfolio.entity.Task;

/**
 * Utility class used to perform operations for service classes.
 * 
 * @author Marcin Mazur
 *
 */
@Component
public class ServiceUtilsImpl implements ServiceUtils {

	private final String BASIC_DATE_FORMAT = "yyyy-MM-dd";
	private final String FULL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private final String START_TIME = " 00:00:00.0";
	private final String SHORT_START_TIME = ":00.0";
	private final String LIST_TYPE_NEW = "new";
	private final String LIST_TYPE_ARCHIVE = "archive";

	@Override
	public ContactFormMessage createContactFormMessage(String senderEmail, String senderName, String messageText,
			String messageSubject) {

		ContactFormMessage contactFormMessage = new ContactFormMessage();

		contactFormMessage.setSenderEmail(senderEmail);
		contactFormMessage.setSenderName(senderName);
		contactFormMessage.setMessageText(messageText);
		contactFormMessage.setMessageSubject(messageSubject);
		contactFormMessage.setIsReplied(false);
		contactFormMessage.setDate(new Date());
		contactFormMessage.setIsActive(true);
		contactFormMessage.setIsReaded(false);

		return contactFormMessage;
	}

	@Override
	public String prepareHqlDependsOnListType(String hqlType, String listType) {

		StringBuilder sb = new StringBuilder();
		sb.append(hqlType);

		if (listType.equals(LIST_TYPE_NEW))
			sb.append("where isActive=true ");

		else if (listType.equals(LIST_TYPE_ARCHIVE))
			sb.append("where isActive=false ");

		sb.append("ORDER BY id DESC");

		return sb.toString();
	}

	@Override
	public Comment createComment(String commentText, ContactFormMessage contactFormMessage) {

		Comment comment = new Comment();
		comment.setDate(new Date());
		comment.setCommentText(commentText);
		comment.setContactFormMessage(contactFormMessage);

		return comment;
	}

	@Override
	public void changeIsActiveAndIsReadedStatus(ContactFormMessage contactFormMessage) {
		if (contactFormMessage.getIsActive()) {
			contactFormMessage.setIsActive(false);
			contactFormMessage.setIsReaded(true);
		} else {
			contactFormMessage.setIsActive(true);
			contactFormMessage.setIsReaded(true);
		}
	}

	@Override
	public void changeIsRepliedStatus(ContactFormMessage contactFormMessage) {
		if (contactFormMessage.getIsReplied())
			contactFormMessage.setIsReplied(false);
		else {
			contactFormMessage.setIsReplied(true);
			contactFormMessage.setIsReaded(false);
		}
	}

	@Override
	public void changeIsReadedStatus(ContactFormMessage contactFormMessage) {
		if (contactFormMessage.getIsReaded())
			contactFormMessage.setIsReaded(false);
		else
			contactFormMessage.setIsReaded(true);
	}

	@Override
	public ProjectVisitingHistory createProjectVisitingHistory(String projectName) {

		ProjectVisitingHistory projectVisitingHistory = new ProjectVisitingHistory();

		projectVisitingHistory.setProjectName(projectName);
		SimpleDateFormat sdf = new SimpleDateFormat(BASIC_DATE_FORMAT);
		String date = sdf.format(new Date()) + START_TIME;

		try {
			projectVisitingHistory.setDate(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return projectVisitingHistory;
	}

	@Override
	public Task createNewTask(String taskName, String taskCategory, String taskDeadline, String taskDescription) {

		Task theTask = new Task();
		theTask.setTaskName(taskName);
		theTask.setTaskDescription(taskDescription);
		theTask.setIsActive(true);
		theTask.setIsCompleted(false);
		theTask.setDateOfAdded(new Date());
		theTask.setTaskCategory(taskCategory);

		if (taskDeadline != null) {
			try {
				taskDeadline = taskDeadline + SHORT_START_TIME;
				theTask.setDeadline(new SimpleDateFormat(FULL_DATE_FORMAT).parse(taskDeadline));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return theTask;
	}

	@Override
	public void updateTask(Task task, String taskName, String taskDescription, boolean taskIsActive,
			String taskCategory, String taskDate, String taskDeadline, boolean taskIsCompleted) {

		task.setTaskName(taskName);
		task.setTaskDescription(taskDescription);
		task.setIsActive(taskIsActive);
		task.setIsCompleted(taskIsCompleted);
		task.setDateOfAdded(new Date());
		task.setTaskCategory(taskCategory);

		try {
			Date deadlineDate;
			if (taskDeadline == "" || taskDeadline == null) {
				deadlineDate = null;
			} else {
				taskDeadline = taskDeadline + SHORT_START_TIME;
				deadlineDate = new SimpleDateFormat(FULL_DATE_FORMAT).parse(taskDeadline);
			}

			taskDate = taskDate + SHORT_START_TIME;

			task.setDateOfAdded(new SimpleDateFormat(FULL_DATE_FORMAT).parse(taskDate));
			task.setDeadline(deadlineDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}