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
@WebServlet(name = "AddToCartController", urlPatterns = {"/AddToCartController"})
public class AddToCartController extends HttpServlet {

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
        if (mobileId == null) {
            return false;
        }

        Mobile mobile = mobileDAO.getOneMobile("mobileId", mobileId);

        if (mobile == null) {
            request.setAttribute("errorMessage", "Mobile ID is not correct!");
            return false;
        }
        
        if (mobile.isNotSale()) {
            request.setAttribute("errorMessage", "Mobile is not sale!");
            return false;
        }

        HttpSession session = request.getSession(false);
        HashMap<String, Integer> cartListId = (HashMap<String, Integer>) session.getAttribute("cartListId");
        HashMap<String, Integer> updateCartListId;
        if (cartListId == null) {
            updateCartListId = new HashMap<>();
            updateCartListId.put(mobile.getMobileId(), 1);
            
        } else {
            updateCartListId = cartListId;
            if (updateCartListId.containsKey(mobile.getMobileId())) {
                
                if (updateCartListId.get(mobile.getMobileId()) >= mobile.getQuantity()) {
                    request.setAttribute("errorMessage", "Mobile quantity is not enough!");
                    return false;
                }
                
                updateCartListId.put(mobile.getMobileId(), updateCartListId.get(mobile.getMobileId()) + 1);
            }
            else {
                    updateCartListId.put(mobile.getMobileId(), 1);
            }
        }

        session.setAttribute("cartListId", updateCartListId);
        request.setAttribute("message", "Add to card success!");

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
            request.getRequestDispatcher(Urls.USER_CONTROLLER).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Something went wrong!");
            request.getRequestDispatcher(Urls.ERROR_PAGE).forward(request, response);
        }
    }

}
