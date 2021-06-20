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

    public static String getStringParam(HttpServletRequest request, String field, String label, int min, int max,
            String defaultValue) {
        String value = (String) request.getParameter(field);

        if (value == null || value.trim().isEmpty()) {
            if (defaultValue == null) {
                request.setAttribute(field + "Error", label + " is required");
                return null;
            }

            return defaultValue;
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

    public static Integer getIntParams(HttpServletRequest request, String field, String label, int min, int max,
            Integer defaultValue) {

        String value = (String) request.getParameter(field);
        Integer realValue;

        if (value == null || value.isEmpty()) {
            if (defaultValue == null) {
                request.setAttribute(field + "Error", label + " is required");
                return null;
            }
            return defaultValue;
        }
        try {
            realValue = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            request.setAttribute(field + "Error", label + " must be a number and less than " + Integer.MAX_VALUE);
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

    public static Float getFloatParams(HttpServletRequest request, String field, String label, float min, float max,
            Float defaultValue) {

        String value = (String) request.getParameter(field);
        Float realValue;
        if (value == null || value.isEmpty()) {
            if (defaultValue == null) {
                request.setAttribute(field + "Error", label + " is required");
                return null;
            }
            return defaultValue;
        }

        try {
            realValue = Float.parseFloat(value);
        } catch (NumberFormatException e) {

            request.setAttribute(field + "Error", label + " must be a number and less than " + Float.MAX_VALUE);
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

    public static String[] getStringArrayParams(HttpServletRequest request, String field, String label) {
        String[] input = request.getParameterValues(field);
        if (input == null) {
            request.setAttribute(field + "Error", label + "is required");
            return null;
        }

        return input;
    }

    public static Object getClientAttribute(HttpServletRequest request, String field, Object defaultValue) {
        Object value = request.getAttribute(field);
        if (value == null) {
            return defaultValue;
        }

        return value;
    }

    public static Object getClientParams(HttpServletRequest request, String field, Object defaultValue) {
        Object value = request.getParameter(field);
        if (value == null) {
            return defaultValue;
        }

        return value;
    }
}
