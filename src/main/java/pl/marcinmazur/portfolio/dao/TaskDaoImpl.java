package pl.marcinmazur.portfolio.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.marcinmazur.portfolio.entity.Task;

/**
 * Repository class for performing database operations on Task objects.
 * 
 * @author Marcin Mazur
 *
 */
@Repository
public class TaskDaoImpl implements TaskDao {

	/**
	 * The EntityManager interface
	 */
	private EntityManager entityManager;

	/**
	 * Constructs a TaskDaoImpl with the EntityManager
	 * 
	 * @param entityManager
	 *            The EntityManager
	 */
	@Autowired
	public TaskDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Task getTaskById(int taskId) {

		String hql = "FROM Task WHERE id=:taskId";
		Query<Task> theQuery = (Query<Task>) entityManager.createQuery(hql);
		theQuery.setParameter("taskId", taskId);

		return theQuery.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> getTaskList(boolean isActive) {

		String hql = "FROM Task WHERE isActive=:isActive";
		Query<Task> theQuery = (Query<Task>) entityManager.createQuery(hql);
		theQuery.setParameter("isActive", isActive);

		return theQuery.getResultList();
	}

	@Override
	public void saveTask(Task task) {
		entityManager.persist(task);
	}

}
