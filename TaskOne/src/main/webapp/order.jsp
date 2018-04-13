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
<h1> Order travel </h1>

 
   <p> Do you want to order this travel? </p>
<p><c:out value="${elem.id}" /></p>
<p><c:out value="${elem.name}" /></p>
<p><c:out value="${elem.type}" /></p>
<p><c:out value="${elem.transport}" /></p>
<p><c:out value="${elem.start}" /></p>
<p><c:out value="${elem.cost}" /></p>

  
      <p> Enter your info: </p>
    <form method="POST"
            action="${pageContext.request.contextPath}/travels/processOrder?travelId=${elem.id}">
        name <input type="text" name="name" value="Oleksii"/> <br>
        phone <input type="text" name="phone" value="0670880808" /> <br>
        number of days <input type="text" name="days" value="14" /> <br>
        number of people <input type="text" name="quantity" value="1" /> <br>
        <input style="font-size:200%;" type="submit" value="Order" />
    </form>
       
</body>
</html>