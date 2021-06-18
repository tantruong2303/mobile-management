<%-- 
    Document   : user
    Created on : Jun 18, 2021, 4:55:56 PM
    Author     : Lenovo
--%>

<%@page import="dtos.Mobile"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff Page</title>
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

        <h1>Staff Menu</h1>

        <table border="1"  style="text-align: center">
            <form action="StaffController" method="GET">
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
                <button><a style="text-decoration: none; color: black" href="addMobile.jsp">ADD NEW MOBILE DEVICE</a></button>
                <button><a style="text-decoration: none; color: black" href="servletController?action=logout">LOG OUT</a></button>
                <p style="color: red"><%= errorMessage + minPriceError + maxPriceError %></p>
            </form>
                <% ArrayList<Mobile> mobileList = (ArrayList<Mobile>) request.getAttribute("mobileList");
                if (mobileList == null) {
                    mobileList = new ArrayList<>();
                }
                if (mobileList.size() != 0) {
                    for (Mobile mobile : mobileList) {
            %>
            <tr>
            </tr>
            <tr>
                <td>Mobile ID</td>
                <td>Mobile Name</td>
                <td>Price</td>
                <td>Description</td>
                <td>Year Of Production</td>
                <td>Quantity</td>
                <td>Not Sale</td>
            </tr>

            <tr>
                <td><%= mobile.getMobileId()%></td>
                <td><%= mobile.getMobileName()%></td>
                <td><%= mobile.getPrice()%></td>
                <td><%= mobile.getDescription()%></td>
                <td><%= mobile.getYearOfProduction()%></td>
                <td><%= mobile.getQuantity()%></td>
                <td><%= mobile.isNotSale()%></td>
                <td><a href="servletController?action=updateMobile&mobileId=<%= mobile.getMobileId()%>">Edit</a></td>
                <td><a onclick="return confirmation()" href="servletController?action=deleteMobile&mobileId=<%= mobile.getMobileId()%>" >Delete</a></td>
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
