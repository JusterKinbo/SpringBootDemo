package demo.juster.spboot.service.interfaces;

import java.util.List;

import demo.juster.spboot.pojo.user.User;

public interface IUserService {
	
	void saveUser(User u);
	
	void deleteUser(User u);
	void deleteUserById(Long id);
	
	void updateUser(User u);
	
	User findUserById(Long id);
	User findUserByName(String name);
	
	List<User> findAllUsers();
}
