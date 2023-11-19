<%@ page import="vn.edu.iuh.fit.models.Account" %>
<%@ page import="vn.edu.iuh.fit.models.Role" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.models.GrantAccess" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 04/11/2023
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Role</title>
    <link rel="stylesheet" href="manager.css"/>
</head>
<body>
    <%
        String btnSubmit = request.getAttribute("btnSubmit").toString();
        Role roleChosen = (Role) request.getAttribute("role");
        List<Role> roles = (List<Role>) request.getAttribute("roles");
    %>
    <div class="container">
        <form class="form-crud" action="role" method="post">
            <div class="form-info">
                <div class="form-content">
                    <label for="roleName" class="label">Role Name</label>
                    <input type="text" name="roleName" id="roleName" class="input" value="<%= roleChosen.getName()%>"/>
                </div>
                <div class="form-content">
                    <label for="description" class="label">Description</label>
                    <input type="text" name="description" id="description" class="input" value="<%= roleChosen.getDescription()%>"/>
                </div>
                <div class="form-content">
                    <label for="status" class="label">IsGrant</label>
                    <select name="selectStatus" id="status" class="input" value="<%= roleChosen.getStatus()%>">
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
            <form action="role" method="post" class="form-btn-crud">
                <% if(btnSubmit.equalsIgnoreCase("Thêm")) {%>
                <input type="submit" name="btnSubmitFeature" value="THÊM" class="btn-submit-insert" disabled="disabled"
                       style="background-color: rgb(128,128,128)"/>
                <% } else { %>
                <input type="submit" name="btnSubmitFeature" value="THÊM" class="btnSubmit"
                       style="background-image: linear-gradient(319deg, #dbf26e 0%, #61fa74 37%, #1cfdd6 100%)">
                <% } %>
                <input type="hidden" name="roleID" value="<%= roleChosen.getId()%>" class="input"/>
                <input type="submit" name="btnSubmitFeature" value="XÓA" class="btn-submit-delete"/>
            </form>
        </div>
        <table class="table">
            <thead class="thead">
            <tr class="tr">
                <th class="column column-account">ID</th>
                <th class="column column-role">ROLE NAME</th>
                <th class="column column-note">DESCRIPTION</th>
                <th class="column column-is-grant">STATUS</th>
            </tr>
            </thead>
            <tbody class="tbody">
            <% for(Role rl : roles){ %>
            <form action="role" method="post">
                <input type="hidden" name="roleID" value="<%= rl.getId()%>"/>
                <tr class="tr">
                    <td class="column column-account"><%= rl.getId()%>
                        <input type="submit" name="btnSubmitFeature" value="selectRow" class="btn-submit-grant"/>
                    </td>
                    <td class="column column-role"><%= rl.getName()%></td>
                    <td class="column column-note"><%= rl.getDescription()%></td>
                    <td class="column column-is-grant"><%= rl.getStatus()%></td>
                </tr>
            </form>
            <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
