<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/> 

<html>
<head>

    <title>
        <fmt:message key="page.title"/>
    </title>

</head>
<body>
<h2>    <fmt:message key="page.orders"/> </h2>

<table border="3" style="font-size:125%;" align="center">
<tr>
<td>id</td>
<td>travel</td>
<td>username</td>
<td>userphone</td>
<td>numOfDays</td>
<td>numOfPeople</td>
</tr>
<c:forEach var="elem" items="${orders}">
<tr>
<td><c:out value="${elem.id}" /></td>
<td><c:out value="${elem.travel}" /></td>
<td><c:out value="${elem.username}" /></td>
<td><c:out value="${elem.userphone}" /></td>
<td><c:out value="${elem.numOfDays}" /></td>
<td><c:out value="${elem.numOfPeople}" /></td>
</tr>
</c:forEach>
</table>

<a href="${pageContext.request.contextPath}/index.jsp">На головну</a>

</body>
</html>