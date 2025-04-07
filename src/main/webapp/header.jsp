<%--
  Created by IntelliJ IDEA.
  User: kl-25
  Date: 06.04.2025
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="bg-dark text-white py-3">
    <div class="container-fluid d-flex justify-content-between align-items-center">
        <h1 class="m-0">💀 Квест: Про НЛО</h1>
        <c:if test="${sessionScope.PLAYER_NAME ne null}">
            <div class="text-end">
                <h6 class="mb-0">Имя: <c:out value="${sessionScope.PLAYER_NAME}"/></h6>
                <h6 class="mb-0">Сыграно игр: <c:out value="${sessionScope.GAMES_PLAYED}"/></h6>
            </div>
        </c:if>
    </div>
</header>