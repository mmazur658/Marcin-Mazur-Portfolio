package pl.marcinmazur.portfolio.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.marcinmazur.portfolio.entity.AccessCode;

@Repository
public class AccessCodeDaoImpl implements AccessCodeDao {
	
	private EntityManager entityManager;

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
	
		return theQuery.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccessCode> getListOfAccessCodeByValue(String accessCodeValue) {
		
		String hql = "FROM AccessCode WHERE accessCodeValue=:accessCodeValue";
		Query<AccessCode> theQuery = (Query<AccessCode>) entityManager.createQuery(hql);
		theQuery.setParameter("accessCodeValue", accessCodeValue);

		return theQuery.getResultList();

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
	public List<AccessCode> isAccessCodeCorrect(String accessCodeValue) {
		String hql = "FROM AccessCode WHERE accessCodeValue=:accessCodeValue AND isActive=true";
		Query<AccessCode> theQuery = (Query<AccessCode>) entityManager.createQuery(hql);
		theQuery.setParameter("accessCodeValue", accessCodeValue);

		return theQuery.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public AccessCode getSingleAccessCodeByValue(String accessCodeValue) {
	
		String hql = "FROM AccessCode WHERE accessCodeValue=:accessCodeValue";
		Query<AccessCode> theQuery = (Query<AccessCode>) entityManager.createQuery(hql);
		theQuery.setParameter("accessCodeValue", accessCodeValue);

		return theQuery.getSingleResult();
		
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
		
		String accessCodeOwner = null;
		
		try {
			accessCodeOwner = theQuery.getSingleResult();
		} catch (NullPointerException e) {
			accessCodeOwner = "Anonymous";
		}
		
		return accessCodeOwner;
	}



}
