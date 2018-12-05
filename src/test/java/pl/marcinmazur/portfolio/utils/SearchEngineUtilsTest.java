package pl.marcinmazur.portfolio.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SearchEngineUtilsTest {

	private SearchEngineUtilsImpl searchEngineUtilsImpl = new SearchEngineUtilsImpl();

	@Test
	void shouldCheckTableIfHasAnyParameters() {

		String[] searchParameters = {};
		assertFalse(searchEngineUtilsImpl.hasTableAnyParameters(searchParameters));

		searchParameters = new String[5];
		searchParameters[2] = "Parameter3";
		assertTrue(searchEngineUtilsImpl.hasTableAnyParameters(searchParameters));
	}

	@Test
	void shoudlPrepareHqlUsingContactFormMessageSearchParameters() {

		String[] searchParameters = { "Test subject", "Test sender", "test@email.com", "2018-11-29 00:00:00.0",
				"2018-11-30 23:59:59.9", "archive" };

		String[] fieldsName = { "subject", "sender", "email", "startDate", "endDate", "listType" };

		String searchType = "FROM Message WHERE ";

		String hql = searchEngineUtilsImpl.prepareHqlUsingContactFormMessageSearchParameters(searchParameters,
				searchType, fieldsName);

		String expectedHql = "FROM Message WHERE subject like '%Test subject%' AND sender like '%Test sender%' AND email like '%test@email.com%'"
				+ " AND date BETWEEN '2018-11-29 00:00:00.0' AND '2018-11-30 23:59:59.9' AND isActive = false ORDER BY id DESC";

		assertEquals(expectedHql, hql);

		String[] searchParameters2 = { "Test subject", "", "", "2018-11-29 00:00:00.0", "", "new" };

		hql = searchEngineUtilsImpl.prepareHqlUsingContactFormMessageSearchParameters(searchParameters2, searchType,
				fieldsName);

		expectedHql = "FROM Message WHERE subject like '%Test subject%' AND date >= '2018-11-29 00:00:00.0' AND isActive = true ORDER BY id DESC";

		assertEquals(expectedHql, hql);

	}
}
