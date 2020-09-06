<%@ page import="Learn_JavaWeb.pojo.People" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    // 等价操作
//    People people = new People();
//    people.setId();
//    people.setName();
//    people.setAddress();
//    people.setAge();

//    people.getAddress()
%>

<jsp:useBean id="people" class="Learn_JavaWeb.pojo.People" scope="page" />
<jsp:setProperty name="people" property="address" value="北京"/>
<jsp:setProperty name="people" property="id" value="1"/>
<jsp:setProperty name="people" property="age" value="3"/>
<jsp:setProperty name="people" property="name" value="Sugar"/>

姓名：<jsp:getProperty name="people" property="name"/>
年龄：<jsp:getProperty name="people" property="age"/>
ID：<jsp:getProperty name="people" property="id"/>
地址：<jsp:getProperty name="people" property="address"/>



</body>
</html>
