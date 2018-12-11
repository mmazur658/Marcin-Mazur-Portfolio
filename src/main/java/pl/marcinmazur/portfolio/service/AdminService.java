package pl.marcinmazur.portfolio.service;

/**
 * Interface for managing administrator`s password
 * 
 * @author Marcin Mazur
 *
 */
public interface AdminService {

	/**
	 * Returns TRUE if the password is correct
	 * 
	 * @param thePassword
	 *            The String containing the password of the user
	 * @param username
	 *            The String containing the user`s name
	 * @return A boolean representing the result
	 */
	boolean checkPassword(String thePassword, String username);

	/**
	 * Changes the password of the user
	 * 
	 * @param thePassword
	 *            The String containing the new password of the user
	 * @param username
	 *            The String containing the user`s name
	 */
	void changePassword(String thePassword, String username);

}
