<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>

<u:html title="Список комнат">
    <h1>Booking</h1>
    <table>
        <caption>Список доступных комнат</caption>
        <tr>
            <th scope="col">Номер комнаты</th>
            <th scope="col">Комфортность</th>
            <th scope="col">Цена</th>
            <th scope="col">Занятость</th>
            <th scope="col">Вместительность</th>
        </tr>
        <c:forEach var="room" items="${rooms}">
            <c:url var="roomEditUrl" value="/room/edit.html">
                <c:param name="id" value="${room.id}"/>
            </c:url>
            <tr>
                <c:choose>
                    <c:when test="${session_user.role.name == 'ADMIN'}">
                        <td><a href="${roomEditUrl}">${room.id}</a></td>
                    </c:when>
                    <c:otherwise>
                        <td>${room.id}</td>
                    </c:otherwise>
                </c:choose>
                <td>${room.comfort.name}</td>
                <td>${room.price}</td>
                <td>${room.free}</td>
                <td>${room.capacity}</td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <c:choose>
        <c:when test="${session_user.role.name == 'ADMIN'}">
            <c:url var="roomEditUrl" value="/room/edit.html"/>
            <a href="${roomEditUrl}">Добавить новую комнату</a>
        </c:when>
    </c:choose>
</u:html>
