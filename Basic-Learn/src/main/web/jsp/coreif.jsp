<%--引入JSTL核心标签库，才能使用JSTL标签--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h4>if测试</h4>
<hr>
<%--ERROR！
无法在web.xml或使用此应用程序部署的jar文件中解析绝对uri：[http://java.sun.com/jsp/jstl/core]
--%>

<form action="coreif.jsp" method="get">
    <%--
    EL表达式获取表单中的数据
    ${param.参数名}
    --%>
    <input type="text" name="username" value="${param.username}">
    <input type="submit" value="登录">
</form>

<%--如果提交用户名是管理员，则登录成功--%>
<%--<%--%>
<%--    if (request.getParameter("username").equals("admin")) {--%>
<%--        out.print("登良成功");--%>
<%--    }--%>
<%--%>--%>
<c:if test="${param.username == 'admin'}" var="isAdamin">
    <c:out value="登录成功"/>
</c:if>

<c:out value="${isAdamin}"/>
</body>
</html>
