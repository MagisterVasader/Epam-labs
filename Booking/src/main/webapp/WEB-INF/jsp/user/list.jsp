<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Users list</title>
</head>
    <body>
        <h1>Booking</h1>
        <h2>Список пользователей</h2>
        <table>
            <tr><th>Логин</th><th>Роль</th></tr>
            <c:forEach var="user" items="${users}">
                <c:url var="userEditUrl" value="/user/edit.html">
                    <c:param name="id" value="${user.id}"/>
                </c:url>
                <tr>
                    <td><a href="${userEditUrl}">${user.login}</a></td>
                    <td>${user.role.name}</td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <c:url var="userEditUrl" value="/user/edit.html"/>
        <a href="${userEditUrl}">Добавить нового пользователя</a>
    </body>
</html>