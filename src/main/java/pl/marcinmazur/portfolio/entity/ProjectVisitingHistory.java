package pl.marcinmazur.portfolio.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project_visiting_stats")
public class ProjectVisitingHistory {

	/*
	 * The Entity private fields
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "project_name")
	private String projectName;

	@Column(name = "date")
	private Date date;

	@Column(name = "number_of_visits")
	private int numberOfVisits;

	/*
	 * Setters and getters methods
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getNumberOfVisits() {
		return numberOfVisits;
	}

	public void setNumberOfVisits(int count) {
		this.numberOfVisits = count;
	}

	/*
	 * Public methods
	 */

	public ProjectVisitingHistory(String projectName, Date date, int numberOfVisits) {
		this.projectName = projectName;
		this.date = date;
		this.numberOfVisits = numberOfVisits;
	}

	public ProjectVisitingHistory() {

	}

	@Override
	public String toString() {
		return "ProjectVisitingHistory [id=" + id + ", projectName=" + projectName + ", date=" + date
				+ ", numberOfVisits=" + numberOfVisits + "]";
	}

}
