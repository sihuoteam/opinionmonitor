package com.hhhy.db;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hhhy.db.beans.Article;

/**
 * 对数据库操作集中于此
 * 
 * @author chenlingpeng
 * 
 */
public class DBUtils {
    private static final Logger logger = Logger.getLogger(DBUtils.class);
    private static final String ARTICLE_TABLE = "a_webpage";
    private static final String ADMIN_TABLE = "a_admin";

    public static boolean insertArticle(Article article) throws SQLException {
        String sql = "insert into " + ARTICLE_TABLE + " values(?,?,?,?,?,?)";
        Object[] params = { article.getTitle(), article.getSummary(),
                article.getContent(), article.getTime(), article.getUrl(),
                article.getWebsite() };
        return DBOperator.update(sql, params);
    }

    public static void main(String[] args) throws SQLException {
        Article a = new Article("title1", "content", "url", "website");
        insertArticle(a);
    }
}
