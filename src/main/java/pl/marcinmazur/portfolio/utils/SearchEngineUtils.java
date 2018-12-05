package pl.marcinmazur.portfolio.utils;

public interface SearchEngineUtils {

	public boolean hasTableAnyParameters(String[] searchParameters);

	public String prepareHqlUsingContactFormMessageSearchParameters(String[] searchParameters, String searchType,
			String[] fieldsName);

}
