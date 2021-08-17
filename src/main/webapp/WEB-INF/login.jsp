<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous"/>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous"/>
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
    <script>
        function password_show_hide() {
            var x = document.getElementById("password");
            var show_eye = document.getElementById("show_eye");
            var hide_eye = document.getElementById("hide_eye");
            hide_eye.classList.remove("d-none");
            if (x.type === "password") {
                x.type = "text";
                show_eye.style.display = "none";
                hide_eye.style.display = "block";
            } else {
                x.type = "password";
                show_eye.style.display = "block";
                hide_eye.style.display = "none";
            }
        }
    </script>
</head>
<body>
<div id="login">
    <h3 class="text-center text-white pt-5"></h3>
    <div class="container">
        <div id="login-row" class="row justify-content-center align-items-center">
            <div id="login-column" class="col-md-6">
                <div id="login-box" class="col-md-12">
                    <h4 class="text-center text-info"><fmt:message key="label.authorization"/></h4>
                    <form id="login-form" class="form" method="post" action="login">
                        <div class="form-row">
                            <div class="col-12">
                                <label for="username" class="text-info"><fmt:message key="label.login"/>:</label><br>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i
                                                class="fas fa-user"></i></span>
                                    </div>
                                    <input name="check_username" type="text" value="${login}"
                                           class="input form-control" id="username" placeholder="username"
                                           aria-label="Username" aria-describedby="basic-addon1"/>
                                </div>
                            </div>
                            <div class="col-12">
                                <label for="password" class="text-info"><fmt:message key="label.password"/>:</label><br>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon2"><i
                                                class="fas fa-lock"></i></span>
                                    </div>
                                    <input name="check_password" type="password" value="${pass}"
                                           class="input form-control" id="password" placeholder="password"
                                           required="true" aria-label="password" aria-describedby="basic-addon1"/>
                                    <div class="input-group-append">
                <span class="input-group-text" onclick="password_show_hide();">
                  <i class="fas fa-eye" id="show_eye"></i>
                  <i class="fas fa-eye-slash d-none" id="hide_eye"></i>
                </span>
                                    </div>
                                </div>
                            </div>
                            <%--<div class="col-6">--%>
                            <%--<div class="form-group form-check text-left">--%>
                            <%--<input type="checkbox" name="remember" class="form-check-input" id="remember_me" />--%>
                            <%--<label class="form-check-label" for="remember_me">Remember me</label>--%>
                            <%--</div>--%>
                            <%--</div>--%>
                            <div class="col-sm-12 pt-3 text-right">
                                <p><a href="registration"><fmt:message key="label.registration"/></a></p>
                            </div>
                            <div class="col-12">
                                <p style="color: red">${Error}</p>
                                <button class="btn btn-primary" type="submit" name="signin"><fmt:message
                                        key="label.login"/></button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
