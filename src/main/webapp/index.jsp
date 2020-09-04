<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>登录</title>
</head>

<body>
<h1>登录</h1>

<%--这里提交的路径，需要寻找到项目的路径--%>
<%--${pageContext.request.contextPath} 代表当前项目--%>
<%--<form action="${pageContext.request.contextPath}/request" method="get">--%>
<%--    用户名：<input type="text" name="username"> <br>--%>
<%--    密码：<input type="password" name="password"> <br>--%>
<%--    <input type="submit">--%>
<%--</form>--%>

<div style="text-align: center">
    <%--  以POST方式提交表单  --%>
    <form action="${pageContext.request.contextPath}/login" method="post">
        用户名：<input type="text" name="username"> <br>
        密码：<input type="password" name="password"> <br>
        爱好：
        <input type="checkbox" name="hobbies" value="代码">代码
        <input type="checkbox" name="hobbies" value="电影">电影
        <br>
        <input type="submit">
    </form>
</div>





</body>
</html>
