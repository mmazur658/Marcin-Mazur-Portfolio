package pl.marcinmazur.portfolio.service;

import java.util.List;

import pl.marcinmazur.portfolio.entity.AccessCodeHistory;

/**
 * Interface for managing history of access code
 * 
 * @author Marcin Mazur
 *
 */
public interface AccessCodeHistoryService {

	/**
	 * Creates and save the access code history for given access code value after
	 * creating new access code
	 * 
	 * @param accessCodeValue
	 *            The String containing the value of the access code
	 */
	void createNewAccessCodeHistory(String accessCodeValue);

	/**
	 * Creates and saves the access code history for given access code value after
	 * first code using.
	 * 
	 * @param accessCodeValue
	 *            The String containing the value of the access code
	 */
	void addAccessCodeHistoryAfterCodeUse(String accessCodeValue);

	/**
	 * Returns the list of AccessCodeHistory for given value of access code
	 * 
	 * @param accessCodeValue
	 *            The String containing the value of the access code
	 * @return A List&lt;AccessCodeHistory&gt; representing the list of access code
	 *         history
	 */
	List<AccessCodeHistory> getAccessCodeHistoryList(String accessCodeValue);

	/**
	 * Returns TRUE if the access code has been used before
	 * 
	 * @param accessCodeValue
	 *            The String containing the value of the access code
	 * @return A boolean representing the result
	 */
	boolean hasCodeBeenUsedBefore(String accessCodeValue);

}
