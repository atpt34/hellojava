<html>
<head>
    <title>Registration form</title>
    <form action="${pageContext.request.contextPath }/security_check" method="POST">
        User: <input type="text" name="username" value=""/> <br>
        Password <input type="password" name="password" /> <br>
        <input type="submit" name="submit" value="Submit" />
    </form>
</head>
<body>
        <h2>
            This is registration form! <br/>
        </h2>

        <a href="${pageContext.request.contextPath}/index.jsp">Index</a>
</body>
</html>