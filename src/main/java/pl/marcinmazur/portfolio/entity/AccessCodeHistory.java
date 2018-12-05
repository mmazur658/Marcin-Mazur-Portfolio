package pl.marcinmazur.portfolio.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "access_code_history")
public class AccessCodeHistory {

	/*
	 * The Entity private fields
	 */

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "access_code_value")
	private String accessCodeValue;

	@Column(name = "action")
	private String action;

	@Column(name = "action_date")
	private Date actionDate;

	/*
	 * Setters and getters methods
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccessCodeValue() {
		return accessCodeValue;
	}

	public void setAccessCodeValue(String accessCodeValue) {
		this.accessCodeValue = accessCodeValue;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getActionDate() {
		return actionDate;
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}

	/*
	 * Public methods
	 */

	public AccessCodeHistory() {

	}

	public AccessCodeHistory(String accessCode, String action, Date actionDate) {
		this.accessCodeValue = accessCode;
		this.action = action;
		this.actionDate = actionDate;
	}

	@Override
	public String toString() {
		return "AccessCodeHistory [id=" + id + ", accessCodeValue=" + accessCodeValue + ", action=" + action
				+ ", actionDate=" + actionDate + "]";
	}

}
