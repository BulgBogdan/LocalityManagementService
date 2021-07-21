<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <title>Title</title>
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
</head>
<body>
<%@include file="..//include/header.jsp" %>
<div>
    <div class="container">
        <div id="singUp-row" class="row justify-content-center align-items-center">
            <div id="singUp-column" class="col-md-6">
                <div id="singUp-box" class="col-md-12">

                    <form method="POST" action="infrastructure">
                        <h3 class="text-center text-info"><fmt:message key="label.infrastructure"/></h3>
                        <div class="form-group">
                            <label for="name" class="text-info"><fmt:message key="label.title"/>:</label><br>
                            <input type="text" name="name" id="name"
                                   value="${name}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="square" class="text-info"><fmt:message key="label.square"/>(м):</label><br>
                            <input type="number" name="square" id="square"
                                   value="${square}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="floors" class="text-info"><fmt:message key="label.floors"/>:</label><br>
                            <input type="number" name="floors" id="floors"
                                   value="${floors}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="persons" class="text-info"><fmt:message key="label.persons"/>:</label><br>
                            <input type="number" name="persons" id="persons"
                                   value="${persons}" class="form-control">
                        </div>

                        <p style="color: red">${error}</p>
                        <div class="form-group">
                            <input type="submit" name="submit" class="btn btn-info btn-md"
                                   value="<fmt:message key="label.create"/>">
                        </div>

                        <div id="register-link" class="text-left">
                            <a href="/infrastructure?cityName=${cityName}"><fmt:message key="label.back"/></a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
