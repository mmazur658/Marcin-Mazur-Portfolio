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

/**
 * The controller class is used to return the view depending on the user
 * request. This controller contains the views of: <br>
 * <ul>
 * <li>"parts/new-access-code-modal"</li>
 * <li>"parts/access-code-table"</li>
 * <li>"parts/access-code-modal"</li>
 * <li>"parts/access-code-history-table"</li>
 * </ul>
 * 
 * @author Marcin Mazur
 *
 */
@Controller
@RequestMapping("/administrator-panel/access-code")
public class AccessCodeController {

	/*
	 * The AccessCodeService interface
	 */
	private AccessCodeService accessCodeService;

	/*
	 * The AccessCodeHistoryService interface
	 */
	private AccessCodeHistoryService accessCodeHistoryService;

	/**
	 * Constructs a AccessCodeController with the AccessCodeService and
	 * AccessCodeHistoryService.
	 * 
	 * @param accessCodeService
	 *            The AccessCodeService interface
	 * @param accessCodeHistoryService
	 *            The AccessCodeHistoryService interface
	 */
	@Autowired
	public AccessCodeController(AccessCodeService accessCodeService,
			AccessCodeHistoryService accessCodeHistoryService) {
		this.accessCodeService = accessCodeService;
		this.accessCodeHistoryService = accessCodeHistoryService;
	}

	/**
	 * Returns the view of "parts/new-access-code-modal".
	 * 
	 * @return The String representing the name of the view
	 */
	@RequestMapping("/new-access-code")
	public String showNewAccessCodeForm() {
		return "parts/new-access-code-modal";
	}

	/**
	 * Returns the view of "parts/access-code-table" with model attribute:<br>
	 * <ul>
	 * <li>accessCodeList - The list of AccessCode objects</li>
	 * </ul>
	 * 
	 * @param theModel
	 *            The Model containing the data passed to the view
	 * @return The String representing the name of the view
	 */
	@RequestMapping("/access-codes")
	public String showAccessCodeTable(Model theModel) {

		List<AccessCode> accessCodeList = accessCodeService.getAccessCodes();
		theModel.addAttribute("accessCodeList", accessCodeList);

		return "parts/access-code-table";
	}

	/**
	 * Returns the view of "parts/access-code-modal" with model attribute:<br>
	 * <ul>
	 * <li>accessCode - The AccessCode object</li>
	 * </ul>
	 * 
	 * @param accessCodeId
	 *            The int containing the id of the access code
	 * @param theModel
	 *            The Model containing the data passed to the view
	 * @return The String representing the name of the view
	 */
	@RequestMapping("/access-code")
	public String showAccessCode(@RequestParam(name = "accessCodeId") int accessCodeId, Model theModel) {

		AccessCode accessCode = accessCodeService.getAccessCode(accessCodeId);
		theModel.addAttribute("accessCode", accessCode);

		return "parts/access-code-modal";

	}

	/**
	 * Returns the view of "parts/access-code-history-table" with model
	 * attribute:<br>
	 * <ul>
	 * <li>accessCodeHistoryList - The list of AccessCodeHistory objects</li>
	 * </ul>
	 * 
	 * @param accessCodeValue
	 *            The String containing the value of access code
	 * @param theModel
	 *            The Model containing the data passed to the view
	 * @return The String representing the name of the view
	 */
	@RequestMapping("/access-code-history")
	public String showAccessCodeHistoryTable(@RequestParam(name = "accessCodeValue") String accessCodeValue,
			Model theModel) {

		List<AccessCodeHistory> accessCodeHistoryList = accessCodeHistoryService
				.getAccessCodeHistoryList(accessCodeValue);
		theModel.addAttribute("accessCodeHistoryList", accessCodeHistoryList);

		return "parts/access-code-history-table";
	}
}
