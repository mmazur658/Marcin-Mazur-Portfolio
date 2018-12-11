package pl.marcinmazur.portfolio.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.marcinmazur.portfolio.entity.Comment;
import pl.marcinmazur.portfolio.entity.ContactFormMessage;

/**
 * Repository class for performing database operations on ContactFormMessage and
 * Comment objects.
 * 
 * @author Marcin Mazur
 *
 */
@Repository
public class ContactFormMessageDaoImpl implements ContactFormMessageDao {

	/**
	 * The EntityManager interface
	 */
	private EntityManager entityManager;

	/**
	 * Constructs a ContactFormMessageDaoImpl with the EntityManager
	 * 
	 * @param entityManager
	 *            The EntityManager
	 */
	@Autowired
	public ContactFormMessageDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void sendMessage(ContactFormMessage contactFormMessage) {
		entityManager.persist(contactFormMessage);
	}

	@SuppressWarnings("unchecked")
	@Override
	public long getNumberOfUnreadContactFormMessages() {

		String hql = "SELECT COUNT(*) from ContactFormMessage where isReaded = false";
		Query<Long> theQuery = (Query<Long>) entityManager.createQuery(hql);

		return (Long) theQuery.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContactFormMessage> getListOfContactFormMessages(String hql, Integer resultStartRange,
			Integer resultRange) {

		Query<ContactFormMessage> theQuery = (Query<ContactFormMessage>) entityManager.createQuery(hql);
		theQuery.setFirstResult(resultStartRange);
		theQuery.setMaxResults(resultRange);

		return theQuery.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public long getNumberOfContactFormMessagesForGivenListTpe(String hql) {

		Query<Long> theQuery = (Query<Long>) entityManager.createQuery(hql);
		return (Long) theQuery.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ContactFormMessage getContactFormMessage(Long contactFormMessageId) {

		String hql = "from ContactFormMessage where id=:contactFormMessageId";
		Query<ContactFormMessage> theQuery = (Query<ContactFormMessage>) entityManager.createQuery(hql);
		theQuery.setParameter("contactFormMessageId", contactFormMessageId);

		return theQuery.getSingleResult();
	}

	@Override
	public void addComment(Comment comment) {
		entityManager.persist(comment);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteComment(long commentId) {

		String hql = "DELETE Comment WHERE id=:commentId";
		Query<ContactFormMessage> theQuery = (Query<ContactFormMessage>) entityManager.createQuery(hql);
		theQuery.setParameter("commentId", commentId);
		theQuery.executeUpdate();

	}

	@SuppressWarnings("unchecked")
	@Override
	public long getNumberOfContactFormMessagesForGivenDate(String startDate, String endDate) {

		String hql = "SELECT COUNT(*) FROM ContactFormMessage WHERE date BETWEEN '" + startDate + "' AND '" + endDate
				+ "'";
		Query<Long> theQuery = (Query<Long>) entityManager.createQuery(hql);

		return (Long) theQuery.uniqueResult();

	}

	@SuppressWarnings("unchecked")
	@Override
	public long getNumberOfUnreadContactFormMessagesForGivenDate(String startDate, String endDate) {

		String hql = "SELECT COUNT(*) FROM ContactFormMessage WHERE isActive = true AND date BETWEEN '" + startDate
				+ "' AND '" + endDate + "' AND isReaded = false";
		Query<Long> theQuery = (Query<Long>) entityManager.createQuery(hql);

		return (Long) theQuery.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public long getNumberOfUnreadContactFormMessagesForGivenDate(String stringCurrentDate) {

		String hql = "SELECT COUNT(*) FROM ContactFormMessage WHERE isActive = true AND date <= '" + stringCurrentDate
				+ "' AND isReaded = false";
		Query<Long> theQuery = (Query<Long>) entityManager.createQuery(hql);

		return (Long) theQuery.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public long getNumberOfContactFormMessageForGivenDate(String startDate, String endDate) {

		String hql = "SELECT COUNT(*) FROM ContactFormMessage WHERE date BETWEEN '" + startDate + "' AND '" + endDate
				+ "'";
		Query<Long> theQuery = (Query<Long>) entityManager.createQuery(hql);

		return (Long) theQuery.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getMonthlyMessagesData(String startDate, String endDate) {

		String hql = "SELECT date, COUNT(*) FROM ContactFormMessage WHERE date BETWEEN '" + startDate + "' AND '"
				+ endDate + "' GROUP BY date";
		Query<Object[]> theQuery = (Query<Object[]>) entityManager.createQuery(hql);

		return theQuery.getResultList();
	}

}
