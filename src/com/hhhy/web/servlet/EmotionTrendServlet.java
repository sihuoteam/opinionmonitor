package com.hhhy.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.hhhy.common.utils.JsonUtils;
import com.hhhy.db.DBUtils;
import com.hhhy.db.beans.item.Pair;

/**
 * 
 * @author chenlingpeng
 * 
 */
public class EmotionTrendServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(EmotionTrendServlet.class);

    private static final long serialVersionUID = 1L;

    /* 
     */
    @Override
    protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
        Integer kid = (Integer)req.getSession().getAttribute("kid");
        try {
            if(kid!=null){
                Pair<List<String>,List<Integer>> pair = DBUtils.getEmotionTrendStatis2(kid);
                logger.info("trend size: dateSize: "+pair.getFirst().size()+" trendSize: "+pair.getSecond().size());
                // TODO: test needed
                int size = pair.getFirst().size();
                if(size>0){
                    req.setAttribute("date", JsonUtils.toJson(pair.getFirst()));
                    req.setAttribute("postrend", pair.getSecond().subList(0,size/2));
                    req.setAttribute("negtrend", pair.getSecond().subList(size/2,size));
                }
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        req.getRequestDispatcher("/dimAna_dataSource.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
        doGet(req, resp);
    }

}
