package pl.marcinmazur.portfolio.service;

import java.util.List;

import pl.marcinmazur.portfolio.entity.AccessCodeHistory;

public interface AccessCodeHistoryService {

	void createNewAccessCodeHistory(String accessCodeValue);

	void addAccessCodeHistoryAfterCodeUse(String accessCodeValue);

	List<AccessCodeHistory> getAccessCodeHistoryList(String accessCodeValue);

	boolean hasCodeBeenUsedBefore(String accessCodeValue);

}
