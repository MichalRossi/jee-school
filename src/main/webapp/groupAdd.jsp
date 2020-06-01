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
    <c:forEach items="${userGroups}" var="group">
    <tr>
        <td>${group.id}</td>
        <td>${group.name}</td>

        </c:forEach>
    </tbody>
</table>

<br>

<form action="/GroupAdd" method="get">
    Nazwa grupy:<br>
    <input type="text" name="groupName"><br>
    <button type="submit">Wyślij</button>
</form>


<%@ include file="fragments/footer.jspf" %>
</body>
</html>
