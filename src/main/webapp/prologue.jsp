<%--
  Created by IntelliJ IDEA.
  User: kl-25
  Date: 06.04.2025
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Пролог</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/common.css" rel="stylesheet">
</head>
<body>
<%@ include file="header.jsp" %>
<main class="form-signin w-100 m-auto">
    <div class="container my-5">
        <div class="card shadow mx-auto" style="max-width: 600px;">
            <div class="p-4 p-md-5 border rounded-3 bg-body-tertiary text-center">
                <h2 class="card-title mb-2">Пролог</h2>
                <div class="mb-5">Ты стоишь в космическом порту и готов подняться на борт своего корабля. Разве ты не об
                    этом мечтал? Стать капитаном галактического судна с экипажем, который будет совершать подвиги под
                    твоим командованием.
                    Так что вперёд!
                </div>
                <div class="d-flex justify-content-center">
                    <form action="/" method="get" class="me-2">
                        <button type="submit" class="btn btn-outline-secondary btn-custom">Отказаться</button>
                    </form>
                    <form action="/game" method="post" class="ms-2">
                        <button type="submit" class="btn btn-primary btn-custom">Вперёд</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="text-center text-muted small">
        Session ID: <%= session.getId() %><br>
        Player: <c:out value="${sessionScope.PLAYER_NAME}"/><br>
        Games played: <c:out value="${sessionScope.GAMES_PLAYED}"/>
    </div>
</main>
<%@ include file="footer.jsp" %>
</body>

</html>
