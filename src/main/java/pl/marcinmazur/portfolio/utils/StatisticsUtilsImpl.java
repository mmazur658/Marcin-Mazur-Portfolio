package pl.marcinmazur.portfolio.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Utility class used to perform operations on access codes and project
 * statistics
 * 
 * @author Marcin Mazur
 *
 */
@Component
public class StatisticsUtilsImpl implements StatisticsUtils {

	@Override
	public String getDateMinusGivenValue(int value) {

		value = value * (-1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, value);

		Date tempDate = calendar.getTime();

		return sdf.format(tempDate) + " 00:00:00.0";
	}

	@Override
	public String getToday() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		return sdf.format(new Date()) + " 00:00:00.0";
	}

	@Override
	public List<Object[]> prepareMonthlyStatsListToDisplay(List<Object[]> resultList, int monthLength) {

		// Create a list and fill it with the Object[]. The number of Object[] is equal
		// to monthLength
		List<Object[]> mainList = new ArrayList<>();

		// Each tempObject contains the two indexes: The number of the day and the
		// default value of the calculator usage = 0
		Object[] tempObject = new Object[2];

		for (int i = 0; i < monthLength; i++) {
			tempObject = new Object[2];

			tempObject[0] = i + 1;

			tempObject[1] = 0;
			mainList.add(tempObject);

		}

		// Populate mainList with the values from resultList
		String tempString;
		int index;
		int tempValue;

		for (Object[] object : resultList) {

			tempString = String.valueOf(object[0]);

			tempString = tempString.substring(8, 10);

			index = Integer.parseInt(tempString);

			tempValue = Integer.valueOf((mainList.get(index - 1)[1].toString()))
					+ Integer.valueOf(object[1].toString());

			mainList.get(index - 1)[1] = tempValue;

		}

		return mainList;
	}

	@Override
	public CodeUsageHistoryResult createCodeUsageHistoryResult(String accessCodeValue, long sumOfUsage,
			String accessCodeOwener) {

		CodeUsageHistoryResult codeUsageHistoryResult = new CodeUsageHistoryResult();

		codeUsageHistoryResult = new CodeUsageHistoryResult();
		codeUsageHistoryResult.setAccessCodeValue(accessCodeValue);
		codeUsageHistoryResult.setSumOfUsing(sumOfUsage);
		codeUsageHistoryResult.setAccessCodeOwner(accessCodeOwener);

		return codeUsageHistoryResult;
	}

	@Override
	public List<CodeUsageHistoryResult> prepareCodeUsageHistoryResultListToDisplay(
			List<CodeUsageHistoryResult> codeUsageHistoryResultList) {

		codeUsageHistoryResultList.sort(Comparator.comparing(CodeUsageHistoryResult::getSumOfUsing).reversed());
		codeUsageHistoryResultList = codeUsageHistoryResultList.subList(0, 3);

		return codeUsageHistoryResultList;

	}

	@Override
	public ProjectVisitingHistoryResult createProjectVisitingHistoryResult(String projectName, long sumOfVisits) {

		ProjectVisitingHistoryResult theProjectVisitingHistoryResult = new ProjectVisitingHistoryResult();

		theProjectVisitingHistoryResult = new ProjectVisitingHistoryResult();
		theProjectVisitingHistoryResult.setProjectName(projectName);
		theProjectVisitingHistoryResult.setSumOfVisits(sumOfVisits);

		return theProjectVisitingHistoryResult;
	}
}
