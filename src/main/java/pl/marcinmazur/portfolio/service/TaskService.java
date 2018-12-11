package pl.marcinmazur.portfolio.service;

import java.util.List;

import pl.marcinmazur.portfolio.entity.Task;

/**
 * Interface for managing tasks.
 * 
 * @author Marcin Mazur
 *
 */
public interface TaskService {

	/**
	 * Returns the list of all active or inactive tasks.
	 * 
	 * @param taskListType
	 *            The String containing the type of the list. Use "active" to get
	 *            all active tasks. Use any other word to get all inactive tasks.
	 * @return A List&lt;Task&gt; representing the list of tasks
	 */
	List<Task> getTaskList(String taskListType);

	/**
	 * Returns the Task with given id
	 * 
	 * @param taskId
	 *            The int containing the id of the task
	 * @return A Task representing the Task
	 */
	Task getTask(int taskId);

	/**
	 * Creates and saves the Task with the given name, category, deadline and
	 * description.
	 * 
	 * @param taskName
	 *            The String containing the name of the task
	 * @param taskCategory
	 *            The String containing the category of the task
	 * @param taskDeadline
	 *            The String containing the date of deadline of the task
	 * @param taskDescription
	 *            The String containing the description of the task
	 */
	void addNewTask(String taskName, String taskCategory, String taskDeadline, String taskDescription);

	/**
	 * Updates the task with the given parameters
	 * 
	 * @param taskId
	 *            The int containing the id of the task
	 * @param taskName
	 *            The String containing the name of the task
	 * @param taskCategory
	 *            The String containing the category of the task
	 * @param taskDate
	 *            The String containing the date of added of the task
	 * @param taskDeadline
	 *            The String containing the date of deadline of the task
	 * @param taskDescription
	 *            The String containing the description of the task
	 * @param taskIsActive
	 *            The boolean containing the isActive status of the task
	 * @param taskIsCompleted
	 *            The boolean containing the isCompleted status of the task
	 * 
	 */
	void updateTask(int taskId, String taskName, String taskCategory, String taskDate, String taskDeadline,
			String taskDescription, boolean taskIsActive, boolean taskIsCompleted);
}
