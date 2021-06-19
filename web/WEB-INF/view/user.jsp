<%-- 
    Document   : user
    Created on : Jun 18, 2021, 4:55:56 PM
    Author     : Lenovo
--%>

<%@page import="ultils.Urls"%>
<%@page import="dtos.Mobile"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
    </head>
    <body>
        <%
            String minPriceError = (String) request.getAttribute("minPriceError");
            String maxPriceError = (String) request.getAttribute("maxPriceError");
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (minPriceError == null) {
                minPriceError = "";
            }
            if (maxPriceError == null) {
                maxPriceError = "";
            }
            if (errorMessage == null) {
                errorMessage = "";
            }
        %>

        <h1>User Menu</h1>


        <form action="<%= Urls.USER_CONTROLLER%>" method="GET">
            <div>
                <div style="display: inline-block">
                    <label for="minPrice">Min Price</label>
                    <input type="number" name="minPrice" value="0" id="minPrice" />
                </div>
                <div style="display: inline-block">
                    <label for="maxPrice">Max Price</label>
                    <input type="number" name="maxPrice" value="9999999" id="maxPrice" />
                </div>
            </div>
            <button type="submit">Search</button> 
        </form>
        <button><a style="text-decoration: none; color: black" href="<%= Urls.SHOPPING_CART_CONTROLLER%>">SHOPPING CART</a></button>
        <button><a style="text-decoration: none; color: black" href="<%= Urls.LOGOUT_CONTROLLER%>">LOG OUT</a></button>
        <p style="color: red"><%= errorMessage + minPriceError + maxPriceError%></p>

        <table style="text-align: center" >
            <% ArrayList<Mobile> mobileList = (ArrayList<Mobile>) request.getAttribute("mobileList");
                if (mobileList == null) {
                    mobileList = new ArrayList<>();
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
                <td>Not Sale</td>
            </tr>
            <%
                for (Mobile mobile : mobileList) {
            %>


            <tr>

            <tr>
                <td><%= mobile.getMobileId()%></td>
                <td><%= mobile.getMobileName()%></td>
                <td><%= mobile.getDescription()%></td>
                <td><%= mobile.getYearOfProduction()%></td>
                <td><%= mobile.getPrice()%></td>
                <td><%= mobile.getQuantity()%></td>
                <td><%= mobile.isNotSale()%></td>
                <td><a href="<%= Urls.ADD_TO_CART_CONTROLLER%>?mobileId=<%= mobile.getMobileId()%>">Add To Cart</a></td>
            </tr>
            <%      }
            } else {
            %>
            <h2> Mobile list is empty! </h2>
            <%
                }
            %>

        </table>


        <script>
            function confirmation() {
                const message = confirm("Do you really want to delete this mobile?");
                return message;
            }
        </script>
    </body>
</html>
