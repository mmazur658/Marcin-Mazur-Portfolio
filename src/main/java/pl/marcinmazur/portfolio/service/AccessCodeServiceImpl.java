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

	/**
	 * Creates and saves new access code with the given value, owner and
	 * description.
	 * 
	 * @param newAccessCodeValue
	 *            The String containing the value of the access code
	 * @param newAccessCodeOwner
	 *            The String containing the owner of the access code
	 * @param newAccessCodeDescription
	 *            The String containing the description of the access code
	 */
	@Override
	@Transactional
	public void createNewAccessCode(String newAccessCodeValue, String newAccessCodeOwner,
			String newAccessCodeDescription) {

		AccessCode accessCode = accessCodeUtils.createNewAccessCode(newAccessCodeValue, newAccessCodeOwner,
				newAccessCodeDescription);
		accessCodeDao.addNewAccessCode(accessCode);
	}

	/**
	 * Returns TRUE is access code is unique
	 * 
	 * @param accessCodeValue
	 *            The String containing the value of the access code
	 * @return A boolean representing the result
	 */
	@Override
	@Transactional
	public boolean isAccessCodeUnique(String accessCodeValue) {

		AccessCode theAccessCode = accessCodeDao.getAccessCodeByValue(accessCodeValue);

		if (theAccessCode == null)
			return true;
		else
			return false;

	}

	/**
	 * Returns the list of all AccessCodes
	 * 
	 * @return A List&lt;AccessCode&gt; representing the list of all access codes
	 */
	@Override
	@Transactional
	public List<AccessCode> getAccessCodes() {

		return accessCodeDao.getListOfAccessCodes();
	}

	/**
	 * Return AccessCode with given id
	 * 
	 * @param accessCodeId
	 *            The int containing the id of the access code
	 * @return An AccessCode representing the access code with given id
	 */
	@Override
	@Transactional
	public AccessCode getAccessCode(int accessCodeId) {

		return accessCodeDao.getAccessCodeById(accessCodeId);
	}

	/**
	 * Updates AccessCode with the given parameters
	 * 
	 * @param accessCodeId
	 *            The String containing the id of the access code
	 * @param accessCodeValue
	 *            The String containing the value of the access code
	 * @param accessCodeDate
	 *            The String containing the date of added of the access code
	 * @param accessCodeIsActive
	 *            The boolean containing the isActive status of the access code
	 * @param accessCodeDescription
	 *            The String containing the description of the access code
	 * @param accessCodeOwner
	 *            The String containing the owner of the access code
	 */
	@Override
	@Transactional
	public void updateAccessCode(String accessCodeId, String accessCodeValue, String accessCodeDate,
			boolean accessCodeIsActive, String accessCodeDescription, String accessCodeOwner) throws ParseException {

		AccessCode accessCode = accessCodeDao.getAccessCodeById(Integer.valueOf(accessCodeId));
		accessCodeUtils.updateAccessCode(accessCode, accessCodeDate, accessCodeIsActive, accessCodeDescription,
				accessCodeOwner);

	}

	/**
	 * Returns TRUE is access code is correct
	 * 
	 * @param accessCodeValue
	 *            The String containing the value of the access code
	 * @return A boolean representing the result
	 */
	@Override
	@Transactional
	public boolean isAccessCodeCorrect(String accessCodeValue) {

		AccessCode theAccessCode = accessCodeDao.getAccessCodeByValue(accessCodeValue);

		if (theAccessCode == null)
			return true;
		else
			return false;

	}
}
