package com.hhhy.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hhhy.db.DBUtils;
import com.hhhy.db.beans.KeyWord;
import com.hhhy.db.beans.PostArt;

public class DingtieDFCFServlet extends HttpServlet {
    /**
     * 
     */
    private static final Logger logger = Logger
            .getLogger(DingtieDFCFServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<PostArt> p = null;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // response.sendRedirect("error.jsp");
        doGet(request, response);
    }

}
