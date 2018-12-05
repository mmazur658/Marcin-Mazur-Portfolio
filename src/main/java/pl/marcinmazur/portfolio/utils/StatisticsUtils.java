package pl.marcinmazur.portfolio.utils;

import java.util.List;

public interface StatisticsUtils {

	String getDateMinusGivenValue(int value);

	String getToday();

	List<Object[]> prepareMonthlyStatsListToDisplay(List<Object[]> resultList, int monthLength);

	CodeUsageHistoryResult createCodeUsageHistoryResult(String accessCodeValue,
			long sumOfUsage, String accessCodeOwener);

	List<CodeUsageHistoryResult> prepareCodeUsageHistoryResultListToDisplay(List<CodeUsageHistoryResult> codeUsageHistoryResultList);

	ProjectVisitingHistoryResult createProjectVisitingHistoryResult(String projectName,
			long sumOfVisits);

}
