package com.hhhy.core.service.process;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hhhy.db.DBUtils;
import com.hhhy.db.beans.Article;
import com.hhhy.db.beans.KeyWordTrend;

/**
 * 关键词各项统计
 * @author chenlingpeng
 * 
 */
public class StatisticsProcessor {
    private static final Logger logger = Logger.getLogger(StatisticsProcessor.class);

    public static void statistics(Article art) {
//        statisticsOpinionType(art);
//        statisticsSrcType(art);
//        statisticsMediaType(art);
        String keyword = art.getKeyword();
        try {
            int kid = DBUtils.getKeyWordId(keyword);
            KeyWordTrend trend = new KeyWordTrend();
            trend.setCtime(System.currentTimeMillis());
            trend.setEmotion(art.getEmotion());
            trend.setKid(kid);
            trend.setType(art.getType());
            trend.setUrl(art.getUrl());
            trend.setWebsite(art.getWebsite());
            DBUtils.addTrend(trend);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        
    }

    private static void statisticsOpinionType(Article art) {
        // TODO:
    }

    private static void statisticsSrcType(Article art) {
        // TODO:
    }

    private static void statisticsMediaType(Article art) {
        // TODO:
    }
}
