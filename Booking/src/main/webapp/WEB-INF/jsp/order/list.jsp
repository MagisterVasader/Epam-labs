<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>

<u:html title="Список заказов">
    <h1>Booking</h1>
    <table>
        <caption>Список заказов</caption>
        <tr>
            <th scope="col">Номер заказа</th>
            <th scope="col">Номер комнаты</th>
            <th scope="col">Заселение</th>
            <th scope="col">Выселение</th>
            <c:choose>
                <c:when test="${session_user.role.name == 'ADMIN'}">
                    <th scope="col">Пользователь</th>
                </c:when>
            </c:choose>
        </tr>
        <c:forEach var="order" items="${orders}">
            <c:url var="orderEditUrl" value="/order/edit.html">
                <c:param name="id" value="${order.id}"/>
            </c:url>
            <tr>
                <td><a href="${orderEditUrl}">${order.id}</a></td>
                <td>${order.room.id}</td>
                <td>${order.durationStart}</td>
                <td>${order.durationEnd}</td>
                <c:choose>
                    <c:when test="${session_user.role.name == 'ADMIN'}">
                        <td>${order.user.id}</td>
                    </c:when>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
    <br>
    <c:url var="orderEditUrl" value="/order/edit.html"/>
    <a href="${orderEditUrl}">Добавить новый заказ</a>
</u:html>

