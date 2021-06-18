/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.UserDAO;
import dtos.User;
import java.io.IOException;
import javax.naming.Context;
import javax.naming.InitialContext;
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
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    /**
     * Processes requests for HTTP <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected boolean processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // initialize resource
        UserDAO userDAO = new UserDAO();

        // validate params
        String userId = GetParam.getStringParam(request, "userId", "User ID", 1, 20, null);
        Integer password = GetParam.getIntParams(request, "password", "Password", 1, Integer.MAX_VALUE, null);
        if (userId == null || password == null) {
            return false;
        }

        // checking exist user and correct password
        User user = userDAO.getOneUserByUserId(userId);
        if (user == null || !(user.getPassword() == password)) {
            request.setAttribute("errorMessage", "User Id or Password is not correct");
            return false;
        }

        // handle on login
        HttpSession session = request.getSession();

        session.setAttribute("userId", user.getUserId());
        session.setAttribute("role", user.getRole());
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
        request.getRequestDispatcher(Urls.LOGIN_PAGE).forward(request, response);
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
                Context env = (Context) new InitialContext().lookup("java:comp/env");
                Integer userRole = (Integer) env.lookup("userRole");
                Integer staffRole = (Integer) env.lookup("staffRole");
                
                HttpSession session = request.getSession();
                int role = (int) session.getAttribute("role");

                if (role == userRole) {
                    request.getRequestDispatcher(Urls.USER_PAGE).forward(request, response);
                }
                if (role == staffRole) {
                    request.getRequestDispatcher(Urls.STAFF_PAGE).forward(request, response);
                }
                return;
            }

            request.getRequestDispatcher(Urls.LOGIN_PAGE).forward(request, response);

        } catch (Exception e) {
            request.getRequestDispatcher(Urls.ERROR_PAGE).forward(request, response);
        }
    }

}
