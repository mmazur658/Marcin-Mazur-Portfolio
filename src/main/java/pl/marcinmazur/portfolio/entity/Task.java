package pl.marcinmazur.portfolio.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a Task record in the database
 * 
 * @author Marcin Mazur
 */
@Entity
@Table(name = "tasks")
public class Task {

	/**
	 * Unique identification number
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	/**
	 * The date of added
	 */
	@Column(name = "date_of_added")
	private Date dateOfAdded;

	/**
	 * The date of deadline
	 */
	@Column(name = "deadline")
	private Date deadline;

	/**
	 * The name of the task
	 */
	@Column(name = "task_name")
	private String taskName;

	/**
	 * The description of the task
	 */
	@Column(name = "task_description")
	private String taskDescription;

	/**
	 * The category of the task
	 */
	@Column(name = "task_category")
	private String taskCategory;

	/**
	 * The isActive status
	 */
	@Column(name = "is_active")
	private boolean isActive;

	/**
	 * The isCompleted status
	 */
	@Column(name = "is_completed")
	private boolean isCompleted;

	/**
	 * Gets the unique identification number of the Task
	 * 
	 * @return A long representing the unique identification number of the Task
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the unique identification number of the Task
	 * 
	 * @param id
	 *            A long containing the unique identification number of the Task
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the date of added of the Task
	 * 
	 * @return A Date representing the date of added of the Task
	 */
	public Date getDateOfAdded() {
		return dateOfAdded;
	}

	/**
	 * Sets the date of added of the Task
	 * 
	 * @param dateOfAdded
	 *            A Date containing the date of added of the Task
	 */
	public void setDateOfAdded(Date dateOfAdded) {
		this.dateOfAdded = dateOfAdded;
	}

	/**
	 * Gets the date of deadline of the Task
	 * 
	 * @return A Date representing the date of deadline of the Task
	 */
	public Date getDeadline() {
		return deadline;
	}

	/**
	 * Sets the date of deadlin of the Task
	 * 
	 * @param deadline
	 *            A Date containing the date of deadline of the Task
	 */
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	/**
	 * Gets the name of the Task
	 * 
	 * @return A String representing the name of the Task
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * Sets the name of the Task
	 * 
	 * @param taskName
	 *            A String containing the name of the Task
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	/**
	 * Gets the description of the Task
	 * 
	 * @return A String representing the description of the Task
	 */
	public String getTaskDescription() {
		return taskDescription;
	}

	/**
	 * Sets the description of the Task
	 * 
	 * @param taskDescription
	 *            A String containing the description of the Task
	 */
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	/**
	 * Gets the category of the Task
	 * 
	 * @return A String representing the category of the Task
	 */
	public String getTaskCategory() {
		return taskCategory;
	}

	/**
	 * Sets the category of the Task
	 * 
	 * @param taskCategory
	 *            A String containing the category of the Task
	 */
	public void setTaskCategory(String taskCategory) {
		this.taskCategory = taskCategory;
	}

	/**
	 * Gets the isActive status of the Task
	 * 
	 * @return A boolean representing the isActive status of the Task
	 */
	public boolean getIsActive() {
		return isActive;
	}

	/**
	 * Sets the isActive status of the Task
	 * 
	 * @param isActive
	 *            A boolean containing the isActive status of the Task
	 */
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * Gets the isCompleted status of the Task
	 * 
	 * @return A boolean representing the isCompleted status of the Task
	 */
	public boolean getIsCompleted() {
		return isCompleted;
	}

	/**
	 * Sets the isCompleted status of the Task
	 * 
	 * @param isCompleted
	 *            A boolean containing the isCompleted status of the Task
	 */
	public void setIsCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	/**
	 * Constructs a Task object.
	 */
	public Task() {

	}

	/**
	 * Constructs a Task with the date of added, date of deadline, name of the task,
	 * description of the task, category of the task, isActive status and
	 * isCompleted status.
	 * 
	 * @param dateOfAdded
	 *            The date of added
	 * @param deadline
	 *            The date of deadline
	 * @param taskName
	 *            The name of the task
	 * @param taskDescription
	 *            The description of the task
	 * @param taskCategory
	 *            The category of the task
	 * @param isActive
	 *            The isActive status of the task
	 * @param isCompleted
	 *            The isCompleted status of the task
	 */
	public Task(Date dateOfAdded, Date deadline, String taskName, String taskDescription, String taskCategory,
			boolean isActive, boolean isCompleted) {
		this.dateOfAdded = dateOfAdded;
		this.deadline = deadline;
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.taskCategory = taskCategory;
		this.isActive = isActive;
		this.isCompleted = isCompleted;
	}

	/**
	 * Returns the String representation of the Task object.
	 * 
	 * @return The String representation of the Task object.
	 */
	@Override
	public String toString() {
		return "Task [id=" + id + ", dateOfAdded=" + dateOfAdded + ", deadline=" + deadline + ", taskName=" + taskName
				+ ", taskDescription=" + taskDescription + ", taskCategory=" + taskCategory + ", isActive=" + isActive
				+ ", isCompleted=" + isCompleted + "]";
	}

}
