<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

    <%--<%@include 会将两页页面合二为一--%>
    <%@include file="../common/header.jsp"%>
    <h1>网页主体</h1>
    <%@include file="../common/footer.jsp"%>
    <hr>

    <%--JSP标签
    <jsp:include 会拼接页面，本质还是三个。建议用这种（虽然JSP已经不用了）
    --%>
    <jsp:include page="/common/header.jsp" />
    <h1>网页主体</h1>
    <jsp:include page="/common/footer.jsp" />
</body>
</html>
