<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Add New Article</title>
    <link href="webjars/bootstrap/4.6.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@include file="common/header.jsp" %>

<div class="container">
    <h2>Add New Article</h2>
    <form method="post" action="articles.do" class="needs-validation" novalidate>
        <div class="form-group">
            <label for="description">Description:</label>
            <input type="text" class="form-control" id="description" name="description" required>
            <div class="invalid-feedback">Please enter a description.</div>
        </div>
        <div class="form-group">
            <label for="quantity">Quantity:</label>
            <input type="number" class="form-control" id="quantity" name="quantity" required>
            <div class="invalid-feedback">Please enter a quantity.</div>
        </div>
        <div class="form-group">
            <label for="price">Price:</label>
            <input type="number" step="0.01" class="form-control" id="price" name="price" required>
            <div class="invalid-feedback">Please enter a price.</div>
        </div>
        <button type="submit" class="btn btn-primary">Add Article</button>
    </form>
</div>

<%@include file="common/footer.jsp" %>
</body>
</html>