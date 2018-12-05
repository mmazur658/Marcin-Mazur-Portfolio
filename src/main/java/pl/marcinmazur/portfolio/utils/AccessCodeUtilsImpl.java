package pl.marcinmazur.portfolio.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import pl.marcinmazur.portfolio.entity.AccessCode;
import pl.marcinmazur.portfolio.entity.AccessCodeHistory;

@Component
public class AccessCodeUtilsImpl implements AccessCodeUtils {

	@Override
	public AccessCodeHistory createAccessCodeHistory(String accessCodeValue, String accessCodeHistoryActionName) {

		AccessCodeHistory accessCodeHistory = new AccessCodeHistory();
		accessCodeHistory.setAccessCodeValue(accessCodeValue);
		accessCodeHistory.setActionDate(new Date());
		accessCodeHistory.setAction(accessCodeHistoryActionName);

		return accessCodeHistory;

	}

	@Override
	public boolean hasCodeBeenUsedBefore(long numberOfCodeUsage) {
		if (numberOfCodeUsage > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean isAccessCodeUniqe(List<AccessCode> accessCodeList) {

		if (accessCodeList.size() == 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean isAccessCodeCorrect(List<AccessCode> accessCodeList) {
		if (accessCodeList.size() == 0)
			return false;
		else
			return true;
	}

	@Override
	public void updateAccessCode(AccessCode accessCode, String accessCodeDate, boolean accessCodeIsActive,
			String accessCodeDescription, String accessCodeOwner) {

		accessCode.setAccessCodeDescription(accessCodeDescription);
		accessCode.setAccessCodeOwner(accessCodeOwner);
		accessCode.setIsActive(accessCodeIsActive);
		try {
			accessCode.setDateOfAdded(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(accessCodeDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	@Override
	public AccessCode createNewAccessCode(String newAccessCodeValue, String newAccessCodeOwner,
			String newAccessCodeDescription) {

		AccessCode accessCode = new AccessCode();
		accessCode.setAccessCodeDescription(newAccessCodeDescription);
		accessCode.setAccessCodeOwner(newAccessCodeOwner);
		accessCode.setAccessCodeValue(newAccessCodeValue);
		accessCode.setIsActive(true);
		accessCode.setDateOfAdded(new Date());

		return accessCode;
	}

}
