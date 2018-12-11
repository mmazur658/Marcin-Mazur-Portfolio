package pl.marcinmazur.portfolio.service;

import java.text.ParseException;
import java.util.List;

import pl.marcinmazur.portfolio.entity.AccessCode;

/**
 * Interface for managing access codes
 * 
 * @author Marcin Mazur
 *
 */
public interface AccessCodeService {

	/**
	 * Creates and saves new access code with the given value, owner and
	 * description.
	 * 
	 * @param newAccessCodeValue
	 *            The String containing the value of the access code
	 * @param newAccessCodeOwner
	 *            The String containing the owner of the access code
	 * @param newAccessCodeDescription
	 *            The String containing the description of the access code
	 */
	void createNewAccessCode(String newAccessCodeValue, String newAccessCodeOwner, String newAccessCodeDescription);

	/**
	 * Returns TRUE is access code is unique
	 * 
	 * @param accessCodeValue
	 *            The String containing the value of the access code
	 * @return A boolean representing the result
	 */
	boolean isAccessCodeUnique(String accessCodeValue);

	/**
	 * Returns the list of all AccessCodes
	 * 
	 * @return A List&lt;AccessCode&gt; representing the list of all access codes
	 */
	List<AccessCode> getAccessCodes();

	/**
	 * Return AccessCode with given id
	 * 
	 * @param accessCodeId
	 *            The int containing the id of the access code
	 * @return An AccessCode representing the access code with given id
	 */
	AccessCode getAccessCode(int accessCodeId);

	/**
	 * Updates AccessCode with the given parameters
	 * 
	 * @param accessCodeId
	 *            The String containing the id of the access code
	 * @param accessCodeValue
	 *            The String containing the value of the access code
	 * @param accessCodeDate
	 *            The String containing the date of added of the access code
	 * @param accessCodeIsActive
	 *            The boolean containing the isActive status of the access code
	 * @param accessCodeDescription
	 *            The String containing the description of the access code
	 * @param accessCodeOwner
	 *            The String containing the owner of the access code
	 * @throws ParseException
	 *             A ParseException is thrown when the String Date can't be parsed
	 *             to Date object.
	 */
	void updateAccessCode(String accessCodeId, String accessCodeValue, String accessCodeDate,
			boolean accessCodeIsActive, String accessCodeDescription, String accessCodeOwner) throws ParseException;

	/**
	 * Returns TRUE is access code is correct
	 * 
	 * @param accessCodeValue
	 *            The String containing the value of the access code
	 * @return A boolean representing the result
	 */
	boolean isAccessCodeCorrect(String accessCodeValue);

}
