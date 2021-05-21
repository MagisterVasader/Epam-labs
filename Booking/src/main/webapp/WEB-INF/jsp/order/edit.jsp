<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>

<c:choose>
    <c:when test="${not empty order}">
        <c:set var="title" value="Редактирование брони ${order.id}"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Создание брони"/>
    </c:otherwise>
</c:choose>
<u:html title="${title}">
    <h1>Booking</h1>
    <h2>${title}</h2>
    <c:url var="orderSaveUrl" value="/order/save.html"/>
    <form action="${orderSaveUrl}" method="post">
        <c:if test="${not empty order}">
            <input type="hidden" name="id" value="${order.id}">
        </c:if>
        Номер комнаты:<br>
        <select name="room">
            <c:forEach var="room" items="${rooms}">
                <c:choose>
                    <c:when test="${room.id == order.room.id}">
                        <c:set var="selected" value="selected"/>
                    </c:when>
                    <c:otherwise>
                        <c:remove var="selected"/>
                    </c:otherwise>
                </c:choose>
                <option value="${room.id}" ${selected}>${room.id}</option>
            </c:forEach>
        </select><br>
        Заселение:<br>
        <label>
            <input type="text" name="durationStart" value="${order.durationStart}">
        </label><br>
        Выселение:<br>
        <label>
            <input type="text" name="durationEnd" value="${order.durationEnd}">
        </label><br>
        Пользователь:<br>
        <c:choose>
            <c:when test="${session_user.role.name == 'ADMIN'}">
                <select name="user">
                    <c:forEach var="user" items="${users}">
                        <c:choose>
                            <c:when test="${user.id == order.user.id}">
                                <c:set var="selected" value="selected"/>
                            </c:when>
                            <c:otherwise>
                                <c:remove var="selected"/>
                            </c:otherwise>
                        </c:choose>
                        <option value="${user.id}" ${selected}>${user.id}</option>
                    </c:forEach>
                </select><br>
            </c:when>
            <c:otherwise>
                <select name="user">
                    <option value="${session_user.id}">Вы</option>
                </select><br>
            </c:otherwise>
        </c:choose>
        <button type="submit">Сохранить</button>
        <c:if test="${empty order.id}">
            <c:set var="disabled" value="disabled"/>
        </c:if>
        <button formaction="delete.html" formmethod="post" ${disabled}>Удалить</button>
    </form>
</u:html>
