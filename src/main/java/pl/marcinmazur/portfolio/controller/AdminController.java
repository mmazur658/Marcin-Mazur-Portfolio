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

@Controller
@RequestMapping("/administrator-panel")
public class AdminController {

	private ContactFormMessageService contactFormMessageService;
	private StatisticService statisticService;
	private NotificationService notificationService;

	@Autowired
	public AdminController(ContactFormMessageService contactFormMessageService, NotificationService notificationService,
			StatisticService statisticService) {
		this.contactFormMessageService = contactFormMessageService;
		this.statisticService = statisticService;
		this.notificationService = notificationService;
	}

	@RequestMapping("/dashboard")
	public String showDashboard(Model theModel) {

		long numberOfActiveNotification = notificationService.getNumberOfActiveNotification();
		long numberOfContactFormMessage = contactFormMessageService.getNumberOfUnreadContactFormMessage();

		theModel.addAttribute("numberOfActiveNotification", numberOfActiveNotification);
		theModel.addAttribute("numberOfContactFormMessage", numberOfContactFormMessage);

		return "dashboard";
	}

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

	@RequestMapping("/contact-form-messages")
	public String showContactFormMessages(Model theModel) {
		return "contact-form-messages";
	}

	@RequestMapping("/get-contact-form-messages")
	public String geContactFormMessageList(@RequestParam(name = "listType") String listType, Model theModel,
			@RequestParam(required = false, name = "resultStartRange") Integer resultStartRange) {

		Integer resultRange = 20;
		resultStartRange = (resultStartRange == null || resultStartRange == 0) ? 0 : resultStartRange;

		List<ContactFormMessage> contactFormMessageList = contactFormMessageService.getContactFormMessages(listType,
				resultStartRange, resultRange);
		Long totalResults = contactFormMessageService.getTotalAmountOfContactFormMessagesList(listType);

		theModel.addAttribute("totalResults", totalResults);
		theModel.addAttribute("contactFormMessageList", contactFormMessageList);

		return "parts/contact-form-message-table";
	}

	@RequestMapping("/get-search-contact-form-messages")
	public String getSearchContactFormMessageList(@RequestParam(name = "listType") String listType, Model theModel,
			@RequestParam(required = false, name = "searchFormName") String searchFormName,
			@RequestParam(required = false, name = "searchFormEmail") String searchFormEmail,
			@RequestParam(required = false, name = "searchFormSubject") String searchFormSubject,
			@RequestParam(required = false, name = "searchFormStartDate") String searchFormStartDate,
			@RequestParam(required = false, name = "searchFormEndDate") String searchFormEndDate) {

		Integer resultRange = 20;
		searchFormEndDate = (searchFormEndDate != "") ? searchFormEndDate + " 23:59:59.0" : "";
		searchFormStartDate = (searchFormStartDate != "") ? searchFormStartDate + " 00:00:00.0" : "";

		String[] searchParametersValue = { searchFormName.trim(), searchFormEmail.trim(), searchFormSubject.trim(),
				searchFormStartDate.trim(), searchFormEndDate.trim(), listType.trim() };

		List<ContactFormMessage> contactFormMessageList = contactFormMessageService
				.getContactFormMessageSearchResult(searchParametersValue, 0, resultRange);
		Long totalResults = contactFormMessageService.getContactFormMessageAmountOfSearchResult(searchParametersValue);

		theModel.addAttribute("totalResults", totalResults);
		theModel.addAttribute("contactFormMessageList", contactFormMessageList);

		return "parts/contact-form-message-table";
	}

	@RequestMapping("/get-message-modal")
	public String getAdminMessageModal(@RequestParam(name = "contactFormMessageId") Long contactFormMessageId,
			Model theModel) {

		ContactFormMessage contactFormMessage = contactFormMessageService.getContactFormMessage(contactFormMessageId);
		contactFormMessage.getCommentsList().sort(Comparator.comparing(Comment::getId).reversed());

		theModel.addAttribute("commentsList", contactFormMessage.getCommentsList());
		theModel.addAttribute("contactFormMessage", contactFormMessage);

		return "parts/message-modal";
	}

	@RequestMapping("/access-codes")
	public String showAccessCodes(Model theModel) {
		return "access-codes";
	}

	@RequestMapping("/statistics")
	public String showStatisticsPage() {
		return "statistics";
	}

	@RequestMapping("/tasks")
	public String showTasksPage() {
		return "tasks";
	}

}
