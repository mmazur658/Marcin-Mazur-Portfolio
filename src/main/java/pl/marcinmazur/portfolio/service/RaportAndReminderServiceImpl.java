package pl.marcinmazur.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.marcinmazur.portfolio.dao.ContactFormMessageDao;
import pl.marcinmazur.portfolio.dao.NotificationDao;
import pl.marcinmazur.portfolio.entity.Notification;
import pl.marcinmazur.portfolio.utils.NotificationUtils;

/**
 * Service class for managing reports and reminders.
 * 
 * @author Marcin Mazur
 *
 */
@Service
public class RaportAndReminderServiceImpl implements RaportAndReminderService {

	/**
	 * The ContactFormMessageDao interface
	 */
	private ContactFormMessageDao contactFormMessageDao;

	/**
	 * The NotificationDao interface
	 */
	private NotificationDao notificationDao;

	/**
	 * The NotificationUtils interface
	 */
	private NotificationUtils notificationUtils;

	/**
	 * Constructs RaportAndReminderServiceImpl a with the ContactFormMessageDao,
	 * NotificationDao and NotificationUtils.
	 * 
	 * @param contactFormMessageDao
	 *            The ContactFormMessageDao interface
	 * @param notificationDao
	 *            The NotificationDao interface
	 * @param notificationUtils
	 *            The NotificationUtils interface
	 */
	@Autowired
	public RaportAndReminderServiceImpl(ContactFormMessageDao contactFormMessageDao, NotificationDao notificationDao,
			NotificationUtils notificationUtils) {
		this.contactFormMessageDao = contactFormMessageDao;
		this.notificationDao = notificationDao;
		this.notificationUtils = notificationUtils;
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
		Notification theNotification = notificationUtils.createDailyMessageRaport(numberOfMessages,
				numberOfUnreadMessages, stringCurrentDate);

		notificationDao.saveNotification(theNotification);
	}

	@Override
	@Transactional
	public void lookForUnreadMessages(String stringCurrentDate) {

		long numberOfUnreadMessage = contactFormMessageDao
				.getNumberOfUnreadContactFormMessagesForGivenDate(stringCurrentDate);

		if (numberOfUnreadMessage > 0) {
			Notification theNotification = notificationUtils.createUnreadMessagesNotification(numberOfUnreadMessage);
			notificationDao.saveNotification(theNotification);
		}

	}


}
