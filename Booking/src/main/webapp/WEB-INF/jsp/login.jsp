<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>

<u:html title="Booking Web Application">
    <h1>Booking</h1>
    <c:if test="${not empty param.message}">
        <p style="color:red">${param.message}</p>
    </c:if>
    <c:url var="loginUrl" value="/login.html"/>
    <form action="${loginUrl}" method="post">
        Имя пользователя:<br>
        <label>
            <input type="text" name="login">
        </label><br>
        <br>
        Пароль:<br>
        <label>
            <input type="text" name="password">
        </label><br>
        <br>
        <button type="submit">Войти</button>
    </form>
</u:html>