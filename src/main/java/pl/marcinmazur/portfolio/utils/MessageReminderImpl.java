package pl.marcinmazur.portfolio.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pl.marcinmazur.portfolio.service.RaportAndReminderService;

/**
 * Utility class used for scheduling and generating reports and reminders.
 * 
 * @author Marcin Mazur
 *
 */
@Component
@EnableScheduling
public class MessageReminderImpl {

	private final long ONE_HOUR = 60 * 60 * 1000;
	private final String FULL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private final String VERY_SHORT_START_TIME = ".0";

	/**
	 * The RaportAndReminderService interface
	 */
	private RaportAndReminderService raportAndReminderService;

	/**
	 * Constructs a MessageReminderImpl with the RaportAndReminderService.
	 * 
	 * @param raportAndReminderService
	 *            The RaportAndReminderService interface
	 */
	@Autowired
	public MessageReminderImpl(RaportAndReminderService raportAndReminderService) {
		this.raportAndReminderService = raportAndReminderService;
	}

	/**
	 * Generates Notification if the one or more messages are unread longer than
	 * scheduled time.
	 */
	@Scheduled(fixedDelay = ONE_HOUR)
	public void unreadMessagaReminder() {

		Calendar theCalendar = Calendar.getInstance();
		theCalendar.add(Calendar.HOUR_OF_DAY, -1);

		SimpleDateFormat sdf = new SimpleDateFormat(FULL_DATE_FORMAT);
		String stringCurrentDate = sdf.format(theCalendar.getTime()) + VERY_SHORT_START_TIME;

		raportAndReminderService.lookForUnreadMessages(stringCurrentDate);

	}

}
