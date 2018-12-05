package pl.marcinmazur.portfolio.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pl.marcinmazur.portfolio.service.RaportAndReminderService;

@Component
@EnableScheduling
public class MessageReminderImpl {

	private RaportAndReminderService raportAndReminderService;

	@Autowired
	public MessageReminderImpl(RaportAndReminderService raportAndReminderService) {
		this.raportAndReminderService = raportAndReminderService;
	}

	@Scheduled(fixedDelay = 60 * 60 * 1000)
	public void unreadMessagaReminder() {

		Calendar theCalendar = Calendar.getInstance();
		theCalendar.add(Calendar.HOUR_OF_DAY, -1);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String stringCurrentDate = sdf.format(theCalendar.getTime()) + ".0";

		raportAndReminderService.lookForUnreadMessages(stringCurrentDate);

	}

}
