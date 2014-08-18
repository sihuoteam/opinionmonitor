package com.hhhy.core.service.process;

import java.util.List;

import com.hhhy.db.beans.Article;

public class ReportProcessor {
    private static Logger logger = Logger.getLogger(ReportProcessor.class);

    public static void reportProcess(Article art){
        List<Long> users = DBUtils.getUserSet(art.getKeyWord());
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
    }

    public static void sendEmail(String email, Article art){
        if(!StringUtls.notEmpty(email)){return;}
        logger.info("will send email to: "+email);
        EmailEntry entry = new EmailEntry();
        entry.subject = "邮件告警";
        entry.text = "有新的重要舆情需要关注: <br>标题: "+art.getTitle()+
            "<br>摘要: "+art.getSummarize()+"<br>URL: <a href=\""+art.getUrl()+"\">这里</a>";
        entry.toAddresses = email;
        try {
            EmailReport.sendMail(entry);
        } catch (MessagingException e) {
            logger.warn(e.getMessage());
        }
    }

    public static void sendMessage(String phone, Article art){
        // TODO
        if(!StringUtls.notEmpty(phone)){return;}
        logger.info("will send message to: "+phone);
    }
    
}
