<%-- 
    Document   : navbar
    Created on : Jun 20, 2021, 1:49:01 PM
    Author     : Lenovo
--%>

<%@page import="ultils.Urls"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="ultils.Helper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <%
        Context env = (Context) new InitialContext().lookup("java:comp/env");
        Integer staffRole = (Integer) env.lookup("staffRole");
        Integer userRole = (Integer) env.lookup("userRole");
    %>

    <% if (Helper.isLogin(request)) { %>

    <% if (Helper.correctRole(request, staffRole, staffRole)) { %>
    <a href="<%= Urls.STAFF_CONTROLLER %>"><h3 style="display: inline-block">Mobiles-Management</h3></a>
    <a href="<%= Urls.ADD_MOBILE_CONTROLLER %>"><h3 style="display: inline-block">Add-New-Mobile</h3></a>
    <% } %>	

    <% if (Helper.correctRole(request, userRole, userRole)) { %>
    <a href="<%= Urls.USER_CONTROLLER %>"><h3 style="display: inline-block">Mobiles-List</h3></a>
    <a href="<%= Urls.SHOPPING_CART_CONTROLLER %>"><h3 style="display: inline-block">View-Shopping-Cart</h3></a>
    <% } %>	

    <a href="<%= Urls.LOGOUT_CONTROLLER %>"><h3 style="display: inline-block">Logout</h3></a>

    <% } else { %>
    <a href="<%= Urls.LOGIN_CONTROLLER %>"><h3 style="display: inline-block">Login</h3></a>
    <% }%>	

</div>
