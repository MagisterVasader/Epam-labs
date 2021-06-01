<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag language="java" pageEncoding="UTF-8" %>
<%@attribute name="title" required="true" rtexprvalue="true" type="java.lang.String" %>

<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
    <meta charset="UTF-8">
    <title>${title}</title>
</head>
<body>

<style>
    .navbar-nav {
        display: flex;
        flex-direction: row;
        width: 100vw;
        justify-content: space-around;
    }

    h1 {
        color: aliceblue;
        text-align: center;
        font-family: Roboto, serif;
    }
    body {
        height: 100vh;
        background: rgb(121,218,255);
        background: radial-gradient(circle, rgba(121,218,255,0.9023984593837535) 0%, rgba(8,59,69,1) 100%);
    }
    label {
        color: aliceblue;
    }
    th{
        text-align: center;
        color: aliceblue;
    }
    td {
        color: aliceblue;
        text-align: center;
    }
    a{
        color: aliceblue;
        text-decoration: none;
    }
    a:hover{
        color: #97ffff;
    }
</style>

<c:choose>
    <c:when test="${session_user.role.name == 'ADMIN'}">
        <h1>Booking</h1>
        <c:url var="logoutUrl" value="/logout.html"/>
        <c:url var="userListUrl" value="/user/list.html"/>
        <c:url var="billListUrl" value="/bill/list.html"/>
        <c:url var="orderListUrl" value="/order/list.html"/>
        <c:url var="roomListUrl" value="/room/list.html"/>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a class="nav-link" href="${userListUrl}">Пользователи</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${billListUrl}">Выставленные счета</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${orderListUrl}">Ордера</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${roomListUrl}">Комнаты</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${logoutUrl}">Выйти</a>
                    </li>
                </ul>
            </div>
        </nav>
    </c:when>
    <c:when test="${session_user.role.name == 'CLIENT'}">
        <h1>Booking</h1>
        <c:url var="logoutUrl" value="/logout.html"/>
        <c:url var="orderListUrl" value="/order/list.html"/>
        <c:url var="roomListUrl" value="/room/list.html"/>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="${orderListUrl}">Ордера</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${roomListUrl}">Комнаты</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${logoutUrl}">Выйти</a>
                    </li>
                </ul>
            </div>
        </nav>
    </c:when>
</c:choose>
<jsp:doBody/>
</body>
</html>
