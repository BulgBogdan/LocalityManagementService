<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script>
        // $(document).ready(function () {
        //     $('select').on('change', function (e) {
        //         var value = $('#languageSelect').val();
        //         var urlNow = window.location.href.replace(/\?.*/g, '').replace(/\/$/g, '');
        //         var str = document.location.search;
        //         if (typeof str == 'undefined' || !str || str.length === 0 || str === ""
        //             || !/[^\s]/.test(str) || /^\s*$/.test(str) || str.replace(/\s/g, "") === "") {
        //             window.location.href = urlNow + '?sessionLocale=' + value;
        //         } else {
        //             window.location.href = urlNow + paramsString + '&sessionLocale=' + value;
        //         }
        //         // history.replaceState({},'',urlNow + paramsString + '?sessionLocale='+value);
        //     });
        // });
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
