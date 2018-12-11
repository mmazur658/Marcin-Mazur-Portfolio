package pl.marcinmazur.portfolio.dao;

import java.util.List;

import pl.marcinmazur.portfolio.entity.Task;

/**
 * Interface for performing database operations on Task objects.
 * 
 * @author Marcin Mazur
 *
 */
public interface TaskDao {

	/**
	 * Returns the task with the given id
	 * 
	 * @param taskId
	 *            An int containing the value of id
	 * @return A Task representing the task with given id
	 */
	Task getTaskById(int taskId);

	/**
	 * Returns the list of the tasks with given isActive status
	 * 
	 * @param isActive
	 *            The boolean containing the value of isActive status
	 * @return A List&lt;Task&gt; representing the list of the tasks with given
	 *         isActive status
	 */
	List<Task> getTaskList(boolean isActive);

	/**
	 * Saves the task in the database
	 * 
	 * @param task
	 *            The task to be saved
	 */
	void saveTask(Task task);

}
