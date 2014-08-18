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
public class EmailSetServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(EmailSetServlet.class);

    private static final long serialVersionUID = 1L;

    /* 
     */
    @Override
    protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
        String enable = req.getParamater("SendForm[enable_warn]");
        String email = req.getParamater("SendForm[warn_mail]");
        if(!EmailValidator.getInstance().isValid(email)){
            request.getSession().setAttribute("emailseterror", "邮箱不合法");
            response.sendRedirect("emailSet.jsp");
            return;
        }
        Long uid = (Long)req.getSession().getAttribute("userid");
        if(uid==null){
            response.sendRedirect("loginWeb.jsp");
            return;
        }
        DBUtils.updateWarnMail(String email, String enable, long uid);
    }

    @Override
    protected void doPost(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
        doGet(req, resp);
    }

}
