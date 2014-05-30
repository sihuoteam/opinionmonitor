package com.hhhy.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpUtils;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * 关键词的添加，删除
 * 
 * @author chenlingpeng
 * 
 */
public class KeyWordServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(KeyWordServlet.class);

    private static final long serialVersionUID = 1L;

    /* 
     * 对应关键词的增删操作
     */
    @Override
    protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
        String type = req.getParameter("type");
        if (type == null) {
            logger.error("type missing");
        } else {

        }
    }

    @Override
    protected void doPost(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
        doGet(req, resp);
    }

    @Override
    public void init() throws ServletException {
        String path = getInitParameter("log4j.properties");
        path = getServletContext().getRealPath(path);
        PropertyConfigurator.configure(path);
        System.out.println(path);
    }

}
