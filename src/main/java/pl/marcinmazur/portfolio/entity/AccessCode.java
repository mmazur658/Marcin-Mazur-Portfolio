package pl.marcinmazur.portfolio.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a AccessCode record in the database
 * 
 * @author Marcin Mazur
 */
@Entity
@Table(name = "access_codes")
public class AccessCode {

	/**
	 * Unique identification number
	 */
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * The date of added of the ContactFormMessage
	 */
	@Column(name = "access_code_value")
	private String accessCodeValue;

	/**
	 * The date of added
	 */
	@Column(name = "date_of_added")
	private Date dateOfAdded;

	/**
	 * The isActive status
	 */
	@Column(name = "is_active")
	private boolean isActive;

	/**
	 * The name of the owner of the access code
	 */
	@Column(name = "access_code_owner")
	private String accessCodeOwner;

	/**
	 * The description of the code
	 */
	@Column(name = "access_code_description")
	private String accessCodeDescription;

	/**
	 * Gets the unique identification number of the AccessCode
	 * 
	 * @return A long representing the unique identification number of the
	 *         AccessCode
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the unique identification number of the AccessCode
	 * 
	 * @param id
	 *            A long containings the unique identification number of the
	 *            AccessCode
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the access code value of the AccessCode
	 * 
	 * @return A String representing the access code value of the AccessCode
	 */
	public String getAccessCodeValue() {
		return accessCodeValue;
	}

	/**
	 * Sets the access code value of the AccessCode
	 * 
	 * @param accessCodeValue
	 *            A String containings the access code value of the AccessCode
	 */
	public void setAccessCodeValue(String accessCodeValue) {
		this.accessCodeValue = accessCodeValue;
	}

	/**
	 * Gets the date of added of the AccessCode
	 * 
	 * @return A Date representing the date of added of the AccessCode
	 */
	public Date getDateOfAdded() {
		return dateOfAdded;
	}

	/**
	 * Sets the date of added of the AccessCode
	 * 
	 * @param dateOfAdded
	 *            A Date containings the date of added of the AccessCode
	 */
	public void setDateOfAdded(Date dateOfAdded) {
		this.dateOfAdded = dateOfAdded;
	}

	/**
	 * Gets the isActive status of the AccessCode
	 * 
	 * @return A boolean representing the isActive status of the AccessCode
	 */
	public boolean getIsActive() {
		return isActive;
	}

	/**
	 * Sets the isActive status of the AccessCode
	 * 
	 * @param isActive
	 *            A boolean containings the isActive status of the AccessCode
	 */
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * Gets the owner of the AccessCode
	 * 
	 * @return A String representing the owner of the AccessCode
	 */
	public String getAccessCodeOwner() {
		return accessCodeOwner;
	}

	/**
	 * Sets the owner of the AccessCode
	 * 
	 * @param accessCodeOwner
	 *            A String containings the owner of the AccessCode
	 */
	public void setAccessCodeOwner(String accessCodeOwner) {
		this.accessCodeOwner = accessCodeOwner;
	}

	/**
	 * Gets the description of the AccessCode
	 * 
	 * @return A String representing the description of the AccessCode
	 */
	public String getAccessCodeDescription() {
		return accessCodeDescription;
	}

	/**
	 * Sets the description of the AccessCode
	 * 
	 * @param accessCodeDescription
	 *            A String containings the description of the AccessCode
	 */
	public void setAccessCodeDescription(String accessCodeDescription) {
		this.accessCodeDescription = accessCodeDescription;
	}

	/**
	 * Constructs an AccessCode object.
	 */
	public AccessCode() {

	}

	/**
	 * Constructs an AccessCode with the value of access code
	 * 
	 * @param accessCodeValue
	 *            The value of the access code
	 */
	public AccessCode(String accessCodeValue) {
		this.accessCodeValue = accessCodeValue;
	}

	/**
	 * Constructs an AccessCode with the value of access code, date of added,
	 * isActive status and the owner of access code.
	 * 
	 * @param accessCodeValue
	 *            The value of the access code
	 * @param dateOfAdded
	 *            The date of added
	 * @param isActive
	 *            The isActive status
	 * @param accessCodeOwner
	 *            The owner of the access code
	 * @param accessCodeDescription
	 *            The description of the access code
	 */
	public AccessCode(String accessCodeValue, Date dateOfAdded, boolean isActive, String accessCodeOwner,
			String accessCodeDescription) {
		this.accessCodeValue = accessCodeValue;
		this.dateOfAdded = dateOfAdded;
		this.isActive = isActive;
		this.accessCodeOwner = accessCodeOwner;
		this.accessCodeDescription = accessCodeDescription;
	}

	/**
	 * Returns the String representation of the AccessCode object.
	 * 
	 * @return The String representation of the AccessCode object.
	 */
	@Override
	public String toString() {
		return "AccessCode [id=" + id + ", accessCodeValue=" + accessCodeValue + ", dateOfAdded=" + dateOfAdded
				+ ", isActive=" + isActive + ", accessCodeOwner=" + accessCodeOwner + ", accessCodeDescription="
				+ accessCodeDescription + "]";
	}

}
