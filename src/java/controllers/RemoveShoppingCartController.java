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
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ultils.GetParam;
import ultils.Urls;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "RemoveShoppingCartController", urlPatterns = {"/RemoveShoppingCartController"})
public class RemoveShoppingCartController extends HttpServlet {

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

        String[] mobileIds = GetParam.getStringArrayParams(request, "mobileId", "Mobile ID");
        if (mobileIds == null) {
            return false;
        }

        HttpSession session = request.getSession(false);
        HashMap<String, Integer> cartListId = (HashMap<String, Integer>) session.getAttribute("cartListId");

        if (cartListId == null) {
            request.setAttribute("errorMessage", "Shopping cart is empty!");
            return false;
        }

        for (String mobileId : mobileIds) {
         
            if (!cartListId.containsKey(mobileId)) {
                request.setAttribute("errorMessage", "Mobile is not in your shopping cart!");
                return false;
            }
            
            if (cartListId.containsKey(mobileId)) {
                cartListId.remove(mobileId);
            }
        }

        session.setAttribute("cartListId", cartListId);
        request.setAttribute("message", "Remove items success!");

        return true;

    }

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
            request.getRequestDispatcher(Urls.SHOPPING_CART_CONTROLLER).forward(request, response);

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Something went wrong!");
            request.getRequestDispatcher(Urls.ERROR_PAGE).forward(request, response);
        }

    }

}
