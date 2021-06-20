<%-- 
    Document   : staff
    Created on : Jun 16, 2021, 4:15:20 PM
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
        <title>Staff Page</title>
    </head>
    <body>
        <%
            String searchError = (String) request.getAttribute("searchError");
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (searchError == null) {
                searchError = "";
            }
            if (errorMessage == null) {
                errorMessage = "";
            }
            String mobileId = (String) request.getParameter("mobileId");
            String mobileIdError = (String) request.getAttribute("mobileIdError");
            String descriptionError = (String) request.getAttribute("descriptionError");
            String priceError = (String) request.getAttribute("priceError");
            String mobileNameError = (String) request.getAttribute("mobileNameError");
            String yearOfProductionError = (String) request.getAttribute("yearOfProductionError");
            String quantityError = (String) request.getAttribute("quantityError");
            String notSaleError = (String) request.getAttribute("notSaleError");

            if (mobileId == null) {
                mobileId = "";
            }
            if (mobileIdError == null) {
                mobileIdError = "";
            }
            if (descriptionError == null) {
                descriptionError = "";
            }
            if (priceError == null) {
                priceError = "";
            }
            if (mobileNameError == null) {
                mobileNameError = "";
            }
            if (yearOfProductionError == null) {
                yearOfProductionError = "";
            }
            if (quantityError == null) {
                quantityError = "";
            }
            if (notSaleError == null) {
                notSaleError = "";
            }
        %>

        <h1>Staff Menu</h1>


        <form action="<%=Urls.SEARCH_CONTROLLER%>" method="GET">

            <div>
                <input type="text" name="search" placeholder="Enter Name or ID" />
            </div>

            <button type="submit">Search</button> 
        </form>
        <button><a style="text-decoration: none; color: black" href="<%= Urls.ADD_MOBILE_CONTROLLER%>">ADD NEW MOBILE DEVICE</a></button>
        <button><a style="text-decoration: none; color: black" href="<%= Urls.LOGOUT_CONTROLLER%>">LOG OUT</a></button>
        <p style="color: red"><%= errorMessage + searchError + mobileIdError%></p>

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
                <td>Price</td>
                <td>Description</td>
                <td>Year Of Production</td>
                <td>Quantity</td>
                <td>Not Sale</td>
            </tr>
            <% for (Mobile mobile : mobileList) {%>



            <form action="<%= Urls.UPDATE_MOBILE_CONTROLLER%>" method="POST">
                <tr>
                    <td><input type="text" name="mobileId" value="<%= mobile.getMobileId()%>" readonly="true"/></td>
                    <td><input type="text" name="mobileName" value="<%= mobile.getMobileName()%>"/></td>
                    <td><input type="text" name="price" value="<%= mobile.getPrice()%>"/></td>
                    <td><input type="text" name="description" value="<%= mobile.getDescription()%>"/></td>
                    <td><input type="text" name="yearOfProduction" value="<%= mobile.getYearOfProduction()%>"/></td>
                    <td><input type="text" name="quantity" value="<%= mobile.getQuantity()%>"/></td>
                    <td><% if (!mobile.isNotSale()) { %>
                        <input type="radio" id="notSale" name="notSale" value="0" />
                        <label for="notSale">Not sale</label>
                        <input type="radio" id="sale" name="notSale" value="1" checked/>
                        <label for="sale">Sale</label>
                        <% } else { %>
                        <input type="radio" id="notSale" name="notSale" value="0" checked/>
                        <label for="notSale">Not sale</label>
                        <input type="radio" id="sale" name="notSale" value="1" />
                        <label for="sale">Sale</label>
                        <% }%>
                    </td> <td><button type="submit">Edit</button> </td>
                    <td><button><a onclick="return confirmation()" href="<%=Urls.DELETE_MOBILE_CONTROLLER%>?mobileId=<%= mobile.getMobileId()%>" >Delete</a></button></td>
                </tr>
            </form>

            <tr>
                <% if (mobile.getMobileId().equals(mobileId)) {%>
                <td style="color: red"><%= mobileIdError%></td>
                <td style="color: red"><%= mobileNameError%></td>
                <td style="color: red"><%= priceError%></td>
                <td style="color: red"><%= descriptionError%></td>
                <td style="color: red"><%= yearOfProductionError%></td>
                <td style="color: red"><%= quantityError%></td>
                <td style="color: red"><%= notSaleError%></td>
                <% } %>
            </tr>


            <% }
            } else { %>
            <h2> Mobile list is empty! </h2>
            <% }%>

        </table>


        <script>
            function confirmation() {
                const message = confirm("Do you really want to delete this mobile?");
                return message;
            }
        </script>
    </body>
</html>
