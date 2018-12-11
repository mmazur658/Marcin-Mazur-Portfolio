package pl.marcinmazur.portfolio.utils;

import pl.marcinmazur.portfolio.entity.AccessCode;
import pl.marcinmazur.portfolio.entity.AccessCodeHistory;

/**
 * Interface used to perform operations on AccessCodeHistory and AccessCode
 * objects,
 * 
 * @author Marcin Mazur
 *
 */
public interface AccessCodeUtils {

	/**
	 * Creates and returns An AccessCodeHistory with given parameters
	 * 
	 * @param accessCodeValue
	 *            The String containing the value of the access code
	 * @param accessCodeHistoryActionName
	 *            The String containing the name of the action performed on the
	 *            AccessCode object
	 * @return An AccessCodeHistory representing the
	 */
	AccessCodeHistory createAccessCodeHistory(String accessCodeValue, String accessCodeHistoryActionName);

	/**
	 * Return TRUE if the has been used before.
	 * 
	 * @param numberOfCodeUsage
	 *            The long containing the number of code usage
	 * @return A boolean representing the result
	 */
	boolean hasCodeBeenUsedBefore(long numberOfCodeUsage);

	/**
	 * Updates the AccessCode with given parameters.
	 * 
	 * @param accessCode
	 *            The AccessCode containing the AccessCode to be updated
	 * @param accessCodeDate
	 *            The String containing the date of added of the access code
	 * @param accessCodeIsActive
	 *            The boolean containing the isActive status of the access code
	 * @param accessCodeDescription
	 *            The String containing the description of the access code
	 * @param accessCodeOwner
	 *            The String containing the owner of the access code
	 */
	void updateAccessCode(AccessCode accessCode, String accessCodeDate, boolean accessCodeIsActive,
			String accessCodeDescription, String accessCodeOwner);

	/**
	 * Creates and returns an AccessCode with the given parameters.
	 * 
	 * @param newAccessCodeValue
	 *            The String containing the value of the access code
	 * @param newAccessCodeOwner
	 *            The String containing the owner of the access code
	 * @param newAccessCodeDescription
	 *            The String containing the description of the access code
	 * @return An AccessCode representing the AccessCode with given parameters
	 */
	AccessCode createNewAccessCode(String newAccessCodeValue, String newAccessCodeOwner,
			String newAccessCodeDescription);

}
