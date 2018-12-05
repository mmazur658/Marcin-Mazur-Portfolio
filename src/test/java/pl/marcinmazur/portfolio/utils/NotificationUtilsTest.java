package pl.marcinmazur.portfolio.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pl.marcinmazur.portfolio.entity.AccessCode;
import pl.marcinmazur.portfolio.entity.Notification;

class NotificationUtilsTest {

	private NotificationUtilsImpl notificationUtilsImpl = new NotificationUtilsImpl();

	@Test
	void shouldCreateNotificationAfterFirstCodeUsage() {

		AccessCode theAccessCode = new AccessCode();
		theAccessCode.setAccessCodeValue("222222");
		theAccessCode.setAccessCodeOwner("Marcin");

		Notification notification = notificationUtilsImpl.createNotificationAfterFirstCodeUsage(theAccessCode);
		String expectedNotificationText = "Gratuluję! Kod dostępu: 222222 wygenerowany dla: Marcin został użyty po raz pierwszy!!";

		assertNotNull(notification.getDateOfAdded());
		assertTrue(notification.getIsActive());
		assertEquals("success", notification.getNotificationType());
		assertEquals(expectedNotificationText, notification.getNotificationText());
	}

	@Test
	void shouldCreateDailyMessageRaport() {

		String stringCurrentDate = "2018-12-04";
		long numberOfMessages = 22;
		long numberOfUnreadMessages = 5;

		Notification notification = notificationUtilsImpl.createDailyMessageRaport(numberOfMessages,
				numberOfUnreadMessages, stringCurrentDate);

		String expectedNotificationText = "Raport dobowy z dnia: 2018-12-04. Otrzymałeś: 22 wiadomości. Nie wszystkie zostały przeczytane.";

		assertNotNull(notification.getDateOfAdded());
		assertTrue(notification.getIsActive());
		assertEquals("raport", notification.getNotificationType());
		assertEquals(expectedNotificationText, notification.getNotificationText());

		numberOfMessages = 1;
		numberOfUnreadMessages = 0;
		expectedNotificationText = "Raport dobowy z dnia: 2018-12-04. Otrzymałeś: 1 wiadomość. Wszystkie zostały przeczytane.";
		notification = notificationUtilsImpl.createDailyMessageRaport(numberOfMessages, numberOfUnreadMessages,
				stringCurrentDate);

		assertEquals(expectedNotificationText, notification.getNotificationText());
	}

	@Test
	void shouldCreateUnreadMessagesNotification() {

		long numberOfUnreadMessage = 5;
		Notification notification = notificationUtilsImpl.createUnreadMessagesNotification(numberOfUnreadMessage);
		String expectedNotificationText = "Uwaga !!! 5 wiadomości są nieprzeczytane dłużej niż godzinę.";

		assertNotNull(notification.getDateOfAdded());
		assertTrue(notification.getIsActive());
		assertEquals("information", notification.getNotificationType());
		assertEquals(expectedNotificationText, notification.getNotificationText());

		numberOfUnreadMessage = 1;
		notification = notificationUtilsImpl.createUnreadMessagesNotification(numberOfUnreadMessage);
		expectedNotificationText = "Uwaga !!! 1 wiadomość jest nieprzeczytana dłużej niż godzinę.";
		assertEquals(expectedNotificationText, notification.getNotificationText());
	}

	@Test
	void shouldCreateNotification() {

		String notificationText = "success";
		String notificationType = "Notification Text";

		Notification notification = notificationUtilsImpl.createNotification(notificationType, notificationText);

		assertEquals(notificationType, notification.getNotificationType());
		assertEquals(notificationText, notification.getNotificationText());
		assertNotNull(notification.getDateOfAdded());
		assertTrue(notification.getIsActive());

	}

}
