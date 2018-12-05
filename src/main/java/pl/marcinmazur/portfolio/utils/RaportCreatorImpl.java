package pl.marcinmazur.portfolio.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pl.marcinmazur.portfolio.service.RaportAndReminderService;

@Component
@EnableScheduling
public class RaportCreatorImpl {

	private RaportAndReminderService raportAndReminderService;

	@Autowired
	public RaportCreatorImpl(RaportAndReminderService raportAndReminderService) {
		this.raportAndReminderService = raportAndReminderService;
	}

	@Scheduled(cron = "0 0 0 * * *")
	public void createDailyMessageRaport() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String stringCurrentDate = sdf.format(new Date());

		raportAndReminderService.createDailyMessageRaport(stringCurrentDate);

	}
}
