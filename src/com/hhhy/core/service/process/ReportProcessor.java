package com.hhhy.core.service.process;

import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;

import com.hhhy.common.utils.StringUtils;
import com.hhhy.db.DBUtils;
import com.hhhy.db.beans.Article;
import com.hhhy.db.beans.User;
import com.hhhy.web.service.notice.EmailEntry;
import com.hhhy.web.service.notice.EmailReport;

public class ReportProcessor {
    private static Logger logger = Logger.getLogger(ReportProcessor.class);

    public static void reportProcess(Article art){
        logger.info(art.getUrl()+"'s emotion is "+art.getEmotion()+", will enter report process");
        try {
            List<Long> users = DBUtils.getUserSet(art.getKeyword());
            for(Long userid:users){
                User user = DBUtils.getUserById(userid);
                if(user!=null && user.getEmail()!=null){
                    int needemail = user.getNeedemail();
                    int needphone = user.getNeedphone();
                    String reportemail = user.getReportemail();
                    String reportphone = user.getReportphone();
                    if(needemail==1){
                        sendEmail(reportemail,art);
                    }
                    if(needphone==1){
                        sendMessage(reportphone,art);
                    }
                }
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
    }

    public static void sendEmail(String email, Article art){
        if(!StringUtils.notEmpty(email)){return;}
        logger.info("will send email to: "+email);
        EmailEntry entry = new EmailEntry();
        entry.subject = "邮件告警";
        entry.text = "有新的重要舆情需要关注: <br>标题: "+art.getTitle()+
            "<br>摘要: "+art.getSummary()+"<br>URL: <a href=\""+art.getUrl()+"\">这里</a>";
        entry.toAddresses = email;
        try {
            EmailReport.sendMail(entry);
        } catch (MessagingException e) {
            logger.warn(e.getMessage());
        }
    }

    public static void sendMessage(String phone, Article art){
        // TODO
        if(!StringUtils.notEmpty(phone)){return;}
        logger.info("will send message to: "+phone);
    }
    
}
