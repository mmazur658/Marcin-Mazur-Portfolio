package pl.marcinmazur.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.marcinmazur.portfolio.dao.UserDao;
import pl.marcinmazur.portfolio.entity.User;

/**
 * Service class for managing administrator`s password
 * 
 * @author Marcin Mazur
 *
 */
@Service
public class AdminServiceImpl implements AdminService {

	/**
	 * The PasswordEncoder interface
	 */
	private PasswordEncoder passwordEncoder;

	/**
	 * The UserDao interface
	 */
	private UserDao userDao;

	/**
	 * Constructs a AdminServiceImpl with the PasswordEncoder and UserDao.
	 * 
	 * @param passwordEncoder
	 *            The PasswordEncoder interface
	 * @param userDao
	 *            The UserDao interface
	 */
	@Autowired
	public AdminServiceImpl(PasswordEncoder passwordEncoder, UserDao userDao) {
		this.passwordEncoder = passwordEncoder;
		this.userDao = userDao;
	}

	@Override
	public boolean checkPassword(String thePassword, String username) {

		User user = userDao.getUserByUserName(username);
		return passwordEncoder.matches(thePassword, user.getPassword());
	}

	@Override
	@Transactional
	public void changePassword(String thePassword, String username) {

		User user = userDao.getUserByUserName(username);
		user.setPassword(passwordEncoder.encode(thePassword));

	}
}
