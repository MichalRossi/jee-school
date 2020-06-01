<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="fragments/header.jspf" %>

<form action="/GroupEdit" method="post">
    Nazwa grupy:<br>
    <input type="text" name="groupName"><br>
    <input type="hidden" name="groupId" value="${param.id}"><br>
    <button type="submit">Wyślij</button>
</form>


<%@ include file="fragments/footer.jspf" %>
</body>
</html>
