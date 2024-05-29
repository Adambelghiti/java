<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Article</title>
</head>
<body>
<h1>Delete Article</h1>
<form action="articles.do" method="post">
    <input type="hidden" name="action" value="delete">
    <label for="articleId">Article ID:</label>
    <input type="text" id="articleId" name="articleId"><br><br>
    <input type="submit" value="Delete">
</form>
</body>
</html>