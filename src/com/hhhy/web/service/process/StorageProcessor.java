package com.hhhy.web.service.process;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hhhy.db.DBUtils;
import com.hhhy.db.beans.Article;

public class StorageProcessor {
    private static final Logger logger = Logger.getLogger(StorageProcessor.class);
    
    public static long storage(Article art) {
        long id = -1;
         try {
            id = DBUtils.insertArticle(art);
        } catch (SQLException e) {
            logger.warn("can't insert article with url: "+art.getUrl());
            logger.warn(e);
        }
        return id;
    }
}
