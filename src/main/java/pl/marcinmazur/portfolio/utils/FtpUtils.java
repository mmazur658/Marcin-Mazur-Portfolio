package pl.marcinmazur.portfolio.utils;

import java.io.File;
import java.io.IOException;

/**
 * Interface used to download files from FTP servers
 * 
 * @author Marcin Mazur
 *
 */
public interface FtpUtils {

	/**
	 * Returns the file with given name from FTP Server. This method returns the
	 * resume in Polish or English.
	 * 
	 * @param fileName
	 *            The String containing the name of the file
	 * @return A File representing the PDF file with resume
	 * @throws IOException
	 *             A IOException is thrown then the file is not available
	 */
	File getFileWithGievenNameFromFtpServer(String fileName) throws IOException;

}
