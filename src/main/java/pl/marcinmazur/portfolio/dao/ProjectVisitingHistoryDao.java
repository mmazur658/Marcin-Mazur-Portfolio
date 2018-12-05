package pl.marcinmazur.portfolio.dao;

import java.util.List;

import pl.marcinmazur.portfolio.entity.ProjectVisitingHistory;

public interface ProjectVisitingHistoryDao {

	void saveProjectVisitingHistory(ProjectVisitingHistory projectVisitingHistory);

	long getSumOfVisitsForGivenProjectNameAndGivenDate(String projectName, String startDate, String endDate);

	List<String> getListOfProjectNames();

	List<Object[]> getMonthlyDataForSelectedProject(String projectName, String startDate, String endDate);

}
