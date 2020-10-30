<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>tag1</h1>
<%--<jsp:include--%>

<%--
http://localhost:8080/jsptag.jsp?name=sugar&age=3
--%>
<jsp:forward page="jsptag2.jsp">
    <jsp:param name="name" value="sugar"/>
    <jsp:param name="age" value="3"/>
</jsp:forward>


</body>
</html>
