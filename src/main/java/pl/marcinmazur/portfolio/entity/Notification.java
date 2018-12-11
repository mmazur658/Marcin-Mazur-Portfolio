package pl.marcinmazur.portfolio.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a Notification record in the database
 * 
 * @author Marcin Mazur
 */
@Entity
@Table(name = "notifications")
public class Notification {

	/**
	 * Unique identification number
	 */
	@Id
	@Column(name = "id")
	private int id;

	/**
	 * The date of added
	 */
	@Column(name = "date_of_added")
	private Date dateOfAdded;

	/**
	 * The type of notification
	 */
	@Column(name = "notification_type")
	private String notificationType;

	/**
	 * The text of notification
	 */
	@Column(name = "notification_text")
	private String notificationText;

	/**
	 * The isActive status
	 */
	@Column(name = "is_active")
	private boolean isActive;

	/**
	 * Gets the unique identification number of the Notification
	 * 
	 * @return A long representing the unique identification number of the
	 *         Notification
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the unique identification number of the Notification
	 * 
	 * @param id
	 *            A long containing the unique identification number of the
	 *            Notification
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the date of added of the Notification
	 * 
	 * @return A Date representing the date of added of the Notification
	 */
	public Date getDateOfAdded() {
		return dateOfAdded;
	}

	/**
	 * Sets the date of added of the Notification
	 * 
	 * @param dateOfAdded
	 *            A Date containing the date of added of the Notification
	 */
	public void setDateOfAdded(Date dateOfAdded) {
		this.dateOfAdded = dateOfAdded;
	}

	/**
	 * Gets the text of the Notification
	 * 
	 * @return A String representing the text of the Notification
	 */
	public String getNotificationText() {
		return notificationText;
	}

	/**
	 * Sets the text of the Notification
	 * 
	 * @param notificationText
	 *            A String containing the text of the Notification
	 */
	public void setNotificationText(String notificationText) {
		this.notificationText = notificationText;
	}

	/**
	 * Gets the isActive status of the Notification
	 * 
	 * @return A boolean representing the isActive status of the Notification
	 */
	public boolean getIsActive() {
		return isActive;
	}

	/**
	 * Sets the isActive status of the Notification
	 * 
	 * @param isActive
	 *            A boolean containing the isActive status of the Notification
	 */
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * Gets the type of the Notification
	 * 
	 * @return A String representing the type of the Notification
	 */
	public String getNotificationType() {
		return notificationType;
	}

	/**
	 * Sets the type of the Notification
	 * 
	 * @param notificationType
	 *            A String containing the type of the Notification
	 */
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	/**
	 * Constructs a Notification object.
	 */
	public Notification() {

	}

	/**
	 * Constructs a Notification with the date of added, type of notification, text
	 * of notification and isActive status.
	 * 
	 * @param dateOfAdded
	 *            The date of added
	 * @param notificationType
	 *            The type of the notification
	 * @param notificationText
	 *            The text of the notification
	 * @param isActive
	 *            The isActive status
	 */
	public Notification(Date dateOfAdded, String notificationType, String notificationText, boolean isActive) {
		this.dateOfAdded = dateOfAdded;
		this.notificationType = notificationType;
		this.notificationText = notificationText;
		this.isActive = isActive;
	}

	/**
	 * Returns the String representation of the Notification object.
	 * 
	 * @return The String representation of the Notification object.
	 */
	@Override
	public String toString() {
		return "Notification [id=" + id + ", dateOfAdded=" + dateOfAdded + ", notificationType=" + notificationType
				+ ", notificationText=" + notificationText + ", isActive=" + isActive + "]";
	}

}
