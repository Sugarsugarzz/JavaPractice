<%--
  Created by IntelliJ IDEA.
  User: sugar
  Date: 2020/9/6
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>当前有 <span><%=session.getServletContext().getAttribute("OnlineCount")%></span></h1>

</body>
</html>
