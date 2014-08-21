package com.hhhy.core.service.process;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.hhhy.db.DBUtils;
import com.hhhy.db.beans.Article;

public class ProcessChain {
    private static final Logger logger = Logger.getLogger(ProcessChain.class);

    public static void process(Article art) {
//        logger.info("split word for article: "+art.getUrl());
        if(art.getTitle().length()>50){
            art.setTitle(art.getTitle().substring(0,50));
        }
        List<String> contentWords = WordSplitProcessor.split(art.getContent());
        List<String> titleWords = WordSplitProcessor.split(art.getTitle());
//        logger.info("emotionParser for article: "+art.getUrl());
        int titleScore = EmotionAnalysisProcessor.emotionParser(titleWords);
        int contentScore = EmotionAnalysisProcessor.emotionParser(contentWords);
        int score = titleScore<0?titleScore:titleScore + contentScore;
        art.setEmotion(score);

        long id = -1l;
         try {
            id = DBUtils.isArticleExist(art.getUrl());
            if(id<=0){
                id = DBUtils.insertArticle(art);
                if(id<=0){
                    logger.info("insert error: "+art.getUrl());
                    return;
                }
                art.setId(id);
                logger.info("artid: "+art.getId());
                //no need for repeat index if url already exist
                IndexProcessor.addIndex(art);
                if(score<-3){ // only report the first time
                    ReportProcessor.reportProcess(art);
                }
            }else{
                logger.info("duplicated url: "+art.getUrl());
            }
        } catch (SQLException e) {
            logger.warn("can't insert article with url: "+art.getUrl());
            logger.warn(e);
        }

        if (id <= 0) {
            return;
        }

        

        StatisticsProcessor.statistics(art);
    }

}
