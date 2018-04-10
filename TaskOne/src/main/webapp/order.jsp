<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order </title>
</head>
<body>
<h1> Order travel </h1>

    <p>This travel you want to order</p>
    <table><tr>
<td><c:out value="${elem.id}" /></td>
<td><c:out value="${elem.type}" /></td>
<td><c:out value="${elem.name}" /></td>
<td><c:out value="${elem.start}" /></td>
<td><c:out value="${elem.cost}" /></td>
</tr></table>
     <p> Enter your info: </p>
    <form method="POST"
            action="${pageContext.request.contextPath}/app/processOrder?travelId=${elem.id}">
    <!--        id <input type="text" name="id"  /> <br> -->
        name <input type="text" name="name" /> <br>
        phone <input type="text" name="phone" /> <br>
        number of days <input type="text" name="days"  /> <br>
        number of people <input type="text" name="quantity" /> <br>
        <input style="font-size:200%;" type="submit" value="Order" />
    </form>
</body>
</html>