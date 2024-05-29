package ma.formations.jdbc.service;

import java.util.List;

import ma.formations.jdbc.service.model.Article;
import ma.formations.jdbc.service.model.User;

public interface IService {
	
	Boolean checkAccount(String username,String password);
	List<Article> getAllArticle();

	void addArticle(Article article);


	void deleteArticle(Long articleId);

	void updateArticle(Article updatedArticle);

	void addUser(User user);

	List<User> getAllUsers();

	void updatePassword(int userId, String newPassword);
}
