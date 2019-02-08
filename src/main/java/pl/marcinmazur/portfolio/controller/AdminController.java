package pl.marcinmazur.portfolio.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.marcinmazur.portfolio.entity.Comment;
import pl.marcinmazur.portfolio.entity.ContactFormMessage;
import pl.marcinmazur.portfolio.service.ContactFormMessageService;
import pl.marcinmazur.portfolio.service.NotificationService;
import pl.marcinmazur.portfolio.service.StatisticService;
import pl.marcinmazur.portfolio.utils.CodeUsageHistoryResult;
import pl.marcinmazur.portfolio.utils.ProjectVisitingHistoryResult;

/**
 * The controller class is used to return the view depending on the user
 * request. This controller contains the views of: <br>
 * <ul>
 * <li>"dashboard"</li>
 * <li>"parts/statistic-table"</li>
 * <li>"contact-form-messages"</li>
 * <li>"parts/contact-form-message-table"</li>
 * <li>"parts/message-modal"</li>
 * <li>"access-codes"</li>
 * <li>"statistics"</li>
 * <li>"tasks"</li>
 * </ul>
 * 
 * @author Marcin Mazur
 *
 */
@Controller
@RequestMapping("/administrator-panel")
public class AdminController {

	/**
	 * The number of the results to be returned
	 */
	private final int NUMBER_OF_RESULTS = 20;

	/*
	 * The ContactFormMessageService interface
	 */
	private ContactFormMessageService contactFormMessageService;

	/*
	 * The StatisticService interface
	 */
	private StatisticService statisticService;

	/*
	 * The NotificationService interface
	 */
	private NotificationService notificationService;

	/**
	 * Constructs a AdminController with the ContactFormMessageService,
	 * NotificationService and StatisticService.
	 * 
	 * @param contactFormMessageService
	 *            The ContactFormMessageService interface
	 * @param notificationService
	 *            The NotificationService interface
	 * @param statisticService
	 *            The StatisticService interface
	 */
	@Autowired
	public AdminController(ContactFormMessageService contactFormMessageService, NotificationService notificationService,
			StatisticService statisticService) {
		this.contactFormMessageService = contactFormMessageService;
		this.statisticService = statisticService;
		this.notificationService = notificationService;
	}

	/**
	 * Returns the view of "dashboard" with model attributes:<br>
	 * <ul>
	 * <li>numberOfActiveNotification - The number of active notifications</li>
	 * <li>numberOfContactFormMessage - The number of unread contact form
	 * messages</li>
	 * </ul>
	 * 
	 * @param theModel
	 *            The Model containing the data passed to the view
	 * @return The String representing the name of the view
	 */
	@RequestMapping("/dashboard")
	public String showDashboard(Model theModel) {

		long numberOfActiveNotification = notificationService.getNumberOfActiveNotification();
		long numberOfContactFormMessage = contactFormMessageService.getNumberOfUnreadContactFormMessage();

		theModel.addAttribute("numberOfActiveNotification", numberOfActiveNotification);
		theModel.addAttribute("numberOfContactFormMessage", numberOfContactFormMessage);

		return "dashboard";
	}

	/**
	 * Returns the view of "parts/statistic-table" with model attributes:<br>
	 * <ul>
	 * <li>projectVisitingHistoryResultList - The list of
	 * ProjectVisitingHistoryResult objects</li>
	 * <li>codeUsageHistoryResultList - The list of CodeUsageHistoryResult
	 * objects</li>
	 * <li>sumOfContactFormMessages - The number of contact form messages</li>
	 * </ul>
	 * 
	 * @param startDate
	 *            The String containing the first day of the range
	 * @param endDate
	 *            The String containing the first day of the last
	 * @param theModel
	 *            The Model containing the data passed to the view
	 * @return The String representing the name of the view
	 */
	@RequestMapping("/statistic-table")
	public String showStatisticTable(@RequestParam(name = "startDate") String startDate,
			@RequestParam(name = "endDate") String endDate, Model theModel) {

		List<ProjectVisitingHistoryResult> projectVisitingHistoryResultList = statisticService
				.getProjectVisitingHistoryListForGivenDate(startDate, endDate);

		List<CodeUsageHistoryResult> codeUsageHistoryResultList = statisticService
				.getCodeUsageHistoryResultListForGivenDate(startDate, endDate);

		long sumOfContactFormMessages = contactFormMessageService.getNumberOfContactFormMessageForGivenDate(startDate,
				endDate);

		theModel.addAttribute("projectVisitingHistoryResultList", projectVisitingHistoryResultList);
		theModel.addAttribute("codeUsageHistoryResultList", codeUsageHistoryResultList);
		theModel.addAttribute("sumOfContactFormMessages", sumOfContactFormMessages);

		return "parts/statistic-table";

	}

	/**
	 * Returns the view of "contact-form-messages".
	 * 
	 * @return The String representing the name of the view
	 */
	@RequestMapping("/contact-form-messages")
	public String showContactFormMessages() {
		return "contact-form-messages";
	}

	/**
	 * Returns the view of "" with model attributes:<br>
	 * <ul>
	 * <li>contactFormMessageList - The list of ContactFormMessage objects.</li>
	 * <li>totalResults - The length of the contactFormMessageList</li>
	 * </ul>
	 * 
	 * @param listType
	 *            The String containing the type of the list
	 * @param theModel
	 *            The Model containing the data passed to the view
	 * @param resultStartRange
	 *            The Integer containing the number of results to return
	 * @return The String representing the name of the view
	 */
	@RequestMapping("/get-contact-form-messages")
	public String geContactFormMessageList(@RequestParam(name = "listType") String listType, Model theModel,
			@RequestParam(required = false, name = "resultStartRange") Integer resultStartRange) {

		resultStartRange = (resultStartRange == null || resultStartRange == 0) ? 0 : resultStartRange;

		List<ContactFormMessage> contactFormMessageList = contactFormMessageService.getContactFormMessages(listType,
				resultStartRange, NUMBER_OF_RESULTS);
		long totalResults = contactFormMessageService.getNumberOfAllContactFormMessages(listType);

		theModel.addAttribute("totalResults", totalResults);
		theModel.addAttribute("contactFormMessageList", contactFormMessageList);

		return "parts/contact-form-message-table";
	}

	/**
	 * Returns the view of "parts/contact-form-message-table" with model
	 * attributes:<br>
	 * <ul>
	 * <li>contactFormMessageList - The list of ContactFormMessage objects.</li>
	 * <li>totalResults - The length of the contactFormMessageList</li>
	 * </ul>
	 * 
	 * @param listType
	 *            The String containing the type of the list
	 * @param theModel
	 *            The Model containing the data passed to the view
	 * @param searchFormName
	 *            The String containing the name of the sender
	 * @param searchFormEmail
	 *            The String containing the email of the sender
	 * @param searchFormSubject
	 *            The String containing the subject of the message
	 * @param searchFormStartDate
	 *            The String containing the first day of the range
	 * @param searchFormEndDate
	 *            The String containing the last day of the range
	 * @return The String representing the name of the view
	 */
	@RequestMapping("/get-search-contact-form-messages")
	public String getSearchContactFormMessageList(@RequestParam(name = "listType") String listType, Model theModel,
			@RequestParam(required = false, name = "searchFormName") String searchFormName,
			@RequestParam(required = false, name = "searchFormEmail") String searchFormEmail,
			@RequestParam(required = false, name = "searchFormSubject") String searchFormSubject,
			@RequestParam(required = false, name = "searchFormStartDate") String searchFormStartDate,
			@RequestParam(required = false, name = "searchFormEndDate") String searchFormEndDate) {

		// Integer resultRange = 20;
		searchFormEndDate = (searchFormEndDate != "") ? searchFormEndDate + " 23:59:59.0" : "";
		searchFormStartDate = (searchFormStartDate != "") ? searchFormStartDate + " 00:00:00.0" : "";

		String[] searchParametersValue = { searchFormName.trim(), searchFormEmail.trim(), searchFormSubject.trim(),
				searchFormStartDate.trim(), searchFormEndDate.trim(), listType.trim() };

		List<ContactFormMessage> contactFormMessageList = contactFormMessageService
				.getContactFormMessageSearchResult(searchParametersValue, 0, NUMBER_OF_RESULTS);
		long totalResults = contactFormMessageService
				.getNumberOfContactFormMessagesForGivenSearchParams(searchParametersValue);

		theModel.addAttribute("totalResults", totalResults);
		theModel.addAttribute("contactFormMessageList", contactFormMessageList);

		return "parts/contact-form-message-table";
	}

	/**
	 * Returns the view of "parts/message-modal" with model attributes:<br>
	 * <ul>
	 * <li>commentsList - The list of the Comment objects</li>
	 * <li>contactFormMessage - The ContactFormMessage object</li>
	 * </ul>
	 * 
	 * @param contactFormMessageId
	 *            The Long containing the id of the message
	 * @param theModel
	 *            The Model containing the data passed to the view
	 * @return The String representing the name of the view
	 */
	@RequestMapping("/get-message-modal")
	public String getAdminMessageModal(@RequestParam(name = "contactFormMessageId") Long contactFormMessageId,
			Model theModel) {

		ContactFormMessage contactFormMessage = contactFormMessageService.getContactFormMessage(contactFormMessageId);
		contactFormMessage.getCommentsList().sort(Comparator.comparing(Comment::getId).reversed());

		theModel.addAttribute("commentsList", contactFormMessage.getCommentsList());
		theModel.addAttribute("contactFormMessage", contactFormMessage);

		return "parts/message-modal";
	}

	/**
	 * Returns the view of "access-codes".
	 * 
	 * @return The String representing the name of the view
	 */
	@RequestMapping("/access-codes")
	public String showAccessCodes() {
		return "access-codes";
	}

	/**
	 * Returns the view of "statistics".
	 * 
	 * @return The String representing the name of the view
	 */
	@RequestMapping("/statistics")
	public String showStatisticsPage() {
		return "statistics";
	}

	/**
	 * Returns the view of "tasks".
	 * 
	 * @return The String representing the name of the view
	 */
	@RequestMapping("/tasks")
	public String showTasksPage() {
		return "tasks";
	}

}
