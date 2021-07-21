<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script>
        var url = "${pageContext.request.contextPath}";
        $(document).ready(function () {
            $("#languageSelect").change(function () {
                var value = $('#languageSelect').val();
                window.location.href = url + '?sessionLocale=' + value;
            });
        });
    </script>
</head>
<nav class="navbar navbar-expand-lg navbar-light bg-light">

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02"
            aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>


    <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
        <ul class="navbar-nav mr-auto mt-3 mt-lg-0">
            <li class="nav-item">
                <a class="nav-link" href="/home"><fmt:message key="label.home"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#"></a>
            </li>

            <li class="nav-item">
                <%--name="language" method="post" action="${pageContext.request.contextPath}?sessionLocale=${langSelect}"--%>
                <%--onchange="this.form.submit()"--%>
                <form class="form-inline my-2 my-lg-0">
                    <select class="form-control mr-sm-0" id="languageSelect">
                        <option value="en"
                                <c:if test="${param.selectValue == 'en'})"> selected </c:if> >
                            <fmt:message key="label.lang.en"/>
                        </option>
                        <option value="ru"
                                <c:if test="${param.selectValue == 'ru'})"> selected </c:if> >
                            <fmt:message key="label.lang.ru"/>
                        </option>
                    </select>
                    <c:set var="langSelect" value="${param.languageSelect}" scope="session"/>
                </form>
                <a class="nav-link" href="#"></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#"></a>
            </li>
        </ul>
        <ul class="navbar-nav my-2 my-lg-0">
            <li class="nav-item active">
                <a class="nav-link" href="/cabinet"><span class="sr-only">(current)</span>
                    <fmt:message key="label.cabinet"/>
                </a>
            </li>
            <li class="nav-item active">
                <a class="btn btn-sm btn-outline-danger" title="<fmt:message key="label.exit"/>"
                   href="/logout"><fmt:message key="label.logout"/></a>
            </li>
        </ul>

    </div>
</nav>
