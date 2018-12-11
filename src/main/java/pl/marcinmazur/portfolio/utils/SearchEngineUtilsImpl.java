package pl.marcinmazur.portfolio.utils;

import org.springframework.stereotype.Component;

/**
 * Utility class used to create HQL for given search parameters
 * 
 * @author Marcin Mazur
 *
 */
@Component
public class SearchEngineUtilsImpl implements SearchEngineUtils {

	@Override
	public boolean hasTableAnyParameters(String[] searchParameters) {
		boolean hasAnyParameters = false;
		for (int i = 0; i < searchParameters.length; i++) {
			if (searchParameters[i] != "")
				hasAnyParameters = true;
		}
		return hasAnyParameters;
	}

	@Override
	public String prepareHqlUsingContactFormMessageSearchParameters(String[] searchParameters, String searchType,
			String[] fieldsName) {

		
		boolean isContent = false;
		StringBuilder sb = new StringBuilder();

		// The HQL starts with the text form the searchType. The searchType should
		// contain "FROM" word, the name of the entity and the space char as a last
		// character of the string
		sb.append(searchType);
		if (!searchParameters[0].equals("")) {
			sb.append(fieldsName[0] + " like '%" + searchParameters[0] + "%'");
			isContent = true;
		}
		
		// build HQL with given search parameters values and names of the fields		
		for (int i = 1; i <= fieldsName.length - 4; i++) {
			if (!searchParameters[i].equals("")) {
				if (isContent) {
					sb.append(" AND ");
					sb.append(fieldsName[i] + " like '%" + searchParameters[i] + "%'");
				} else {
					sb.append(fieldsName[i] + " like '%" + searchParameters[i] + "%'");
					isContent = true;
				}
			}
		}

		// last 3 fields contain the startDate, endDate and listType
		String startDate = searchParameters[searchParameters.length - 3];
		String endDate = searchParameters[searchParameters.length - 2];
		String listType = searchParameters[searchParameters.length - 1];

		// if the startDate is not equal to "" and the endDate is equal to "", then HQL
		// returns all elements created after startDate
		if (!startDate.equals("") && endDate.equals("")) {
			if (isContent) {
				sb.append(" AND ");
				sb.append("date >= '" + startDate + "'");
			} else {
				sb.append("date >= '" + startDate + "'");
				isContent = true;
			}
			// if the startDate is equal to "" and the endDate is not equal to "", then HQL
			// returns all elements created before endDate
		} else if (startDate.equals("") && !endDate.equals("")) {
			if (isContent) {
				sb.append(" AND ");
				sb.append("date <= '" + endDate + "'");
			} else {
				sb.append("date <= '" + endDate + "'");
				isContent = true;
			}
			// if the startDate is not equal to "" and the endDate is not equal to "", then
			// HQL
			// returns all elements created after startDate and before endDate
		} else if (!startDate.equals("") && !endDate.equals("")) {
			if (isContent) {
				sb.append(" AND ");
				sb.append("date BETWEEN '" + startDate + "' AND '" + endDate + "'");
			} else {
				sb.append("date BETWEEN '" + startDate + "' AND '" + endDate + "'");
				isContent = true;
			}
		}

		// if the listType is equal to "new" then HQL returns all active elements
		if (listType.equals("new")) {
			if (isContent) {
				sb.append(" AND ");
				sb.append("isActive = true ORDER BY id DESC");
			} else {
				sb.append("isActive = true ORDER BY id DESC");
				isContent = true;
			}
			// if the listType is equal to "archive" then HQL returns all inactive elements
		} else if (listType.equals("archive")) {
			if (isContent) {
				sb.append(" AND ");
				sb.append("isActive = false ORDER BY id DESC");
			} else {
				sb.append("isActive = false ORDER BY id DESC");
				isContent = true;
			}
			// if the listType is not equal to "new" or "archive" then HQL returns all
			// active and inactive elements
		} else {
			if (isContent) {
				sb.append(" AND ");
				sb.append("isActive = false OR isActive = false ORDER BY id DESC");
			} else {
				sb.append("isActive = false OR isActive = false ORDER BY id DESC");
				isContent = true;
			}
		}
		return sb.toString();
	}
}