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

	private final String BASIC_DATE_FORMAT = "yyyy-MM-dd";
	private final String START_TIME = " 00:00:00.0";

	@Override
	public String getDateMinusGivenValue(int value) {

		value = value * (-1);
		SimpleDateFormat sdf = new SimpleDateFormat(BASIC_DATE_FORMAT);

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, value);

		Date tempDate = calendar.getTime();

		return sdf.format(tempDate) + START_TIME;
	}

	@Override
	public String getToday() {

		SimpleDateFormat sdf = new SimpleDateFormat(BASIC_DATE_FORMAT);
		return sdf.format(new Date()) + START_TIME;
	}

	@Override
	public List<Object[]> prepareMonthlyStatsListToDisplay(List<Object[]> resultList, int monthLength) {

		// Create a list and fill it with the Object[]. The number of Object[] is equal
		// to monthLength
		List<Object[]> mainList = new ArrayList<>();

		// The list must contains the number of Object[] equals to monthLength
		addBlankObjectsToTheList(mainList, monthLength);

		// Populate mainList with the values from resultList
		populateListWithValuesFromAnotherList(resultList, mainList);

		return mainList;
	}

	/**
	 * Populates the mainList with the values from the resultList
	 * 
	 * @param resultList
	 *            The list of Object[] containing the list with values
	 * @param mainList
	 *            The list of Object[] containing the list to be populated
	 */
	private void populateListWithValuesFromAnotherList(List<Object[]> resultList, List<Object[]> mainList) {

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

	/**
	 * Adds a Object[] to the list. The list must contains the number of Object[]
	 * equals to monthLength
	 * 
	 * @param mainList
	 *            The list of Object[] containing the list to be populated
	 * @param monthLength
	 *            The int containing the length of the month
	 */
	private void addBlankObjectsToTheList(List<Object[]> mainList, int monthLength) {

		// Each tempObject contains two indexes: The number of the day and the
		// default value of the calculator usage equals to 0
		Object[] tempObject = new Object[2];

		for (int i = 0; i < monthLength; i++) {
			tempObject = new Object[2];

			tempObject[0] = i + 1;

			tempObject[1] = 0;
			mainList.add(tempObject);

		}

	}
}
