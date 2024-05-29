package ma.formations.jdbc.dao.article;

import java.util.List;

import ma.formations.jdbc.service.model.Article;

public interface IArticleDao {
	List<Article> findAll();
	void save(Article article);
	void deleteById(Long articleId);
	void update(Article article);
}

