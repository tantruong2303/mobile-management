<%-- 
    Document   : shopingCart
    Created on : Jun 19, 2021, 2:30:35 PM
    Author     : Lenovo
--%>

<%@page import="ultils.GetParam"%>
<%@page import="java.util.HashMap"%>
<%@page import="daos.MobileDAO"%>
<%@page import="dtos.Mobile"%>
<%@page import="ultils.Urls"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Cart</title>
    </head>
    <body>
        <%
            String errorMessage = (String) GetParam.getClientAttribute(request, "errorMessage", "");
        %>

        <h1>User Shopping Cart</h1>

        <button><a style="text-decoration: none; color: black" href="<%= Urls.USER_CONTROLLER%> ">USER MENU</a></button>
        <button><a style="text-decoration: none; color: black" href="<%= Urls.LOGOUT_CONTROLLER%>">LOG OUT</a></button>
        <p style="color: red"><%= errorMessage%></p>

        <table border="1" style="text-align: center" >
            <form action="<%= Urls.REMOVE_SHOPPING_CART_CONTROLLER%>">
                <% HashMap<String, Integer> mobileList = (HashMap<String, Integer>) session.getAttribute("cartListId");
                    if (mobileList == null) {
                        mobileList = new HashMap<>();
                    }
                    if (!mobileList.isEmpty()) {
                %>

                <tr>
                    <td>Mobile ID</td>
                    <td>Mobile Name</td>
                    <td>Description</td>
                    <td>Year Of Production</td>
                    <td>Price</td>
                    <td>Quantity</td>
                    <td>Sub total</td>
                    <td>Remove</td>
                </tr>
                <%
                    Float total = 0F;
                    MobileDAO mobileDAO = new MobileDAO();
                    for (String mobileId : mobileList.keySet()) {
                        Mobile mobile = mobileDAO.getOneMobile("mobileId", mobileId);
                        total += mobile.getPrice() * mobileList.get(mobileId);
                %>


                <tr>

                <tr>
                    <td><%= mobile.getMobileId()%></td>
                    <td><%= mobile.getMobileName()%></td>
                    <td><%= mobile.getDescription()%></td>
                    <td><%= mobile.getYearOfProduction()%></td>
                    <td><%= mobile.getPrice()%></td>
                    <td><%= mobileList.get(mobileId)%></td>
                    <td><%= mobile.getPrice() * mobileList.get(mobileId)%></td>
                    <td><input type="checkbox" name="mobileId" value="<%= mobile.getMobileId()%>"></td>
                </tr>
                <% }%>

                <tr>
                    <td><h3>Total: <%= total%></h3></td>
                    <td><a onclick="return confirmation()" href="<%= Urls.ORDER_CONTROLLER%>"><h2>Buy</h2></a></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><button type="submit">Remove</button> </td>
                </tr>

                <% } else { %>
                <h2> Mobile list is empty! </h2>
                <% }%>
            </form>
        </table>

        <script>
            function confirmation() {
                const message = confirm("Do you really want to buy?");
                return message;
            }
        </script>
    </body>
</html>
