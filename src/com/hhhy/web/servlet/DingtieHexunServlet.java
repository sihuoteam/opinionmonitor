package com.hhhy.web.servlet;

import com.hhhy.web.service.webservice.hexun.HexunGuba;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ghost on 2014/9/24 0024.
 */
public class DingtieHexunServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = (String) request.getParameter("url");
        String content =(String) request.getParameter("content");

        String regex = "http://guba.hexun.com/(\\d+),guba,(\\d+)\\.html";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(url);



        if(matcher.find()){
            String tiebaID = matcher.group(1);
            String tieziID = matcher.group(2);
            HexunGuba hexunGuba = new HexunGuba();

            boolean rst = hexunGuba.HexunGubaHuiTie(username, password, tiebaID, tieziID, content);
            if(rst)
                response.getWriter().write("success");
            else
                response.getWriter().write("failed, huifu failed!")
        }
        else{
            response.getWriter().write("failed, didn't find tiebaID or tieziID");
        }
    }
}
