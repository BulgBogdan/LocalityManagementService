<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="en"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <meta charset="utf-8">
    <title>Language</title>
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
<div class="text-center">
    <h1 class="text-centre">
        <fmt:message key="label.chooseLanguage"/>
    </h1>
</div>
<br>
<div class="container">
    <div class="text-center">
        <a class="btn btn-primary btn-xs pull-right" href="/login?sessionLocale=en"><fmt:message
                key="label.lang.en"/></a>
    </div>
    <br>
    <div class="text-center">
        <a class="btn btn-primary btn-xs pull-right" href="/login?sessionLocale=ru"><fmt:message
                key="label.lang.ru"/></a>
    </div>
</div>
</body>
</html>
