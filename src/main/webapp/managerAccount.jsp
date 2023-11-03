<%--
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
</head>
<body>
    <div class="container">
        <form class="form-crud">
            <div class="form-info">
                <div class="form-content">
                    <label for="fullName" class="label">FullName</label>
                    <input type="text" name="fullName" id="fullName" class="input"/>
                </div>
                <div class="form-content">
                    <label for="email" class="label">Email</label>
                    <input type="email" name="email" id="email" class="input"/>
                </div>
                <div class="form-content">
                    <label for="password" class="label">Password</label>
                    <input type="password" name="password" id="password" class="input"/>
                </div>
                <div class="form-content">
                    <label for="phone" class="label">Phone</label>
                    <input type="tel" name="phone" id="phone" class="input"/>
                </div>
                <div class="form-content">
                    <select name="status">
                        <option value="1">ACTIVE</option>
                        <option value="0">DEACTIVE</option>
                        <option value="-1">DELETE</option>
                    </select>
                </div>
            </div>
            <input type="submit" name="btnSubmit" value="Thêm"/>
        </form>
    </div>
</body>
</html>
