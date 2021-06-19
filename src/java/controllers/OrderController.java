/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.MobileDAO;
import daos.OrderDAO;
import daos.OrderDetailDAO;
import daos.UserDAO;
import dtos.Mobile;
import dtos.Order;
import dtos.OrderDetail;
import dtos.User;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ultils.Helper;
import ultils.Urls;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "OrderController", urlPatterns = {"/OrderController"})
public class OrderController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        MobileDAO mobileDAO = new MobileDAO();
        UserDAO userDAO = new UserDAO();
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        OrderDAO orderDAO = new OrderDAO();

        HttpSession session = request.getSession(false);
        HashMap<String, Integer> cartListId = (HashMap<String, Integer>) session.getAttribute("cartListId");

        if (cartListId == null || cartListId.isEmpty()) {
            request.setAttribute("errorMessage", "Shopping cart is empty!");
            return false;
        }

        Integer orderId = Helper.generateOrderId();
        String userId = (String) session.getAttribute("userId");
        User user = userDAO.getOneUserByUserId(userId);
        Date orderDate = Helper.getCurrentDate();
        float total = 0F;

        Order order = new Order(orderId, user.getUserId(), orderDate, total);
        if (!orderDAO.addOneOrder(order)) {
            request.setAttribute("errorMessage", "Some thing went wrong!");
            return false;
        }

        for (String mobileId : cartListId.keySet()) {
            Mobile mobile = mobileDAO.getOneMobile("mobileId", mobileId);
            if (mobile != null) {
                OrderDetail orderDetail = new OrderDetail(orderId, mobileId, mobile.getPrice(), cartListId.get(mobileId));

                if (orderDetail.getQuantity() > mobile.getQuantity()) {
                    request.setAttribute("errorMessage", "Invalid quantity!");
                    return false;
                }

                if (!orderDetailDAO.addOneOrderDetail(orderDetail)) {
                    request.setAttribute("errorMessage", "Some thing went wrong!");
                    return false;
                }

                mobile.setQuantity(mobile.getQuantity() - orderDetail.getQuantity());

                if (!mobileDAO.updateOneMobile(mobile)) {
                    request.setAttribute("errorMessage", "Mobile ID is not correct!");
                    return false;
                }
                total += orderDetail.getQuantity() * orderDetail.getUnitPrice();
            }
        }

        order.setTotal(total);
        if (!orderDAO.updateOneOrder(order)) {
            request.setAttribute("errorMessage", "Some thing went wrong!");
            return false;
        }

        session.removeAttribute("cartListId");

        request.setAttribute("errorMessage", "Buy success!");
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
            if (processRequest(request, response)) {
                MobileDAO mobileDAO = new MobileDAO();
                ArrayList<Mobile> mobileList = mobileDAO.getMobiles(0, Float.MAX_VALUE);
                request.setAttribute("mobileList", mobileList);
                request.getRequestDispatcher(Urls.USER_PAGE).forward(request, response);
            } else {
                request.getRequestDispatcher(Urls.SHOPPING_CART_PAGE).forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher(Urls.ERROR_PAGE).forward(request, response);
        }
    }

}