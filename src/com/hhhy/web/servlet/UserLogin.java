package com.hhhy.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hhhy.common.utils.StringUtils;
import com.hhhy.db.DBUtils;

public class UserLogin extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UserLogin.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = (String) request.getParameter("username");
        String psw = (String) request.getParameter("psw");
        if (!(StringUtils.notEmpty(username) && StringUtils.notEmpty(psw))) {
            response.sendRedirect("login.jsp");
        }
        try {
            long uid = DBUtils.loginCheck(username, psw);
            if(uid<0){
                logger.info("login error: "+username);
            }
            request.getSession().setAttribute("uid", uid);
            request.getSession().setAttribute("name", "chenlingpeng");
            // request.getSession().setAttribute("password", "password");
            request.setAttribute("name", "chenlingpeng");
            request.setAttribute("password", "password");
            request.getRequestDispatcher("/keylist.jsp").forward(request,
                    response);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("error.jsp");
        doGet(request, response);
    }

}
