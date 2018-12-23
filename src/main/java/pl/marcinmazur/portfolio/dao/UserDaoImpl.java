package pl.marcinmazur.portfolio.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.marcinmazur.portfolio.entity.User;

/**
 * Repository class for performing database operations on User objects.
 * 
 * @author Marcin Mazur
 *
 */
@Repository
public class UserDaoImpl implements UserDao {

	/**
	 * The EntityManager interface
	 */
	private EntityManager entityManager;

	/**
	 * Constructs a UserDaoImpl with the EntityManager
	 * 
	 * @param entityManager
	 *            The EntityManager
	 */
	@Autowired
	public UserDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUserByUserName(String userName) {

		String hql = "FROM User WHERE username=:userName";
		Query<User> theQuery = (Query<User>) entityManager.createQuery(hql);
		theQuery.setParameter("userName", userName);

		try {
			return theQuery.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}
}
