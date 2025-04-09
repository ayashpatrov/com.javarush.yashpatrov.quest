<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>Квест</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        <%@include file="css/common.css"%>
    </style>
</head>
<body>
<%@ include file="header.jsp" %>

<main class="form-signin w-100 m-auto">
    <div class="container my-5">
        <div class="card shadow mx-auto" style="max-width: 600px;">
            <div class="card-body text-center gap-5">
                <h2 class="card-title mb-5">${question.text}</h2>
                <c:choose>
                    <c:when test="${not empty question.getAnswers()}">
                        <form action="/game" method="post">
                            <div class="d-grid gap-2 d-md-flex justify-content-md-start">
                                <c:forEach var="answer" items="${question.getAnswers()}">

                                    <button class="btn btn-primary w-50 py-2" type="submit" name="answerId"
                                            value="${answer.getId()}">
                                            ${answer.getText()}
                                    </button>

                                    <br>
                                </c:forEach>
                            </div>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <div class="d-flex justify-content-center">
                            <form action="/" method="get" class="me-2">
                                <button type="submit" class="btn btn-outline-secondary btn-custom">С меня хватит
                                </button>
                            </form>
                            <form action="/prologue" method="get" class="ms-2">
                                <button type="submit" class="btn btn-primary btn-custom">Заново</button>
                            </form>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</main>

<%@ include file="footer.jsp" %>
</body>
</html>