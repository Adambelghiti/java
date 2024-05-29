package ma.formations.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnectionTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/exemple_db?serverTimezone=UTC";
        String username = "adam";
        String password = "adam";
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);

            String insertUserQuery = "INSERT INTO user (username, password) VALUES (?, ?)";
            pstmt = connection.prepareStatement(insertUserQuery);
            pstmt.setString(1, "testuser");
            pstmt.setString(2, "testpass");

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User added successfully!");
            } else {
                System.out.println("No rows affected.");
            }

            connection.commit();
            System.out.println("Transaction committed successfully");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();
                    System.out.println("Transaction rolled back due to error");
                }
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
