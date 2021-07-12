<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

                    <form method="POST" action="locality">
                        <h3 class="text-center text-info">Населенный пункт</h3>
                        <div class="form-group">
                            <label for="name" class="text-info">Название:</label><br>
                            <input type="text" name="name" id="name"
                                   value="${locality.getName()}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="population" class="text-info">Население:</label><br>
                            <input type="text" name="population" id="population"
                                   value="${locality.getPopulation()}" class="form-control">
                        </div>

                        <div class="form-group">
                            <input type="submit" name="submit" class="btn btn-info btn-md" value="Создать">
                        </div>

                        <div id="register-link" class="text-left">
                            <a href="/locality?chairmenName=${chairmenName}">Вернуться назад</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
