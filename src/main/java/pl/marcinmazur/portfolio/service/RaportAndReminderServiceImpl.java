package pl.marcinmazur.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.marcinmazur.portfolio.dao.ContactFormMessageDao;
import pl.marcinmazur.portfolio.dao.NotificationDao;
import pl.marcinmazur.portfolio.entity.Notification;
import pl.marcinmazur.portfolio.utils.NotificationUtils;

@Service
public class RaportAndReminderServiceImpl implements RaportAndReminderService {

	private ContactFormMessageDao contactFormMessageDao;
	private NotificationDao notificationDao;
	private NotificationUtils notificationUtils;

	// raport - information - success - error

	@Autowired
	public RaportAndReminderServiceImpl(ContactFormMessageDao contactFormMessageDao, NotificationDao notificationDao, NotificationUtils notificationUtils) {
		this.contactFormMessageDao = contactFormMessageDao;
		this.notificationDao = notificationDao;
		this.notificationUtils=notificationUtils;
	}

	@Override
	@Transactional
	public long getNumberOfMessagesForGivenDate(String stringCurrentDate) {

		String startDate = stringCurrentDate + " 00:00:00.0";
		String endDate = stringCurrentDate + " 23:59:59.9";

		return contactFormMessageDao.getNumberOfContactFormMessagesForGivenDate(startDate, endDate);
	}

	@Override
	@Transactional
	public long getNumberOfUnreadMessagesForGivenDate(String stringCurrentDate) {

		String startDate = stringCurrentDate + " 00:00:00.0";
		String endDate = stringCurrentDate + " 23:59:59.9";

		return contactFormMessageDao.getNumberOfUnreadContactFormMessagesForGivenDate(startDate, endDate);
	}

	@Override
	@Transactional
	public void createDailyMessageRaport(String stringCurrentDate) {

		long numberOfMessages = getNumberOfMessagesForGivenDate(stringCurrentDate);
		long numberOfUnreadMessages = getNumberOfUnreadMessagesForGivenDate(stringCurrentDate);
		Notification theNotification = notificationUtils.createDailyMessageRaport(numberOfMessages, numberOfUnreadMessages, stringCurrentDate);

		notificationDao.saveNotification(theNotification);
	}

	@Override
	@Transactional
	public void lookForUnreadMessages(String stringCurrentDate) {

		long numberOfUnreadMessage = contactFormMessageDao.getNumberOfUnreadContactFormMessagesForGivenDate(stringCurrentDate);

		if (numberOfUnreadMessage > 0) {
			Notification theNotification = notificationUtils.createUnreadMessagesNotification(numberOfUnreadMessage);										
			notificationDao.saveNotification(theNotification);
		}

	}

}
