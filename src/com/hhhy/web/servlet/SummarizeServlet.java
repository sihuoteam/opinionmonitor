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
        logger.info("will summarize for kid: "+kid);
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
            Map<String, Integer> mediaStatis = DBUtils.getMediaSourceStatis(Integer.parseInt(kid));
            Map<String, Integer> sourceStatis =DBUtils.getSourceTypeStatis(Integer.parseInt(kid));
            request.setAttribute("mediaStatis", mediaStatis);
            request.setAttribute("sourceStatis", sourceStatis);
            request.getRequestDispatcher("/sentimentSummarize.jsp").forward(request, response);
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
