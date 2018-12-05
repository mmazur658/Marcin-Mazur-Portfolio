package pl.marcinmazur.portfolio.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {

	/*
	 * The Entity private fields
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "date_of_added")
	private Date dateOfAdded;

	@Column(name = "deadline")
	private Date deadline;

	@Column(name = "task_name")
	private String taskName;

	@Column(name = "task_description")
	private String taskDescription;

	@Column(name = "task_category")
	private String taskCategory;

	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "is_completed")
	private boolean isCompleted;

	/*
	 * Setters and getters methods
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateOfAdded() {
		return dateOfAdded;
	}

	public void setDateOfAdded(Date dateOfAdded) {
		this.dateOfAdded = dateOfAdded;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public String getTaskCategory() {
		return taskCategory;
	}

	public void setTaskCategory(String taskCategory) {
		this.taskCategory = taskCategory;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	/*
	 * Public methods
	 */

	public Task() {

	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", dateOfAdded=" + dateOfAdded + ", deadline=" + deadline + ", taskName=" + taskName
				+ ", taskDescription=" + taskDescription + ", taskCategory=" + taskCategory + ", isActive=" + isActive
				+ ", isCompleted=" + isCompleted + "]";
	}

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

}
