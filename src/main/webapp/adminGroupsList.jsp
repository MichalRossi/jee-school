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
    <th>Akcja</th>
    </thead>

    <tbody>
    <c:forEach items="${userGroups}" var="group">
        <tr>
            <td>${group.id}</td>
            <td>${group.name}</td>
            <td><a href="/GroupEdit?id=${group.id}">Edycja</a>    <a href="/GroupDelete?id=${group.id}">Usuń</a></td>


        </c:forEach>
    </tbody>
</table>
<br>
<a href="/GroupAdd">Dodanie grupy</a>
<%@ include file="fragments/footer.jspf" %>
</body>
</html>
