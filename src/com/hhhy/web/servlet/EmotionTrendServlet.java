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
        if(kid!=null){
            Pair<List<String>,List<Integer>> pair = DBUtils.getEmotionTrendStatis2(Integer.parseInt(kid));
            logger.info("trend size: dateSize: "+pair.getFirst().size()+" trendSize: "+pair.getSecond().size());
            // TODO: test needed
            int size = pair.getFirst().size();
            if(size>0){
                request.setAttribute("date", JsonUtils.toJson(pair.getFirst()));
                request.setAttribute("postrend", pair.getSecond().subList(0,size/2));
                request.setAttribute("negtrend", pair.getSecond().subList(size/2,size));
            }
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
