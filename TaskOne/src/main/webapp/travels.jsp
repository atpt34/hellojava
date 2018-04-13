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
<h1 style="text-align:center;font-family:tahoma;"><fmt:message key="page.title"/></h1>
<div style="text-align:center;font-family:verdana;">
    <form method="POST"  action="${pageContext.request.contextPath}/travels/travelsSort"> 
        <input style="font-size:200%;"  type="submit" value="Sort by cost" />
    </form>

    <form method="POST"  action="${pageContext.request.contextPath}/travels/travelsSelectByType">
     <c:forEach var="elem" items="${types}">
    <input type="radio" name="param" value="${elem}" checked><c:out value="${elem}" /> <br>
    </c:forEach>
        <input style="font-size:200%;" type="submit" value="Select by type" />
    </form>
    <form method="POST"  action="${pageContext.request.contextPath}/travels/travelsCreate"> 
        <input style="font-size:200%;"  type="submit" value="Create new travel" />
    </form>
</div>
<table border="3" style="font-size:125%;" align="center">
<tr>

<td>type</td>
<td>name</td>
<td>transport</td>
<td>start date</td>
<td>cost</td>
<td>order</td>
<td>edit</td>
<td>delete</td>
</tr>
<c:forEach var="elem" items="${travels}">
<tr>

<td><c:out value="${elem.type}" /></td>
<td><c:out value="${elem.name}" /></td>
<td><c:out value="${elem.transport}" /></td>
<td><c:out value="${elem.start}" /></td>
<td><c:out value="${elem.cost}" /></td>
<td><a href="${pageContext.request.contextPath}/travels/order?travelId=${elem.id}">order</a></td>
<td><a href="${pageContext.request.contextPath}/travels/travelsEdit?travelId=${elem.id}">edit</a></td>
<td><a href="${pageContext.request.contextPath}/travels/travelsDelete?id=${elem.id}">delete</a></td>
</tr>
</c:forEach>
</table>

<a href="${pageContext.request.contextPath}/index.jsp">На головну</a>

</body>
</html>