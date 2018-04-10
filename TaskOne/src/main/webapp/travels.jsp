<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Travels: </title>
</head>
<body>
<h1 style="text-align:center;font-family:tahoma;">List of available travels:</h1>
<div style="text-align:center;font-family:verdana;">
    <form method="POST"  action="${pageContext.request.contextPath}/app/travels">
    <input type="hidden" name="action" value="sort" /> 
        <input style="font-size:200%;"  type="submit" value="Sort by cost" />
    </form>

    <form method="POST"  action="${pageContext.request.contextPath}/app/travels">
     <c:forEach var="elem" items="${types}">
    <input type="radio" name="param" value="${elem}" checked><c:out value="${elem}" /> <br>
    </c:forEach>
    <input type="hidden" name="action" value="type" /> 
        <input style="font-size:200%;" type="submit" value="Select by type" />
    </form>
</div>
<table border="3" style="font-size:125%;" align="center">
<tr>
<!-- <td>id</td> -->
<td>type</td>
<td>name</td>
<td>start date</td>
<td>cost</td>
<td></td>
</tr>
<c:forEach var="elem" items="${travels}">
<tr>
<!-- <td><c:out value="${elem.id}" /></td> -->
<td><c:out value="${elem.type}" /></td>
<td><c:out value="${elem.name}" /></td>
<td><c:out value="${elem.start}" /></td>
<td><c:out value="${elem.cost}" /></td>
<td><a href="${pageContext.request.contextPath}/app/order?travelId=${elem.id}">order</a></td>
</tr>
</c:forEach>
</table>

<a href="${pageContext.request.contextPath}/index.jsp">На головну</a>

</body>
</html>