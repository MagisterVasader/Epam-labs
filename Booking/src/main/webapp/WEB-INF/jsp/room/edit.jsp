<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>

<c:choose>
    <c:when test="${not empty room}">
        <c:set var="title" value="Редактирование комнаты ${room.id}"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Добавление комнаты"/>
    </c:otherwise>
</c:choose>

<u:html title="${title}">
    <h1>Booking</h1>
    <h2>${title}</h2>
    <c:url var="roomSaveUrl" value="/room/save.html"/>
    <form action="${roomSaveUrl}" method="post">
        <c:if test="${not empty room.id}">
            <input type="hidden" name="id" value="${room.id}">
        </c:if>
        Комфортность:
        <select name="comfort">
            <c:forEach var="comfort" items="${comforts}">
                <c:choose>
                    <c:when test="${comfort == room.comfort}">
                        <c:set var="selected" value="selected"/>
                    </c:when>
                    <c:otherwise>
                        <c:remove var="selected"/>
                    </c:otherwise>
                </c:choose>
                <option value="${comfort}" ${selected}>${comfort.name}</option>
            </c:forEach>
        </select>
        <br>
        Цена за 1 ночь:
        <label>
            <input type="text" name="price" value="${room.price}">
        </label>
        <br>
        Свободность:<br>
        <select name="free">
            <c:choose>
                <c:when test="${true == room.free}">
                    <c:set var="selected" value="selected"/>
                </c:when>
            </c:choose>
            <option value="${true}" ${selected}>true</option>
            <c:remove var="selected"/>
            <c:choose>
                <c:when test="${false == room.free}">
                    <c:set var="selected" value="selected"/>
                </c:when>
            </c:choose>
            <option value="${false}" ${selected}>false</option>
            <c:remove var="selected"/>
        </select>
        <br>
        Вместительность:
        <label>
            <input type="text" name="capacity" value="${room.capacity}">
        </label><br>
        <br>
        <button type="submit">Сохранить</button>
        <c:if test="${empty room.id}">
            <c:set var="disabled" value="disabled"/>
        </c:if>
        <button formaction="delete.html" formmethod="post" ${disabled}>Удалить</button>
    </form>
</u:html>
