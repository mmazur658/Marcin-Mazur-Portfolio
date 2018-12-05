package pl.marcinmazur.portfolio.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import pl.marcinmazur.portfolio.entity.Comment;
import pl.marcinmazur.portfolio.entity.ContactFormMessage;

@Repository
public interface ContactFormMessageDao {

	void sendMessage(ContactFormMessage contactFormMessage);

	void addComment(Comment comment);

	void deleteComment(long commentId);

	ContactFormMessage getContactFormMessage(Long contactFormMessageId);

	List<ContactFormMessage> getListOfContactFormMessages(String listType, Integer resultStartRange,
			Integer resultRange);

	long getNumberOfUnreadContactFormMessages();

	long getNumberOfContactFormMessagesForGivenListTpe(String listType);

	long getNumberOfContactFormMessageSearchResult(String hql);

	long getNumberOfContactFormMessagesForGivenDate(String startDate, String endDate);

	long getNumberOfUnreadContactFormMessagesForGivenDate(String startDate, String endDate);

	long getNumberOfUnreadContactFormMessagesForGivenDate(String stringCurrentDate);

	long getNumberOfContactFormMessageForGivenDate(String startDate, String endDate);

	List<Object[]> getMonthlyMessagesData(String startDate, String endDate);

}
