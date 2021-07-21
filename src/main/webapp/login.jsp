<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="utf-8">
    <title>Login</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"
            integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s"
            crossorigin="anonymous"></script>
    <script src="C:/Projects/examples/ProjectCar/src/main/webapp/res/js/hidden_eye.js"></script>
    <style>body {
        background-color: #f6fcff
    }</style>
</head>

<body>
<div id="login">
    <h3 class="text-center text-white pt-5"></h3>
    <div class="container">
        <div id="login-row" class="row justify-content-center align-items-center">
            <div id="login-column" class="col-md-6">
                <div id="login-box" class="col-md-12">

                    <form id="login-form" class="form" method="post" action="login">
                        <h3 class="text-center text-info"><fmt:message key="label.authorization"/></h3>
                        <div class="form-group">
                            <label for="username" class="text-info"><fmt:message key="label.login"/>:</label><br>
                            <input type="text" name="check_username" id="username" class="form-control"
                                   value="${login}">
                        </div>
                        <div class="password">
                            <label for="password" class="text-info"><fmt:message key="label.password"/>:</label><br>
                            <input type="password" name="check_password" id="password" class="form-control"
                                   value="${pass}">
                            <a href="#" class="password-control" onclick="return show_hide_password(this);"></a>
                        </div>
                        <div class="form-group">
                            <p style="color: red">${Error}</p>
                            <%--<label for="remember-me" class="text-info"><span>Запомнить</span>--%>
                            <%--<span><input id="remember-me" name="remember_me" type="checkbox"></span>--%>
                            <%--</label><br>--%>
                            <input type="submit" name="submit" class="btn btn-info btn-md"
                                   value="<fmt:message key="label.input"/>">
                        </div>
                        <div id="register-link" class="text-right">
                            <a href="registration" class="text-info"><fmt:message key="label.registration"/></a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
