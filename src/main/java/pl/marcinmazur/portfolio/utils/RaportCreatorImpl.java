package pl.marcinmazur.portfolio.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

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
public class RaportCreatorImpl {
	
	private final String BASIC_DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * The RaportAndReminderService interface
	 */
	private RaportAndReminderService raportAndReminderService;

	/**
	 * Constructs a RaportCreatorImpl with the RaportAndReminderService.
	 * 
	 * @param raportAndReminderService
	 *            The RaportAndReminderService interface
	 */
	@Autowired
	public RaportCreatorImpl(RaportAndReminderService raportAndReminderService) {
		this.raportAndReminderService = raportAndReminderService;
	}

	/**
	 * Generates daily report containing the information about messages
	 */
	@Scheduled(cron = "0 0 0 * * *")
	public void createDailyMessageRaport() {

		SimpleDateFormat sdf = new SimpleDateFormat(BASIC_DATE_FORMAT);
		String stringCurrentDate = sdf.format(new Date());

		raportAndReminderService.createDailyMessageRaport(stringCurrentDate);

	}
}
