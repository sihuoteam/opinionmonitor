package com.hhhy.web.service.process;

import java.util.List;

import com.hhhy.db.beans.Article;

public class ProcessChain {

    public static void process(Article art) {
        long id = StorageProcessor.storage(art);
        art.setId(id);

        if (id < 0) {
            return;
        }

        List<String> contentWords = WordSplitProcessor.split(art.getContent());
        List<String> titleWords = WordSplitProcessor.split(art.getTitle());

        int titleScore = EmotionAnalysisProcessor.emotionParser(titleWords);
        int contentScore = EmotionAnalysisProcessor.emotionParser(contentWords);

        if (titleScore < 0) {
            art.setEmotion(titleScore);
        } else {
            art.setEmotion(titleScore + contentScore);
        }

        StatisticsProcessor.statistics(art);
        IndexProcessor.addIndex(art, titleWords, contentWords);
    }

}
