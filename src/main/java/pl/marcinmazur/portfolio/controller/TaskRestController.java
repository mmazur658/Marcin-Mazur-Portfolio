package pl.marcinmazur.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.marcinmazur.portfolio.service.TaskService;

@RestController
@RequestMapping("/administrator-panel/task-actions")
public class TaskRestController {

	private TaskService taskService;

	@Autowired
	public TaskRestController(TaskService taskService) {
		this.taskService = taskService;
	}

	@RequestMapping("/add-new-task")
	public void createNewTask(@RequestParam(name = "taskName") String taskName,
			@RequestParam(name = "taskCategory") String taskCategory,
			@RequestParam(required = false, name = "taskDeadline") String taskDeadline,
			@RequestParam(name = "taskDescription") String taskDescription) {

		taskService.addNewTask(taskName, taskCategory, taskDeadline, taskDescription);

	}

	@RequestMapping("/update-task-details")
	public void updateTask(@RequestParam(name = "taskId") int taskId, @RequestParam(name = "taskName") String taskName,
			@RequestParam(name = "taskCategory") String taskCategory, @RequestParam(name = "taskDate") String taskDate,
			@RequestParam(name = "taskDeadline") String taskDeadline,
			@RequestParam(name = "taskDescription") String taskDescription,
			@RequestParam(name = "taskIsActive") boolean taskIsActive,
			@RequestParam(name = "taskIsCompleted") boolean taskIsCompleted) {

		taskService.updateTask(taskId, taskName, taskCategory, taskDate, taskDeadline, taskDescription, taskIsActive, taskIsCompleted);
	}

}
