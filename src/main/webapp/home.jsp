<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
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
<%@include file="include/header.jsp" %>
<%--<nav class="navbar navbar-expand-lg navbar-light bg-light">--%>

<%--<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02"--%>
<%--aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">--%>
<%--<span class="navbar-toggler-icon"></span>--%>
<%--</button>--%>


<%--<div class="collapse navbar-collapse" id="navbarTogglerDemo02">--%>
<%--<ul class="navbar-nav mr-auto mt-3 mt-lg-0">--%>
<%--<li class="nav-item">--%>
<%--<a class="nav-link" href="home">Главная</a>--%>
<%--</li>--%>
<%--<li class="nav-item">--%>
<%--<a class="nav-link" href="#"></a>--%>
<%--</li>--%>

<%--<li class="nav-item">--%>
<%--<a class="nav-link" href="#"></a>--%>
<%--</li>--%>
<%--<li class="nav-item">--%>
<%--<a class="nav-link" href="#"></a>--%>
<%--</li>--%>
<%--</ul>--%>
<%--<ul class="navbar-nav my-2 my-lg-0">--%>
<%--<li class="nav-item active">--%>
<%--<a class="nav-link" href="cabinet"><span class="sr-only">(current)</span>Личный кабинет</a>--%>
<%--</li>--%>
<%--<li class="nav-item active">--%>
<%--<a class="btn btn-sm btn-outline-danger" title="Выход из профиля"--%>
<%--href="logout">Выход</a>--%>
<%--</li>--%>
<%--</ul>--%>

<%--</div>--%>
<%--</nav>--%>

<main class="col offset-md-0 bg-faded py-0">
    <div class="col-md-5 text-center">
        <c:if test="${!isChairmen}">
            <h6>Выберите председателя:</h6>
            <form name="test" method="post" action="home">
                <select class="form-control mr-sm-0" id="chairmen" name="chairmen" onchange="this.form.submit()">
                    <c:forEach items="${chairmens}" var="chairmenName">
                        <option value="${chairmenName}"
                            ${chairmenName == nameChairmen ? 'selected="selected"' : ''}>${chairmenName}</option>
                    </c:forEach>
                </select>
            </form>
        </c:if>
    </div>
    <br>
    <div class="text-center">
        <h4>Выберите раздел:</h4>
    </div>
    <br>
    <div class="row">

        <div class="col-md-6">
            <div class="card border-info mx-auto mb-5" style="max-width: 350px; height: 350px">
                <div class="card-header text-center" style="background-color: #77a4ff">
                    <b>Населенные пункты</b>
                </div>
                <div class="card-body" style="background-color: #d9eeff">
                    <p class="card-text">В разделе отображена информация о населенных пунктах.</p>
                    <c:choose>
                        <c:when test="${isChairmen}">
                            <a href="/locality?nameChairmen=${nameChairmen}" class="btn btn-info btn-md">Перейти</a>
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${nameChairmen != null}">
                                    <a href="/locality?nameChairmen=${nameChairmen}"
                                       class="btn btn-info btn-md">Перейти</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="/locality?nameChairmen=${chairmens.get(0)}"
                                       class="btn btn-info btn-md">Перейти</a>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>
        </div>

        <div class="col-md-6">
            <div class="card mx-auto border-danger mb-5" style="max-width: 350px; height: 350px">
                <div class="card-header text-center" style="background-color: #77a4ff">
                    <b>Инфраструктура</b>
                </div>
                <div class="card-body" style="background-color: #d9eeff">
                    <c:choose>
                        <c:when test="${cities.isEmpty()}">
                            <p>У данного председателя нет населенных пунктов.</p>
                        </c:when>
                        <c:otherwise>
                            <p>Выберите населенный пункт:</p>
                            <form name="test" method="post" action="home">
                                <select class="form-control mr-sm-0" id="city" name="city"
                                        onchange="this.form.submit()">
                                    <c:forEach items="${cities}" var="cityName">
                                        <option value="${cityName}"
                                            ${cityName == nameCity ? 'selected="selected"' : ''}>${cityName}</option>
                                    </c:forEach>
                                </select>
                            </form>
                            <p class="card-text">В разделе, отображена инфраструктура выбранного населенного пункта.</p>
                            <br>
                            <c:choose>
                                <c:when test="${nameCity != null}">
                                    <a href="/infrastructure?cityName=${nameCity}"
                                       class="btn btn-info btn-md">Перейти</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="/infrastructure?cityName=${cities.get(0)}" class="btn btn-info btn-md">Перейти</a>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>
        </div>
    </div>
    <br>
    <br>
</main>


</body>
</html>
