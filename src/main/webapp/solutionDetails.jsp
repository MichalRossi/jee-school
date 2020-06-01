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
    <th>Tytuł zadania</th>
    <th>Autor rozwiązania</th>
    <th>Id rozwiązania</th>
    <th>Data dodania</th>
    <th>Data aktualizacji</th>
    <th>Opis</th>
    </thead>

    <tbody>
        <tr>
            <td>${solution.exercise.title}</td>
            <td>${solution.user.username}</td>
            <td>${solution.id}</td>
            <td>${solution.created}</td>
            <td>${solution.updated}</td>
            <td>${solution.description}</td>
        </tr>
    </tbody>
</table>

<%@ include file="fragments/footer.jspf" %>
</body>
</html>
