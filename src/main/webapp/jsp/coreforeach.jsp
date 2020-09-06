<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    ArrayList<String> people = new ArrayList<>();
    people.add(0, "张1");
    people.add(1, "张2");
    people.add(2, "张3");
    people.add(3, "张4");
    request.setAttribute("list", people);
%>
<%--
var，每一次遍历出来的变量
items，要遍历的对象
begin，起始
end，结束
step，步长
--%>
<c:forEach var="people" items="${list}">
    <c:out value="${people}"/> <br>
</c:forEach>

<hr>

<c:forEach begin="1" end="3" step="2" var="people" items="${list}">
    <c:out value="${people}" /> <br>
</c:forEach>


</body>
</html>
