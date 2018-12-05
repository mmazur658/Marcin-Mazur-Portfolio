package pl.marcinmazur.portfolio.service;

import java.text.ParseException;
import java.util.List;

import pl.marcinmazur.portfolio.entity.AccessCode;

public interface AccessCodeService {

	void createNewAccessCode(String newAccessCodeValue, String newAccessCodeOwner, String newAccessCodeDescription);

	boolean isAccessCodeUniqe(String accessCodeValue);

	List<AccessCode> getAccessCodes();

	AccessCode getAccessCode(int accessCodeId);

	void updateAccessCode(String accessCodeId, String accessCodeValue, String accessCodeDate,
			boolean accessCodeIsActive, String accessCodeDescription, String accessCodeOwner) throws ParseException;

	boolean isAccessCodeCorrect(String accessCodeValue);

}
