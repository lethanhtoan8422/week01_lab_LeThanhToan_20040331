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
    <title>Grant Access</title>
    <link rel="stylesheet" href="manager.css"/>
</head>
<body>
    <%
        String btnSubmit = request.getAttribute("btnSubmit").toString();
        List<GrantAccess> grantAccess = (List<GrantAccess>) request.getAttribute("grantAccess");
        GrantAccess grantChosen = (GrantAccess) request.getAttribute("grant");
        List<Account> accounts = (List<Account>) request.getAttribute("accounts");
        List<Role> roles = (List<Role>) request.getAttribute("roles");
    %>
    <div class="container">
        <form class="form-crud" action="grant-access" method="post">
            <div class="form-info">
                <div class="form-content">
                    <label for="account" class="label">Account</label>
                    <select name="selectAccount" id="account" class="input">
                        <% for(Account a : accounts) {
                                if(grantChosen.getAccount().getId() == a.getId()) { %>
                                    <option selected value="<%= a.getId()%>"><%= a.getEmail()%></option>
                                <% } else { %>
                                    <option value="<%= a.getId()%>"><%= a.getEmail()%></option>
                                <% } %>
                        <% } %>
                    </select>
                </div>
                <div class="form-content">
                    <label for="role" class="label">Role</label>
                    <select name="selectRole" id="role" class="input">
                        <% for(Role r : roles) {
                            if(grantChosen.getRole().getId() == r.getId()) { %>
                                <option selected value="<%= r.getId()%>"><%= r.getName()%></option>
                            <% } else { %>
                                <option value="<%= r.getId()%>"><%= r.getName()%></option>
                            <% } %>
                        <% } %>
                    </select>
                </div>
                <div class="form-content">
                    <label for="isGrant" class="label">IsGrant</label>
                    <select name="selectIsGrant" id="isGrant" class="input">
                        <option value="1">ENABLE</option>
                        <option value="0">DISABLE</option>
                    </select>
                </div>
                <div class="form-content">
                    <label for="note" class="label">Note</label>
                    <input type="text" name="note" id="note" class="input" value="<%= grantChosen.getNote()%>"/>
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
            <form action="grant-access" method="post" class="form-btn-crud">
                <% if(btnSubmit.equalsIgnoreCase("Thêm")) {%>
                <input type="submit" name="btnSubmitFeature" value="THÊM" class="btn-submit-insert" disabled="disabled"
                       style="background-color: rgb(128,128,128)"/>
                <% } else { %>
                <input type="submit" name="btnSubmitFeature" value="THÊM" class="btnSubmit"
                       style="background-image: linear-gradient(319deg, #dbf26e 0%, #61fa74 37%, #1cfdd6 100%)">
                <% } %>
                <input type="hidden" name="accountID" value="<%= grantChosen.getAccount().getId()%>" class="input"/>
                <input type="hidden" name="roleID" value="<%= grantChosen.getRole().getId()%>" class="input"/>
                <input type="submit" name="btnSubmitFeature" value="XÓA" class="btn-submit-delete"/>
            </form>
        </div>
        <table class="table">
            <thead class="thead">
            <tr class="tr">
                <th class="column column-account">ACCOUNT</th>
                <th class="column column-role">ROLE</th>
                <th class="column column-note">NOTE</th>
                <th class="column column-is-grant">ISGRANT</th>
            </tr>
            </thead>
            <tbody class="tbody">
            <% for(GrantAccess gr : grantAccess){ %>
            <form action="grant-access" method="post">
                <input type="hidden" name="accountID" value="<%= gr.getAccount().getId()%>"/>
                <input type="hidden" name="roleID" value="<%= gr.getRole().getId()%>"/>
                <input type="hidden" name="isGrant" value="<%= gr.getIsGrant()%>"/>
                <input type="hidden" name="note" value="<%= gr.getNote()%>"/>
                <tr class="tr">
                    <td class="column column-account"><%= gr.getAccount().getEmail()%>
                        <input type="submit" name="btnSubmitFeature" value="selectRow" class="btn-submit-grant"/>
                    </td>
                    <td class="column column-role"><%= gr.getRole().getName()%></td>
                    <td class="column column-note"><%= gr.getNote()%></td>
                    <td class="column column-is-grant"><%= gr.getIsGrant()==1?"ENABLE":"DISABLE"%></td>
                </tr>
            </form>
            <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
