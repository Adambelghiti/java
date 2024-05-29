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
import ma.formations.jdbc.service.model.User;

@WebServlet("/users.do")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IService service = new ServiceImpl();


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> userList = service.getAllUsers();
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("/view/users.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve user data from request parameters
        String userIdString = request.getParameter("userId");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Check if userId is provided
        if (userIdString == null || userIdString.isEmpty()) {
            // Adding a new user
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);

            // Add the user using the ServiceImpl
            service.addUser(newUser);
        } else {
            // Updating an existing user
            int userId = Integer.parseInt(userIdString);
            service.updatePassword(userId, password);
        }

        // Retrieve all users
        List<User> userList = service.getAllUsers();

        // Store the list of users in a request attribute
        request.setAttribute("userList", userList);

        // Forward the request to users.jsp to display the updated list of users
        request.getRequestDispatcher("/view/users.jsp").forward(request, response);
    }




    // ...
}