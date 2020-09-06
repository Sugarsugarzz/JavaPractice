<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    pageContext.forward("/index.jsp");
//    等价于  request.getRequestDispatcher("/index.jsp").forward(request, response);
%>

</body>
</html>
