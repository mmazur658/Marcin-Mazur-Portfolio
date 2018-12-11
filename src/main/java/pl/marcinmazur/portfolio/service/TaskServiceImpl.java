package pl.marcinmazur.portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.marcinmazur.portfolio.dao.TaskDao;
import pl.marcinmazur.portfolio.entity.Task;
import pl.marcinmazur.portfolio.utils.ServiceUtils;

/**
 * Service class for managing tasks.
 * 
 * @author Marcin Mazur
 *
 */
@Service
public class TaskServiceImpl implements TaskService {

	/**
	 * The TaskDao interface
	 */
	private TaskDao taskDao;

	/**
	 * The ServiceUtils interface
	 */
	private ServiceUtils serviceUtils;

	/**
	 * Constructs a TaskServiceImpl with the TaskDao and ServiceUtils.
	 * 
	 * @param taskDao
	 *            The TaskDao interface
	 * @param serviceUtils
	 *            The ServiceUtils interface
	 */
	@Autowired
	public TaskServiceImpl(TaskDao taskDao, ServiceUtils serviceUtils) {
		this.taskDao = taskDao;
		this.serviceUtils = serviceUtils;
	}

	@Override
	@Transactional
	public Task getTask(int taskId) {
		return taskDao.getTaskById(taskId);
	}

	@Override
	@Transactional
	public List<Task> getTaskList(String taskListType) {

		if (taskListType.equals("active"))
			return taskDao.getTaskList(true);
		else
			return taskDao.getTaskList(false);

	}

	@Override
	@Transactional
	public void addNewTask(String taskName, String taskCategory, String taskDeadline, String taskDescription) {

		Task task = serviceUtils.createNewTask(taskName, taskCategory, taskDeadline, taskDescription);
		taskDao.saveTask(task);

	}

	@Override
	@Transactional
	public void updateTask(int taskId, String taskName, String taskCategory, String taskDate, String taskDeadline,
			String taskDescription, boolean taskIsActive, boolean taskIsCompleted) {

		Task task = taskDao.getTaskById(taskId);

		serviceUtils.updateTask(task, taskName, taskDescription, taskIsActive, taskCategory, taskDate, taskDeadline,
				taskIsCompleted);

	}

}
