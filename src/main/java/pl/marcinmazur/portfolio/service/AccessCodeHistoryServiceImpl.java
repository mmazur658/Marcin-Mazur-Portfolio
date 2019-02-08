package pl.marcinmazur.portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.marcinmazur.portfolio.dao.AccessCodeHistoryDao;
import pl.marcinmazur.portfolio.entity.AccessCodeHistory;
import pl.marcinmazur.portfolio.utils.AccessCodeUtils;

/**
 * Service class for managing history of access code
 * 
 * @author Marcin Mazur
 *
 */
@Service
public class AccessCodeHistoryServiceImpl implements AccessCodeHistoryService {

	private final String CODE_CREATION_MESSAGE = "New access code has been created";
	private final String CODE_USAGE_MESSAGE = "The access code has been used.";

	/**
	 * The AccessCodeHistoryDao interface
	 */
	private AccessCodeHistoryDao accessCodeHistoryDao;

	/**
	 * The NotificationService interface
	 */
	private NotificationService notificationService;

	/**
	 * The AccessCodeUtils interface
	 */
	private AccessCodeUtils accessCodeUtils;

	/**
	 * Constructs a AccessCodeHistoryServiceImpl with the
	 * AccessCodeHistoryDao,AccessCodeUtils and NotificationService.
	 * 
	 * @param accessCodeHistoryDao
	 *            The AccessCodeHistoryDao interface
	 * @param accessCodeUtils
	 *            The AccessCodeUtils interface
	 * @param notificationService
	 *            The NotificationService interface
	 */
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
		accessCodeHistoryDao
				.saveAccessCodeHistory(accessCodeUtils.createAccessCodeHistory(accessCodeValue, CODE_CREATION_MESSAGE));
	}

	@Override
	@Transactional
	public void addAccessCodeHistoryAfterCodeUse(String accessCodeValue) {

		boolean hasCodeBeenUsedBefore = hasCodeBeenUsedBefore(accessCodeValue);

		if (!hasCodeBeenUsedBefore)
			notificationService.createNotificationAfterFirstCodeUsing(accessCodeValue);

		accessCodeHistoryDao
				.saveAccessCodeHistory(accessCodeUtils.createAccessCodeHistory(accessCodeValue, CODE_USAGE_MESSAGE));
	}

	@Override
	@Transactional
	public List<AccessCodeHistory> getAccessCodeHistoryList(String accessCodeValue) {
		return accessCodeHistoryDao.getListOfAccessCodeHistory(accessCodeValue);
	}

	@Override
	@Transactional
	public boolean hasCodeBeenUsedBefore(String accessCodeValue) {

		long numberOfCodeUsage = accessCodeHistoryDao.getNumberOfCodeUsage(accessCodeValue, CODE_USAGE_MESSAGE);

		return accessCodeUtils.hasCodeBeenUsedBefore(numberOfCodeUsage);

	}
}
