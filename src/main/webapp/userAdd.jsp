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

    </thead>

    <tbody>
    <c:forEach items="${users}" var="user">
    <tr>
        <td>${user.id}</td>
        <td>${user.username}</td>
        <td>${user.email}</td>
        <td>${user.userGroup.name}</td>
        </c:forEach>
    </tbody>
</table>

<br>

<c:if test="${groupExist=='false'}">
    Podaj nazwę istniejacej grupy!
</c:if>

<br>

<form action="/UserAdd" method="get">
    Nazwa użytkownika:<br>
    <input type="text" name="userName"><br>
    E-mail użytkownika:<br>
    <input type="text" name="userEmail"><br>
    Hasło użytkownika:<br>
    <input type="text" name="userPassword"><br>
    Grupa użytkownika:<br>
    <input type="text" name="userGroup"><br>
    <button type="submit">Wyślij</button>
</form>


<%@ include file="fragments/footer.jspf" %>
</body>
</html>
