/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.MobileDAO;
import dtos.Mobile;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ultils.GetParam;
import ultils.Helper;
import ultils.Urls;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "UpdateMobileController", urlPatterns = {"/UpdateMobileController"})
public class UpdateMobileController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected boolean processRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        MobileDAO mobileDAO = new MobileDAO();

        // validate params
        String mobileId = GetParam.getStringParam(request, "mobileId", "Mobile ID", 1, 10, null);
        String description = GetParam.getStringParam(request, "description", "Description", 1, 250, null);
        Float price = GetParam.getFloatParams(request, "price", "Price", 0, Float.MAX_VALUE, null);
        String mobileName = GetParam.getStringParam(request, "mobileName", "Mobile Name", 1, 20, null);
        Integer yearOfProduction = GetParam.getIntParams(request, "yearOfProduction", "Year Of Production", 2000, 2021, null);
        Integer quantity = GetParam.getIntParams(request, "quantity", "Quantity", 1, Integer.MAX_VALUE, null);
        Integer notSale = GetParam.getIntParams(request, "notSale", "Not Sale", 0, 1, null);

        if (mobileId == null || description == null || price == null || mobileName == null || yearOfProduction == null || quantity == null || notSale == null) {

            return false;
        }

        Mobile mobile = new Mobile(mobileId, description, price, mobileName,
                yearOfProduction, quantity, notSale == 1);
        mobileDAO.updateOneMobile(mobile);

        return true;
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (processRequest(request, response)) {
                response.sendRedirect(Urls.STAFF_CONTROLLER);
            } else {
                MobileDAO mobileDAO = new MobileDAO();
                ArrayList<Mobile> mobileList = mobileDAO.getMobiles(0, Float.MAX_VALUE);
                request.setAttribute("mobileList", mobileList);
                request.getRequestDispatcher(Urls.STAFF_PAGE).forward(request, response);
            }
        } catch (Exception e) {
            request.getRequestDispatcher(Urls.ERROR_PAGE).forward(request, response);
        }
    }
}
