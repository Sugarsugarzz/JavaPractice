<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改书籍</title>

    <%--BootStrap美化界面--%>
    <script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
    <link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>
</head>
<body>

<div class="container">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>修改书籍</small>
                </h1>
            </div>
        </div>
    </div>

    <form action="${pageContext.request.contextPath}/book/updateBook" method="post">
        <%--出现问题：提交了修改到SQL的请求，但是修改失败，初次考虑是事务问题，配置事务后依旧失败--%>
        <%--然后查看SQL是否执行成功：SQL执行失败，id未传入--%>
        <%--前端传递隐藏域id--%>
        <input type="hidden" name="bookID" value="${books.bookID}">
        <div class="form-group">
            <label>书籍名称：</label>
            <input type="text" class="form-control" name="bookName" value="${books.bookName}" required>
        </div>
        <div class="form-group">
            <label>书籍数量：</label>
            <input type="text" class="form-control" name="bookCounts" value="${books.bookCounts}" required>
        </div>
        <div class="form-group">
            <label>书籍描述：</label>
            <input type="text" class="form-control" name="detail" value="${books.detail}" required>
        </div>

        <div class="form-group">
            <input type="submit" class="form-control" value="修改">
        </div>
    </form>
</div>


</body>
</html>
