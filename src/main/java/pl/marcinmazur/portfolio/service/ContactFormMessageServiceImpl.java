package pl.marcinmazur.portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.marcinmazur.portfolio.dao.ContactFormMessageDao;
import pl.marcinmazur.portfolio.entity.ContactFormMessage;
import pl.marcinmazur.portfolio.utils.SearchEngineUtils;
import pl.marcinmazur.portfolio.utils.ServiceUtils;

/**
 * Service class for managing contact form messages.
 * 
 * @author Marcin Mazur
 *
 */
@Service
public class ContactFormMessageServiceImpl implements ContactFormMessageService {

	/**
	 * The array containing the names of the message search parameters
	 */
	private final String[] NAMES_OF_CONTACT_FORM_MESSAGE_FIELDS = { "senderName", "senderEmail", "messageSubject",
			"date", "listType" };

	/**
	 * The ContactFormMessageDao interface
	 */
	private ContactFormMessageDao contactFormMessageDao;

	/**
	 * The SearchEngineUtils interface
	 */
	private SearchEngineUtils searchEngineUtils;

	/**
	 * The ServiceUtils interface
	 */
	private ServiceUtils serviceUtils;

	/**
	 * Constructs a ContactFormMessageServiceImpl with the ContactFormMessageDao,
	 * SearchEngineUtils and ServiceUtils.
	 * 
	 * @param contactFormMessageDao
	 *            The ContactFormMessageDao interface
	 * @param searchEngineUtils
	 *            The SearchEngineUtils interface
	 * @param serviceUtils
	 *            The ServiceUtils interface
	 */
	@Autowired
	public ContactFormMessageServiceImpl(ContactFormMessageDao contactFormMessageDao,
			SearchEngineUtils searchEngineUtils, ServiceUtils serviceUtils) {
		this.contactFormMessageDao = contactFormMessageDao;
		this.searchEngineUtils = searchEngineUtils;
		this.serviceUtils = serviceUtils;
	}

	@Override
	@Transactional
	public void sendMessage(String senderEmail, String senderName, String messageText, String messageSubject) {
		contactFormMessageDao.sendMessage(
				serviceUtils.createContactFormMessage(senderEmail, senderName, messageText, messageSubject));
	}

	@Override
	@Transactional
	public long getNumberOfUnreadContactFormMessage() {
		return contactFormMessageDao.getNumberOfUnreadContactFormMessages();
	}

	@Override
	@Transactional
	public List<ContactFormMessage> getContactFormMessages(String listType, Integer resultStartRange,
			Integer resultRange) {

		String hqlType = "from ContactFormMessage ";
		String hql = serviceUtils.prepareHqlDependsOnListType(hqlType, listType);

		return contactFormMessageDao.getListOfContactFormMessages(hql, resultStartRange, resultRange);
	}

	@Override
	@Transactional
	public void deleteContactFormMessage(long contactFormMessageId) {

		ContactFormMessage contactFormMessage = contactFormMessageDao.getContactFormMessage(contactFormMessageId);
		serviceUtils.changeIsActiveAndIsReadedStatus(contactFormMessage);

	}

	@Override
	@Transactional
	public void changeContactFormMessageIsReadStatus(long contactFormMessageId) {

		ContactFormMessage contactFormMessage = contactFormMessageDao.getContactFormMessage(contactFormMessageId);
		serviceUtils.changeIsReadedStatus(contactFormMessage);

	}

	@Override
	@Transactional
	public void changeContactFormMessageRepliedStatus(long contactFormMessageId) {

		ContactFormMessage contactFormMessage = contactFormMessageDao.getContactFormMessage(contactFormMessageId);
		serviceUtils.changeIsRepliedStatus(contactFormMessage);

	}

	@Override
	@Transactional
	public List<ContactFormMessage> getContactFormMessageSearchResult(String[] searchParameters, int startResult,
			Integer resultRange) {

		String searchType = "from ContactFormMessage where ";
		String hql = searchEngineUtils.prepareHqlUsingContactFormMessageSearchParameters(searchParameters, searchType,
				NAMES_OF_CONTACT_FORM_MESSAGE_FIELDS);

		return contactFormMessageDao.getListOfContactFormMessages(hql, startResult, resultRange);
	}

	@Override
	@Transactional
	public ContactFormMessage getContactFormMessage(Long contactFormMessageId) {
		return contactFormMessageDao.getContactFormMessage(contactFormMessageId);
	}

	@Override
	@Transactional
	public void setContactFormMessageReadStatusTrue(long contactFormMessageId) {

		ContactFormMessage contactFormMessage = contactFormMessageDao.getContactFormMessage(contactFormMessageId);
		contactFormMessage.setIsReaded(true);

	}

	@Override
	@Transactional
	public void addComment(long messageId, String commentText) {

		ContactFormMessage contactFormMessage = contactFormMessageDao.getContactFormMessage(messageId);
		contactFormMessageDao.addComment(serviceUtils.createComment(commentText, contactFormMessage));

	}

	@Override
	@Transactional
	public void deleteComment(long commentId) {
		contactFormMessageDao.deleteComment(commentId);

	}

	@Override
	@Transactional
	public long getNumberOfContactFormMessageForGivenDate(String startDate, String endDate) {
		return contactFormMessageDao.getNumberOfContactFormMessageForGivenDate(startDate, endDate);
	}

	@Override
	public long getNumberOfAllContactFormMessages(String listType) {

		// Prepare the HQL Statement
		String hqlType = "SELECT COUNT(*) FROM ContactFormMessage ";
		String hql = serviceUtils.prepareHqlDependsOnListType(hqlType, listType);

		return contactFormMessageDao.getNumberOfAllContactFormMessages(hql);
	}

	@Override
	public long getNumberOfContactFormMessagesForGivenSearchParams(String[] searchParametersValue) {

		// Prepare the HQL Statement
		String searchType = "SELECT COUNT(*) FROM ContactFormMessage WHERE ";
		String hql = searchEngineUtils.prepareHqlUsingContactFormMessageSearchParameters(searchParametersValue,
				searchType, NAMES_OF_CONTACT_FORM_MESSAGE_FIELDS);

		return contactFormMessageDao.getNumberOfAllContactFormMessages(hql);
	}

}
