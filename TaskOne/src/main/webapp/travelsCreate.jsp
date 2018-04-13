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
     <p> Create travel: </p>
    <form method="POST"
            action="${pageContext.request.contextPath}/travels/processCreate">
       <input type="hidden" name="id"  value="" /> <br>
        name <input type="text" name="name" value=""/> <br>
        type <input type="text" name="type" value="" /> <br>
        transport <input type="text" name="transport" /> <br>
        start date <input type="text" name="start"  /> <br>
        cost <input type="text" name="cost"  /> <br>
        <input style="font-size:200%;" type="submit" value="Create" />
    </form>
</body>
</html>