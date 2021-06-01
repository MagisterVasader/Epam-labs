<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>

<u:html title="Список пользователей">
    <div style="width: 50vw;height: 50vh;margin-top: 15vh; margin-left: 25vw;border: aliceblue;">
        <table class="table table-bordered" style="table-layout: fixed; border: inset aliceblue;">
            <thead>
            <tr>
                <th scope="col">Логин</th>
                <th scope="col">Роль</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}">
                <c:url var="userEditUrl" value="/user/edit.html">
                    <c:param name="id" value="${user.id}"/>
                </c:url>
                <tr>
                    <td><a href="${userEditUrl}">${user.login}</a></td>
                    <td>${user.role.name}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <br>
        <c:url var="userEditUrl" value="/user/edit.html"/>
        <a href="${userEditUrl}" class="btn btn-primary" role="button">Добавить нового
            пользователя</a>
    </div>
</u:html>
