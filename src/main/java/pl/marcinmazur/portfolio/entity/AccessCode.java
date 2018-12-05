package pl.marcinmazur.portfolio.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "access_codes")
public class AccessCode {

	/*
	 * The Entity private fields
	 */

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "access_code_value")
	private String accessCodeValue;

	@Column(name = "date_of_added")
	private Date dateOfAdded;

	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "access_code_owner")
	private String accessCodeOwner;

	@Column(name = "access_code_description")
	private String accessCodeDescription;

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

	public Date getDateOfAdded() {
		return dateOfAdded;
	}

	public void setDateOfAdded(Date dateOfAdded) {
		this.dateOfAdded = dateOfAdded;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getAccessCodeOwner() {
		return accessCodeOwner;
	}

	public void setAccessCodeOwner(String accessCodeOwner) {
		this.accessCodeOwner = accessCodeOwner;
	}

	public String getAccessCodeDescription() {
		return accessCodeDescription;
	}

	public void setAccessCodeDescription(String accessCodeDescription) {
		this.accessCodeDescription = accessCodeDescription;
	}

	/*
	 * Public methods
	 */
	public AccessCode(String accessCodeValue, Date dateOfAdded, boolean isActive, String accessCodeOwner,
			String accessCodeDescription) {
		this.accessCodeValue = accessCodeValue;
		this.dateOfAdded = dateOfAdded;
		this.isActive = isActive;
		this.accessCodeOwner = accessCodeOwner;
		this.accessCodeDescription = accessCodeDescription;
	}

	public AccessCode() {

	}

	public AccessCode(String accessCodeValue) {
		this.accessCodeValue = accessCodeValue;
	}

	@Override
	public String toString() {
		return "AccessCode [id=" + id + ", accessCodeValue=" + accessCodeValue + ", dateOfAdded=" + dateOfAdded
				+ ", isActive=" + isActive + ", accessCodeOwner=" + accessCodeOwner + ", accessCodeDescription="
				+ accessCodeDescription + "]";
	}

}
