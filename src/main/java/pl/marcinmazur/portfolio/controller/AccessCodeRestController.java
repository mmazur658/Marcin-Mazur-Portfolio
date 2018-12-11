package pl.marcinmazur.portfolio.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.marcinmazur.portfolio.service.AccessCodeService;

/**
 * The rest controller class is used to perform actions depending on the user
 * request.
 * 
 * @author Marcin Mazur
 *
 */
@RestController
@RequestMapping("/administrator-panel/access-code")
public class AccessCodeRestController {

	/**
	 * The AccessCodeService interface
	 */
	private AccessCodeService accessCodeService;

	/**
	 * Constructs a AccessCodeRestController with the AccessCodeService.
	 * 
	 * @param accessCodeService
	 *            The AccessCodeService interface
	 */
	@Autowired
	public AccessCodeRestController(AccessCodeService accessCodeService) {
		this.accessCodeService = accessCodeService;
	}

	/**
	 * Creates a AccessCode with the given value, owner and description.
	 * 
	 * @param newAccessCodeValue
	 *            The String containing the value of the access code
	 * @param newAccessCodeOwner
	 *            The String containing the owner of the access code
	 * @param newAccessCodeDescription
	 *            The String containing the description of the access code
	 */
	@RequestMapping("/create-new-access-code")
	void createNewAccessCode(@RequestParam(name = "newAccessCodeValue") String newAccessCodeValue,
			@RequestParam(name = "newAccessCodeOwner") String newAccessCodeOwner,
			@RequestParam(name = "newAccessCodeDescription") String newAccessCodeDescription) {
		accessCodeService.createNewAccessCode(newAccessCodeValue, newAccessCodeOwner, newAccessCodeDescription);

	}

	/**
	 * Returns TRUE if the value of access code is unique.
	 * 
	 * @param accessCodeValue
	 *            The String containing the value of the access code
	 * @return A boolean representing the result
	 */
	@RequestMapping("/check-access-code")
	boolean isAccessCodeUnique(@RequestParam(name = "accessCodeValue") String accessCodeValue) {
		return accessCodeService.isAccessCodeUnique(accessCodeValue);

	}

	/**
	 * Updates AccessCode with the given parameters
	 * 
	 * @param accessCodeId
	 *            The String containing the of id the access code
	 * @param accessCodeValue
	 *            The String containing the value of the access code
	 * @param accessCodeDate
	 *            The String containing the of date of added the access code
	 * @param accessCodeIsActive
	 *            The boolean containing the isActive status the access code
	 * @param accessCodeDescription
	 *            The String containing the description of the access code
	 * @param accessCodeOwner
	 *            The String containing the owner of the access code
	 * @throws ParseException
	 *             A ParseException is thrown when the String containing the date
	 *             can`t be parsed to the Date object
	 */
	@RequestMapping("/update-access-code-details")
	void updateAccessCode(@RequestParam(name = "accessCodeId") String accessCodeId,
			@RequestParam(name = "accessCodeValue") String accessCodeValue,
			@RequestParam(name = "accessCodeDate") String accessCodeDate,
			@RequestParam(name = "accessCodeIsActive") boolean accessCodeIsActive,
			@RequestParam(name = "accessCodeDescription") String accessCodeDescription,
			@RequestParam(name = "accessCodeOwner") String accessCodeOwner) throws ParseException {

		accessCodeService.updateAccessCode(accessCodeId, accessCodeValue, accessCodeDate, accessCodeIsActive,
				accessCodeDescription, accessCodeOwner);

	}

}
