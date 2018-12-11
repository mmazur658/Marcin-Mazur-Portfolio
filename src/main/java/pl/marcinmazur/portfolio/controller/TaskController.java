package pl.marcinmazur.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.marcinmazur.portfolio.entity.Task;
import pl.marcinmazur.portfolio.service.TaskService;

/**
 * The controller class is used to return the view depending on the user
 * request. This controller contains the views of: <br>
 * <ul>
 * <li>"parts/new-task-modal"</li>
 * <li>"parts/task-table"</li>
 * <li>"parts/task-modal"</li>
 * </ul>
 * 
 * @author Marcin Mazur
 *
 */
@Controller
@RequestMapping("/administrator-panel/task")
public class TaskController {

	/*
	 * The TaskService interface
	 */
	private TaskService taskService;

	/**
	 * Constructs a TaskController with the TaskService.
	 * 
	 * @param taskService
	 *            The TaskService interface
	 */
	@Autowired
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	/**
	 * Returns the view of "parts/new-task-modal".<br>
	 * 
	 * @returnThe String representing the name of the view
	 */
	@RequestMapping("/new-task")
	public String showNewTaskForm() {
		return "parts/new-task-modal";
	}

	/**
	 * Returns the view of "parts/task-table" with model attribute:<br>
	 * <ul>
	 * <li>taskList</li>
	 * </ul>
	 * 
	 * @param taskListType
	 *            The String containing the type of the list
	 * @param theModel
	 *            The Model containing the data passed to the view
	 * @return The String representing the name of the view
	 */
	@RequestMapping("/tasks")
	public String showTasksTable(@RequestParam(name = "taskListType") String taskListType, Model theModel) {

		List<Task> taskList = taskService.getTaskList(taskListType);
		theModel.addAttribute("taskList", taskList);

		return "parts/task-table";
	}

	/**
	 * Returns the view of "parts/task-modal" with model attribute:<br>
	 * <ul>
	 * <li>task</li>
	 * </ul>
	 * 
	 * @param taskId
	 *            The int containing the id of the task
	 * @param theModel
	 *            The Model containing the data passed to the view
	 * @return The String representing the name of the view
	 */
	@RequestMapping("/task")
	public String showTask(@RequestParam(name = "taskId") int taskId, Model theModel) {

		Task task = taskService.getTask(taskId);
		theModel.addAttribute("task", task);

		return "parts/task-modal";

	}

}
