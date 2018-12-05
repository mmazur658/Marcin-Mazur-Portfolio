package pl.marcinmazur.portfolio.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pl.marcinmazur.portfolio.entity.Comment;
import pl.marcinmazur.portfolio.entity.ContactFormMessage;
import pl.marcinmazur.portfolio.entity.ProjectVisitingHistory;
import pl.marcinmazur.portfolio.entity.Task;

class ServiceUtilsTest {

	private ServiceUtilsImpl serviceUtilsImpl = new ServiceUtilsImpl();

	@Test
	void shouldCreateContactFormMessage() {

		String senderEmail = "New sender emial";
		String senderName = "New sender name";
		String messageText = "New message text";
		String messageSubject = "New message subject";

		ContactFormMessage contactFormMessage = serviceUtilsImpl.createContactFormMessage(senderEmail, senderName,
				messageText, messageSubject);

		assertEquals(messageSubject, contactFormMessage.getMessageSubject());
		assertEquals(messageText, contactFormMessage.getMessageText());
		assertEquals(senderEmail, contactFormMessage.getSenderEmail());
		assertEquals(senderName, contactFormMessage.getSenderName());

		assertNotNull(contactFormMessage.getDate());
		assertTrue(contactFormMessage.getIsActive());
		assertFalse(contactFormMessage.getIsReaded());
		assertFalse(contactFormMessage.getIsReplied());

	}

	@Test
	void shouldPrepareHqlDependsOnListType() {

		String hqlType = "FROM Cat ";
		String listType = "new";

		String hql = serviceUtilsImpl.prepareHqlDependsOnListType(hqlType, listType);
		String expectedHql = "FROM Cat where isActive=true ORDER BY id DESC";

		assertEquals(expectedHql, hql);

	}

	@Test
	void shouldCreateComment() {

		String commentText = "New test comment";
		ContactFormMessage contactFormMessage = new ContactFormMessage();
		contactFormMessage.setIsActive(true);
		contactFormMessage.setSenderEmail("test@test.com");

		Comment comment = serviceUtilsImpl.createComment(commentText, contactFormMessage);

		assertEquals(commentText, comment.getCommentText());
		assertEquals("test@test.com", comment.getContactFormMessage().getSenderEmail());
		assertTrue(comment.getContactFormMessage().getIsActive());

	}

	@Test
	void shouldChangeIsActiveAndIsReadedStatus() {

		ContactFormMessage contactFormMessage = new ContactFormMessage();
		contactFormMessage.setIsActive(true);
		contactFormMessage.setIsReaded(false);

		serviceUtilsImpl.changeIsActiveAndIsReadedStatus(contactFormMessage);

		assertTrue(contactFormMessage.getIsReaded());
		assertFalse(contactFormMessage.getIsActive());

		serviceUtilsImpl.changeIsActiveAndIsReadedStatus(contactFormMessage);

		assertTrue(contactFormMessage.getIsReaded());
		assertTrue(contactFormMessage.getIsActive());
	}

	@Test
	void shouldChangeIsRepliedStatus() {

		ContactFormMessage contactFormMessage = new ContactFormMessage();
		contactFormMessage.setIsReplied(true);
		contactFormMessage.setIsReaded(true);

		serviceUtilsImpl.changeIsRepliedStatus(contactFormMessage);

		assertFalse(contactFormMessage.getIsReplied());
		assertTrue(contactFormMessage.getIsReaded());

		serviceUtilsImpl.changeIsRepliedStatus(contactFormMessage);

		assertFalse(contactFormMessage.getIsReaded());
		assertTrue(contactFormMessage.getIsReplied());

	}

	@Test
	void shouldChangeIsReadedStatus() {

		ContactFormMessage contactFormMessage = new ContactFormMessage();
		contactFormMessage.setIsReaded(true);

		serviceUtilsImpl.changeIsReadedStatus(contactFormMessage);

		assertFalse(contactFormMessage.getIsReaded());

		serviceUtilsImpl.changeIsReadedStatus(contactFormMessage);

		assertTrue(contactFormMessage.getIsReaded());

	}

	@Test
	void shouldCreateProjectVisitingHistory() {

		String projectName = "Test Project Name";
		ProjectVisitingHistory projectVisitingHistory = serviceUtilsImpl.createProjectVisitingHistory(projectName);

		assertEquals(projectName, projectVisitingHistory.getProjectName());
		assertNotNull(projectVisitingHistory.getDate());

	}

	@Test
	void shouldCreateNewTask() {

		String taskName = "New task name";
		String taskCategory = "New task category";
		String taskDeadline = "2018-12-02 12:59:18";
		String taskDescription = "New task description";

		Task theTask = serviceUtilsImpl.createNewTask(taskName, taskCategory, taskDeadline, taskDescription);

		assertEquals(taskCategory, theTask.getTaskCategory());
		assertEquals(taskName, theTask.getTaskName());
		assertEquals(taskDescription, theTask.getTaskDescription());
		assertNotNull(theTask.getDeadline());
		assertNotNull(theTask.getDateOfAdded());
		assertTrue(theTask.getIsActive());
		assertFalse(theTask.getIsCompleted());

	}

	@Test
	void shouldUpdateTask() {

		Task task = new Task();
		String taskName = "new taks name";
		String taskDescription = "New task name";
		boolean taskIsActive = false;
		String taskCategory = "New task category";
		String taskDate = "2018-11-12 12:22";
		String taskDeadline = "";
		boolean taskIsCompleted = true;

		serviceUtilsImpl.updateTask(task, taskName, taskDescription, taskIsActive, taskCategory, taskDate, taskDeadline,
				taskIsCompleted);
		
		assertEquals(taskCategory, task.getTaskCategory());
		assertEquals(taskName, task.getTaskName());
		assertEquals(taskDescription, task.getTaskDescription());
		assertNull(task.getDeadline());
		assertNotNull(task.getDateOfAdded());
		assertFalse(task.getIsActive());
		assertTrue(task.getIsCompleted());
		
	}

}
