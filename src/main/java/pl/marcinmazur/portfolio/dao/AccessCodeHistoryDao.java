package pl.marcinmazur.portfolio.dao;

import java.util.List;

import pl.marcinmazur.portfolio.entity.AccessCodeHistory;

public interface AccessCodeHistoryDao {

	void saveAccessCodeHistory(AccessCodeHistory accessCodeHistory);

	List<AccessCodeHistory> getListOfAccessCodeHistory(String accessCodeValue);

	long getNumberOfCodeUsage(String accessCodeValue, String action);

	long getSumOfGivenAccessCodeValueAndGivenDate(String accessCodeValue, String startDate, String endDate);

}
