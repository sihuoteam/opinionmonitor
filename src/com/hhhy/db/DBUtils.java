package com.hhhy.db;

import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.log4j.Logger;

import com.hhhy.db.beans.Article;
import com.hhhy.db.beans.User;

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

    public static long createUser(User user) throws SQLException {
        String sql = "insert into " + ADMIN_TABLE
                + " (nickname, email, password) values(?,?,?)";
        Object[] params = { user.getNickname(), user.getEmail(),
                user.getPassword() };
        return DBOperator.insert(sql, params);
    }
    
    public static boolean checkUserExist(String email) throws SQLException{
        String sql = "select * from "+ADMIN_TABLE +" where email='"+email+"'";
        User user = DBOperator.select(sql, new BeanHandler<User>(User.class));
        return user==null;
    }

    public static void main(String[] args) throws SQLException {
//        Article a = new Article("title1", "content", "url", "website");
//        insertArticle(a);
        logger.info(DigestUtils.md5Hex("6"));
        User user = new User("nickname1","email1",DigestUtils.md5Hex("password1"));
        logger.info("..."+createUser(user));
    }
}
