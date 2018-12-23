package pl.marcinmazur.portfolio.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.marcinmazur.portfolio.entity.AccessCodeHistory;

/**
 * Repository class for performing database operations on AccessCodeHistory
 * objects.
 * 
 * @author Marcin Mazur
 *
 */
@Repository
public class AccessCodeHistoryDaoImpl implements AccessCodeHistoryDao {

	/**
	 * The EntityManager interface
	 */
	private EntityManager entityManager;

	/**
	 * Constructs a AccessCodeHistoryDaoImpl with the EntityManager
	 * 
	 * @param entityManager
	 *            The EntityManager interface
	 */
	@Autowired
	public AccessCodeHistoryDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void saveAccessCodeHistory(AccessCodeHistory accessCodeHistory) {
		entityManager.persist(accessCodeHistory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccessCodeHistory> getListOfAccessCodeHistory(String accessCodeValue) {

		String hql = "FROM AccessCodeHistory WHERE accessCodeValue=:accessCodeValue";
		Query<AccessCodeHistory> theQuery = (Query<AccessCodeHistory>) entityManager.createQuery(hql);
		theQuery.setParameter("accessCodeValue", accessCodeValue);

		return theQuery.getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public long getNumberOfCodeUsage(String accessCodeValue, String action) {

		String hql = "SELECT COUNT(*) FROM AccessCodeHistory WHERE accessCodeValue=:accessCodeValue AND action=:action";
		Query<Long> theQuery = (Query<Long>) entityManager.createQuery(hql);
		theQuery.setParameter("action", action);
		theQuery.setParameter("accessCodeValue", accessCodeValue);

		return (Long) theQuery.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public long getNumberOfGivenAccessCodeValueAndGivenDate(String accessCodeValue, String startDate, String endDate) {

		String hql = "SELECT COUNT(*) FROM AccessCodeHistory WHERE accessCodeValue=:accessCodeValue AND actionDate BETWEEN '"
				+ startDate + "' AND '" + endDate + "'";

		Query<Long> theQuery = (Query<Long>) entityManager.createQuery(hql);
		theQuery.setParameter("accessCodeValue", accessCodeValue);

		try {
			return (Long) theQuery.uniqueResult();
		} catch (NullPointerException e) {
			return 0;
		}

	}

}
