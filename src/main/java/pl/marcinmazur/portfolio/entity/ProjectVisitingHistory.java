package pl.marcinmazur.portfolio.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a ProjectVisitingHistory record in the database
 * 
 * @author Marcin Mazur
 */
@Entity
@Table(name = "project_visiting_stats")
public class ProjectVisitingHistory {

	/**
	 * Unique identification number
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	/**
	 * The name of the project
	 */
	@Column(name = "project_name")
	private String projectName;

	/**
	 * The date of added
	 */
	@Column(name = "date")
	private Date date;

	/**
	 * The number of visits
	 */
	@Column(name = "number_of_visits")
	private int numberOfVisits;

	/**
	 * Gets the unique identification number of the ProjectVisitingHistory
	 * 
	 * @return A long representing the unique identification number of the
	 *         ProjectVisitingHistory
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the unique identification number of the ProjectVisitingHistory
	 * 
	 * @param id
	 *            A long containing the unique identification number of the
	 *            ProjectVisitingHistory
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the name of the project of the ProjectVisitingHistory
	 * 
	 * @return A String representing the name of the project of the
	 *         ProjectVisitingHistory
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * Sets the name of the project of the ProjectVisitingHistory
	 * 
	 * @param projectName
	 *            A String containing the name of the project of the
	 *            ProjectVisitingHistory
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * Gets the date of added of the ProjectVisitingHistory
	 * 
	 * @return A Date representing the date of added of the ProjectVisitingHistory
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date of added of the ProjectVisitingHistory
	 * 
	 * @param date
	 *            A Date containing the date of added of the ProjectVisitingHistory
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Gets the number of visits of the ProjectVisitingHistory
	 * 
	 * @return A int representing the the number of visits of the
	 *         ProjectVisitingHistory
	 */
	public int getNumberOfVisits() {
		return numberOfVisits;
	}

	/**
	 * Sets the the number of visits of the ProjectVisitingHistory
	 * 
	 * @param count
	 *            A int containing the the number of visits of the
	 *            ProjectVisitingHistory
	 */
	public void setNumberOfVisits(int count) {
		this.numberOfVisits = count;
	}

	/**
	 * Constructs a ProjectVisitingHistory object.
	 */
	public ProjectVisitingHistory() {

	}

	/**
	 * Constructs a ProjectVisitingHistory with the name of the project, date of
	 * added and number of visits.
	 * 
	 * @param projectName
	 *            The name of the project
	 * @param date
	 *            The date of added
	 * @param numberOfVisits
	 *            The number of visits
	 */
	public ProjectVisitingHistory(String projectName, Date date, int numberOfVisits) {
		this.projectName = projectName;
		this.date = date;
		this.numberOfVisits = numberOfVisits;
	}

	/**
	 * Returns the String representation of the ProjectVisitingHistory object.
	 * 
	 * @return The String representation of the ProjectVisitingHistory object.
	 */
	@Override
	public String toString() {
		return "ProjectVisitingHistory [id=" + id + ", projectName=" + projectName + ", date=" + date
				+ ", numberOfVisits=" + numberOfVisits + "]";
	}

}
