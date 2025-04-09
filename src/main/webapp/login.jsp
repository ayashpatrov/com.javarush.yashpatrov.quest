<%--
  Created by IntelliJ IDEA.
  User: kl-25
  Date: 05.04.2025
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Добро пожаловать</title>
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
            <div class="p-4 p-md-5 border rounded-3 bg-body-tertiary text-center">
                <h2 class="card-title mb-2" >Привет, путник!</h2>
                <p>Введи своё имя, чтобы начать квест:</p>
                <div class="form-signin w-100 m-auto">
                    <form action="login" method="post">
                        <div class="d-flex gap-2 justify-content-center py-5">
                            <input type="text" class="form-control" name="playerName" placeholder="Имя" required/>
                            <button type="submit" class="btn btn-primary w-30 py-2">Начать</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>
<%@ include file="footer.jsp" %>
</body>
</html>