package pl.marcinmazur.portfolio.dao;

import java.util.List;

import pl.marcinmazur.portfolio.entity.AccessCodeHistory;

/**
 * Interface for performing database operations on AccessCodeHistory objects.
 * 
 * @author Marcin Mazur
 *
 */
public interface AccessCodeHistoryDao {

	/**
	 * Saves the AccessCodeHistory in the database
	 * 
	 * @param accessCodeHistory
	 *            The AccessCodeHistory to be saved
	 */
	void saveAccessCodeHistory(AccessCodeHistory accessCodeHistory);

	/**
	 * Returns the list of AccessCodeHistory with given value of AccessCode
	 * 
	 * @param accessCodeValue
	 *            A String containing the value of AccessCode
	 * @return A List&lt;AccessCodeHistory&gt; representing the list list of
	 *         AccessCodeHistory with given value of AccessCode
	 */
	List<AccessCodeHistory> getListOfAccessCodeHistory(String accessCodeValue);

	/**
	 * Returns the number of code usage with given value of accesCode and name of
	 * the action
	 * 
	 * @param accessCodeValue
	 *            A String containing the value of AccessCode
	 * @param action
	 *            A String containing the name of the action
	 * @return A long representing the number of code usage
	 */
	long getNumberOfCodeUsage(String accessCodeValue, String action);

	/**
	 * Returns the number of code usage with given value of accesCode and given date
	 * range
	 * 
	 * @param accessCodeValue
	 *            A String containing the value of AccessCode
	 * @param startDate
	 *            A String containing the first day of the range
	 * @param endDate
	 *            A String containing the last day of the range
	 * @return A long representing the number of code usage
	 */
	long getNumberOfGivenAccessCodeValueAndGivenDate(String accessCodeValue, String startDate, String endDate);

}
