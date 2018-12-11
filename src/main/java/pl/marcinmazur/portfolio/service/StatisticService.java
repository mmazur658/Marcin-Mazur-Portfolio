package pl.marcinmazur.portfolio.service;

import java.util.List;

import pl.marcinmazur.portfolio.utils.CodeUsageHistoryResult;
import pl.marcinmazur.portfolio.utils.ProjectVisitingHistoryResult;

/**
 * Interface for managing statistics of access codes and project visiting.
 * 
 * @author Marcin Mazur
 *
 */
public interface StatisticService {

	/**
	 * Returns the list of ProjectVisitingHistoryResult for given date range
	 * 
	 * @param startDate
	 *            The String containing the first day of the range
	 * @param endDate
	 *            The String containing the last day of the range
	 * @return A List&lt;ProjectVisitingHistoryResult&gt; representing the list of
	 *         ProjectVisitingHistoryResult
	 */
	List<ProjectVisitingHistoryResult> getProjectVisitingHistoryListForGivenDate(String startDate, String endDate);

	/**
	 * Returns the number of the contact form messages for given date range
	 * 
	 * @param startDate
	 *            The String containing the first day of the range
	 * @param endDate
	 *            The String containing the last day of the range
	 * @return A long representing the number of the contact form messages
	 */
	long getNumberOfContactFormMessages(String startDate, String endDate);

	/**
	 * Returns the list of CodeUsageHistoryResult for given date range
	 * 
	 * @param startDate
	 *            The String containing the first day of the range
	 * @param endDate
	 *            The String containing the last day of the range
	 * @return A List&lt;CodeUsageHistoryResult&gt; representing the list of
	 *         CodeUsageHistoryResult
	 * 
	 */
	List<CodeUsageHistoryResult> getCodeUsageHistoryResultListForGivenDate(String startDate, String endDate);

	/**
	 * Returns the general statistics for given project name
	 * 
	 * @param projectName
	 *            The String containing the name of the project
	 * 
	 * @return A String[] representing the general statistics
	 */
	String[] getGeneralStatisticsForSelectedProject(String projectName);

	/**
	 * Returns the monthly statistical data (data - number of project visiting) as a
	 * list of Object[] for given project name and date range.
	 * 
	 * @param projectName
	 *            The String containing the name of the project
	 * @param startDate
	 *            The String containing the first day of the range
	 * @param endDate
	 *            The String containing the last day of the range
	 * @param monthLength
	 *            The int containing the length of the month
	 * @return A List&lt;Object[]&gt; representing the list of results
	 */
	List<Object[]> getMonthlyDataForSelectedProject(String projectName, String startDate, String endDate,
			int monthLength);

	/**
	 * Returns the general statistics of the messages
	 * 
	 * @return A String[] representing the general statistics of the messages
	 */
	String[] getGeneralStatisticsForMessages();

	/**
	 * Returns the monthly statistical data (data - number of messages) of messages
	 * as a list of Object[] for given date range.
	 * 
	 * @param startDate
	 *            The String containing the first day of the range
	 * @param endDate
	 *            The String containing the last day of the range
	 * @param monthLength
	 *            The int containing the length of the month
	 * @return A List&lt;Object[]&gt; representing the list of results
	 */
	List<Object[]> getMonthlyDataOfMessages(String startDate, String endDate, int monthLength);

}
