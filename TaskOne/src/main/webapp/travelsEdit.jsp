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
     <p> Edit travel: </p>
    <form method="POST"
            action="${pageContext.request.contextPath}/travels/processEdit">
       <input type="hidden" name="id"  value="${elem.id}" /> <br>
        name <input type="text" name="name" value="${elem.name}"/> <br>
        type <input type="text" name="type" value="${elem.type}" /> <br>
        transport <input type="text" name="transport" value="${elem.transport}" /> <br>
        start date <input type="text" name="start" value="${elem.start}" /> <br>
        cost <input type="text" name="cost" value="${elem.cost}" /> <br>
        <input style="font-size:200%;" type="submit" value="Edit" />
    </form>
</body>
</html>