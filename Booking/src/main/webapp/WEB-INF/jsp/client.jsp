<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>

<u:html title="Booking">
    <c:if test="${not empty session_user}">
        <c:url var="logoutUrl" value="logout.html"/>
        <p>
            <a href="${logoutUrl}">Выйти</a>
        </p>
    </c:if>
    <h1>Booking</h1>
    <c:url var="orderListUrl" value="/order/list.html"/>
    <c:url var="roomListUrl" value="/room/list.html"/>
    <a href="${orderListUrl}">Список броней</a><br>
    <a href="${roomListUrl}">Список комнат</a><br>
</u:html>