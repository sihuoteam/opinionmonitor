package com.hhhy.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * 
 * @author chenlingpeng
 * 
 */
public class DataSourceServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(DataSourceServlet.class);

    private static final long serialVersionUID = 1L;

    /* 
     */
    @Override
    protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
        Integer kid = (Integer)req.getSession().getAttribute("kid");
        if(kid!=null){
            Map<String, Integer> sourceStatis = DBUtils.getMediaSourceStatis(kid);
            request.setAttribute("sourceStatis", sourceStatis);
        }
        request.getRequestDispatcher("/dimAna_dataSource.jsp").forward(request, response);
    }

    @Override
    protected void doPost(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
        doGet(req, resp);
    }

}
