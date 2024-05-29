package ma.formations.jdbc.service;

import java.util.List;

import ma.formations.jdbc.dao.DaoImplJDBC;
import ma.formations.jdbc.dao.IDao;
import ma.formations.jdbc.dao.article.ArticleDaoImplJDBC;
import ma.formations.jdbc.dao.article.IArticleDao;
import ma.formations.jdbc.dao.user.IUserDao;
import ma.formations.jdbc.dao.user.UserDaoImplJDBC;
import ma.formations.jdbc.service.model.Article;
import ma.formations.jdbc.service.model.User;

public class ServiceImpl implements IService {
	private IDao dao = new DaoImplJDBC();
	private IArticleDao daoArticle = new ArticleDaoImplJDBC();
	private IUserDao userDao = new UserDaoImplJDBC();
	@Override
	public Boolean checkAccount(String username, String password) {
		User u = dao.getUserByUsername(username);
		if (u == null)
			return false;
		return (password.equals(u.getPassword()));
	}

	@Override
	public List<Article> getAllArticle() {
		return daoArticle.findAll();
	}


	public void addArticle(Article article) {
		daoArticle.save(article);
	}

	@Override
	public void deleteArticle(Long articleId) {
		daoArticle.deleteById(articleId);
	}
	@Override
	public void updateArticle(Article article) {
		daoArticle.update(article);
	}

	public void addUser(User user) {
		userDao.addUser(user);
	}

	@Override
	public List<User> getAllUsers() {return userDao.getAllUsers();
	}
	public void updatePassword(int userId, String newPassword){
		userDao.updatePassword(userId, newPassword);
	}
}
