package pl.marcinmazur.portfolio.utils;

public class CodeUsageHistoryResult {

	private String accessCodeValue;
	private String accessCodeOwner;
	private long sumOfUsing;

	public String getAccessCodeValue() {
		return accessCodeValue;
	}

	public void setAccessCodeValue(String accessCodeValue) {
		this.accessCodeValue = accessCodeValue;
	}

	public String getAccessCodeOwner() {
		return accessCodeOwner;
	}

	public void setAccessCodeOwner(String accessCodeOwner) {
		this.accessCodeOwner = accessCodeOwner;
	}

	public long getSumOfUsing() {
		return sumOfUsing;
	}

	public void setSumOfUsing(long sumOfUsing) {
		this.sumOfUsing = sumOfUsing;
	}

}
