package pl.marcinmazur.portfolio.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import pl.marcinmazur.portfolio.entity.AccessCode;
import pl.marcinmazur.portfolio.entity.AccessCodeHistory;

class AccessCodeUtilsTest {

	private AccessCodeUtilsImpl accessCodeUtilsImpl = new AccessCodeUtilsImpl();

	@Test
	void shouldCreateAccessCodeHistory() {

		String accessCodeValue = "222222";
		String accessCodeHistoryActionName = "Code has been used.";

		AccessCodeHistory accessCodeHistory = accessCodeUtilsImpl.createAccessCodeHistory(accessCodeValue,
				accessCodeHistoryActionName);

		assertEquals(accessCodeValue, accessCodeHistory.getAccessCodeValue());
		assertEquals(accessCodeHistoryActionName, accessCodeHistory.getAction());
		assertNotNull(accessCodeHistory.getActionDate());

	}

	@Test
	void shouldCheckIfCodeHasBeenUsedBefore() {

		long numberOfCodeUsage = 15;
		assertTrue(accessCodeUtilsImpl.hasCodeBeenUsedBefore(numberOfCodeUsage));

		numberOfCodeUsage = 0;
		assertFalse(accessCodeUtilsImpl.hasCodeBeenUsedBefore(numberOfCodeUsage));

	}

	@Test
	void shouldCheckIfAccessCodeIsUniqe() {

		List<AccessCode> accessCodeList = new ArrayList<>();
		assertTrue(accessCodeUtilsImpl.isAccessCodeUniqe(accessCodeList));

		accessCodeList.add(new AccessCode());
		assertFalse(accessCodeUtilsImpl.isAccessCodeUniqe(accessCodeList));

	}

	@Test
	void shouldCheckIfAccessCodeIsCorrect() {
		List<AccessCode> accessCodeList = new ArrayList<>();
		assertFalse(accessCodeUtilsImpl.isAccessCodeCorrect(accessCodeList));

		accessCodeList.add(new AccessCode());
		assertTrue(accessCodeUtilsImpl.isAccessCodeCorrect(accessCodeList));
	}

	@Test
	void shouldUpdateAccessCode() {

		AccessCode accessCode = new AccessCode();
		accessCode.setAccessCodeDescription("Test Description");
		accessCode.setAccessCodeOwner("Test Owner");
		accessCode.setAccessCodeValue("222222");
		accessCode.setIsActive(true);

		String accessCodeDate = "2018-11-30 12:22";
		String accessCodeDescription = "New Description";
		String accessCodeOwner = "New Owner";
		boolean accessCodeIsActive = false;

		accessCodeUtilsImpl.updateAccessCode(accessCode, accessCodeDate, accessCodeIsActive, accessCodeDescription,
				accessCodeOwner);

		assertNotNull(accessCode.getDateOfAdded());
		assertEquals(accessCodeOwner, accessCode.getAccessCodeOwner());
		assertEquals(accessCodeDescription, accessCode.getAccessCodeDescription());
		assertFalse(accessCode.getIsActive());
	}

	@Test
	void shouldCreateNewAccessCode() {

		String newAccessCodeValue = "222222";
		String newAccessCodeOwner = "New Owner";
		String newAccessCodeDescription = "New Description";

		AccessCode accessCode = accessCodeUtilsImpl.createNewAccessCode(newAccessCodeValue, newAccessCodeOwner,
				newAccessCodeDescription);

		assertEquals(newAccessCodeValue, accessCode.getAccessCodeValue());
		assertEquals(newAccessCodeOwner, accessCode.getAccessCodeOwner());
		assertEquals(newAccessCodeDescription, accessCode.getAccessCodeDescription());
		assertTrue(accessCode.getIsActive());
		assertNotNull(accessCode.getDateOfAdded());
	}

}
