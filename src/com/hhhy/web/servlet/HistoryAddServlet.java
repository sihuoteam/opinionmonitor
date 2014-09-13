package com.hhhy.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hhhy.common.utils.DateFormatUtils;
import com.hhhy.db.DBUtils;
import com.hhhy.db.beans.KeyWord;

public class HistoryAddServlet extends HttpServlet {
    /**
     * 
     */
    private static final Logger logger = Logger.getLogger(HistoryAddServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	Long uid = (Long)request.getSession().getAttribute("userid");
        if(uid==null || uid<0){
            response.sendRedirect("loginWeb.jsp");
        }
        KeyWord keyword = (KeyWord)request.getSession().getAttribute("keyword");
        if(keyword==null){
            response.sendRedirect("keylist.jsp");
        }
    	
    	String start_date = request.getParameter("start_date");
        String end_date = request.getParameter("end_date");
        
        try {
            long start = DateFormatUtils.getTime(start_date,
                    DateFormatUtils.yyyyMMdd2);
            long end = DateFormatUtils.getTime(end_date,
                    DateFormatUtils.yyyyMMdd2);
            end+=24*60*60*1000;
            DBUtils.addHistoryKeyword(keyword.getKeyword(), start, end);
        } catch (ParseException e) {
            logger.warn(e.getMessage());
        } catch (SQLException e) {
			e.printStackTrace();
		}
    	
        

        
        
        
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.sendRedirect("error.jsp");
        doGet(request, response);
    }

}
