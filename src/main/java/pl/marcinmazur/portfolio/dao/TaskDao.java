package pl.marcinmazur.portfolio.dao;

import java.util.List;

import pl.marcinmazur.portfolio.entity.Task;

public interface TaskDao {

	Task getTaskById(int taskId);

	List<Task> getTaskList(boolean isActive);

	void saveTask(Task task);

}
