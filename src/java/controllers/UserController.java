/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.MobileDAO;
import dtos.Mobile;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ultils.GetParam;
import ultils.Urls;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

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

        Float min = GetParam.getFloatParams(request, "minPrice", "Min price", 0f, Float.MAX_VALUE, 0f);
        Float max = GetParam.getFloatParams(request, "maxPrice", "Max price", 0, Float.MAX_VALUE, Float.MAX_VALUE);

        if (min == null || max == null) {
            return false;
        }

        if (min >= max) {
            request.setAttribute("errorMessage", "Min Price must be greater than max price");
            return false;
        }

        ArrayList<Mobile> mobileList = mobileDAO.getMobiles(min, max);

        request.setAttribute("mobileList", mobileList);
        return true;

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
            request.getRequestDispatcher(Urls.USER_PAGE).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Something went wrong!");
            request.getRequestDispatcher(Urls.ERROR_PAGE).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }

}
