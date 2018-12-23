package pl.marcinmazur.portfolio.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Utility class used to download files from FTP servers
 * 
 * @author Marcin Mazur
 *
 */
@Component
@PropertySource("classpath:ftp.properties")
public class FtpUtilsImpl implements FtpUtils {

	/**
	 * The Environment interface
	 */
	private Environment env;

	/**
	 * Constructs a FtpUtilsImpl with the Environment.
	 * 
	 * @param env
	 *            The Environment interface
	 */
	@Autowired
	public FtpUtilsImpl(Environment env) {
		this.env = env;
	}

	@Override
	public File getFileWithGievenNameFromFtpServer(String fileName) throws IOException {

		String server = env.getProperty("ftp.server");
		int port = Integer.valueOf(env.getProperty("ftp.port"));
		String user = env.getProperty("ftp.user");
		String pass = env.getProperty("ftp.pass");

		FTPClient ftpClient = new FTPClient();
		String remoteFile = "/files/" + fileName;
		File tempFile = new File(fileName);

		try {

			ftpClient.connect(server, port);
			ftpClient.login(user, pass);
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(tempFile));
			ftpClient.retrieveFile(remoteFile, outputStream1);
			outputStream1.close();

		} catch (SocketException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return tempFile;
	}

}
