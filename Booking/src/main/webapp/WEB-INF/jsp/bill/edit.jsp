<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>

<c:choose>
    <c:when test="${not empty bill}">
        <c:set var="title" value="Редактирование счета ${bill.id}"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Создание счета"/>
    </c:otherwise>
</c:choose>

<u:html title="${title}">
    <h1>Booking</h1>
    <h2>${title}</h2>
    <c:url var="billSaveUrl" value="/bill/save.html"/>
    <form action="${billSaveUrl}" method="post">
        <c:choose>
            <c:when test="${not empty bill}">
                <input type="hidden" name="id" value="${bill.id}">
            </c:when>
            <c:otherwise>
                Номер брони:<br>
                <select name="id">
                    <c:forEach var="order" items="${orders}">
                        <option value="${order.id}">${order.id}</option>
                    </c:forEach>
                </select>
                <input type="hidden" name="create" value="create">
            </c:otherwise>
        </c:choose>
        <br>
        Общая стоимость:<br>
        <label>
            <input type="text" name="totalPrice" value="${bill.totalPrice}">
        </label><br>
        <br>

        <button type="submit">Сохранить</button>
        <c:if test="${empty bill.id}">
            <c:set var="disabled" value="disabled"/>
        </c:if>
        <button formaction="delete.html" formmethod="post" ${disabled}>Удалить</button>
    </form>
</u:html>
