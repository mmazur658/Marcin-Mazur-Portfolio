package pl.marcinmazur.portfolio.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.marcinmazur.portfolio.service.AccessCodeService;

@RestController
@RequestMapping("/access-code")
public class AccessCodeValidationRestController {

	private AccessCodeService accessCodeService;

	@Autowired
	public AccessCodeValidationRestController(AccessCodeService accessCodeService) {
		this.accessCodeService = accessCodeService;
	}

	@RequestMapping("/access-code-validation")
	public boolean isAccessCodeCorrect(@RequestParam(name = "accessCodeValue") String accessCodeValue,
			HttpServletRequest request) {

		HttpSession session = request.getSession();

		boolean isAccessGranted = accessCodeService.isAccessCodeCorrect(accessCodeValue);
		session.setAttribute("accessGranted", isAccessGranted);

		return isAccessGranted;
	}
}
