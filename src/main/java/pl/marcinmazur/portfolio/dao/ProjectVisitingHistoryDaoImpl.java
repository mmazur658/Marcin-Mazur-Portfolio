package pl.marcinmazur.portfolio.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.marcinmazur.portfolio.entity.ProjectVisitingHistory;

@Repository
public class ProjectVisitingHistoryDaoImpl implements ProjectVisitingHistoryDao {

	private EntityManager entityManager;

	@Autowired
	public ProjectVisitingHistoryDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void saveProjectVisitingHistory(ProjectVisitingHistory projectVisitingHistory) {

		String hql = "FROM ProjectVisitingHistory WHERE projectName=:projectName AND date=:date";
		Query<ProjectVisitingHistory> theQuery = (Query<ProjectVisitingHistory>) entityManager.createQuery(hql);
		theQuery.setParameter("projectName", projectVisitingHistory.getProjectName());
		theQuery.setParameter("date", projectVisitingHistory.getDate());

		ProjectVisitingHistory tempProjectVisitingHistory = null;
		try {
			tempProjectVisitingHistory = theQuery.getSingleResult();
		} catch (Exception e) {
			tempProjectVisitingHistory = null;
		}

		if (tempProjectVisitingHistory == null) {
			projectVisitingHistory.setNumberOfVisits(1);
			entityManager.persist(projectVisitingHistory);
		} else
			tempProjectVisitingHistory.setNumberOfVisits(tempProjectVisitingHistory.getNumberOfVisits() + 1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getListOfProjectNames() {

		String hql = "SELECT DISTINCT projectName FROM ProjectVisitingHistory";
		Query<String> theQuery = (Query<String>) entityManager.createQuery(hql);

		List<String> projectsNameList = theQuery.getResultList();

		return projectsNameList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public long getSumOfVisitsForGivenProjectNameAndGivenDate(String projectName, String startDate, String endDate) {

		String hql = "SELECT SUM(numberOfVisits) FROM ProjectVisitingHistory WHERE projectName=:projectName AND date BETWEEN '"
				+ startDate + "' AND '" + endDate + "'";
		Query<Long> theQuery = (Query<Long>) entityManager.createQuery(hql);
		theQuery.setParameter("projectName", projectName);

		long sumOfGivenProject;

		try {
			sumOfGivenProject = (Long) theQuery.uniqueResult();
		} catch (NullPointerException e) {
			sumOfGivenProject = 0;
		}
		return sumOfGivenProject;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getMonthlyDataForSelectedProject(String projectName, String startDate, String endDate) {

		String hql = "SELECT date, numberOfVisits FROM ProjectVisitingHistory WHERE projectName=:projectName AND date BETWEEN '"
				+ startDate + "' AND '" + endDate + "' ORDER BY date ASC";

		Query<Object[]> theQuery = (Query<Object[]>) entityManager.createQuery(hql);
		theQuery.setParameter("projectName", projectName);
		List<Object[]> tempList = theQuery.getResultList();

		return tempList;
	}
}
