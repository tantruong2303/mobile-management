/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultils;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Lenovo
 */
public class GetParam {

    public static String getStringParam(HttpServletRequest request, String field, String label, int min, int max) {
        String value = (String) request.getParameter(field);
        if (value == null) {
            request.setAttribute(field + "Error", label + " is required");
            return null;
        }
        if (value.trim().length() > max) {
            request.setAttribute(field + "Error", label + " is less than " + max + " character(s)");
            return null;
        }
        if (value.trim().length() < min) {
            request.setAttribute(field + "Error", label + " is greater than " + min + " character(s)");
            return null;
        }

        return value;
    }

    public static Integer getIntParams(HttpServletRequest request, String field, String label, int min, int max) {

        String value = (String) request.getParameter(field);
        Integer realValue;
        if (value == null) {
            request.setAttribute(field + "Error", label + " is required");
            return null;
        }
        try {
            realValue = Integer.parseInt(value);
        } catch (Exception e) {
            request.setAttribute(field + "Error", label + " must be a number");
            return null;
        }
        if (realValue > max) {
            request.setAttribute(field + "Error", label + " is less than " + max);
            return null;
        }
        if (realValue < min) {
            request.setAttribute(field + "Error", label + " is greater than " + min);
            return null;
        }

        return realValue;
    }
}