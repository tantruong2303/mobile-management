package filter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ultils.Helper;
import ultils.Urls;

/**
 *
 * @author Lenovo
 */
@WebFilter(filterName = "BothControllerFilter", urlPatterns = {"/" + Urls.LOGOUT_CONTROLLER})
public class BothControllerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        try {
            Context env = (Context) new InitialContext().lookup("java:comp/env");
            Integer staffRole = (Integer) env.lookup("staffRole");
            Integer userRole = (Integer) env.lookup("userRole");
            if (!Helper.protectedRouter(req, res, userRole, staffRole, Urls.LOGIN_PAGE)) {
                return;
            }

            chain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher(Urls.ERROR_PAGE).forward(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
