package pl.marcinmazur.portfolio.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.marcinmazur.portfolio.service.AccessCodeService;

@RestController
@RequestMapping("/administrator-panel/access-code")
public class AccessCodeRestController {

	private AccessCodeService accessCodeService;

	@Autowired
	public AccessCodeRestController(AccessCodeService accessCodeService) {
		this.accessCodeService = accessCodeService;
	}

	@RequestMapping("/create-new-access-code")
	void createNewAccessCode(@RequestParam(name = "newAccessCodeValue") String newAccessCodeValue,
			@RequestParam(name = "newAccessCodeOwner") String newAccessCodeOwner,
			@RequestParam(name = "newAccessCodeDescription") String newAccessCodeDescription) {
		accessCodeService.createNewAccessCode(newAccessCodeValue, newAccessCodeOwner, newAccessCodeDescription);

	}

	@RequestMapping("/check-access-code")
	boolean isAccessCodeUniqe(@RequestParam(name = "accessCodeValue") String accessCodeValue) {
		return accessCodeService.isAccessCodeUniqe(accessCodeValue);

	}

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
