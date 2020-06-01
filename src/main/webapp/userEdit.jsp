<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="fragments/header.jspf" %>



<form action="/UserEdit" method="post">
    Nazwa użytkownika:<br>
    <input type="text" name="userName"><br>
    Adres e-mail użytkownika:<br>
    <input type="text" name="userEmail"><br>
    Hasło użytkownika:<br>
    <input type="text" name="userPassword"><br>
    Grupa użytkownika:<br>
    <input type="text" name="userGroup"><br>
    <input type="hidden" name="userId" value="${param.id}"><br>
    <button type="submit">Wyślij</button>
</form>


<%@ include file="fragments/footer.jspf" %>
</body>
</html>
