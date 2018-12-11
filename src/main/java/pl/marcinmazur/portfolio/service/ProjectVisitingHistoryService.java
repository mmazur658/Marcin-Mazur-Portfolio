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
	 */
	void addProjectVisitingHistory(String string) throws ParseException;

}
