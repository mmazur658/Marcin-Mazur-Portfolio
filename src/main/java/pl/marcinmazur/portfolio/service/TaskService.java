package pl.marcinmazur.portfolio.service;

import java.util.List;

import pl.marcinmazur.portfolio.entity.Task;

public interface TaskService {

	List<Task> getTaskList(String taskListType);

	Task getTask(int taskId);

	void addNewTask(String taskName, String taskCategory, String taskDeadline, String taskDescription);

	void updateTask(int taskId, String taskName, String taskCategory, String taskDate, String taskDeadline,
			String taskDescription, boolean taskIsActive, boolean taskIsCompleted);
}
