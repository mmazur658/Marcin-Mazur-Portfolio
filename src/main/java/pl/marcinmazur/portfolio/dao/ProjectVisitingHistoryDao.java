package pl.marcinmazur.portfolio.dao;

import java.util.List;

import pl.marcinmazur.portfolio.entity.ProjectVisitingHistory;

/**
 * Interface for performing database operations on ProjectVisitingHistory
 * objects.
 * 
 * @author Marcin Mazur
 *
 */
public interface ProjectVisitingHistoryDao {

	/**
	 * Saves the ProjectVisitingHistory in the database. <br>
	 * <br>
	 * If the object already exists in the database, then increase its count value
	 * by 1.
	 * 
	 * @param projectVisitingHistory
	 *            The ProjectVisitingHistory to be saved
	 */
	void saveProjectVisitingHistory(ProjectVisitingHistory projectVisitingHistory);

	/**
	 * Returns the number of visits for given project name and given date range
	 * 
	 * @param projectName
	 *            A String containing the name of the project
	 * @param startDate
	 *            A String containing the first day of the range
	 * @param endDate
	 *            A String containing the last day of the range
	 * @return A long representing the number of visits for given parameters
	 */
	long getSumOfVisitsForGivenProjectNameAndGivenDate(String projectName, String startDate, String endDate);

	/**
	 * Returns the list of all project names
	 * 
	 * @return A List&lt;String&gt; representing the list of all project names
	 */
	List<String> getListOfProjectNames();

	/**
	 * Returns the date and the number of visits as a list of Object[] with given
	 * name of project and for given date range.
	 * 
	 * @param projectName
	 *            A String containing the name of the project
	 * @param startDate
	 *            A String containing the first day of the range
	 * @param endDate
	 *            A String containing the last day of the range
	 * @return A List&lt;Object[]&gt; representing the date and the number of visit
	 */
	List<Object[]> getMonthlyDataForSelectedProject(String projectName, String startDate, String endDate);

}
