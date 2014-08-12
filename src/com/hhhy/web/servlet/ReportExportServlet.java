package com.hhhy.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.hhhy.common.utils.DateFormatUtils;
import com.hhhy.common.utils.JsonUtils;
import com.hhhy.db.DBUtils;
import com.hhhy.db.beans.KeyWord;

/**
 * report export
 * @author chenlingpeng
 * 
 */
public class ReportExportServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ReportExportServlet.class);

    private static final long serialVersionUID = 1L;

    /* 
     * 对应关键词的增删操作
     */
    @Override
    protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
        // TODO: 
        String start_date = req.getParameter("start_date");
        String end_date = req.getParameter("end_date");
        String[] merge = req.getParameterValues("merge");
        String[] sources = req.getParameterValues("source[]");
        String[] emotions = req.getParameterValues("sentiment[]");
        String[] topic_ids = req.getParameterValues("topic_id[]");
        String[] fields = req.getParameterValues("field[]");
        String limit = req.getParameter("limit");
        String source = JsonUtils.toJson(sources);
        String emotion = JsonUtils.toJson(emotions);
        String topic_id = JsonUtils.toJson(topic_ids);
        String field = JsonUtils.toJson(fields);
        logger.info(source);
        logger.info(emotion);
        logger.info(topic_id);
        logger.info(field);
        logger.info(limit);
        logger.info(JsonUtils.toJson(merge));
        try {
            logger.info(DateFormatUtils.formatTime(DateFormatUtils.getTime(start_date, "yyyy-MM-dd"),DateFormatUtils.yyyyMMddhhmmss));
        } catch (ParseException e) {
            logger.warn(e.getMessage());
        }
        
        
        
        resp.sendRedirect("loginWeb.jsp");
    }

    @Override
    protected void doPost(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
        doGet(req, resp);
    }

}
