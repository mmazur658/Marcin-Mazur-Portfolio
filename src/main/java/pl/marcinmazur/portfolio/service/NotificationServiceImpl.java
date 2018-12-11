package pl.marcinmazur.portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.marcinmazur.portfolio.dao.AccessCodeDao;
import pl.marcinmazur.portfolio.dao.NotificationDao;
import pl.marcinmazur.portfolio.entity.AccessCode;
import pl.marcinmazur.portfolio.entity.Notification;
import pl.marcinmazur.portfolio.utils.NotificationUtils;

/**
 * Service class for managing notifications.
 * 
 * @author Marcin Mazur
 *
 */
@Service
public class NotificationServiceImpl implements NotificationService {

	/**
	 * The NotificationDao interface
	 */
	private NotificationDao notificationDao;

	/**
	 * The AccessCodeDao interface
	 */
	private AccessCodeDao accessCodeDao;

	/**
	 * The NotificationUtils interface
	 */
	private NotificationUtils notificationUtils;

	/**
	 * Constructs NotificationServiceImpl a with the NotificationDao, AccessCodeDao
	 * and NotificationUtils.
	 * 
	 * @param notificationDao
	 *            The NotificationDao interface
	 * @param accessCodeDao
	 *            The AccessCodeDao interface
	 * @param notificationUtils
	 *            The NotificationUtils interface
	 */
	@Autowired
	public NotificationServiceImpl(NotificationDao notificationDao, AccessCodeDao accessCodeDao,
			NotificationUtils notificationUtils) {
		this.notificationDao = notificationDao;
		this.accessCodeDao = accessCodeDao;
		this.notificationUtils = notificationUtils;
	}

	@Override
	public List<Notification> getActiveNotifications() {
		return notificationDao.getListOfActiveNotifications();
	}

	@Override
	public long getNumberOfActiveNotification() {
		return notificationDao.getSumOfActiveNotifications();
	}

	@Override
	@Transactional
	public void deleteNotification(int notificationId) {

		Notification notification = notificationDao.getNotificationById(notificationId);
		notification.setIsActive(false);

	}

	@Override
	@Transactional
	public void createNotificationAfterFirstCodeUsing(String accessCodeValue) {

		AccessCode theAccessCode = accessCodeDao.getAccessCodeByValue(accessCodeValue);
		
		if (theAccessCode != null) {
			Notification theNotification = notificationUtils.createNotificationAfterFirstCodeUsage(theAccessCode);
			notificationDao.saveNotification(theNotification);
		}


	}

}
