<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>tag2</h2>

<%--取出参数--%>
名字：<%=request.getParameter("name")%>
年龄：<%=request.getParameter("age")%>


</body>
</html>
