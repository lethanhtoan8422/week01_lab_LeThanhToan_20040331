<%@ page import="vn.edu.iuh.fit.models.Account" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 03/11/2023
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DashBoard</title>
    <link rel="stylesheet" href="dashboard.css"/>
</head>
<body>
    <%
        Account account = (Account) request.getAttribute("account");
    %>
    <div class="container">
        <div class="header">
            <h1>QUẢN LÝ THÔNG TIN</h1>
            <form action="dashboard" method="post" class="form-logout">
                <input type="hidden" name="accountID" value="<%= account.getId()%>"/>
                <input type="submit" name="btnSubmit" value="Đăng Xuất"/>
            </form>
        </div>
        <form class="form" action="dashboard" method="post">
            <input type="submit" name="btnSubmit" value="ACCOUNT" class="btn-submit"/>
            <input type="submit" name="btnSubmit" value="ROLE" class="btn-submit"/>
            <input type="submit" name="btnSubmit" value="GRANT-ACCESS" class="btn-submit"/>
            <input type="submit" name="btnSubmit" value="LOG" class="btn-submit"/>
        </form>
    </div>
</body>
</html>
