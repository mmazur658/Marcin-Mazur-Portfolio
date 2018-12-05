package pl.marcinmazur.portfolio.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

class StatisticsUtilsTest {

	private StatisticsUtilsImpl statisticsUtilsImpl = new StatisticsUtilsImpl();

	@Test
	void shoudlGetDateMinusGivenValue() {

		int value = 5;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, (-1) * value);
		Date tempDate = calendar.getTime();

		String expectedDate = sdf.format(tempDate) + " 00:00:00.0";

		assertEquals(expectedDate, statisticsUtilsImpl.getDateMinusGivenValue(value));

	}

	@Test
	void shouldGetToday() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String expectedDate = sdf.format(new Date()) + " 00:00:00.0";

		assertEquals(expectedDate, statisticsUtilsImpl.getToday());
	}

	@Test
	void shouldPrepareMonthlyStatsListToDisplay() {

		List<Object[]> resultList = new ArrayList<>();
		int monthLength = 31;

		Object[] tempObject = new Object[2];
		tempObject[0] = "2018-11-05";
		tempObject[1] = 15;
		resultList.add(tempObject);

		tempObject = new Object[2];
		tempObject[0] = "2018-11-11";
		tempObject[1] = 22;
		resultList.add(tempObject);

		tempObject = new Object[2];
		tempObject[0] = "2018-11-17";
		tempObject[1] = 5;
		resultList.add(tempObject);

		List<Object[]> mainList = statisticsUtilsImpl.prepareMonthlyStatsListToDisplay(resultList, monthLength);

		assertEquals(monthLength, mainList.size());
		assertEquals(5, mainList.get(4)[0]);
		assertEquals(11, mainList.get(10)[0]);
		assertEquals(17, mainList.get(16)[0]);
		assertEquals(15, mainList.get(4)[1]);
		assertEquals(22, mainList.get(10)[1]);
		assertEquals(5, mainList.get(16)[1]);
		assertEquals(8, mainList.get(7)[0]);
		assertEquals(20, mainList.get(19)[0]);
		assertEquals(25, mainList.get(24)[0]);
		assertEquals(0, mainList.get(7)[1]);
		assertEquals(0, mainList.get(19)[1]);
		assertEquals(0, mainList.get(24)[1]);

	}

	@Test
	void shouldCreateCodeUsageHistoryResult() {

		String accessCodeValue = "222222";
		long sumOfUsage = 10;
		String accessCodeOwener = "Test Owner";

		CodeUsageHistoryResult codeUsageHistoryResult = statisticsUtilsImpl
				.createCodeUsageHistoryResult(accessCodeValue, sumOfUsage, accessCodeOwener);

		assertEquals(accessCodeOwener, codeUsageHistoryResult.getAccessCodeOwner());
		assertEquals(accessCodeValue, codeUsageHistoryResult.getAccessCodeValue());
		assertEquals(sumOfUsage, codeUsageHistoryResult.getSumOfUsing());

	}

	@Test
	void shouldPrepareCodeUsageHistoryResultListToDisplay() {

		List<CodeUsageHistoryResult> codeUsageHistoryResultList = new ArrayList<>();

		CodeUsageHistoryResult codeUsageHistoryResult = new CodeUsageHistoryResult();
		codeUsageHistoryResult.setAccessCodeValue("222222");
		codeUsageHistoryResult.setSumOfUsing(15);
		codeUsageHistoryResultList.add(codeUsageHistoryResult);

		codeUsageHistoryResult = new CodeUsageHistoryResult();
		codeUsageHistoryResult.setAccessCodeValue("452568");
		codeUsageHistoryResult.setSumOfUsing(8);
		codeUsageHistoryResultList.add(codeUsageHistoryResult);

		codeUsageHistoryResult = new CodeUsageHistoryResult();
		codeUsageHistoryResult.setAccessCodeValue("111111");
		codeUsageHistoryResult.setSumOfUsing(2);
		codeUsageHistoryResultList.add(codeUsageHistoryResult);

		codeUsageHistoryResult = new CodeUsageHistoryResult();
		codeUsageHistoryResult.setAccessCodeValue("333333");
		codeUsageHistoryResult.setSumOfUsing(37);
		codeUsageHistoryResultList.add(codeUsageHistoryResult);

		codeUsageHistoryResult = new CodeUsageHistoryResult();
		codeUsageHistoryResult.setAccessCodeValue("444444");
		codeUsageHistoryResult.setSumOfUsing(11);
		codeUsageHistoryResultList.add(codeUsageHistoryResult);

		assertTrue(codeUsageHistoryResultList.size() == 5);

		codeUsageHistoryResultList = statisticsUtilsImpl
				.prepareCodeUsageHistoryResultListToDisplay(codeUsageHistoryResultList);

		assertTrue(codeUsageHistoryResultList.size() == 3);
		assertEquals("333333", codeUsageHistoryResultList.get(0).getAccessCodeValue());
		assertEquals("222222", codeUsageHistoryResultList.get(1).getAccessCodeValue());
		assertEquals("444444", codeUsageHistoryResultList.get(2).getAccessCodeValue());
		assertEquals(37, codeUsageHistoryResultList.get(0).getSumOfUsing());
		assertEquals(15, codeUsageHistoryResultList.get(1).getSumOfUsing());
		assertEquals(11, codeUsageHistoryResultList.get(2).getSumOfUsing());

	}

	@Test
	void shouldCreateProjectVisitingHistoryResult() {

		String projectName = "Test project name";
		long sumOfVisits = 20;

		ProjectVisitingHistoryResult theProjectVisitingHistoryResult = statisticsUtilsImpl
				.createProjectVisitingHistoryResult(projectName, sumOfVisits);

		assertEquals(projectName, theProjectVisitingHistoryResult.getProjectName());
		assertEquals(sumOfVisits, theProjectVisitingHistoryResult.getSumOfVisits());

	}

}
