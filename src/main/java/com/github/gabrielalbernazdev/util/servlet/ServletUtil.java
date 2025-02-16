package com.github.gabrielalbernazdev.util.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.github.gabrielalbernazdev.util.constants.ParamConstants;

public final class ServletUtil {

    private ServletUtil() {}

    public static void forward(HttpServletRequest request, HttpServletResponse response, String target)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(target);
        dispatcher.forward(request, response);
    }

    public static void redirect(HttpServletResponse response, String target) throws IOException {
        response.sendRedirect(target);
    }

    public static void forwardWithError(HttpServletRequest request, HttpServletResponse response, 
                                        String errorMessage, String target)
            throws ServletException, IOException {
        request.setAttribute(ParamConstants.ERROR_PARAM, errorMessage);
        forward(request, response, target);
    }


    public static void redirectWithError(HttpServletRequest request, HttpServletResponse response, 
                                         String errorMessage, String target)
            throws IOException {
        request.getSession().setAttribute(ParamConstants.ERROR_PARAM, errorMessage);
        redirect(response, target);
    }

    public static void clearError(HttpServletRequest request) {
        request.getSession().removeAttribute(ParamConstants.ERROR_PARAM);
    }
}

