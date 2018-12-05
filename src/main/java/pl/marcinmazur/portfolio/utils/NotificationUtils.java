package pl.marcinmazur.portfolio.utils;

import pl.marcinmazur.portfolio.entity.AccessCode;
import pl.marcinmazur.portfolio.entity.Notification;

public interface NotificationUtils {

	Notification createNotificationAfterFirstCodeUsage(AccessCode theAccessCode) ;

	Notification createDailyMessageRaport(long numberOfMessages, long numberOfUnreadMessages, String stringCurrentDate);

	Notification createUnreadMessagesNotification(long numberOfUnreadMessage);
}
