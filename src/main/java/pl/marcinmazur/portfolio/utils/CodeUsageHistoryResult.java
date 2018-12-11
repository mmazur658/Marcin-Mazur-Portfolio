package pl.marcinmazur.portfolio.utils;

/**
 * Helper class used to display data related to Access Code History on the web page.
 * 
 * @author Marcin Mazur
 *
 */
public class CodeUsageHistoryResult {

	/**
	 * The value of the access code
	 */
	private String accessCodeValue;

	/**
	 * The owner of the access code
	 */
	private String accessCodeOwner;

	/**
	 * The number of code usage
	 */
	private long sumOfUsing;

	/**
	 * Gets the value of the AccessCode
	 * 
	 * @return A String representing the value of the AccessCode
	 */
	public String getAccessCodeValue() {
		return accessCodeValue;
	}

	/**
	 * Sets the value of the AccessCode
	 * 
	 * @param accessCodeValue
	 *            The String containing the value of the AccessCode
	 */
	public void setAccessCodeValue(String accessCodeValue) {
		this.accessCodeValue = accessCodeValue;
	}

	/**
	 * Gets the owner of the AccessCode
	 * 
	 * @return A String representing the owner of the AccessCode
	 */
	public String getAccessCodeOwner() {
		return accessCodeOwner;
	}

	/**
	 * Sets the owner of the AccessCode.
	 * 
	 * @param accessCodeOwner
	 *            The String containing the owner of the AccessCode
	 */
	public void setAccessCodeOwner(String accessCodeOwner) {
		this.accessCodeOwner = accessCodeOwner;
	}

	/**
	 * Gets the number of the code using
	 * 
	 * @return A long representing the number of the code using
	 */
	public long getSumOfUsing() {
		return sumOfUsing;
	}

	/**
	 * Sets the number of the code using.
	 * 
	 * @param sumOfUsing
	 *            The long containing the number of the code using.
	 */
	public void setSumOfUsing(long sumOfUsing) {
		this.sumOfUsing = sumOfUsing;
	}

}
