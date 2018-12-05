package pl.marcinmazur.portfolio.service;

import java.util.List;

import pl.marcinmazur.portfolio.utils.CodeUsageHistoryResult;
import pl.marcinmazur.portfolio.utils.ProjectVisitingHistoryResult;

public interface StatisticService {

	List<ProjectVisitingHistoryResult> getProjectVisitingHistoryListForGivenDate(String startDate, String endDate);

	long getSumOfContactFormMessages(String startDate, String endDate);

	List<CodeUsageHistoryResult> getCodeUsageHistoryResultListForGivenDate(String startDate, String endDate);

	String[] getGeneralStatisticsForSelectedProject(String projectName);

	List<Object[]> getMonthlyDataForSelectedProject(String projectName, String startDate, String endDate,
			int monthLength);

	String[] getGeneralStatisticsForMessages();

	List<Object[]> getMonthlyDataForMessages(String startDate, String endDate, int monthLength);

}
