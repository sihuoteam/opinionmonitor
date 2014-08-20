package com.hhhy.web.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hhhy.db.beans.item.SrcType;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.hhhy.common.utils.DateFormatUtils;
import com.hhhy.common.utils.JsonUtils;
import com.hhhy.db.DBUtils;
import com.hhhy.db.beans.Article;
import com.hhhy.db.beans.KeyWord;
import com.hhhy.db.beans.item.Condition;
import org.apache.poi.xssf.usermodel.*;

/**
 * report export
 * 
 * @author chenlingpeng
 * 
 */
public class ReportExportServlet extends HttpServlet {
    private static final Logger logger = Logger
            .getLogger(ReportExportServlet.class);

    private static final long serialVersionUID = 1L;

    /*
     * 对应关键词的增删操作
     */
    @Override
    protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
        // TODO:
        String start_date = req.getParameter("start_date");
        String end_date = req.getParameter("end_date");
        String[] merge = req.getParameterValues("merge");
        String[] sources = req.getParameterValues("source[]");
        String[] emotions = req.getParameterValues("sentiment[]");
        // String[] topic_ids = req.getParameterValues("topic_id[]");
        String[] fields = req.getParameterValues("field[]");
        Integer kid = (Integer) req.getSession().getAttribute("kid");
        if (kid == null) {
            resp.sendRedirect("keylist");
            return;
        }
        
        // String[] firstTitles = new
        // String[]{"关键字","标题","摘要","url","来源","媒体","情感"，"时间"};
        // topic title summary url source media sentiment time
        Map<String, String> fieldsMap = new HashMap<String, String>();
        fieldsMap.put("关键字", "topic");
        fieldsMap.put("标题", "title");
        fieldsMap.put("摘要", "summary");
        fieldsMap.put("url", "url");
        fieldsMap.put("来源", "source");
        fieldsMap.put("媒体", "media");
        fieldsMap.put("情感", "sentiment");
        fieldsMap.put("时间", "time");
        String limit = req.getParameter("limit");
        String source = JsonUtils.toJson(sources);
        String emotion = JsonUtils.toJson(emotions);
        // String topic_id = JsonUtils.toJson(topic_ids);
        String field = JsonUtils.toJson(fields);
        Condition condition = new Condition();
        condition.setSize(Integer.parseInt(limit));
        // condition.setKeywords(topic_ids);
        condition.setSources(sources);
        int[] emotionArr = new int[3];
        for (String em : emotions) {
            if (em.equals("positive")) {
                emotionArr[0] = 1;
            } else if (em.equals("negative")) {
                emotionArr[1] = 1;
            } else if (em.equals("neutral")) {
                emotionArr[2] = 1;
            }
        }
        condition.setSentiments(emotionArr);
        try {
            long start = DateFormatUtils.getTime(start_date,
                    DateFormatUtils.yyyyMMdd);
            long end = DateFormatUtils.getTime(end_date,
                    DateFormatUtils.yyyyMMdd);
            condition.setStart(start);
            condition.setEnd(end);
        } catch (ParseException e) {
            logger.warn(e.getMessage());
        }

        try {
            String topic = DBUtils.getKeyWordById(kid);
            condition.setKeyword(kid);
            List<Article> arts = DBUtils.exportData(condition);
            // List<Article> arts = new LinkedList<Article>();
            // Article art0 = new Article();
            // // art.setContent("content");
            // art0.setTime("11");
            // art0.setEmotion(2);
            // art0.setSummary("summary");
            // art0.setTitle("title");
            // art0.setType(2);
            // art0.setUrl("url");
            // art0.setWebsite("website");
            //
            // arts.add(art0);

            // 生成新版xls
            /*
             * 关键字 标题 摘要 来源 url 媒体 情感 时间
             */
            XSSFWorkbook xwb = new XSSFWorkbook();
            XSSFSheet sheet = xwb.createSheet("datas");
            // 第一行标题准备
            XSSFRow firstRow = sheet.createRow(0);
            XSSFCell[] firstCell = new XSSFCell[fields.length];
            // String[] firstTitles = new
            // String[]{"关键字","标题","摘要","url","来源","媒体","情感"，"时间"};
            for (int i = 0; i < fields.length; i++) {
                firstCell[i] = firstRow.createCell(i);
                firstCell[i].setCellValue(new XSSFRichTextString(fieldsMap
                        .get(fields[i])));
            }
            int i = 1;
            // 写入数据部分
            for (Article art : arts) {
                XSSFRow row = sheet.createRow(i);
                XSSFCell[] cells = new XSSFCell[fields.length];
                for (int j = 0; j < fields.length; j++) {
                    cells[j] = row.createCell(j);
                    cells[j].setCellValue(new XSSFRichTextString(getFieldValue(
                            fields[j], art, topic)));
                }
                i++;
            }

            // 输出文件
            OutputStream out = new FileOutputStream("D:/index/WriteXlsx.xlsx");
            xwb.write(out);
            out.close();

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        // logger.info(source);
        // logger.info(emotion);
        // logger.info(topic_id);
        // logger.info(field);
        // logger.info(limit);
        // logger.info(JsonUtils.toJson(merge));
        // try {
        // logger.info(DateFormatUtils.formatTime(DateFormatUtils.getTime(start_date,
        // "yyyy-MM-dd"),DateFormatUtils.yyyyMMddhhmmss));
        // } catch (ParseException e) {
        // logger.warn(e.getMessage());
        // }

        resp.sendRedirect("loginWeb.jsp");
    }

    private String getFieldValue(String field, Article art, String topic) {
        /*
         * 关键字 标题 摘要 来源 url 媒体 情感 topic title summary url source media sentiment
         * id url type website time emotion
         */
        if (field.equals("topic")) {
            return topic;
        } else if (field.equals("title")) {
            return art.getTitle();
        } else if (field.equals("summary")) {
            return art.getSummary();
        } else if (field.equals("url")) {
            return art.getUrl();
        } else if (field.equals("source")) {
            // news("新闻", 1), bbs("论坛", 2), blog("博客", 3), elePaper("搜索引擎", 4);
            int type = art.getType();
            if (type == 1) {
                return "新闻";
            } else if (type == 2) {
                return "论坛";
            } else if (type == 3) {
                return "博客";
            } else if (type == 4) {
                return "搜索引擎";
            } else {
                return null;
            }
        } else if (field.equals("media")) {
            return art.getWebsite();
        } else if (field.equals("sentiment")) {
            int emotion = art.getEmotion();
            if (emotion >= 0) {
                return "正面";
            } else
                return "负面";
        } else
            return null;

        // return null;
    }

    @Override
    protected void doPost(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
        doGet(req, resp);
    }

}
