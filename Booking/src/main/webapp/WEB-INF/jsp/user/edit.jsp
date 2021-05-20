<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>

<c:choose>
    <c:when test="${not empty user}">
        <c:set var="title" value="Редактирование пользователя ${user.login}"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Создание пользователя"/>
    </c:otherwise>
</c:choose>
<u:html title="${title}">
    <h1>Booking</h1>
    <h2>${title}</h2>
    <c:url var="userSaveUrl" value="/user/save.html"/>
    <form action="${userSaveUrl}" method="post">
        <c:if test="${not empty user}">
            <input type="hidden" name="id" value="${user.id}">
        </c:if>
        Имя пользователя:<br>
        <label>
            <input type="text" name="login" value="${user.login}">
        </label><br>
        <br>
        Пароль:<br>
        <label>
            <input type="text" name="password" value="${user.password}">
        </label><br>
        <br>
        Роль:<br>
        <select name="role">
            <c:forEach var="role" items="${roles}">
                <c:choose>
                    <c:when test="${role == user.role}">
                        <c:set var="selected" value="selected"/>
                    </c:when>
                    <c:otherwise>
                        <c:remove var="selected"/>
                    </c:otherwise>
                </c:choose>
                <option value="${role}" ${selected}>${role.name}</option>
            </c:forEach>
        </select>
        <br>
        <br>
        <button type="submit">Сохранить</button>
        <c:if test="${empty user.id}">
            <c:set var="disabled" value="disabled"/>
        </c:if>
        <button formaction="delete.html" formmethod="post" ${disabled}>Удалить</button>
    </form>
</u:html>
