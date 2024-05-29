package ma.formations.jdbc.dao.user;

import ma.formations.jdbc.dao.DatabaseManager;
import ma.formations.jdbc.service.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImplJDBC implements IUserDao {
    // Existing code...
    private static final String SELECT_ALL_USERS_QUERY = "SELECT * FROM user";
    private static final String INSERT_USER_QUERY = "INSERT INTO user (username, password) VALUES (?, ?)";
    private static final String UPDATE_PASSWORD_QUERY = "UPDATE user SET password = ? WHERE id = ?";

    public void addUser(User user) {
        try (Connection connection = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(INSERT_USER_QUERY)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Connection connection = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_USERS_QUERY);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                userList.add(new User(rs.getLong("id"), rs.getString("username"), rs.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public void updatePassword(int userId, String newPassword) {
        try (Connection connection = DatabaseManager.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PASSWORD_QUERY)) {

            // Hash the new password
            String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

            // Set the new hashed password in the statement
            statement.setString(1, hashedPassword);
            statement.setInt(2, userId);

            // Execute the update statement
            int rowsAffected = statement.executeUpdate();

            // Check if the password was updated successfully
            if (rowsAffected > 0) {
                System.out.println("Password updated successfully for user with ID: " + userId);
            } else {
                System.out.println("Failed to update password for user with ID: " + userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Implement other methods for user-related operations
}