package ma.formations.jdbc.dao.user;

import ma.formations.jdbc.service.model.User;

import java.util.List;

public interface IUserDao {
    void addUser(User user);

    List<User> getAllUsers();
    // Add other methods for user-related operations
    void updatePassword(int userId, String newPassword);
}