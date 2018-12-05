package pl.marcinmazur.portfolio.service;

public interface AdminService {

	boolean checkPassword(String thePassword, String username);

	void changePassword(String thePassword, String username);

}
