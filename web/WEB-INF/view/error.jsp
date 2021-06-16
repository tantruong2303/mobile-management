<%-- 
    Document   : error
    Created on : Jun 16, 2021, 4:01:34 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
         <%
            String errorMessage = (String) request.getAttribute("errorMessage");
           
            if (errorMessage == null) {
                errorMessage = "Page not found!";
            }
        
        %>
        <div class="text-center">
            <h1><%= errorMessage %></h1>
            <a href="IndexController">
                Go Back To Home
            </a >
        </div>
    </body>
</html>
