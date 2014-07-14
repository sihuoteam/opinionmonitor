package com.hhhy.db;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.Logger;

import com.hhhy.db.beans.Article;
import com.hhhy.db.beans.EmotionWord;
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
    private static final String KEYWORD_TABLE = "a_keyword";
    private static final String EMOTIONWORD_TABLE = "a_emotionword";

    public static long insertArticle(Article article) throws SQLException {
        String sql = "insert into " + ARTICLE_TABLE + " values(?,?,?,?,?,?,?)";
        Object[] params = { article.getTitle(), article.getSummary(),
                article.getContent(), article.getTime(), article.getUrl(),
                article.getWebsite(),article.getType() };
        return DBOperator.insert(sql, params);
    }

    public static long createUser(User user) throws SQLException {
        String sql = "insert into " + ADMIN_TABLE
                + " (nickname, email, password) values(?,?,?)";
        Object[] params = { user.getNickname(), user.getEmail(),
                user.getPassword() };
        return DBOperator.insert(sql, params);
    }

    public static boolean checkUserExist(String email) throws SQLException {
        String sql = "select * from " + ADMIN_TABLE + " where email=?";
        User users = DBOperator.select(sql, new BeanHandler<User>(
                User.class),new Object[]{email});
        return users != null;
    }

    public static boolean loginCheck(String email, String password)
            throws SQLException {
        String sql = "select * from " + ADMIN_TABLE + " where email=?";
        User user = DBOperator.select(sql, new BeanHandler<User>(User.class), new Object[]{email});
        if (user != null) {
            logger.info(user.getId());
            return user.getPassword().equals(DigestUtils.md5Hex(password));
        }
        return false;
    }
    
    public static List<EmotionWord> getEmotionWords() throws SQLException{
        String sql = "select * from "+EMOTIONWORD_TABLE;
        List<EmotionWord> emotionWords = DBOperator.select(sql, new BeanListHandler<EmotionWord>(EmotionWord.class));
        return emotionWords;
    }

    public static void main(String[] args) throws SQLException {
        // Article a = new Article("title1", "content", "url", "website");
        // insertArticle(a);
        // logger.info(DigestUtils.md5Hex("6"));
        // User user = new User("nickname1", "email1", DigestUtils
        // .md5Hex("password1"));
        // logger.info("..." + createUser(user));
//        logger.info(checkUserExist("email2"));
//         logger.info(loginCheck("email3","password13"));
        logger.info(getEmotionWords().get(0).getWord());
    }
    
    
}
