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
	 * Generates Notification if the one or more messages are unread longer than scheduled time.
	 */
	@Scheduled(fixedDelay = 60 * 60 * 1000)
	public void unreadMessagaReminder() {

		Calendar theCalendar = Calendar.getInstance();
		theCalendar.add(Calendar.HOUR_OF_DAY, -1);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String stringCurrentDate = sdf.format(theCalendar.getTime()) + ".0";

		raportAndReminderService.lookForUnreadMessages(stringCurrentDate);

	}

}
