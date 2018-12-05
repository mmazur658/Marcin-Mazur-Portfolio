package pl.marcinmazur.portfolio.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.marcinmazur.portfolio.dao.ProjectVisitingHistoryDao;
import pl.marcinmazur.portfolio.entity.ProjectVisitingHistory;
import pl.marcinmazur.portfolio.utils.ServiceUtils;

@Service
public class ProjectVisitingHistoryServiceImpl implements ProjectVisitingHistoryService {
	
	private ProjectVisitingHistoryDao projectVisitingHistoryDao;
	private ServiceUtils serviceUtils;
	
	public ProjectVisitingHistoryServiceImpl(ProjectVisitingHistoryDao projectVisitingHistoryDao,ServiceUtils serviceUtils) {
		this.projectVisitingHistoryDao=projectVisitingHistoryDao;
		this.serviceUtils=serviceUtils;
	}

	@Override
	@Transactional
	public void addProjectVisitingHistory(String projectName) throws ParseException {
		
		ProjectVisitingHistory projectVisitingHistory = serviceUtils.createProjectVisitingHistory(projectName);		
		projectVisitingHistoryDao.saveProjectVisitingHistory(projectVisitingHistory);
		
	}

}
