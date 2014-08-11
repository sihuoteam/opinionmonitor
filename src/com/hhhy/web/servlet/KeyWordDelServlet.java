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

import com.hhhy.db.DBUtils;
import com.hhhy.db.beans.KeyWord;

/**
 * 关键词的添加，删除
 * 
 * @author chenlingpeng
 * 
 */
public class KeyWordDelServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(KeyWordDelServlet.class);

    private static final long serialVersionUID = 1L;

    /* 
     * 对应关键词的增删操作
     */
    @Override
    protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
        Long uid = (Long)req.getSession().getAttribute("userid");
        if(uid==null){
            resp.sendRedirect("loginWeb.jsp");
            return;
        }
        String kid = req.getParameter("kid");
        if(kid==null || kid.trim().equals("")){
            // TODO: 不应该出现这种情况，页面检查是否空值
        }else{
            try {
                
                boolean flag = DBUtils.deleteUserKeyWord(uid, Integer.parseInt(kid));
                logger.info("add keyword: uid("+uid+"), keywordid("+kid+")");
//                if(flag)
                List<KeyWord> keyWords = DBUtils.getUserKeyWord(uid);
                if(keyWords.size()>0)
                logger.info("get keyword: "+keyWords.get(0).getKeyword());
                req.setAttribute("keywords", keyWords);
                req.getRequestDispatcher("/keylist.jsp").forward(req, resp);
            } catch (SQLException e) {
                logger.warn(e.getMessage());
            }
        }
        
    }

    @Override
    protected void doPost(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
        doGet(req, resp);
    }

}
