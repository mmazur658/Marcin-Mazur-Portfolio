package pl.marcinmazur.portfolio.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.marcinmazur.portfolio.utils.FtpUtils;

/**
 * The controller class is used to return the view depending on the user
 * request. This controller contains the views of: <br>
 * <ul>
 * <li>"index"</li>
 * <li>"login-page"</li>
 * <li>"parts/career-section-access-form"</li>
 * <li>"parts/career"</li>
 * <li>"simple-abc-library"</li>
 * <li>"web-calculator"</li>
 * <li>"marcin-mazur-portfolio"</li>
 * </ul>
 * <br>
 * <br>
 * This controller returns also two documents: Resume and Motivational Letter as
 * a PDF file
 * 
 * @author Marcin Mazur
 *
 */
@Controller

public class PortfolioController {

	/**
	 * The FtpUtils interface
	 */
	private FtpUtils ftpUtils;

	/**
	 * Constructs a PortfolioController with the FtpUtils
	 * 
	 * @param ftpUtils
	 *            The FtpUtils interface
	 */
	@Autowired
	public PortfolioController(FtpUtils ftpUtils) {
		this.ftpUtils = ftpUtils;
	}

	/**
	 * Returns the view of "index".
	 * 
	 * @return The String representing the name of the view
	 */
	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}

	/**
	 * Returns the view of "login-page".
	 * 
	 * @return The String representing the name of the view
	 */
	@RequestMapping("/login-page")
	public String showLoginPage() {
		return "login-page";
	}

	/**
	 * Returns the view of "parts/career" or "parts/career-section-access-form".
	 * 
	 * @param request
	 *            The HttpServletRequest
	 * @return The String representing the name of the view
	 */
	@RequestMapping("/career-section")
	public String showCareerSection(HttpServletRequest request) {

		HttpSession session = request.getSession();

		boolean isAccessGranted = (session.getAttribute("accessGranted") == null) ? false
				: (boolean) session.getAttribute("accessGranted");

		if (isAccessGranted)
			return "parts/career";
		else
			return "parts/career-section-access-form";
	}

	/**
	 * Returns the view of "simple-abc-library".
	 * 
	 * @return The String representing the name of the view
	 */
	@RequestMapping("/simple-abc-library")
	public String showSimpleAbcLibraryProject() {
		return "simple-abc-library";
	}

	/**
	 * Returns the view of "web-calculator".
	 * 
	 * @return The String representing the name of the view
	 */
	@RequestMapping("/web-calculator")
	public String showWebCalculatorProject() {
		return "web-calculator";
	}

	/**
	 * Returns the view of "marcin-mazur-portfolio".
	 * 
	 * @return The String representing the name of the view
	 */
	@RequestMapping("/marcin-mazur-portfolio")
	public String showMarcinMazurPortfolioProject() {
		return "marcin-mazur-portfolio";
	}

	/**
	 * Returns the resume in Polish as a PDF File
	 * 
	 * @param locale
	 *            The Locale containing the user`s locale
	 * @return A PDF file representing the Marcin Mazur`s Resume
	 * @throws IOException
	 *             A IOException is thrown then the file is not available
	 */
	@RequestMapping("/download-resume-pl")
	public ResponseEntity<InputStreamResource> downloadResumePl(Locale locale) throws IOException {

		File resume = ftpUtils.getFileWithGievenNameFromFtpServer("Mazur_Marcin_CV_PL.pdf");

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_PDF);
		responseHeaders.setContentLength(resume.length());
		responseHeaders.setContentDispositionFormData("attachment", resume.getName());

		InputStreamResource isr = new InputStreamResource(new FileInputStream(resume));
		return new ResponseEntity<InputStreamResource>(isr, responseHeaders, HttpStatus.OK);

	}

	/**
	 * Returns the resume in English as a PDF File
	 * 
	 * @param locale
	 *            The Locale containing the user`s locale
	 * @return A PDF file representing the Marcin Mazur`s motivational letter
	 * @throws IOException
	 *             A IOException is thrown then the file is not available
	 */

	@RequestMapping("/download-resume-en")
	public ResponseEntity<InputStreamResource> downloadResumeEn(Locale locale) throws IOException {

		File resume = ftpUtils.getFileWithGievenNameFromFtpServer("Mazur_Marcin_Resume_EN.pdf");

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_PDF);
		responseHeaders.setContentLength(resume.length());
		responseHeaders.setContentDispositionFormData("attachment", resume.getName());

		InputStreamResource isr = new InputStreamResource(new FileInputStream(resume));
		return new ResponseEntity<InputStreamResource>(isr, responseHeaders, HttpStatus.OK);

	}

}
