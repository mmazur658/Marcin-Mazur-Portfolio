package pl.marcinmazur.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.marcinmazur.portfolio.entity.AccessCode;
import pl.marcinmazur.portfolio.entity.AccessCodeHistory;
import pl.marcinmazur.portfolio.service.AccessCodeHistoryService;
import pl.marcinmazur.portfolio.service.AccessCodeService;

@Controller
@RequestMapping("/administrator-panel/access-code")
public class AccessCodeController {

	private AccessCodeService accessCodeService;
	private AccessCodeHistoryService accessCodeHistoryService;

	@Autowired
	public AccessCodeController(AccessCodeService accessCodeService, AccessCodeHistoryService accessCodeHistoryService) {
		this.accessCodeService = accessCodeService;
		this.accessCodeHistoryService=accessCodeHistoryService;
	}

	@RequestMapping("/new-access-code")
	public String showNewAccessCodeForm() {
		return "parts/new-access-code-modal";
	}

	@RequestMapping("/access-codes")
	public String showAccessCodeTable(Model theModel) {

		List<AccessCode> accessCodeList = accessCodeService.getAccessCodes();
		theModel.addAttribute("accessCodeList", accessCodeList);

		return "parts/access-code-table";
	}

	@RequestMapping("/access-code")
	public String showAccessCode(@RequestParam(name = "accessCodeId") int accessCodeId, Model theModel) {

		AccessCode accessCode = accessCodeService.getAccessCode(accessCodeId);
		theModel.addAttribute("accessCode", accessCode);

		return "parts/access-code-modal";

	}

	@RequestMapping("/access-code-history")
	public String showAccessCodeHistoryTable(@RequestParam(name = "accessCodeValue") String accessCodeValue,
			Model theModel) {

		List<AccessCodeHistory> accessCodeHistoryList = accessCodeHistoryService
				.getAccessCodeHistoryList(accessCodeValue);
		theModel.addAttribute("accessCodeHistoryList", accessCodeHistoryList);

		return "parts/access-code-history-table";
	}
}
