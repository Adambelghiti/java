<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Article</title>
</head>
<body>
<h1>Edit Article</h1>
<form action="articles.do" method="post">
    <input type="hidden" name="action" value="update">
    <label for="articleId">Article ID:</label>
    <input type="text" id="articleId" name="articleId"><br><br>
    <label for="description">Description:</label>
    <input type="text" id="description" name="description"><br><br>
    <label for="quantity">Quantity:</label>
    <input type="text" id="quantity" name="quantity"><br><br>
    <label for="price">Price:</label>
    <input type="text" id="price" name="price"><br><br>
    <input type="submit" value="Update">
</form>
</body>
</html>