package pl.marcinmazur.portfolio.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PortfolioController {

	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}

	@RequestMapping("/login-page")
	public String showLoginPage() {
		return "login-page";
	}

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

	@RequestMapping("/simple-abc-library")
	public String showSimpleAbcLibraryProject() {
		return "simple-abc-library";
	}

	@RequestMapping("/web-calculator")
	public String showWebCalculatorProject() {
		return "web-calculator";
	}

	@RequestMapping("/marcin-mazur-portfolio")
	public String showMarcinMazurPortfolioProject() {
		return "marcin-mazur-portfolio";
	}

	@RequestMapping("/download-resume-pl")
	public ResponseEntity<InputStreamResource> downloadResumePl(Locale locale) throws FileNotFoundException {

		URL url = ClassLoader.class.getResource("/files/Mazur_Marcin_CV_PL.pdf");
		File resume = new File(url.getPath());

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_PDF);
		responseHeaders.setContentLength(resume.length());
		responseHeaders.setContentDispositionFormData("attachment", resume.getName());

		InputStreamResource isr = new InputStreamResource(new FileInputStream(resume));
		return new ResponseEntity<InputStreamResource>(isr, responseHeaders, HttpStatus.OK);

	}
	
	@RequestMapping("/download-resume-en")
	public ResponseEntity<InputStreamResource> downloadResumeEn(Locale locale) throws FileNotFoundException {

		URL url = ClassLoader.class.getResource("/files/Mazur_Marcin_CV_EN.pdf");
		File resume = new File(url.getPath());

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_PDF);
		responseHeaders.setContentLength(resume.length());
		responseHeaders.setContentDispositionFormData("attachment", resume.getName());

		InputStreamResource isr = new InputStreamResource(new FileInputStream(resume));
		return new ResponseEntity<InputStreamResource>(isr, responseHeaders, HttpStatus.OK);

	}
}
