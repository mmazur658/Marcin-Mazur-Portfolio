package pl.marcinmazur.portfolio.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
@RequestMapping("/access-code")
public class AccessCodeValidationRestController {

	/**
	 * The AccessCodeService interface
	 */
	private AccessCodeService accessCodeService;

	/**
	 * Constructs a AccessCodeValidationRestController with the AccessCodeService.
	 * 
	 * @param accessCodeService
	 *            The AccessCodeService interface
	 */
	@Autowired
	public AccessCodeValidationRestController(AccessCodeService accessCodeService) {
		this.accessCodeService = accessCodeService;
	}

	/**
	 * Returns TRUE if the value of access code is correct
	 * 
	 * @param accessCodeValue
	 *            The String containing the value of the access code
	 * @param request
	 *            The HttpServletRequest
	 * @return A boolean representing the result
	 */
	@RequestMapping("/access-code-validation")
	public boolean isAccessCodeCorrect(@RequestParam(name = "accessCodeValue") String accessCodeValue,
			HttpServletRequest request) {

		HttpSession session = request.getSession();

		boolean isAccessGranted = accessCodeService.isAccessCodeCorrect(accessCodeValue);
		session.setAttribute("accessGranted", isAccessGranted);

		return isAccessGranted;
	}
}
