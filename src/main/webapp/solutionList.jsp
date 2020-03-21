<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="fragments/header.jspf"%>

<a href="/SolutionAdd">Dodaj rozwiązanie</a><br><br>

<table border="1">
    <thead>
    <th>Tytuł zadania</th>
    <th>Autor rozwiązania</th>
    <th>Data dodania</th>
    <th>Akcje</th>
    </thead>
    <tbody>
    <c:forEach items="${solutions}" var="solution">
        <tr>
            <td>${solution.description}</td>
            <td>user</td>
            <td><fmt:formatDate value="${solution.created}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td>
                <a href="/SolutionUpdate?id=${solution.id}">Edytuj</a>
                <a href="/SolutionDelete?id=${solution.id}">Usuń</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%@ include file="fragments/footer.jspf"%>
</body>
</html>
