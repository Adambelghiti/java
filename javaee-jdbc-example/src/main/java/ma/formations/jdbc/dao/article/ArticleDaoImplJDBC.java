package ma.formations.jdbc.dao.article;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ma.formations.jdbc.dao.DatabaseManager;
import ma.formations.jdbc.service.model.Article;

public class ArticleDaoImplJDBC implements IArticleDao {
	private static long nextId = 100;
	@Override
	public List<Article> findAll() {
		List<Article> articles = new ArrayList<Article>();
		try {
			Connection connection = DatabaseManager.getInstance().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from article");
			while (rs.next()) {
				articles.add(new Article(rs.getLong("id"), rs.getString("description"), rs.getDouble("quantite"),
						rs.getDouble("price")));
			}
			rs.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
		return articles;
	}
	@Override
	public void save(Article article) {
		article.setId(nextId); // Set the id before saving
		nextId++;
		try {
			Connection connection = DatabaseManager.getInstance().getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"INSERT INTO article (id, description, quantite, price) VALUES (?, ?, ?, ?)"
			);

			pstmt.setLong(1, article.getId()); // Set the id value
			pstmt.setString(2, article.getDescription());
			pstmt.setDouble(3, article.getQuantite());
			pstmt.setDouble(4, article.getPrice());

			pstmt.executeUpdate();

			pstmt.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
	}
	@Override
	public void deleteById(Long articleId) {
		try {
			Connection connection = DatabaseManager.getInstance().getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"DELETE FROM article WHERE id = ?"
			);

			pstmt.setLong(1, articleId);
			pstmt.executeUpdate();

			pstmt.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
	}
	@Override
	public void update(Article article) {
		try {
			Connection connection = DatabaseManager.getInstance().getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"UPDATE article SET description = ?, quantite = ?, price = ? WHERE id = ?"
			);

			pstmt.setString(1, article.getDescription());
			pstmt.setDouble(2, article.getQuantite());
			pstmt.setDouble(3, article.getPrice());
			pstmt.setLong(4, article.getId());

			pstmt.executeUpdate();

			pstmt.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
	}
}
