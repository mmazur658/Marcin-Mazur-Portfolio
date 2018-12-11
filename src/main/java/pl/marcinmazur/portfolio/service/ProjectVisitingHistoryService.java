package pl.marcinmazur.portfolio.service;

import java.text.ParseException;

/**
 * Interface for managing history of projects visiting.
 * 
 * @author Marcin Mazur
 *
 */
public interface ProjectVisitingHistoryService {

	/**
	 * Creates and saves the ProjectVisitingHistory with given name of the project
	 * 
	 * @param projectName
	 *            The String containing the name of the project
	 * @throws ParseException - A ParseException is thrown then the String date can't be parsed to Date object.
	 *
	 */
	void addProjectVisitingHistory(String projectName) throws ParseException;

}
