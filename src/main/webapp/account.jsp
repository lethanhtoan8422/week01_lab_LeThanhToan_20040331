<%@ page import="vn.edu.iuh.fit.models.Account" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.models.Role" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 03/11/2023
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account</title>
    <link rel="stylesheet" href="account.css"/>
</head>
<body class="body">
    <%
        Account account = (Account) request.getAttribute("account");
        List<Role> roles = (List<Role>) request.getAttribute("roles");
    %>
    <div class="container">
        <h1>THÔNG TIN TÀI KHOẢN</h1>
        <table class="table">
            <thead class="thead">
                <tr class="tr">
                    <th class="column column-id">ID</th>
                    <th class="column column-name">FULLNAME</th>
                    <th class="column column-phone">PHONE</th>
                    <th class="column column-email">EMAIL</th>
                    <th class="column column-password">PASSWORD</th>
                    <th class="column column-status">STATUS</th>
                </tr>
            </thead>
            <tbody class="tbody">
                <tr class="tr">
                    <td class="column column-id"><%= account.getId()%></td>
                    <td class="column column-name"><%= account.getFullName()%></td>
                    <td class="column column-phone"><%= account.getPhone()%></td>
                    <td class="column column-email"><%= account.getEmail()%></td>
                    <td class="column column-password"><%= account.getPassword()%></td>
                    <td class="column column-status"><%= account.getStatus()%></td>
                </tr>
            </tbody>
        </table>

        <h1>KỸ NĂNG</h1>

        <table class="table-role">
            <thead class="thead-role">
            <tr class="tr">
                <th class="column column-id-role">ID</th>
                <th class="column column-name-role">NAME</th>
                <th class="column column-description">DESCRIPTION</th>
                <th class="column column-status-role">STATUS</th>
            </tr>
            </thead>
            <tbody class="tbody-role">
                <% for(Role r : roles){ %>
                    <tr class="tr">
                        <td class="column column-id-role"><%= r.getId()%></td>
                        <td class="column column-name-role"><%= r.getName()%></td>
                        <td class="column column-description"><%= r.getDescription()%></td>
                        <td class="column column-status-role"><%= r.getStatus()%></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
