package com.hhhy.core.service.process;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;

import com.hhhy.common.utils.StringUtils;
import com.hhhy.db.DBUtils;
import com.hhhy.db.beans.Article;
import com.hhhy.db.beans.User;
import com.hhhy.web.service.notice.EmailEntry;
import com.hhhy.web.service.notice.EmailReport;
import com.sun.org.apache.commons.collections.MapUtils;

public class ReportProcessor {
    private static Logger logger = Logger.getLogger(ReportProcessor.class);
    private static Map<String, String> emailWaiting = new ConcurrentHashMap<String, String>();
    private static Map<String, String> phoneWaiting = new ConcurrentHashMap<String, String>();
    static{
        Timer timer = new Timer("email-sending-task");
        TimerTask emailTask = new EmailTask();
        timer.schedule(emailTask, 1l, 1000*60*10);
    }

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
        String content = MapUtils.getString(emailWaiting, email, "");
        content+="有新的重要舆情需要关注: <br>标题: "+art.getTitle()+
        "<br>摘要: "+art.getSummary()+"<br>URL: "+art.getUrl()+"<br>";
        emailWaiting.put(email, content);
    }

    public static void sendMessage(String phone, Article art){
        // TODO
        if(!StringUtils.notEmpty(phone)){return;}
        logger.info("will send message to: "+phone);
    }
    
    public static class EmailTask extends TimerTask {

        @Override
        public void run() {
            logger.info("run email sending task");
            Map<String, String> tmp = new ConcurrentHashMap<String, String>(emailWaiting);
            emailWaiting.clear();
            Set<String> users = tmp.keySet();
            for(String user:users){
                String content = tmp.get(user);
                if(content==null || content.length()<10) continue;
                EmailEntry entry = new EmailEntry();
                entry.subject = "邮件告警";
                entry.text = content;
                entry.toAddresses = user;
                try {
                    EmailReport.sendMail(entry);
                } catch (MessagingException e) {
                    logger.warn(e.getMessage());
                }
            }
//            emailWaiting.clear();
        }
        
    }
    
}
