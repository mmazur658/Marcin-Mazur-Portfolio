package pl.marcinmazur.portfolio.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.marcinmazur.portfolio.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	private EntityManager entityManager;

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

		User tempUser;

		try {
			tempUser = theQuery.getSingleResult();
		} catch (NoResultException e) {
			tempUser = null;
		}

		return tempUser;
	}
}
