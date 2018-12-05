package pl.marcinmazur.portfolio.utils;

import org.springframework.stereotype.Component;

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

		sb.append(searchType);
		if (!searchParameters[0].equals("")) {
			sb.append(fieldsName[0] + " like '%" + searchParameters[0] + "%'");
			isContent = true;
		}
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

		String startDate = searchParameters[searchParameters.length - 3];
		String endDate = searchParameters[searchParameters.length - 2];
		String listType = searchParameters[searchParameters.length - 1];

		if (!startDate.equals("") && endDate.equals("")) {
			if (isContent) {
				sb.append(" AND ");
				sb.append("date >= '" + startDate + "'");
			} else {
				sb.append("date >= '" + startDate + "'");
				isContent = true;
			}
		} else if (startDate.equals("") && !endDate.equals("")) {
			if (isContent) {
				sb.append(" AND ");
				sb.append("date <= '" + endDate + "'");
			} else {
				sb.append("date <= '" + endDate + "'");
				isContent = true;
			}
		} else if (!startDate.equals("") && !endDate.equals("")) {
			if (isContent) {
				sb.append(" AND ");
				sb.append("date BETWEEN '" + startDate + "' AND '" + endDate + "'");
			} else {
				sb.append("date BETWEEN '" + startDate + "' AND '" + endDate + "'");
				isContent = true;
			}
		}

		if (listType.equals("new")) {
			if (isContent) {
				sb.append(" AND ");
				sb.append("isActive = true ORDER BY id DESC");
			} else {
				sb.append("isActive = true ORDER BY id DESC");
				isContent = true;
			}
		} else if (listType.equals("archive")) {
			if (isContent) {
				sb.append(" AND ");
				sb.append("isActive = false ORDER BY id DESC");
			} else {
				sb.append("isActive = false ORDER BY id DESC");
				isContent = true;
			}
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