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
import com.hhhy.db.DBOperator;
import com.hhhy.db.DBUtils;
import com.hhhy.db.beans.Article;
import com.hhhy.db.beans.item.Pair;

public class SummarizeServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(SummarizeServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String kid = (String)request.getParameter("kid");
        if(kid==null || "".equals(kid)) response.sendRedirect("login.jsp");
        try {
            int[] counts = DBUtils.getEmotionStatisCount(Integer.parseInt(kid));
            request.setAttribute("poscount", counts[0]);
            request.setAttribute("negcount", counts[1]);
            request.setAttribute("plaincount", counts[2]);
            Pair<Map<String,Integer>> pair = DBUtils.getEmotionTrendStatis(Integer.parseInt(kid));
            String posTrend = JsonUtils.toJson(pair.getFirst());
            String negTrend = JsonUtils.toJson(pair.getSecond());
            request.setAttribute("postrend", posTrend);
            request.setAttribute("negtrend", negTrend);
            List<Article> arts = DBUtils.getNegArticles(Integer.parseInt(kid));
            request.setAttribute("negarts", arts);
            request.getRequestDispatcher("/sentimentSummarize.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            logger.warn(e.getMessage());
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
