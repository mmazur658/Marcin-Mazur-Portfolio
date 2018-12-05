package pl.marcinmazur.portfolio.utils;

import java.util.Date;

import org.springframework.stereotype.Component;

import pl.marcinmazur.portfolio.entity.AccessCode;
import pl.marcinmazur.portfolio.entity.Notification;

@Component
public class NotificationUtilsImpl implements NotificationUtils {

	@Override
	public Notification createNotificationAfterFirstCodeUsage(AccessCode theAccessCode) {

		StringBuilder sb = new StringBuilder();
		sb.append("Gratuluję! Kod dostępu: ");
		sb.append(theAccessCode.getAccessCodeValue());
		sb.append(" wygenerowany dla: ");
		sb.append(theAccessCode.getAccessCodeOwner());
		sb.append(" został użyty po raz pierwszy!!");

		return createNotification("success", sb.toString());
	}

	@Override
	public Notification createDailyMessageRaport(long numberOfMessages, long numberOfUnreadMessages,
			String stringCurrentDate) {

		StringBuilder sb = new StringBuilder();
		sb.append("Raport dobowy z dnia: ");
		sb.append(stringCurrentDate);
		sb.append(". Otrzymałeś: ");
		sb.append(numberOfMessages);
		sb.append((numberOfMessages == 1) ? " wiadomość. " : " wiadomości. ");
		sb.append((numberOfUnreadMessages == 0) ? "Wszystkie zostały przeczytane."
				: "Nie wszystkie zostały przeczytane.");

		return createNotification("raport", sb.toString());
	}

	@Override
	public Notification createUnreadMessagesNotification(long numberOfUnreadMessage) {

		StringBuilder sb = new StringBuilder();
		sb.append("Uwaga !!! ");
		sb.append(numberOfUnreadMessage);
		sb.append((numberOfUnreadMessage == 1) ? " wiadomość jest nieprzeczytana dłużej niż godzinę."
				: " wiadomości są nieprzeczytane dłużej niż godzinę.");

		return createNotification("information", sb.toString());
	}

	public Notification createNotification(String notificationType, String notificationText) {

		Notification theNotification = new Notification();
		theNotification.setDateOfAdded(new Date());
		theNotification.setIsActive(true);
		theNotification.setNotificationType(notificationType);
		theNotification.setNotificationText(notificationText);

		return theNotification;

	}

}
