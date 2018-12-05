package pl.marcinmazur.portfolio.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notifications")
public class Notification {

	/*
	 * The Entity private fields
	 */

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "date_of_added")
	private Date dateOfAdded;

	@Column(name = "notification_type")
	private String notificationType;

	@Column(name = "notification_text")
	private String notificationText;

	@Column(name = "is_active")
	private boolean isActive;

	/*
	 * Setters and getters methods
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateOfAdded() {
		return dateOfAdded;
	}

	public void setDateOfAdded(Date dateOfAdded) {
		this.dateOfAdded = dateOfAdded;
	}

	public String getNotificationText() {
		return notificationText;
	}

	public void setNotificationText(String notificationText) {
		this.notificationText = notificationText;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	/*
	 * Public methods
	 */

	public Notification() {

	}

	public Notification(Date dateOfAdded, String notificationType, String notificationText, boolean isActive) {
		this.dateOfAdded = dateOfAdded;
		this.notificationType = notificationType;
		this.notificationText = notificationText;
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", dateOfAdded=" + dateOfAdded + ", notificationType=" + notificationType
				+ ", notificationText=" + notificationText + ", isActive=" + isActive + "]";
	}

}
