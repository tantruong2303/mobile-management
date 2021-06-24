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
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "StaffController", urlPatterns = {"/StaffController"})
public class StaffController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected boolean processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        MobileDAO mobileDAO = new MobileDAO();

        String search = GetParam.getStringParam(request, "search", "Name or ID", 1, 20, "");

        ArrayList<Mobile> allMobileList = mobileDAO.getAllMobiles();
        ArrayList<Mobile> result = new ArrayList<>();

        for (int i = 0; i < allMobileList.size(); i++) {
            if (allMobileList.get(i).getMobileId().contains(search) || allMobileList.get(i).getMobileName().contains(search)) {
                result.add(allMobileList.get(i));
            }
        }

        if (result.isEmpty()) {
            request.setAttribute("errorMessage", "No result is found!");
            return false;
        }

        request.setAttribute("mobileList", result);
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
            request.getRequestDispatcher(Urls.STAFF_PAGE).forward(request, response);
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
