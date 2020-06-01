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
    <th>Id uzytkownika</th>
    <th>Nazwa uzytkownika</th>
    <th>Adres e-mail</th>
    <th>Grupa użytkownika</th>
    <th>Rozwiązania użytkownika</th>

    </thead>

    <tbody>
    <c:forEach items="${users}" var="user">
    <tr>
        <td>${user.id}</td>
        <td>${user.username}</td>
        <td>${user.email}</td>
        <td>${user.userGroup.name}</td>
        <td><a href="/UserEdit?id=${user.id}">Edycja</a>    <a href="/UserDelete?id=${user.id}">Usuń</a></td>
        </c:forEach>
    </tbody>
</table>

<br>
<a href="/UserAdd">Dodaj użytkownika</a>
<%@ include file="fragments/footer.jspf" %>
</body>
</html>
