package pl.marcinmazur.portfolio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a User record in the database
 * 
 * @author Marcin Mazur
 */
@Entity
@Table(name = "users")
public class User {

	/**
	 * Unique user name
	 */
	@Id
	@Column(name = "username")
	private String username;

	/**
	 * The password of the user
	 */
	@Column(name = "password")
	private String password;

	/**
	 * The status of the user
	 */
	@Column(name = "enabled")
	private boolean enabled;

	/**
	 * Gets the user name of the User
	 * 
	 * @return A String representing the user name of the User
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the user name of the User
	 * 
	 * @param username
	 *            A String containing the user name of the User
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password of the User
	 * 
	 * @return A String representing the password of the User
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the User
	 * 
	 * @param password
	 *            A String containing the password of the User
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the enabled status of the User
	 * 
	 * @return A boolean representing the enabled status of the User
	 */
	public boolean getIsEnabled() {
		return enabled;
	}

	/**
	 * Sets the enabled status of the User
	 * 
	 * @param enabled
	 *            A boolean containing the enabled status of the User
	 */
	public void setIsEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Constructs a User object.
	 */
	public User() {

	}

	/**
	 * Constructs a User with the name of the user, password and enabled status.
	 * 
	 * @param username The name of the user
	 * @param password The password of the user
	 * @param enabled the status of the user
	 */
	public User(String username, String password, boolean enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	/**
	 * Returns the String representation of the User object.
	 * 
	 * @return The String representation of the User object.
	 */
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", enabled=" + enabled + "]";
	}

}
