package com.hhhy.core.service.process;

import java.util.List;

import com.hhhy.db.beans.Article;
import com.hhhy.core.lucene.FSIndexBuilder;

public class IndexProcessor {
    public static void addIndex(Article art, List<String> titleWords,
            List<String> contentWords) {

    }

    public static void addIndex(Article art) {
        FSIndexBuilder.getInstance().addArticle(art);
    }

    public static List<Long> query() {
        // TODO
        return null;
    }
}
