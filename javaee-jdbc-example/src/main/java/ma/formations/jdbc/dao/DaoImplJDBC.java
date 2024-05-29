package ma.formations.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ma.formations.jdbc.service.model.User;
import org.mindrot.jbcrypt.BCrypt;

public class DaoImplJDBC implements IDao {

	@Override
	public List<User> findAllUsers() {
		List<User> users=new ArrayList<>();
		try {
			Connection connection=DatabaseManager.getInstance().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from user");
			while (rs.next()) {
				users.add(new User(rs.getLong("id"), rs.getString("username"), rs.getString("password")));
			}
			rs.close();
			stmt.close();
			 connection.close();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
		return users;
	}

	@Override
	public User getUserByUsername(String username) {
		User user = null;
		try {
			Connection connection=DatabaseManager.getInstance().getConnection();
			PreparedStatement stmt = connection.prepareStatement("select * from user where username=?");
			stmt.setString(1, username);
			ResultSet resultats = stmt.executeQuery();

			while (resultats.next()) {
				user = new User(resultats.getLong("id"), resultats.getString("username"), resultats.getString("password"));
			}
			resultats.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
		return user;
	}

	public void addUser(User user) {
		try {
			Connection connection = DatabaseManager.getInstance().getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"INSERT INTO users (username, password) VALUES (?, ?)"
			);

			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());

			pstmt.executeUpdate();

			pstmt.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
	}
	public void updatePassword(int userId, String newPassword) {
		try {
			Connection connection = DatabaseManager.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement("UPDATE user SET password = ? WHERE id = ?");

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

			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
	}
}
