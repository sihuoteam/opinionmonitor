package com.hhhy.core.service.process;

import com.hhhy.db.beans.Article;

/**
 * 关键词各项统计
<<<<<<< HEAD
 * 暂时不需要
=======
 * 
>>>>>>> parent of ab0050e... spider update
 * @author chenlingpeng
 * 
 */
public class StatisticsProcessor {

    public static void statistics(Article art) {
        statisticsOpinionType(art);
        statisticsSrcType(art);
        statisticsMediaType(art);
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
