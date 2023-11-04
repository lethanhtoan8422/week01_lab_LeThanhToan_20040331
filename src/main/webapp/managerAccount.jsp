<%@ page import="vn.edu.iuh.fit.models.Account" %>
<%@ page import="vn.edu.iuh.fit.models.Role" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 03/11/2023
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manager Account</title>
    <link rel="stylesheet" href="manager.css"/>
</head>
<body>
    <%
        String btnSubmit = request.getAttribute("btnSubmit").toString();
        Account accountChosen = (Account) request.getAttribute("account");
        List<Account> accounts = (List<Account>) request.getAttribute("accounts");
        List<Role> roles = (List<Role>) request.getAttribute("roles");
    %>
    <div class="container">
        <form class="form-crud" action="account" method="post">
            <div class="form-info">
                <div class="form-content">
                    <input type="hidden" name="accountID" value="<%= accountChosen.getId()%>" class="input"/>
                    <label for="fullName" class="label">FullName</label>
                    <input type="text" name="fullName" id="fullName" value="<%= accountChosen.getFullName()%>" class="input"/>
                </div>
                <div class="form-content">
                    <label for="email" class="label">Email</label>
                    <input type="email" name="email" id="email" value="<%= accountChosen.getEmail()%>" class="input"/>
                </div>
                <div class="form-content">
                    <label for="password" class="label">Password</label>
                    <input type="password" name="password" id="password" value="<%= accountChosen.getPassword()%>" class="input"/>
                </div>
                <div class="form-content">
                    <label for="phone" class="label">Phone</label>
                    <input type="tel" name="phone" id="phone" value="<%= accountChosen.getPhone()%>" class="input"/>
                </div>
                <div class="form-content">
                    <label for="status" class="label">Status</label>
                    <select name="status" id="status" value="<%= accountChosen.getStatus()%>" class="input">
                        <option value="1">ACTIVE</option>
                        <option value="0">DEACTIVE</option>
                        <option value="-1">DELETE</option>
                    </select>
                </div>
            </div>
            <% if(btnSubmit.equalsIgnoreCase("Thêm")) {%>
                <input type="submit" name="btnSubmitFeature" value="<%= btnSubmit%>" class="btnSubmit"
                style="background-image: linear-gradient(319deg, #dbf26e 0%, #61fa74 37%, #1cfdd6 100%)"
                />
            <% } else { %>
                <input type="submit" name="btnSubmitFeature" value="<%= btnSubmit%>" class="btnSubmit"
                style="background-image: linear-gradient(316deg, #50d8d7 0%, #923993 74%)"
                />
            <% } %>
        </form>
        <div class="filter-crud-btn">
            <form class="filters" action="account" method="post">
                <select name="filter" class="filter">
                    <option value="0">Tất Cả</option>
                    <% for(Role r : roles) {%>
                        <option value="<%= r.getId()%>"><%= r.getName()%></option>
                    <% } %>
                </select>
                <input type="submit" name="btnSubmitFeature" value="Filter" class="btn-filter"/>
            </form>
            <form action="account" method="post" class="form-btn-crud">
                <% if(btnSubmit.equalsIgnoreCase("Thêm")) {%>
                    <input type="submit" name="btnSubmitFeature" value="THÊM" class="btn-submit-insert" disabled="disabled"
                        style="background-color: rgb(128,128,128)"/>
                <% } else { %>
                    <input type="submit" name="btnSubmitFeature" value="THÊM" class="btnSubmit"
                    style="background-image: linear-gradient(319deg, #dbf26e 0%, #61fa74 37%, #1cfdd6 100%)">
                <% } %>
                <input type="hidden" name="accountID" value="<%= accountChosen.getId()%>" class="input"/>
                <input type="submit" name="btnSubmitFeature" value="XÓA" class="btn-submit-delete"/>
            </form>
        </div>
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
                <% for(Account account : accounts){ %>
                    <tr class="tr">
                        <td class="column column-id">
                            <form action="account" method="post" class="form-id">
                                <input type="submit" name="btnSubmitFeature" value="<%= account.getId()%>" class="btnAccountID"/>
                            </form>
                        </td>
                        <td class="column column-name"><%= account.getFullName()%></td>
                        <td class="column column-phone"><%= account.getPhone()%></td>
                        <td class="column column-email"><%= account.getEmail()%></td>
                        <td class="column column-password"><%= account.getPassword()%></td>
                        <td class="column column-status"><%= account.getStatus()%></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
