package pl.marcinmazur.portfolio.service;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.marcinmazur.portfolio.dao.ProjectVisitingHistoryDao;
import pl.marcinmazur.portfolio.entity.ProjectVisitingHistory;
import pl.marcinmazur.portfolio.utils.ServiceUtils;

/**
 * Service class for managing history of projects visiting.
 * 
 * @author Marcin Mazur
 *
 */
@Service
public class ProjectVisitingHistoryServiceImpl implements ProjectVisitingHistoryService {

	/**
	 * The ProjectVisitingHistoryDao interface
	 */
	private ProjectVisitingHistoryDao projectVisitingHistoryDao;

	/**
	 * The ServiceUtils interface
	 */
	private ServiceUtils serviceUtils;

	/**
	 * Constructs a ProjectVisitingHistoryServiceImpl with the
	 * ProjectVisitingHistoryDao and ServiceUtils.
	 * 
	 * @param projectVisitingHistoryDao
	 *            The ProjectVisitingHistoryDao interface
	 * @param serviceUtils
	 *            The ServiceUtils interface
	 */
	@Autowired
	public ProjectVisitingHistoryServiceImpl(ProjectVisitingHistoryDao projectVisitingHistoryDao,
			ServiceUtils serviceUtils) {
		this.projectVisitingHistoryDao = projectVisitingHistoryDao;
		this.serviceUtils = serviceUtils;
	}


	@Override
	@Transactional
	public void addProjectVisitingHistory(String projectName) throws ParseException {

		ProjectVisitingHistory projectVisitingHistory = serviceUtils.createProjectVisitingHistory(projectName);
		projectVisitingHistoryDao.saveProjectVisitingHistory(projectVisitingHistory);

	}

}
