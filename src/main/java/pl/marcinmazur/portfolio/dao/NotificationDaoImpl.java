package pl.marcinmazur.portfolio.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.marcinmazur.portfolio.entity.Notification;

@Repository
public class NotificationDaoImpl implements NotificationDao {
	
	private EntityManager entityManager;

	@Autowired
	public NotificationDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Notification getNotificationById(int notificationId) {
		
		String hql = "FROM Notification WHERE id=:notificationId";
		Query<Notification> theQuery = (Query<Notification>) entityManager.createQuery(hql);
		theQuery.setParameter("notificationId", notificationId);

		return theQuery.getSingleResult();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notification> getListOfNotificationsForGivenDate(String startDate, String endDate) {
		
		String hql = "FROM Notification WHERE dateOfAdded BETWEEN '"+startDate+"' AND '"+endDate+"' AND isActive = true";
		Query<Notification> query = (Query<Notification>) entityManager.createQuery(hql);
		
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public long getSumOfActiveNotifications() {
		
		String hql = "SELECT COUNT(*) FROM Notification WHERE isActive = true";
		Query<Long> query = (Query<Long>) entityManager.createQuery(hql);
		
		return (Long) query.uniqueResult();
	}

	@Override
	public void saveNotification(Notification notification) {
		entityManager.persist(notification);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notification> getListOfActiveNotifications() {
		
		String hql = "FROM Notification WHERE isActive = true ORDER BY dateOfAdded DESC";
		Query<Notification> query = (Query<Notification>) entityManager.createQuery(hql);
		
		return query.getResultList();

	}

}
