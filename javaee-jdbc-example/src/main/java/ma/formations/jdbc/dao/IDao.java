package ma.formations.jdbc.dao;

import java.util.List;

import ma.formations.jdbc.service.model.User;

public interface IDao {
	List<User> findAllUsers();
	User getUserByUsername(String username);
	void addUser(User user);
	void updatePassword(int userId, String newPassword);
}
