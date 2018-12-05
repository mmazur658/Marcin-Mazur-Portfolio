package pl.marcinmazur.portfolio.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.marcinmazur.portfolio.dao.AccessCodeDao;
import pl.marcinmazur.portfolio.dao.AccessCodeHistoryDao;
import pl.marcinmazur.portfolio.dao.ContactFormMessageDao;
import pl.marcinmazur.portfolio.dao.ProjectVisitingHistoryDao;
import pl.marcinmazur.portfolio.utils.CodeUsageHistoryResult;
import pl.marcinmazur.portfolio.utils.ProjectVisitingHistoryResult;
import pl.marcinmazur.portfolio.utils.StatisticsUtils;

@Service
public class StatisticServiceImpl implements StatisticService {

	private ProjectVisitingHistoryDao projectVisitingHistoryDao;
	private ContactFormMessageDao contactFormMessageDao;
	private AccessCodeHistoryDao accessCodeHistoryDao;
	private AccessCodeDao accessCodeDao;
	private StatisticsUtils statisticsUtils;

	@Autowired
	public StatisticServiceImpl(ProjectVisitingHistoryDao projectVisitingHistoryDao, AccessCodeDao accessCodeDao,
			ContactFormMessageDao contactFormMessageDao, AccessCodeHistoryDao accessCodeHistoryDao,
			StatisticsUtils statisticsUtils) {
		this.projectVisitingHistoryDao = projectVisitingHistoryDao;
		this.contactFormMessageDao = contactFormMessageDao;
		this.accessCodeHistoryDao = accessCodeHistoryDao;
		this.accessCodeDao = accessCodeDao;
		this.statisticsUtils = statisticsUtils;
	}

	@Override
	@Transactional
	public List<ProjectVisitingHistoryResult> getProjectVisitingHistoryListForGivenDate(String startDate,
			String endDate) {

		startDate = startDate + " 00:00:00.0";
		endDate = endDate + " 23:59:59.9";

		List<String> projectsNameList = projectVisitingHistoryDao.getListOfProjectNames();

		List<ProjectVisitingHistoryResult> theProjectVisitingHistoryResultList = new ArrayList<>();

		for (String projectName : projectsNameList) {

			theProjectVisitingHistoryResultList
					.add(statisticsUtils.createProjectVisitingHistoryResult(projectName, projectVisitingHistoryDao
							.getSumOfVisitsForGivenProjectNameAndGivenDate(projectName, startDate, endDate)));

		}

		return theProjectVisitingHistoryResultList;
	}

	@Override
	@Transactional
	public long getSumOfContactFormMessages(String startDate, String endDate) {

		startDate = startDate + " 00:00:00.0";
		endDate = endDate + " 23:59:59.9";

		long SumOfContactFormMessages = contactFormMessageDao.getNumberOfContactFormMessagesForGivenDate(startDate,
				endDate);

		return SumOfContactFormMessages;
	}

	@Override
	@Transactional
	public List<CodeUsageHistoryResult> getCodeUsageHistoryResultListForGivenDate(String startDate, String endDate) {

		startDate = startDate + " 00:00:00.0";
		endDate = endDate + " 23:59:59.9";

		// zebranie listy kodów
		List<String> accessCodeValueList = accessCodeDao.getListOfAccessCodeValues();
		List<CodeUsageHistoryResult> codeUsageHistoryResultList = new ArrayList<>();

		// zebranie danych dla kodów
		for (String accessCodeValue : accessCodeValueList) {

			codeUsageHistoryResultList.add(statisticsUtils.createCodeUsageHistoryResult(accessCodeValue,
					accessCodeHistoryDao.getSumOfGivenAccessCodeValueAndGivenDate(accessCodeValue, startDate, endDate),
					accessCodeDao.getAccessCodeOwenerByGivenAccessCodeValue(accessCodeValue)));

		}

		codeUsageHistoryResultList = statisticsUtils.prepareCodeUsageHistoryResultListToDisplay(codeUsageHistoryResultList);

		return codeUsageHistoryResultList;
	}

	@Override
	public String[] getGeneralStatisticsForSelectedProject(String projectName) {

		String[] generalSelectedProjectStatistics = new String[5];

		String today = statisticsUtils.getToday();
		generalSelectedProjectStatistics[0] = String.valueOf(
				projectVisitingHistoryDao.getSumOfVisitsForGivenProjectNameAndGivenDate(projectName, today, today));

		String yesterday = statisticsUtils.getDateMinusGivenValue(1);
		generalSelectedProjectStatistics[1] = String.valueOf(projectVisitingHistoryDao
				.getSumOfVisitsForGivenProjectNameAndGivenDate(projectName, yesterday, yesterday));

		String startDate = statisticsUtils.getDateMinusGivenValue(6);
		generalSelectedProjectStatistics[2] = String.valueOf(
				projectVisitingHistoryDao.getSumOfVisitsForGivenProjectNameAndGivenDate(projectName, startDate, today));

		startDate = statisticsUtils.getDateMinusGivenValue(29);
		generalSelectedProjectStatistics[3] = String.valueOf(
				projectVisitingHistoryDao.getSumOfVisitsForGivenProjectNameAndGivenDate(projectName, startDate, today));

		startDate = statisticsUtils.getDateMinusGivenValue(1000);
		generalSelectedProjectStatistics[4] = String.valueOf(
				projectVisitingHistoryDao.getSumOfVisitsForGivenProjectNameAndGivenDate(projectName, startDate, today));

		return generalSelectedProjectStatistics;
	}

	@Override
	public List<Object[]> getMonthlyDataForSelectedProject(String projectName, String startDate, String endDate,
			int monthLength) {

		startDate = startDate + " 00:00:00.0";
		endDate = endDate + " 23:59:59.9";

		List<Object[]> resultList = projectVisitingHistoryDao.getMonthlyDataForSelectedProject(projectName, startDate,
				endDate);

		List<Object[]> mainList = statisticsUtils.prepareMonthlyStatsListToDisplay(resultList, monthLength);

		return mainList;
	}

	@Override
	public String[] getGeneralStatisticsForMessages() {

		String[] generalMessagesStatistics = new String[5];

		String today = statisticsUtils.getToday();
		generalMessagesStatistics[0] = String
				.valueOf(contactFormMessageDao.getNumberOfContactFormMessagesForGivenDate(today, today));

		String yesterday = statisticsUtils.getDateMinusGivenValue(1);
		generalMessagesStatistics[1] = String
				.valueOf(contactFormMessageDao.getNumberOfContactFormMessagesForGivenDate(yesterday, yesterday));

		String startDate = statisticsUtils.getDateMinusGivenValue(6);
		generalMessagesStatistics[2] = String
				.valueOf(contactFormMessageDao.getNumberOfContactFormMessagesForGivenDate(startDate, today));

		startDate = statisticsUtils.getDateMinusGivenValue(29);
		generalMessagesStatistics[3] = String
				.valueOf(contactFormMessageDao.getNumberOfContactFormMessagesForGivenDate(startDate, today));

		startDate = statisticsUtils.getDateMinusGivenValue(1000);
		generalMessagesStatistics[4] = String
				.valueOf(contactFormMessageDao.getNumberOfContactFormMessagesForGivenDate(startDate, today));

		return generalMessagesStatistics;

	}

	@Override
	public List<Object[]> getMonthlyDataForMessages(String startDate, String endDate, int monthLength) {

		startDate = startDate + " 00:00:00.0";
		endDate = endDate + " 23:59:59.9";

		List<Object[]> resultList = contactFormMessageDao.getMonthlyMessagesData(startDate, endDate);
		List<Object[]> mainList = statisticsUtils.prepareMonthlyStatsListToDisplay(resultList, monthLength);

		return mainList;
	}

}