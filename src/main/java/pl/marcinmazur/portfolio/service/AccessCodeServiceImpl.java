package pl.marcinmazur.portfolio.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.marcinmazur.portfolio.dao.AccessCodeDao;
import pl.marcinmazur.portfolio.entity.AccessCode;
import pl.marcinmazur.portfolio.utils.AccessCodeUtils;

/**
 * Service class for managing access codes
 * 
 * @author Marcin Mazur
 *
 */
@Service
public class AccessCodeServiceImpl implements AccessCodeService {

	/**
	 * The AccessCodeDao interface
	 */
	private AccessCodeDao accessCodeDao;

	/**
	 * The AccessCodeUtils interface
	 */
	private AccessCodeUtils accessCodeUtils;

	/**
	 * Constructs AccessCodeServiceImpl a with the AccessCodeDao and
	 * AccessCodeUtils.
	 * 
	 * @param accessCodeDao
	 *            The AccessCodeDao interface
	 * @param accessCodeUtils
	 *            The AccessCodeUtils interface
	 */
	@Autowired
	public AccessCodeServiceImpl(AccessCodeDao accessCodeDao, AccessCodeUtils accessCodeUtils) {
		this.accessCodeDao = accessCodeDao;
		this.accessCodeUtils = accessCodeUtils;
	}

	@Override
	@Transactional
	public void createNewAccessCode(String newAccessCodeValue, String newAccessCodeOwner,
			String newAccessCodeDescription) {

		AccessCode accessCode = accessCodeUtils.createNewAccessCode(newAccessCodeValue, newAccessCodeOwner,
				newAccessCodeDescription);
		accessCodeDao.addNewAccessCode(accessCode);
	}

	@Override
	@Transactional
	public boolean isAccessCodeUnique(String accessCodeValue) {

		AccessCode theAccessCode = accessCodeDao.getAccessCodeByValue(accessCodeValue);
		return (theAccessCode == null) ? true : false;

	}

	@Override
	@Transactional
	public List<AccessCode> getAccessCodes() {
		return accessCodeDao.getListOfAccessCodes();
	}

	@Override
	@Transactional
	public AccessCode getAccessCode(int accessCodeId) {
		return accessCodeDao.getAccessCodeById(accessCodeId);
	}

	@Override
	@Transactional
	public void updateAccessCode(String accessCodeId, String accessCodeValue, String accessCodeDate,
			boolean accessCodeIsActive, String accessCodeDescription, String accessCodeOwner) throws ParseException {

		AccessCode accessCode = accessCodeDao.getAccessCodeById(Integer.valueOf(accessCodeId));
		accessCodeUtils.updateAccessCode(accessCode, accessCodeDate, accessCodeIsActive, accessCodeDescription,
				accessCodeOwner);

	}

	@Override
	@Transactional
	public boolean isAccessCodeCorrect(String accessCodeValue) {

		AccessCode theAccessCode = accessCodeDao.getAccessCodeByValue(accessCodeValue);

		return (theAccessCode != null) ? true : false;

	}

}
