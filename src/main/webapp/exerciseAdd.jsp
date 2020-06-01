<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="fragments/header.jspf" %>

<table border="1">
    <thead>
    <th>Id grupy</th>
    <th>Nazwa grupy</th>
    </thead>

    <tbody>
    <c:forEach items="${exercises}" var="exercise">
    <tr>
        <td>${exercise.id}</td>
        <td>${exercise.title}</td>
        <td>${exercise.description}</td>

        </c:forEach>
    </tbody>
</table>

<br>

<form action="/ExerciseAdd" method="post">
    Tytuł zadania:<br>
    <input type="text" name="exerciseTitle"><br>
    Opis zadania:<br>
    <input type="text" name="exerciseDescription"><br>
    <button type="submit">Wyślij</button>
</form>


<%@ include file="fragments/footer.jspf" %>
</body>
</html>
