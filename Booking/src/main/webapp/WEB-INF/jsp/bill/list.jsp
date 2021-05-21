<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>

<u:html title="Список счетов">
    <h1>Booking</h1>
    <table>
        <caption>Список счетов</caption>
        <tr>
            <th scope="col">Номер</th>
            <th scope="col">Общая стоимость</th>
        </tr>
        <c:forEach var="bill" items="${bills}">
            <c:url var="billEditUrl" value="/bill/edit.html">
                <c:param name="id" value="${bill.id}"/>
            </c:url>
            <tr>
                <td><a href="${billEditUrl}">${bill.id}</a></td>
                <td>${bill.totalPrice}</td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <c:url var="billEditUrl" value="/bill/edit.html"/>
    <a href="${billEditUrl}">Добавить новый счет</a>
</u:html>
