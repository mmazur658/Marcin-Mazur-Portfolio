package pl.marcinmazur.portfolio.dao;

import pl.marcinmazur.portfolio.entity.User;

/**
 * Interface for performing database operations on User objects.
 * 
 * @author Marcin Mazur
 *
 */
public interface UserDao {

	/**
	 * Returns the user with given user name
	 * 
	 * @param userName
	 *            The String containing the name of user
	 * @return A User representing the user with given name of user
	 */
	User getUserByUserName(String userName);

}
