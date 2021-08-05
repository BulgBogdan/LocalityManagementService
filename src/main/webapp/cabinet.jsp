<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <meta charset="utf-8">
    <title>Cabinet</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous"/>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous"/>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"
            integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s"
            crossorigin="anonymous"></script>
    <style>
        body {
            background-color: #f6fcff
        }
    </style>
    <script>
        function password_show_hide() {
            var x = document.getElementById("oldPassword");
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

        function password_show_hide2() {
            var x = document.getElementById("password");
            var show_eye = document.getElementById("show_eye2");
            var hide_eye = document.getElementById("hide_eye2");
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

        function password_show_hide3() {
            var x = document.getElementById("confirmPassword");
            var show_eye = document.getElementById("show_eye3");
            var hide_eye = document.getElementById("hide_eye3");
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
<%@include file="include/header.jsp" %>
<div>
    <h3 class="text-center text-white pt-5"></h3>
    <div class="container">
        <div id="login-row" class="row justify-content-center align-items-center">
            <div id="login-column" class="col-md-6">
                <div id="login-box" class="col-md-12">
                    <h3 class="text-center text-info"><fmt:message key="label.cabinet"/></h3>

                    <form method="POST" action="cabinet">
                        <div class="form-group">
                            <label for="oldLogin" class="text-info"><fmt:message key="label.login"/>:</label><br>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon1"><i class="fas fa-user"></i></span>
                                </div>
                                <input type="text" name="oldLogin" id="oldLogin" value="${user.getUsername()}"
                                       readonly="readonly" class="form-control" aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="oldPassword" class="text-info"><fmt:message key="label.password"/>:</label><br>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon2"><i class="fas fa-lock"></i></span>
                                </div>
                                <input type="password" name="oldPassword" id="oldPassword" class="form-control"
                                       value="${user.getPassword()}" aria-label="password"
                                       aria-describedby="basic-addon1">
                                <p style="color: red">${errorOldPass}</p>
                                <div class="input-group-append">
                <span class="input-group-text" onclick="password_show_hide();">
                  <i class="fas fa-eye" id="show_eye"></i>
                  <i class="fas fa-eye-slash d-none" id="hide_eye"></i>
                </span>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="password" class="text-info"><fmt:message key="label.newPassword"/>:</label><br>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon3"><i class="fas fa-lock"></i></span>
                                </div>
                                <input type="password" name="password" id="password" class="form-control"
                                       aria-label="password" aria-describedby="basic-addon1">
                                <div class="input-group-append">
                <span class="input-group-text" onclick="password_show_hide2();">
                  <i class="fas fa-eye" id="show_eye2"></i>
                  <i class="fas fa-eye-slash d-none" id="hide_eye2"></i>
                </span>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="confirmPassword" class="text-info">
                                <fmt:message key="label.confirmPassword"/>:</label><br>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon4"><i class="fas fa-lock"></i></span>
                                </div>
                                <input type="password" name="confirmPassword" id="confirmPassword" class="form-control"
                                       aria-label="password" aria-describedby="basic-addon1">
                                <p style="color: red">${passwordError}</p>
                                <div class="input-group-append">
                <span class="input-group-text" onclick="password_show_hide3();">
                  <i class="fas fa-eye" id="show_eye3"></i>
                  <i class="fas fa-eye-slash d-none" id="hide_eye3"></i>
                </span>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="firstName" class="text-info"><fmt:message key="label.firstname"/>:</label><br>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon5"><i class="fas fa-user"></i></span>
                                </div>
                                <input type="text" name="firstName" id="firstName" class="form-control"
                                       value="${user.getFirstName()}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lastName" class="text-info"><fmt:message key="label.lastname"/>:</label><br>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon6"><i class="fas fa-user"></i></span>
                                </div>
                                <input type="text" name="lastName" id="lastName" class="form-control"
                                       value="${user.getLastName()}">
                            </div>
                        </div>

                        <div class="form-group">
                            <p style="color: green">${confirmEdit}</p>
                            <input type="submit" name="submit" class="btn btn-info btn-md"
                                   value="<fmt:message key="label.edit"/>">
                        </div>

                        <div id="register-link" class="text-left">
                            <a href="#" onclick="history.back();"><fmt:message key="label.back"/></a>
                        </div>
                        <c:if test="${correctUsers == true}">
                            <div id="register-link" class="text-right">
                                <a href="/users" class="text-info"><fmt:message key="label.changeParameterUser"/></a>
                            </div>
                        </c:if>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
