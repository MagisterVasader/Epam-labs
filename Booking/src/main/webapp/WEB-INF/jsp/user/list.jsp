<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>

<u:html title="Список пользователей">
        <h1>Booking</h1>
        <table>
            <caption>Список пользователей</caption>
            <tr>
                <th scope="col">Логин</th>
                <th scope="col">Роль</th>
            </tr>
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
</u:html>
