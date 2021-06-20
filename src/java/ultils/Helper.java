/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultils;

import daos.MobileDAO;
import daos.OrderDAO;
import dtos.Mobile;
import dtos.Order;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lenovo
 */
public class Helper {

    public static boolean protectedRouter(HttpServletRequest request, HttpServletResponse response, int minRole,
            int maxRole, String page) throws Exception {

        if (!isLogin(request) || !correctRole(request, minRole, maxRole)) {
            request.setAttribute("errorMessage", "Action is not allow, please login first");
            request.getRequestDispatcher(page).forward(request, response);

            return false;
        }

        return true;
    }

    public static boolean isLogin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return false;
        }
        String userId = (String) session.getAttribute("userId");

        return userId != null;
    }

    public static boolean correctRole(HttpServletRequest request, int minRole, int maxRole) {
        HttpSession session = request.getSession(false);
        Integer role = (Integer) session.getAttribute("role");

        return role != null && role >= minRole && role <= maxRole;
    }

    public static String generateMobileId() throws Exception {
        MobileDAO mobileDAO = new MobileDAO();

        ArrayList<Mobile> list = mobileDAO.getMobiles(0, Float.MAX_VALUE);

        return Integer.toString(list.size() + 1);
    }

    public static Integer generateOrderId() throws Exception {
        OrderDAO orderDAO = new OrderDAO();

        ArrayList<Order> list = orderDAO.getAllOrders();

        return (list.size() + 1);
    }

    public static Date getCurrentDate() {
        Date date = new Date(System.currentTimeMillis());
        return date;
    }
}
