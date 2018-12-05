package pl.marcinmazur.portfolio.service;

public interface RaportAndReminderService {

	long getNumberOfMessagesForGivenDate(String stringCurrentDate);

	long getNumberOfUnreadMessagesForGivenDate(String stringCurrentDate);

	void createDailyMessageRaport(String stringCurrentDate);

	void lookForUnreadMessages(String stringCurrentDate);

}
