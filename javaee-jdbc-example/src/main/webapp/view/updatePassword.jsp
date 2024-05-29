<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.mindrot.jbcrypt.BCrypt" %>
<%@ page import="ma.formations.jdbc.dao.user.UserDaoImplJDBC" %>
<%@ page import="java.sql.SQLException" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Password</title>
</head>
<body>
<h1>Update Password</h1>

<%-- Retrieve form parameters --%>
<% String userIdParam = request.getParameter("userId"); %>
<% String newPasswordParam = request.getParameter("newPassword"); %>

<%-- Validate form parameters --%>
<%-- Make sure userIdParam and newPasswordParam are not null or empty --%>
<% if (userIdParam == null || userIdParam.isEmpty() || newPasswordParam == null || newPasswordParam.isEmpty()) { %>
<p>Please enter both the User ID and the New Password.</p>
<% } else { %>
<%-- Parse userIdParam as an integer --%>
<% int userId; %>
<% try { %>
<% userId = Integer.parseInt(userIdParam); %>

<%-- Update the password --%>
<% UserDaoImplJDBC userDao = new UserDaoImplJDBC(); %>
<% userDao.updatePassword(userId, newPasswordParam); %>
<p>Password updated successfully!</p>

<% } catch (NumberFormatException e) { %>
<p>User ID must be a valid integer.</p>
<% } %>
<% } %>

<%-- Provide a link to go back to the password update form --%>
<a href="mdp.jsp">Go Back</a>
</body>
</html>