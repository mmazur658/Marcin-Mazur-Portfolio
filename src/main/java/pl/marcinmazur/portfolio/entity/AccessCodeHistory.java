package pl.marcinmazur.portfolio.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a AccessCodeHistory record in the database
 * 
 * @author Marcin Mazur
 */
@Entity
@Table(name = "access_code_history")
public class AccessCodeHistory {

	/**
	 * Unique identification number
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * the value of access code
	 */
	@Column(name = "access_code_value")
	private String accessCodeValue;

	/**
	 * The name of the action
	 */
	@Column(name = "action")
	private String action;

	/**
	 * the date of action
	 */
	@Column(name = "action_date")
	private Date actionDate;

	/**
	 * Gets the unique identification number of the AccessCodeHistory
	 * 
	 * @return A long representing the unique identification number of the
	 *         AccessCodeHistory
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the unique identification number of the AccessCodeHistory
	 * 
	 * @param id
	 *            A long containings the unique identification number of the
	 *            AccessCodeHistory
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the access code value of the AccessCodeHistory
	 * 
	 * @return A String representing the access code value of the AccessCodeHistory
	 */
	public String getAccessCodeValue() {
		return accessCodeValue;
	}

	/**
	 * Sets the access code value of the AccessCodeHistory
	 * 
	 * @param accessCodeValue
	 *            A String containings the access code value of the
	 *            AccessCodeHistory
	 */
	public void setAccessCodeValue(String accessCodeValue) {
		this.accessCodeValue = accessCodeValue;
	}

	/**
	 * Gets the name of action of the AccessCodeHistory
	 * 
	 * @return A String representing the name of action of the AccessCodeHistory
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Sets the name of action of the AccessCodeHistory
	 * 
	 * @param action
	 *            A String containings the name of action of the AccessCodeHistory
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * Gets the date of action of the AccessCodeHistory
	 * 
	 * @return A Date representing the date of action of the AccessCodeHistory
	 */
	public Date getActionDate() {
		return actionDate;
	}

	/**
	 * Sets the date of action of the AccessCodeHistory
	 * 
	 * @param actionDate
	 *            A Date containings the date of action of the AccessCodeHistory
	 */
	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}

	/**
	 * Constructs a AccessCodeHistory object.
	 */
	public AccessCodeHistory() {

	}

	/**
	 * Constructs a AccessCodeHistory with the value of access code, name of action
	 * and date of added.
	 * 
	 * @param accessCode
	 *            The value of access code
	 * @param action
	 *            The name of the action
	 * @param actionDate
	 *            The date of added
	 */
	public AccessCodeHistory(String accessCode, String action, Date actionDate) {
		this.accessCodeValue = accessCode;
		this.action = action;
		this.actionDate = actionDate;
	}

	/**
	 * Returns the String representation of the AccessCodeHistory object.
	 * 
	 * @return The String representation of the AccessCodeHistory object.
	 */
	@Override
	public String toString() {
		return "AccessCodeHistory [id=" + id + ", accessCodeValue=" + accessCodeValue + ", action=" + action
				+ ", actionDate=" + actionDate + "]";
	}

}
