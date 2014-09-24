package com.hhhy.web.servlet;

import com.hhhy.web.service.webservice.hexun.HexunGuba;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ghost on 2014/9/24 0024.
 */
public class FatieHexunServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = (String) request.getParameter("url");
        String content =(String) request.getParameter("content");
        String title =(String) request.getParameter("title");

        String regex = "http://guba.hexun.com/(\\d+),guba";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(url);
        if(matcher.find()){
            String tiebaID = matcher.group(1);

            HexunGuba hexunGuba = new HexunGuba();

            boolean rst = hexunGuba.HexunGubFatie(username, password, tiebaID, title, content);
            if(rst)
                response.getWriter().write("success");
            else
                response.getWriter().write("failed, fatie failed!")
        }
        else{
            response.getWriter().write("failed, didn't find tiebaID");
        }
    }
}
