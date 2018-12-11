package pl.marcinmazur.portfolio.utils;

public interface SearchEngineUtils {

	/**
	 * Returns TRUE if the Array of the String contains value different than null or
	 * "".
	 * 
	 * @param searchParameters
	 *            The String[] containing the values to be checked
	 * @return A boolean representing the result
	 */
	public boolean hasTableAnyParameters(String[] searchParameters);

	/**
	 * Creates and returns the HQL as a String for given parameters.
	 * 
	 * The searchType should contains the text like "FROM nameOfTheEnitity "
	 * 
	 * @param searchParameters
	 *            The String[] containing the search parameters
	 * @param searchType
	 *            The String containing the begging of the HQL
	 * @param fieldsName
	 *            The String[] containing the name of the entity fields
	 * @return A String representing the HQL
	 */
	public String prepareHqlUsingContactFormMessageSearchParameters(String[] searchParameters, String searchType,
			String[] fieldsName);

}
