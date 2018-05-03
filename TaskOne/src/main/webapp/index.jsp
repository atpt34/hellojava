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
        <h2>
            <fmt:message key="page.title"/>
        </h2>

    <br/>
        <a href="${pageContext.request.contextPath}/travels/login">Login</a>
    <br/>
        <a href="${pageContext.request.contextPath}/travels/registration">Registration form</a>
    <br>
        <a href="${pageContext.request.contextPath}/travels/exception">Exception</a>
    <br>
        <a href="${pageContext.request.contextPath}/travels/travels"><fmt:message key="page.title"/></a>
    <br>
        <a href="${pageContext.request.contextPath}/travels/orders"><fmt:message key="page.orders"/></a>
     <br>

        <form method="GET" action="${pageContext.request.contextPath}/travels/changeLanguage">
            <select name="lan" size="1">
                <option value="en"> en </option>
                <option value="ua"> ua </option>
                <option value="jp"> jp </option>
                <input type="hidden" name="pageName" value="/" />
            </select>
            <input type="submit" value="<fmt:message key="command.set.language" />">
        </form>
    
    </body>
</html>
