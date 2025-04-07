<%--
  Created by IntelliJ IDEA.
  User: kl-25
  Date: 06.04.2025
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="text-center text-muted small">
    Session ID: <%= session.getId() %><br>
    Player: <c:out value="${sessionScope.PLAYER_NAME}"/><br>
    Games played: <c:out value="${sessionScope.GAMES_PLAYED}"/>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
