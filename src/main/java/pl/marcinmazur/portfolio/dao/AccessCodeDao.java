package pl.marcinmazur.portfolio.dao;

import java.util.List;

import pl.marcinmazur.portfolio.entity.AccessCode;

/**
 * Interface for performing database operations on AccessCode objects.
 * 
 * @author Marcin Mazur
 *
 */
public interface AccessCodeDao {

	/**
	 * Saves the AccessCode object in the database.
	 * 
	 * @param accessCode
	 *            The AccessCode the be saved
	 */
	void addNewAccessCode(AccessCode accessCode);

	/**
	 * Returns the list of all access codes
	 * 
	 * @return The List&lt;AccessCode&gt; representing the list of all AccessCodes
	 */
	List<AccessCode> getListOfAccessCodes();

	/**
	 * Returns the AccessCode object with the given id
	 * 
	 * @param accessCodeId
	 *            A int containing the id of AccessCode
	 * @return The AccessCode with given id
	 */
	AccessCode getAccessCodeById(int accessCodeId);

	/**
	 * Returns the AccessCode object with the given value of access code
	 * 
	 * @param accessCodeValue
	 *            A String containing the value of AccessCode
	 * @return The AccessCode with given value
	 */
	AccessCode getAccessCodeByValue(String accessCodeValue);

	/**
	 * Returns the list of all access code values
	 * 
	 * @return The List&lt;String&gt; representing the list of all access code
	 *         values
	 */
	List<String> getListOfAccessCodeValues();

	/**
	 * Returns the the name of the owner of the access code
	 * 
	 * @param accessCodeValue
	 *            A String containing the value of AccessCode
	 * @return The String representing the name of the owner of the access code
	 */
	String getAccessCodeOwenerByGivenAccessCodeValue(String accessCodeValue);

}
