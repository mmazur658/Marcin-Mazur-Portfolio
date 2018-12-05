package pl.marcinmazur.portfolio.utils;

import java.util.List;

import pl.marcinmazur.portfolio.entity.AccessCode;
import pl.marcinmazur.portfolio.entity.AccessCodeHistory;

public interface AccessCodeUtils {

	AccessCodeHistory createAccessCodeHistory(String accessCodeValue, String accessCodeHistoryActionName);

	boolean hasCodeBeenUsedBefore(long numberOfCodeUsage);

	boolean isAccessCodeUniqe(List<AccessCode> accessCodeList);

	boolean isAccessCodeCorrect(List<AccessCode> accessCodeList);

	void updateAccessCode(AccessCode accessCode, String accessCodeDate, boolean accessCodeIsActive,
			String accessCodeDescription, String accessCodeOwner);

	AccessCode createNewAccessCode(String newAccessCodeValue, String newAccessCodeOwner,
			String newAccessCodeDescription);


}
