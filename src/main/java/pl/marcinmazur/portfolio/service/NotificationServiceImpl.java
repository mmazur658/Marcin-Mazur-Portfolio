package pl.marcinmazur.portfolio.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.marcinmazur.portfolio.dao.AccessCodeDao;
import pl.marcinmazur.portfolio.dao.NotificationDao;
import pl.marcinmazur.portfolio.entity.AccessCode;
import pl.marcinmazur.portfolio.entity.Notification;
import pl.marcinmazur.portfolio.utils.NotificationUtils;
import pl.marcinmazur.portfolio.utils.ServiceUtils;

@Service
public class NotificationServiceImpl implements NotificationService {

	private NotificationDao notificationDao;
	private AccessCodeDao accessCodeDao;
	private ServiceUtils serviceUtils;
	private NotificationUtils notificationUtils;

	public NotificationServiceImpl(NotificationDao notificationDao, AccessCodeDao accessCodeDao,
			ServiceUtils serviceUtils, NotificationUtils notificationUtils) {
		this.notificationDao = notificationDao;
		this.accessCodeDao = accessCodeDao;
		this.serviceUtils = serviceUtils;
		this.notificationUtils= notificationUtils;
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
	public void createNotificationAfterFirstCodeUsage(String accessCodeValue) {

		AccessCode theAccessCode = accessCodeDao.getSingleAccessCodeByValue(accessCodeValue);
		Notification theNotification = notificationUtils.createNotificationAfterFirstCodeUsage(theAccessCode);

		notificationDao.saveNotification(theNotification);

	}

}
