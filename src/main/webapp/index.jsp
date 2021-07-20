<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="language"/>
<html>
<body>
<h1 class="text-centre">
    <fmt:message key="label.chooseLanguage"/>
</h1>
<br>
<div class="container">
    <a class="btn btn-primary btn-xs pull-right" href="?sessionLocale=en"><fmt:message key="label.lang.en"/></a>
    <br>
    <a class="btn btn-primary btn-xs pull-right" href="?sessionLocale=ru"><fmt:message key="label.lang.ru"/></a>
</div>
<h2><a href="home">Начать</a></h2>
</body>
</html>
