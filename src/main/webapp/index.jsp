<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="login.css"/>
</head>
<body>
    <div class="container">
        <h1 class="header">LOGIN</h1>
        <form action="login" method="post" class="form">
            <div class="form-content">
                <label class="label" for="email">Email </label>
                <input type="email" name="email" id="email" class="input"/>
            </div>
            <div class="form-content">
                <label class="label" for="password">Password </label>
                <input type="password" name="password" id="password" class="input"/>
            </div>
            <input type="submit" name="btnLogin" value="LOGIN" class="submit"/>
        </form>
    </div>
</body>
</html>