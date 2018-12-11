package pl.marcinmazur.portfolio.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.marcinmazur.portfolio.service.AccessCodeHistoryService;

/**
 * Aspect class used to create statistics of access codes.
 * 
 * @author Marcin Mazur
 *
 */
@Aspect
@Component
public class AccessCodeHistoryAspect {

	/**
	 * The AccessCodeHistoryService interface
	 */
	private AccessCodeHistoryService accessCodeHistoryService;

	/**
	 * Creates a AccessCodeHistoryAspect with the AccessCodeHistoryService
	 * 
	 * @param accessCodeHistoryService
	 *            The AccessCodeHistoryService interface
	 */
	@Autowired
	public AccessCodeHistoryAspect(AccessCodeHistoryService accessCodeHistoryService) {
		this.accessCodeHistoryService = accessCodeHistoryService;
	}

	@Pointcut("execution(* pl.marcinmazur.portfolio.service.AccessCodeServiceImpl.createNewAccessCode(..))")
	private void createNewAccessCode() {
	}

	@Pointcut("execution(* pl.marcinmazur.portfolio.service.AccessCodeServiceImpl.isAccessCodeCorrect(..))")
	private void usingAccessCode() {
	}

	@Before("createNewAccessCode()")
	public void creatingNewAccessCode(JoinPoint theJoinPoint) {

		Object[] signatureArgs = theJoinPoint.getArgs();
		String accessCodeValue = (String) signatureArgs[0];

		accessCodeHistoryService.createNewAccessCodeHistory(accessCodeValue);

	}

	@AfterReturning(pointcut = "usingAccessCode()", returning = "retValue")
	public void usingAccessCode(JoinPoint theJoinPoint, boolean retValue) {

		Object[] signatureArgs = theJoinPoint.getArgs();
		String accessCodeValue = (String) signatureArgs[0];

		if (retValue)
			accessCodeHistoryService.addAccessCodeHistoryAfterCodeUse(accessCodeValue);
	}

}
