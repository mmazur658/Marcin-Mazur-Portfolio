package pl.marcinmazur.portfolio.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.marcinmazur.portfolio.service.StatisticService;

@RestController
@RequestMapping("/administrator-panel/statistics")
public class StaticticsRestController {

	private StatisticService statisticService;

	public StaticticsRestController(StatisticService statisticService) {
		this.statisticService = statisticService;
	}

	@RequestMapping("/get-general-statistics-for-selected-project")
	public String[] getGeneralStatisticsForSelectedProject(@RequestParam(name = "projectName") String projectName) {

		return statisticService.getGeneralStatisticsForSelectedProject(projectName);
	}

	@RequestMapping("/get-monthly-data-for-selected-project")
	public List<Object[]> getMonthlyDataForSelectedProject(@RequestParam(name = "projectName") String projectName,
			@RequestParam(name = "startDate") String startDate, @RequestParam(name = "endDate") String endDate,
			@RequestParam(name = "monthLength") int monthLength) {

		return statisticService.getMonthlyDataForSelectedProject(projectName, startDate, endDate, monthLength);
	}

	@RequestMapping("/get-general-statistics-for-messages")
	public String[] getGeneralStatisticsForMessages() {
		return statisticService.getGeneralStatisticsForMessages();
	}

	@RequestMapping("/get-monthly-data-for-messages")
	public List<Object[]> getMonthlyDataForSelectedProject(@RequestParam(name = "startDate") String startDate,
			@RequestParam(name = "endDate") String endDate, @RequestParam(name = "monthLength") int monthLength) {

		return statisticService.getMonthlyDataForMessages(startDate, endDate, monthLength);
	}
}
