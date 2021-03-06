package pl.marcinmazur.portfolio.aop;

import java.text.ParseException;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.marcinmazur.portfolio.service.ProjectVisitingHistoryService;

/**
 * Aspect class used to create statistics of project visits
 * 
 * @author Marcin Mazur
 *
 */
@Aspect
@Component
public class ProjectVisitingHistoryAspect {

	/**
	 * The ProjectVisitingHistoryService interface
	 */
	private ProjectVisitingHistoryService projectVisitingHistoryService;

	/**
	 * Constructs a ProjectVisitingHistoryAspect with the
	 * ProjectVisitingHistoryService.
	 * 
	 * @param projectVisitingHistoryService
	 *            The ProjectVisitingHistoryService interface
	 */
	@Autowired
	public ProjectVisitingHistoryAspect(ProjectVisitingHistoryService projectVisitingHistoryService) {
		this.projectVisitingHistoryService = projectVisitingHistoryService;
	}

	@Pointcut("execution(* pl.marcinmazur.portfolio.controller.PortfolioController.showSimpleAbcLibraryProject(..))")
	private void visitSimpleABCLibrary() {
	}

	@Pointcut("execution(* pl.marcinmazur.portfolio.controller.PortfolioController.showMarcinMazurPortfolioProject(..))")
	private void visitMarcinMazurPortfolio() {
	}

	@Pointcut("execution(* pl.marcinmazur.portfolio.controller.PortfolioController.showWebCalculatorProject(..))")
	private void visitWebCalculator() {
	}

	@After("visitMarcinMazurPortfolio()")
	public void visitingMarcinMazurPortfolio() throws ParseException {
		projectVisitingHistoryService.addProjectVisitingHistory("Marcin Mazur Portfolio");
	}

	@After("visitSimpleABCLibrary()")
	public void visitingSimpleABCLibrary() throws ParseException {
		projectVisitingHistoryService.addProjectVisitingHistory("Simple ABC Library");
	}

	@After("visitWebCalculator()")
	public void visitingWebCalculator() throws ParseException {
		projectVisitingHistoryService.addProjectVisitingHistory("Web Calculator");
	}
}
