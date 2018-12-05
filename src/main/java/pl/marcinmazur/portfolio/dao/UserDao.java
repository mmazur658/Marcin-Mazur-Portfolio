package pl.marcinmazur.portfolio.dao;

import pl.marcinmazur.portfolio.entity.User;

public interface UserDao {
	
	User getUserByUserName(String userName);

}
