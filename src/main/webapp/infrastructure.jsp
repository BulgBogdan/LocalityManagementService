<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Infrastructure</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<%@include file="include/header.jsp" %>

<main class="container">
    <div class="container">
        <div class="row col-md-12 col-md-offset-0">
            <h6 class="text-center">Инфрастуктура ${cityName}:</h6>
            <table class="table table-striped table-bordered">
                <thead style="background-color: #77a4ff">
                <tr>
                    <th class="text-center">Название</th>
                    <th class="text-center">Площадь</th>
                    <th class="text-center">Этажность</th>
                    <th class="text-center">Вместимость</th>
                    <th class="text-center"></th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${infrastructures.isEmpty()}">
                        <p>Ничего нет</p>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${infrastructures}" var="infrastructure">
                            <tr>
                                <td class="text-center">${infrastructure.getName()}</td>
                                <td class="text-center">${infrastructure.getSquare()}</td>
                                <td class="text-center">${infrastructure.getFloors()}</td>
                                <td class="text-center">${infrastructure.getPersons()} человек</td>
                                <c:choose>
                                    <c:when test="${isChairmen}">
                                        <c:url value="/edit/infrastructure?infrastructureID=${infrastructure.getId()}"
                                               var="editInfrastructure"/>
                                        <td class="text-center"><a class='btn btn-info btn-xs'
                                                                   href="${editInfrastructure}">
                                            <svg width="1em" height="1em"
                                                 viewBox="0 0 16 16"
                                                 class="bi bi-pencil-fill"
                                                 fill="currentColor"
                                                 xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd"
                                                      d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"/>
                                            </svg>
                                        </a>
                                            <c:url value="/delete/infrastructure?infrastructureID=${infrastructure.getId()}"
                                                   var="delInfrastructure"/>
                                            <a href="${delInfrastructure}" class="btn btn-danger btn-xs">
                                                <svg width="1em" height="1em" viewBox="0 0 16 16"
                                                     class="bi bi-trash"
                                                     fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                    <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                                    <path fill-rule="evenodd"
                                                          d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                                                </svg>
                                            </a>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td></td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach> </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>
        <c:if test="${isChairmen}">
            <a href="create/infrastructure?cityName=${cityName}"
               class="btn btn-primary btn-xs pull-right"><b>+</b> Добавить инфраструктуру</a>
        </c:if>
    </div>
    <div class="text-center">
        <p style="color: green">${confirmData}</p>
    </div>
    <br>
    <div id="register-link" class="text-center">
        <br>
        <a href="#" onclick="history.back();">Вернуться назад</a>
    </div>
</main>

</body>
</html>
