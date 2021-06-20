<%-- 
    Document   : addMobile
    Created on : Jun 18, 2021, 4:39:27 PM
    Author     : Lenovo
--%>

<%@page import="ultils.GetParam"%>
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
            String descriptionError = (String) GetParam.getClientAttribute(request, "descriptionError", "");
            String priceError = (String) GetParam.getClientAttribute(request, "priceError", "");
            String mobileNameError = (String) GetParam.getClientAttribute(request, "mobileNameError", "");
            String yearOfProductionError = (String) GetParam.getClientAttribute(request, "yearOfProductionError", "");
            String quantityError = (String) GetParam.getClientAttribute(request, "quantityError", "");
            String notSaleError = (String) GetParam.getClientAttribute(request, "notSaleError", "");
            String errorMessage = (String) GetParam.getClientAttribute(request, "errorMessage", "");

            String description = (String) GetParam.getClientParams(request, "description", "");
            String price = (String) GetParam.getClientParams(request, "price", "");
            String mobileName = (String) GetParam.getClientParams(request, "mobileName", "");
            String yearOfProduction = (String) GetParam.getClientParams(request, "yearOfProduction", "");
            String quantity = (String) GetParam.getClientParams(request, "quantity", "");
            String notSale = (String) GetParam.getClientParams(request, "notSale", "");
        %>

        <%@include file="navbar.jsp" %>
        <h1>Add Mobile Form</h1>
        <form action="<%= Urls.ADD_MOBILE_CONTROLLER%>" method="POST" id="addMobileForm">
            <table>
                <tr>
                    <td></td>
                    <td style="color: red"><%= errorMessage%></td>
                </tr>
                <tr>
                    <td>Description:</td>
                    <td><textarea form="addMobileForm" name="description" ><%= description%></textarea></td>
                </tr>
                <tr>
                    <td></td>
                    <td style="color: red"><%= descriptionError%></td>
                </tr>
                <tr>
                    <td>Price:</td>
                    <td><input type="number" name="price" value="<%= price%>"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td style="color: red"><%= priceError%></td>
                </tr>
                <tr>
                    <td>Mobile Name:</td>
                    <td><input type="text" name="mobileName" value="<%= mobileName%>"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td style="color: red"><%= mobileNameError%></td>
                </tr>
                <tr>
                    <td>Year Of Production:</td>
                    <td><input type="number" name="yearOfProduction" value="<%= yearOfProduction %>"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td style="color: red"><%= yearOfProductionError%></td>
                </tr>
                <tr>
                    <td>Quantity:</td>
                    <td><input type="number" name="quantity" value="<%= quantity%>" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td style="color: red"><%= quantityError %></td>
                </tr>
                <tr>
                    <td>Not Sale:</td>
                    <td>
                        <% if (notSale.equals("1")) { %>
                        <input type="radio" id="notSale" name="notSale" value="0" />
                        <label for="notSale">No</label>
                        <input type="radio" id="sale" name="notSale" value="1" checked/>
                        <label for="sale">Yes</label>
                        <% } else if (notSale.equals("0")) { %>
                        <input type="radio" id="notSale" name="notSale" value="0" checked/>
                        <label for="notSale">No</label>
                        <input type="radio" id="sale" name="notSale" value="1" />
                        <label for="sale">Yes</label>
                        <% } else {%>
                        <input type="radio" id="notSale" name="notSale" value="0" />
                        <label for="notSale">No</label>
                        <input type="radio" id="sale" name="notSale" value="1" />
                        <label for="sale">Yes</label>
                        <% }%>
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
