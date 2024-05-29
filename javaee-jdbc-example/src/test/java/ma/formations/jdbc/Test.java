package ma.formations.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import ma.formations.jdbc.dao.DatabaseManager;
import ma.formations.jdbc.service.model.Article;
import ma.formations.jdbc.service.model.User;

public class Test {
	public static void main(String[] args) {
		initTableUsers(Arrays.asList(new User(1l, "user01", "user01"), new User(2l, "user02", "user02"),
				new User(3l, "user03", "user03"), new User(4l, "user04", "user04")));
		initTableArticles(Arrays.asList(new Article(1l, "PC PRTABLE HP", 30D, 15000d),
				new Article(2l, "PC PRTABLE DELL", 20D, 12000d), new Article(3l, "TV LG", 100d, 7500d),
				new Article(4l, "TV SONY", 10d, 8500d), new Article(5l, "CAMERA SONY", 12d, 2500d),
				new Article(6l, "DD 100G", 27D, 500D)));
	}
	
	private static void initTableUsers(List<User> users) {
		try {
			Connection connection=DatabaseManager.getInstance().getConnection();
			connection.setAutoCommit(false);
			Statement statment = connection.createStatement();
			users.forEach(user -> {
				try {
					statment.addBatch("insert into user(id,username,password) values('" + user.getId() + "','"
							+ user.getUsername() + "','" + user.getPassword() + "')");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
			statment.executeBatch();
			connection.commit();
			connection.close();
			System.out.println("la liste des utilisateurs a été créee avec succés");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void initTableArticles(List<Article> articles) {
		try {
			Connection connection=DatabaseManager.getInstance().getConnection();
			connection.setAutoCommit(false);
			Statement statment = connection.createStatement();
			articles.forEach(article -> {
				try {
					statment.addBatch("insert into article(id,description,quantite,price) values('" + article.getId()
							+ "','" + article.getDescription() + "','" + article.getQuantite() + "','"
							+ article.getPrice() + "')");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
			statment.executeBatch();
			connection.commit();
			connection.close();
			System.out.println("la liste des articles a été créee avec succés");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
