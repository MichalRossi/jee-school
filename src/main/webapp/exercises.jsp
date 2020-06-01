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
    <th>Id zadania</th>
    <th>Tytuł</th>
    <th>Opis</th>
    </thead>

    <tbody>
    <c:forEach items="${exercises}" var="exercise">
        <tr>
            <td>${exercise.id}</td>
            <td>${exercise.title}</td>
            <td>${exercise.description}</td>
            <td>
                <a href="/ExerciseEdit?id=${exercise.id}">Edycja</a>
                <a href="/ExerciseDelete?id=${exercise.id}">Usuń</a>
            </td>
    </c:forEach>
    </tbody>
</table>

<br>
<a href="/ExerciseAdd">Dodanie zadania</a>

<%@ include file="fragments/footer.jspf" %>
</body>
</html>
