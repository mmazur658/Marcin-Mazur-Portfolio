package pl.marcinmazur.portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.marcinmazur.portfolio.dao.AccessCodeHistoryDao;
import pl.marcinmazur.portfolio.entity.AccessCodeHistory;
import pl.marcinmazur.portfolio.utils.AccessCodeUtils;

@Service
public class AccessCodeHistoryServiceImpl implements AccessCodeHistoryService {

	private AccessCodeHistoryDao accessCodeHistoryDao;
	private NotificationService notificationService;
	private AccessCodeUtils accessCodeUtils;

	@Autowired
	public AccessCodeHistoryServiceImpl(AccessCodeHistoryDao accessCodeHistoryDao, AccessCodeUtils accessCodeUtils,
			NotificationService notificationService) {
		this.accessCodeHistoryDao = accessCodeHistoryDao;
		this.notificationService = notificationService;
		this.accessCodeUtils = accessCodeUtils;
	}

	@Override
	@Transactional
	public void createNewAccessCodeHistory(String accessCodeValue) {
		accessCodeHistoryDao.saveAccessCodeHistory(
				accessCodeUtils.createAccessCodeHistory(accessCodeValue, "New access code has been created"));
	}

	@Override
	@Transactional
	public void addAccessCodeHistoryAfterCodeUse(String accessCodeValue) {

		boolean hasCodeBeenUsedBefore = hasCodeBeenUsedBefore(accessCodeValue);

		if (!hasCodeBeenUsedBefore)
			notificationService.createNotificationAfterFirstCodeUsage(accessCodeValue);

		accessCodeHistoryDao.saveAccessCodeHistory(
				accessCodeUtils.createAccessCodeHistory(accessCodeValue, "The access code has been used."));
	}

	@Override
	@Transactional
	public List<AccessCodeHistory> getAccessCodeHistoryList(String accessCodeValue) {
		return accessCodeHistoryDao.getListOfAccessCodeHistory(accessCodeValue);
	}

	@Override
	@Transactional
	public boolean hasCodeBeenUsedBefore(String accessCodeValue) {

		long numberOfCodeUsage = accessCodeHistoryDao.getNumberOfCodeUsage(accessCodeValue,
				"The access code has been used.");

		return accessCodeUtils.hasCodeBeenUsedBefore(numberOfCodeUsage);

	}
}
