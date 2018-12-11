package pl.marcinmazur.portfolio.utils;

import java.util.List;

/**
 * Interface used to perform operations on access codes and project statistics
 * 
 * @author Marcin Mazur
 *
 */
public interface StatisticsUtils {

	/**
	 * Returns the date minus given value
	 * 
	 * @param value
	 *            The int containing the value
	 * @return A String representing the new date
	 */
	String getDateMinusGivenValue(int value);

	/**
	 * Returns the current date. Format: yyyy-MM-dd hh:mm:ss.S
	 * 
	 * @return A string representing the current date
	 */
	String getToday();

	/**
	 * Creates and returns the list of monthly statistics ready to display
	 * 
	 * @param resultList
	 *            The List&lt;Object[]&gt; containing the list of Object[]
	 * @param monthLength
	 *            The int containing the length of the month
	 * @return A List&lt;Object[]&gt; representing the list of monthly statistics
	 *         ready to display
	 */
	List<Object[]> prepareMonthlyStatsListToDisplay(List<Object[]> resultList, int monthLength);

	/**
	 * Returns a CodeUsageHistoryResult with given parameters.
	 * 
	 * @param accessCodeValue
	 *            The String containing the value of the access code
	 * @param sumOfUsage
	 *            The long containing the number of code usage
	 * @param accessCodeOwener
	 *            The String containing the owner of the access code
	 * @return A CodeUsageHistoryResult representing the CodeUsageHistoryResult with
	 *         given parameters
	 */
	CodeUsageHistoryResult createCodeUsageHistoryResult(String accessCodeValue, long sumOfUsage,
			String accessCodeOwener);

	/**
	 * Returns the first, second and third element of sorted list.
	 * 
	 * @param codeUsageHistoryResultList
	 *            The List&lt;CodeUsageHistoryResult&gt; containing the list to be
	 *            sorted
	 * @return A List&lt;CodeUsageHistoryResult&gt; representing the sorted list of
	 *         CodeUsageHistoryResult with 3 elements
	 */
	List<CodeUsageHistoryResult> prepareCodeUsageHistoryResultListToDisplay(
			List<CodeUsageHistoryResult> codeUsageHistoryResultList);

	/**
	 * Creates a ProjectVisitingHistoryResult object with given parameters
	 * 
	 * @param projectName
	 *            The String containing the name of the project
	 * @param sumOfVisits
	 *            The long containing the number of visits
	 * @return A ProjectVisitingHistoryResult representing the
	 *         ProjectVisitingHistoryResult with given parameters
	 */
	ProjectVisitingHistoryResult createProjectVisitingHistoryResult(String projectName, long sumOfVisits);

}
