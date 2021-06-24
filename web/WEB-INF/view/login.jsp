<%-- 
    Document   : login
    Created on : Jun 16, 2021, 2:55:01 PM
    Author     : Lenovo
--%>

<%@page import="ultils.GetParam"%>
<%@page import="ultils.Urls"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body >
        <%
            String errorMessage = (String) GetParam.getClientAttribute(request, "errorMessage", "");
            String userIdError = (String) GetParam.getClientAttribute(request, "userIdError", "");
            String passwordError = (String) GetParam.getClientAttribute(request, "passwordError", "");
        %>

        <%@include file="navbar.jsp" %>
        <h1>Login From</h1>
        <form action="<%=Urls.LOGIN_CONTROLLER%>" method = "POST">
            <table>
                <tr>
                    <td>  </td>
                    <td style="color: red"><%= errorMessage%></td>
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
        <div style="padding: 50px 0 0 80px">
            <table border="1">
                <tr>
                    <td>User ID</td>
                    <td>Password</td>
                </tr>
                <tr>
                    <td>user</td>
                    <td>123</td>
                </tr>
                <tr>
                    <td>staff</td>
                    <td>123</td>
                </tr>
            </table>
        </div>
    </body>
</html>
