package pl.marcinmazur.portfolio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import pl.marcinmazur.portfolio.entity.ContactFormMessage;

@Service
public interface ContactFormMessageService {

	void sendMessage(String senderEmail, String senderName, String messageText, String messageSubject);

	long getNumberOfUnreadContactFormMessage();

	List<ContactFormMessage> getContactFormMessages(String listType, Integer resultStartRange, Integer resultRange);

	Long getTotalAmountOfContactFormMessagesList(String listType);

	void deleteContactFormMessage(long contactFormMessageId);

	void changeContactFormMessageReadStatus(long selectedCheckboxValue);

	void changeContactFormMessageRepliedStatus(long selectedCheckboxValue);

	public List<ContactFormMessage> getContactFormMessageSearchResult(String[] earchParameters, int startResult,
			Integer resultRange);

	public long getContactFormMessageAmountOfSearchResult(String[] searchParameters);

	ContactFormMessage getContactFormMessage(Long contactFormMessageId);

	void setContactFormMessageReadStatusTrue(long selectedCheckboxValue);

	void addComment(long messageId, String commentText);

	void deleteComment(long commentId);

	long getNumberOfContactFormMessageForGivenDate(String startDate, String endDate);

}
