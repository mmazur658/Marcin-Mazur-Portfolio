package pl.marcinmazur.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.marcinmazur.portfolio.service.StatisticService;

/**
 * The rest controller class is used to perform actions depending on the user
 * request.
 * 
 * @author Marcin Mazur
 *
 */
@RestController
@RequestMapping("/administrator-panel/statistics")
public class StaticticsRestController {

	/**
	 * The StatisticService interface
	 */
	private StatisticService statisticService;

	/**
	 * Constructs a StaticticsRestController with the StatisticService.
	 * 
	 * @param statisticService
	 *            The StatisticService interface
	 */
	@Autowired
	public StaticticsRestController(StatisticService statisticService) {
		this.statisticService = statisticService;
	}

	/**
	 * Returns the general statistics of the project with the given name.
	 * 
	 * @param projectName
	 *            The String containing the name of the project
	 * @return A String[] representing the general statistics of the project
	 */
	@RequestMapping("/get-general-statistics-for-selected-project")
	public String[] getGeneralStatisticsForSelectedProject(@RequestParam(name = "projectName") String projectName) {

		return statisticService.getGeneralStatisticsForSelectedProject(projectName);
	}

	/**
	 * Returns the monthly statistics of the project with given name and date range.
	 * 
	 * @param projectName
	 *            The String containing the name of the project
	 * @param startDate
	 *            The String containing the first day of the month
	 * @param endDate
	 *            The String containing the last day of the month
	 * @param monthLength
	 *            The int containing the length of the month
	 * @return A List&lt;Object[]&gt; representing the monthly statistics of the
	 *         project
	 */
	@RequestMapping("/get-monthly-data-for-selected-project")
	public List<Object[]> getMonthlyDataForSelectedProject(@RequestParam(name = "projectName") String projectName,
			@RequestParam(name = "startDate") String startDate, @RequestParam(name = "endDate") String endDate,
			@RequestParam(name = "monthLength") int monthLength) {

		return statisticService.getMonthlyDataForSelectedProject(projectName, startDate, endDate, monthLength);
	}

	/**
	 * Returns the general statistics of messages
	 * 
	 * @return A String[] representing the general statistics of messages
	 */
	@RequestMapping("/get-general-statistics-for-messages")
	public String[] getGeneralStatisticsForMessages() {
		return statisticService.getGeneralStatisticsForMessages();
	}

	/**
	 * Returns the monthly statistics of the messages for given date range
	 * 
	 * @param startDate
	 *            The String containing the first day of the month
	 * @param endDate
	 *            The String containing the last day of the month
	 * @param monthLength
	 *            The int containing the length of the month
	 * @return A List&lt;Object[]&gt; representing the monthly statistics of the
	 *         messages
	 */
	@RequestMapping("/get-monthly-data-for-messages")
	public List<Object[]> getMonthlyDataOfMessages(@RequestParam(name = "startDate") String startDate,
			@RequestParam(name = "endDate") String endDate, @RequestParam(name = "monthLength") int monthLength) {

		return statisticService.getMonthlyDataOfMessages(startDate, endDate, monthLength);
	}
}
