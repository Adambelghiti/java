package ma.formations.jdbc.presentation.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ma.formations.jdbc.service.IService;
import ma.formations.jdbc.service.ServiceImpl;
import ma.formations.jdbc.service.model.Article;

@WebServlet("/articles.do")
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IService service = new ServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Article> articles = service.getAllArticle();
		request.setAttribute("articles", articles);
		request.getRequestDispatcher("/view/welcome.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action != null && action.equals("delete")) {
			// Retrieve the article ID from the request parameters
			Long articleId = Long.parseLong(request.getParameter("articleId"));

			// Call the service method to delete the article
			service.deleteArticle(articleId);

			// Redirect the user to the updated article list
			response.sendRedirect(request.getContextPath() + "/articles.do");
		} else if (action != null && action.equals("update")) {
			// Retrieve the article ID from the request parameters
			Long articleId = Long.parseLong(request.getParameter("articleId"));
			String description = request.getParameter("description");
			String quantityStr = request.getParameter("quantity");
			String priceStr = request.getParameter("price");
			double quantity = Double.parseDouble(quantityStr);
			double price = Double.parseDouble(priceStr);
			Article updatedArticle = new Article(articleId,description,quantity,price);
			// Call the service method to delete the article
			service.updateArticle(updatedArticle);

			// Redirect the user to the updated article list
			response.sendRedirect(request.getContextPath() + "/articles.do");
		} else {
			// Retrieve the article data from the request parameters
			String description = request.getParameter("description");
			String quantityStr = request.getParameter("quantity");
			String priceStr = request.getParameter("price");

			// Parse the quantity and price values
			double quantity = Double.parseDouble(quantityStr);
			double price = Double.parseDouble(priceStr);

			// Create a new Article object
			Article newArticle = new Article(100L, description, quantity, price);

			// Call the service method to add the article
			service.addArticle(newArticle);

			// Redirect the user to the updated article list
			response.sendRedirect(request.getContextPath() + "/articles.do");
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Retrieve the article ID from the request parameters
		Long articleId = Long.parseLong(request.getParameter("articleId"));

		// Call the service method to delete the article
		service.deleteArticle(articleId);

		// Redirect the user to the updated article list
		response.sendRedirect(request.getContextPath() + "/articles.do");
	}
}