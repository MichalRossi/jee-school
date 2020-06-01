<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="fragments/header.jspf" %>



<form action="/ExerciseEdit" method="post">
    Tytuł zadania:<br>
    <input type="text" name="title"><br>
    <textarea rows="5" cols="80" name="description"></textarea><br><br>
    <input type="hidden" name="exerciseId" value="${param.id}"><br>
    <button type="submit">Wyślij</button>
</form>


<%@ include file="fragments/footer.jspf" %>
</body>
</html>
