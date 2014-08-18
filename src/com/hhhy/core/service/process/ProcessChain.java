package com.hhhy.core.service.process;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.hhhy.db.DBUtils;
import com.hhhy.db.beans.Article;

public class ProcessChain {
    private static final Logger logger = Logger.getLogger(ProcessChain.class);

    public static void process(Article art) {
        List<String> contentWords = WordSplitProcessor.split(art.getContent());
        List<String> titleWords = WordSplitProcessor.split(art.getTitle());

        int titleScore = EmotionAnalysisProcessor.emotionParser(titleWords);
        int contentScore = EmotionAnalysisProcessor.emotionParser(contentWords);

        if (titleScore < 0) {
            art.setEmotion(titleScore);
        } else {
            art.setEmotion(titleScore + contentScore);
        }
        
        long id = -1l;
         try {
            id = DBUtils.isArticleExist(art.getUrl());
            if(id<0){
                id = DBUtils.insertArticle(art);
                art.setId(id);
                //no need for repeat index if url already exist
                IndexProcessor.addIndex(art);
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
