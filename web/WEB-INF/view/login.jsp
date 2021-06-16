<%-- 
    Document   : login
    Created on : Jun 16, 2021, 2:55:01 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body >
        <%
            String userIdError = (String) request.getAttribute("userIdError");
            String passwordError = (String) request.getAttribute("passwordError");
            String loginError = (String) request.getAttribute("loginError");
            String errorMessage = (String) request.getAttribute("errorMessage");

            if (userIdError == null) {
                userIdError = "";
            }
            if (passwordError == null) {
                passwordError = "";
            }
            if (loginError == null) {
                loginError = "";
            }
            if (errorMessage == null) {
                errorMessage = "";
            }
        %>

        <h1>Login From</h1>
        <form action="LoginController" method = "POST">
            <table>
                <tr>
                    <td>  </td>
                    <td style="color: red"><%= loginError + errorMessage%></td>
                </tr>
                <tr>
                    <td>User ID: </td>
                    <td><input type="text" name="userId" /></td>
                </tr>
                <tr>
                    <td>  </td>
                    <td style="color: red"><%= userIdError%></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="password" name="password" /></td>
                </tr>
                <tr>
                    <td>  </td>
                    <td style="color: red"><%= passwordError%></td>
                </tr>
                <tr>
                    <td><button type="submit">Login</button></td>
                </tr>
            </table>
        </form>
    </body>
</html>
