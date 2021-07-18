<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Registration</title>
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
    <style>
        body {
            background-color: #f6fcff
        }
    </style>
</head>
<body>
<div>
    <div class="container">
        <div id="singUp-row" class="row justify-content-center align-items-center">
            <div id="singUp-column" class="col-md-6">
                <div id="singUp-box" class="col-md-12">

                    <form method="POST" action="registration">
                        <h3 class="text-center text-info">Регистарция</h3>
                        <div class="form-group">
                            <label for="login" class="text-info">Логин:</label><br>
                            <input type="text" name="login" id="login" class="form-control" value="${login}">
                            <p style="color: red">${loginError}</p>
                        </div>
                        <div class="form-group">
                            <label for="password" class="text-info">Пароль:</label><br>
                            <input type="password" name="password" id="password" class="form-control"
                                   value="${password}">
                        </div>
                        <div class="form-group">
                            <label for="confirmPassword" class="text-info">Подтверждение пароля:</label><br>
                            <input type="password" name="confirmPassword" id="confirmPassword" class="form-control">
                            <p style="color: red">${passwordError}</p>
                        </div>
                        <div class="form-group">
                            <label for="firstName" class="text-info">Имя:</label><br>
                            <input type="text" name="firstName" id="firstName" class="form-control"
                                   value="${firstName}">
                        </div>
                        <div class="form-group">
                            <label for="lastName" class="text-info">Фамилия:</label><br>
                            <input type="text" name="lastName" id="lastName" class="form-control" value="${lastName}">
                        </div>

                        <div class="form-group">
                            <input type="submit" name="submit" class="btn btn-info btn-md" value="Зарегестрировать">
                        </div>
                        <div id="register-link" class="text-right">
                            <a href="/login" class="text-info">На страницу авторизации</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>