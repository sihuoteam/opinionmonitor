package com.hhhy.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hhhy.common.utils.JsonUtils;
import com.hhhy.db.DBUtils;
import com.hhhy.db.beans.Article;
import com.hhhy.db.beans.item.Pair;

public class MonitorServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(MonitorServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String kid = (String)request.getParameter("kid");
        if(kid==null || "".equals(kid)){
            Integer k = (Integer)request.getSession().getAttribute("kid");
            if(k!=null){
                kid=k+"";
            }
        }else{
            request.getSession().setAttribute("kid", Integer.parseInt(kid));
        }

        logger.info("will summarize for kid: "+kid);
        if(kid==null || "".equals(kid)) response.sendRedirect("loginWeb.jsp");
        try {
            // TODO: 需要加所有舆情文章
            List<Article> arts = DBUtils.getUserArticle(Integer.parseInt(kid));
            request.getSession().setAttribute("all", arts);
            request.getRequestDispatcher("/sentimentMonitor.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            logger.warn(e.getMessage());
            response.sendRedirect("error.jsp");
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            response.sendRedirect("error.jsp");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
