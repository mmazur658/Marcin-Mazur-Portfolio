package pl.marcinmazur.portfolio.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.marcinmazur.portfolio.entity.AccessCode;

/**
 * Repository class for performing database operations on AccessCode objects.
 * 
 * @author Marcin Mazur
 *
 */
@Repository
public class AccessCodeDaoImpl implements AccessCodeDao {

	/**
	 * The EntityManager interface
	 */
	private EntityManager entityManager;

	/**
	 * Constructs a AccessCodeDaoImpl with the EntityManager
	 * 
	 * @param entityManager
	 *            The EntityManager
	 */
	@Autowired
	public AccessCodeDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void addNewAccessCode(AccessCode accessCode) {
		entityManager.persist(accessCode);

	}

	@SuppressWarnings("unchecked")
	@Override
	public AccessCode getAccessCodeById(int accessCodeId) {

		String hql = "FROM AccessCode WHERE id=:accessCodeId";
		Query<AccessCode> theQuery = (Query<AccessCode>) entityManager.createQuery(hql);
		theQuery.setParameter("accessCodeId", accessCodeId);

		try {
			return theQuery.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccessCode> getListOfAccessCodes() {
		String hql = "FROM AccessCode";
		Query<AccessCode> theQuery = (Query<AccessCode>) entityManager.createQuery(hql);

		return theQuery.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public AccessCode getAccessCodeByValue(String accessCodeValue) {

		String hql = "FROM AccessCode WHERE accessCodeValue=:accessCodeValue";
		Query<AccessCode> theQuery = (Query<AccessCode>) entityManager.createQuery(hql);
		theQuery.setParameter("accessCodeValue", accessCodeValue);

		try {
			return theQuery.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getListOfAccessCodeValues() {

		String hql = "SELECT DISTINCT accessCodeValue FROM AccessCode";
		Query<String> theQuery = (Query<String>) entityManager.createQuery(hql);

		return theQuery.getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public String getAccessCodeOwenerByGivenAccessCodeValue(String accessCodeValue) {

		String hql = "SELECT DISTINCT accessCodeOwner FROM AccessCode WHERE accessCodeValue=:accessCodeValue";
		Query<String> theQuery = (Query<String>) entityManager.createQuery(hql);
		theQuery.setParameter("accessCodeValue", accessCodeValue);

		try {
			return theQuery.getSingleResult();
		} catch (NullPointerException e) {
			return "Anonymous";
		}

	}

}
