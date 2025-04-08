<%--
  Created by IntelliJ IDEA.
  User: kl-25
  Date: 06.04.2025
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Квест</title>
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <%--<style>
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
        }

        body {
            display: flex;
            flex-direction: column;
        }

        main {
            flex: 1 0 auto;
        }

        footer {
            flex-shrink: 0;
        }
    </style>--%>
</head>
<body>

<%@ include file="header.jsp" %>

<main>
    <div class="container my-5">
        <div class="card shadow mx-auto" style="max-width: 600px;">
            <div class="card-body text-center">
                <h2 class="card-title">Вопрос или финал</h2>
                <button class="btn btn-primary">Ответ</button>
            </div>
        </div>
    </div>
</main>

<%@ include file="footer.jsp" %>

</body>
</html>