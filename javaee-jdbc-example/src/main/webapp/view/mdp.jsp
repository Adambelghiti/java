<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add or Update User</title>
    <link href="webjars/bootstrap/4.6.1/css/bootstrap.min.css" rel="stylesheet">
    <script src="webjars/jquery/3.6.0/jquery.min.js"></script>
    <script src="webjars/bootstrap/4.6.1/js/bootstrap.min.js"></script>
</head>
<body>
<%@include file="common/header.jsp"%>

<h1>Modify Password</h1>

<form action="users.do" method="post">
    <div class="form-group">
        <label for="userId">User ID:</label>
        <input type="text" class="form-control" id="userId" name="userId">
    </div>
    <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" class="form-control" id="password" name="password" required>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>

<%@include file="common/footer.jsp"%>
</body>
</html>
