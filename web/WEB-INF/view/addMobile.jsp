<%-- 
    Document   : addMobile
    Created on : Jun 18, 2021, 4:39:27 PM
    Author     : Lenovo
--%>

<%@page import="ultils.Urls"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Add Mobile</title>
    </head>
    <body>
        <%
            String mobileIdError = (String) request.getAttribute("mobileIdError");
            String descriptionError = (String) request.getAttribute("descriptionError");
            String priceError = (String) request.getAttribute("priceError");
            String mobileNameError = (String) request.getAttribute("mobileNameError");
            String yearOfProductionError = (String) request.getAttribute("yearOfProductionError");
            String quantityError = (String) request.getAttribute("quantityError");
            String notSaleError = (String) request.getAttribute("notSaleError");
            String addMobileError = (String) request.getAttribute("addMobileError");

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
            if (addMobileError == null) {
                addMobileError = "";
            }
        %>

        <h1>Add Mobile Form</h1>
        <button><a style="text-decoration: none; color: black" href="<%= Urls.STAFF_CONTROLLER %>">STAFF MENU</a></button>
        <button><a style="text-decoration: none; color: black" href="<%= Urls.LOGIN_CONTROLLER %>">LOG OUT</a></button>
        <form action="<%= Urls.ADD_MOBILE_CONTROLLER %>" method="POST" id="addMobileForm">
            <table>
                <tr>
                    <td></td>
                    <td style="color: red"><%= addMobileError%></td>
                </tr>
                <tr>
                    <td>Description:</td>
                    <td><textarea form="addMobileForm" name="description" > </textarea></td>
                </tr>
                <tr>
                    <td></td>
                    <td style="color: red"><%= descriptionError%></td>
                </tr>
                <tr>
                    <td>Price:</td>
                    <td><input type="number" name="price" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td style="color: red"><%= priceError%></td>
                </tr>
                <tr>
                    <td>Mobile Name:</td>
                    <td><input type="text" name="mobileName" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td style="color: red"><%= mobileNameError%></td>
                </tr>
                <tr>
                    <td>Year Of Production:</td>
                    <td><input type="number" name="yearOfProduction" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td style="color: red"><%= yearOfProductionError%></td>
                </tr>
                <tr>
                    <td>Quantity:</td>
                    <td><input type="number" name="quantity" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td style="color: red"><%= quantityError%></td>
                </tr>
                <tr>
                    <td>Not Sale:</td>
                    <td>
                        <input type="radio" id="notSale" name="notSale" value="0" />
                        <label for="notSale">Not sale</label>
                        <input type="radio" id="sale" name="notSale" value="1" />
                        <label for="sale">Sale</label>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td style="color: red"><%= notSaleError%></td>
                </tr>
                <tr>
                    <td><button type="submit">Add Mobile</button></td>
                </tr>
            </table>
        </form>
    </body>
</html>
