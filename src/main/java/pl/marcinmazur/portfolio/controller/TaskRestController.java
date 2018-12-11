package pl.marcinmazur.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.marcinmazur.portfolio.service.TaskService;

/**
 * The rest controller class is used to perform actions depending on the user
 * request.
 * 
 * @author Marcin Mazur
 *
 */
@RestController
@RequestMapping("/administrator-panel/task-actions")
public class TaskRestController {

	/**
	 * The TaskService interface
	 */
	private TaskService taskService;

	/**
	 * Constructs a TaskRestController with the TaskService.
	 * 
	 * @param taskService
	 *            The TaskService interface
	 */
	@Autowired
	public TaskRestController(TaskService taskService) {
		this.taskService = taskService;
	}

	/**
	 * Create and saves a new Task object with given parameters.
	 * 
	 * @param taskName
	 *            The String containing the name of the task.
	 * @param taskCategory
	 *            The String containing the category of the task.
	 * @param taskDeadline
	 *            The String containing the date of deadline of the task.
	 * @param taskDescription
	 *            The String containing the description of the task.
	 */
	@RequestMapping("/add-new-task")
	public void createNewTask(@RequestParam(name = "taskName") String taskName,
			@RequestParam(name = "taskCategory") String taskCategory,
			@RequestParam(required = false, name = "taskDeadline") String taskDeadline,
			@RequestParam(name = "taskDescription") String taskDescription) {

		taskService.addNewTask(taskName, taskCategory, taskDeadline, taskDescription);

	}

	/**
	 * Updates the Task with the given parameters.
	 * 
	 * @param taskId
	 *            The int containing the id of the task.
	 * @param taskName
	 *            The String containing the name of the task.
	 * @param taskCategory
	 *            The String containing the category of the task.
	 * @param taskDate
	 *            The String containing the date of added of the task.
	 * @param taskDeadline
	 *            The String containing the date of deadline of the task.
	 * @param taskDescription
	 *            The String containing the description of the task.
	 * @param taskIsActive
	 *            The boolean containing the isActive status of the task.
	 * @param taskIsCompleted
	 *            The boolean containing the isCompleted status of the task.
	 */
	@RequestMapping("/update-task-details")
	public void updateTask(@RequestParam(name = "taskId") int taskId, @RequestParam(name = "taskName") String taskName,
			@RequestParam(name = "taskCategory") String taskCategory, @RequestParam(name = "taskDate") String taskDate,
			@RequestParam(name = "taskDeadline") String taskDeadline,
			@RequestParam(name = "taskDescription") String taskDescription,
			@RequestParam(name = "taskIsActive") boolean taskIsActive,
			@RequestParam(name = "taskIsCompleted") boolean taskIsCompleted) {

		taskService.updateTask(taskId, taskName, taskCategory, taskDate, taskDeadline, taskDescription, taskIsActive,
				taskIsCompleted);
	}

}
