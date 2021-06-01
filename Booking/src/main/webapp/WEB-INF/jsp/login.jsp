<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>

<u:html title="Booking Web Application">
    <c:url var="loginUrl" value="/login.html"/>
    <form action="${loginUrl}" method="post" style="width: 25rem;height: 100vh;margin: auto auto;display: flex;flex-direction: column;justify-content: center;">
            <h1 style="text-align: center; font-family: Roboto,serif;">Booking</h1>
            <div class="form-group">
                <label for="login">Login</label>
                <input type="text" class="form-control" name="login" id="login" aria-describedby="loginHelp" placeholder="Enter Login">
                <small id="loginHelp" class="form-text text-muted">We'll never share your login with anyone else.</small>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="text" class="form-control" name="password" id="password" placeholder="Password">
            </div>
            <br>
            <button type="submit" class="btn btn-primary">Войти</button>
    </form>
</u:html>