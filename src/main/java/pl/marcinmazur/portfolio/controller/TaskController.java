package pl.marcinmazur.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.marcinmazur.portfolio.entity.Task;
import pl.marcinmazur.portfolio.service.TaskService;

@Controller
@RequestMapping("/administrator-panel/task")
public class TaskController {

	private TaskService taskService;

	@Autowired
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@RequestMapping("/new-task")
	public String showNewTaskForm() {
		return "parts/new-task-modal";
	}

	@RequestMapping("/tasks")
	public String showTasksTable(@RequestParam(name = "taskListType") String taskListType, Model theModel) {

		List<Task> taskList = taskService.getTaskList(taskListType);
		theModel.addAttribute("taskList", taskList);

		return "parts/task-table";
	}

	@RequestMapping("/task")
	public String showTask(@RequestParam(name = "taskId") int taskId, Model theModel) {

		Task task = taskService.getTask(taskId);
		theModel.addAttribute("task", task);

		return "parts/task-modal";

	}

}
