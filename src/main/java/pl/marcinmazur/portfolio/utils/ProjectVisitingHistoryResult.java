package pl.marcinmazur.portfolio.utils;

/**
 * Helper class used to display data related to ProjectVisitingHistory on the
 * web page.
 * 
 * @author Marcin
 *
 */
public class ProjectVisitingHistoryResult {

	/**
	 * The name of the project
	 */
	private String projectName;

	/**
	 * The number of the project visits
	 */
	private long sumOfVisits;

	/**
	 * Gets the name of the project
	 * 
	 * @return A String representing the name of the project
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * Sets the name of the project
	 * 
	 * @param projectName
	 *            The String containing the name of the project
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * Gets the number of the project visits
	 * 
	 * @return A long representing the number of the project visits
	 */
	public long getSumOfVisits() {
		return sumOfVisits;
	}

	/**
	 * Sets the number of the project visits
	 * 
	 * @param sumOfVisits
	 *            The long containing the number of the project visits
	 */
	public void setSumOfVisits(long sumOfVisits) {
		this.sumOfVisits = sumOfVisits;
	}

}
