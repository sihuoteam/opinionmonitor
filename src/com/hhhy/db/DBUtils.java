package com.hhhy.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.Logger;

import com.hhhy.common.utils.DateFormatUtils;
import com.hhhy.db.beans.Article;
import com.hhhy.db.beans.EmotionWord;
import com.hhhy.db.beans.KeyWord;
import com.hhhy.db.beans.KeyWordTrend;
import com.hhhy.db.beans.User;
import com.hhhy.db.beans.item.Condition;
import com.hhhy.db.beans.item.Pair;
import com.hhhy.db.beans.item.SrcType;
import com.sun.org.apache.commons.collections.MapUtils;

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
    private static final String USERKEYWORD_TABLE = "a_userkeyword";
    private static final String KEYWORDPAGE_TABLE = "a_keywordpage";

    public static long isArticleExist(String url) throws SQLException {
        String sql = "select * from " + ARTICLE_TABLE + " where url=?";
        Article art = DBOperator.select(sql, new BeanHandler<Article>(
                Article.class), new Object[] { url });
        if (art == null)
            return -1l; // TODO: need check null?
        return art.getId();
    }

    public static long insertArticle(Article article) throws SQLException {
        String sql = "insert into " + ARTICLE_TABLE + " values(?,?,?,?,?,?,?)";
        Object[] params = { article.getTitle(), article.getSummary(),
                article.getContent(), article.getTime(), article.getUrl(),
                article.getWebsite(), article.getType() };
        return DBOperator.insert(sql, params);
    }

    /*************************** 账号管理部分 **************************/

    public static long createUser(User user) throws SQLException {
        String sql = "insert into " + ADMIN_TABLE
                + " (nickname, email, password) values(?,?,?)";
        Object[] params = { user.getNickname(), user.getEmail(),
                user.getPassword() };
        return DBOperator.insert(sql, params);
    }

    public static boolean checkUserExist(String email) throws SQLException {
        String sql = "select * from " + ADMIN_TABLE + " where email=?";
        User user = DBOperator.select(sql, new BeanHandler<User>(User.class),
                new Object[] { email });
        return user != null && user.getId() > 0;
    }

    public static long loginCheck(String email, String password)
            throws SQLException {
        String sql = "select * from " + ADMIN_TABLE + " where email=?";
        User user = DBOperator.select(sql, new BeanHandler<User>(User.class),
                new Object[] { email });
        if (user != null) {
            logger.info(user.getId());
            if (user.getPassword().equals(password))
                return user.getId();
        }
        return -1l;
    }

    /*************************** 账号管理部分结束 **************************/

    public static List<EmotionWord> getEmotionWords() throws SQLException {
        String sql = "select * from " + EMOTIONWORD_TABLE;
        List<EmotionWord> emotionWords = DBOperator.select(sql,
                new BeanListHandler<EmotionWord>(EmotionWord.class));
        return emotionWords;
    }

    /*************************** 关键词处理部分 **************************/

    public static List<KeyWord> getAllKeyWordObj() throws SQLException {
        String sql = "select * from " + KEYWORD_TABLE;
        List<KeyWord> keywords = DBOperator.select(sql,
                new BeanListHandler<KeyWord>(KeyWord.class));
        return keywords;
    }

    /**
     * 只返回关键词
     * 
     * @return
     * @throws SQLException
     */
    public static List<String> getAllKeyWord() throws SQLException {
        String sql = "select distinct keyword from " + KEYWORD_TABLE;
        List<String> keywords = DBOperator.select(sql,
                new BeanListHandler<String>(String.class));
        return keywords;
    }

    // 不同用户返回不同关键词, 用户信息存放session中
    public static List<KeyWord> getUserKeyWord(long userid) throws SQLException {
        String sql = "select * from " + KEYWORD_TABLE + " where uid=?";
        List<KeyWord> keywords = DBOperator.select(sql,
                new BeanListHandler<KeyWord>(KeyWord.class),
                new Object[] { userid });
        return keywords;
    }

    public static List<String> getUserKeyWordStr(long userid)
            throws SQLException {
        String sql = "select * from " + KEYWORD_TABLE + " where uid=?";
        List<String> keywords = DBOperator.select(sql,
                new BeanListHandler<String>(String.class),
                new Object[] { userid });
        return keywords;
    }

    public static int getKeyWordId(String keyword) throws SQLException {
        String sql = "select id from " + KEYWORD_TABLE + " where keyword=?";
        Integer kid = DBOperator.select(sql, new BeanHandler<Integer>(
                Integer.class), new Object[] { keyword });
        if (kid != null)
            return kid;
        else
            return -1;
    }

    public static boolean deleteUserKeyWord(long userid, int keywordid)
            throws SQLException {
        String sql = "delete from " + KEYWORD_TABLE + " where uid=? and id=?";
        return DBOperator.update(sql, new Object[] { userid, keywordid });
    }

    public static boolean addUserKeyWord(long userid, String keyword)
            throws SQLException {
        String sql = "insert into " + KEYWORD_TABLE
                + "(uid,keyword) values(?,?)";
        return DBOperator.update(sql, new Object[] { userid, keyword });
    }

    public static List<Article> getNegArticles(int kid) throws SQLException {
        String sql = "select pageid from " + KEYWORDPAGE_TABLE
                + " where emotion<0 and kid=? limit 10";
        List<Long> pagesid = DBOperator.select(sql, new BeanListHandler<Long>(
                Long.class), new Object[] { kid });
        List<Article> arts = new ArrayList<Article>();
        sql = "select * from " + ARTICLE_TABLE + " where id=?";
        for (long pageid : pagesid) {
            Article art = DBOperator.select(sql, new BeanHandler<Article>(
                    Article.class), new Object[] { pageid });
            if (art != null && art.getTitle() != null
                    && !"".equals(art.getTitle())) {
                art.setContent(""); // 内容置空，减少存储消耗
                art.setSummary("");
                arts.add(art);
            }
        }
        return arts;
    }

    /*************************** 关键词处理部分结束 **************************/

    /*************************** 关键词统计部分 **************************/

    public static boolean addTrend(KeyWordTrend keyWordTrend) throws SQLException {
        String sql = "insert into " + KEYWORDPAGE_TABLE
                + "(type,kid,emotion,url,website,ctime) values(?,?,?,?,?,?)";
        Object[] params = new Object[] { keyWordTrend.getType(),
                keyWordTrend.getKid(), keyWordTrend.getEmotion(),
                keyWordTrend.getUrl(), keyWordTrend.getWebsite(),
                keyWordTrend.getCtime() };
        return DBOperator.update(sql, params);
    }

    /**
     * 媒体来源统计
     */
    public static Map<String, Integer> getMediaSourceStatis(int kid) throws SQLException {
        // String sql0 = "select website from "+KEYWORDPAGE_TABLE
        // +" where kid=?";
        // List<Integer> websites =
        String sql = "select website from " + KEYWORDPAGE_TABLE
                + " where kid=?";
        List<String> websites = DBOperator
                .select(sql, new BeanListHandler<String>(String.class),
                        new Object[] { kid });
        Map<String, Integer> res = new HashMap<String, Integer>();
        for (String website : websites) {
            int count = MapUtils.getInteger(res, website, 0);
            res.put(website, count + 1);
        }
        return res;
    }

    /**
     * 来源类型统计
     * 
     * @param keyword
     * @return
     * @throws SQLException
     */
    public static Map<String, Integer> getSourceTypeStatis(int kid) throws SQLException {
        String sql = "select type from " + KEYWORDPAGE_TABLE + " where kid=?";
        List<Integer> types = DBOperator.select(sql,
                new BeanListHandler<Integer>(Integer.class),
                new Object[] { kid });
        Map<String, Integer> res = new HashMap<String, Integer>();
        for (Integer type : types) {
            String stype = SrcType.getName(type);
            int count = MapUtils.getInteger(res, stype, 0);
            res.put(stype, count + 1);
        }
        return res;
    }

    /**
     * 获取正负
     * 
     * @param keyword
     * @return
     * @throws SQLException
     */
    public static Pair<Map<String, Integer>,Map<String, Integer>> getEmotionTrendStatis(int kid) throws SQLException {
        String sql = "select emotion,ctime from " + KEYWORDPAGE_TABLE
                + " where kid=?  order by ctime";
        List<Object[]> emotions = DBOperator.selectArrayList(sql,
                new Object[] { kid });

        Map<String, Integer> posMap = new HashMap<String, Integer>();
        Map<String, Integer> negMap = new HashMap<String, Integer>();
        for (Object[] emotion : emotions) {
            int emotionV = (Integer) emotion[0];
            long ctimeV = (Long) emotion[1];
            String time = DateFormatUtils.formatTime(ctimeV,
                    DateFormatUtils.yyyyMMdd);
            if (emotionV > 0) {
                int count = MapUtils.getInteger(posMap, time, 0);
                posMap.put(time, count + 1);
            }
            if (emotionV < 0) {
                int count = MapUtils.getInteger(negMap, time, 0);
                negMap.put(time, count + 1);
            }
        }
        Pair<Map<String, Integer>,Map<String, Integer>> pair = new Pair<Map<String, Integer>,Map<String, Integer>>();
        pair.setFirst(posMap);
        pair.setSecond(negMap);
        return pair;
    }

    public static Pair<List<String>, List<Integer>> getEmotionTrendStatis2(int kid) throws SQLException {
        String sql = "select emotion,ctime from " + KEYWORDPAGE_TABLE
                + " where kid=?  order by ctime";
        List<Object[]> emotions = DBOperator.selectArrayList(sql,
                new Object[] { kid });
        List<String> dates = new ArrayList<String>();
        List<Integer> negt = new ArrayList<Integer>();
        List<Integer> post = new ArrayList<Integer>();
        
        for(Object[] emotion:emotions){
            int emotionV = (Integer) emotion[0];
            long ctimeV = (Long) emotion[1];
            String dateF = DateFormatUtils.formatTime(ctimeV, DateFormatUtils.yyyyMMdd);
            int size = dates.size();
            if(size==0 || !dates.get(size).equals(dateF)){
                dates.add(dateF);
                negt.add(0);
                post.add(0);
            }
            size = dates.size();
            if(emotionV>0){
                post.set(size-1, post.get(size-1)+1);
            }else if(emotionV<0){
                negt.set(size-1, negt.get(size-1)+1);
            }
        }
        Pair<List<String>, List<Integer>> pair = new Pair<List<String>, List<Integer>>();
        pair.setFirst(dates);
        post.addAll(negt);
        pair.setSecond(post);
        
        return pair;
    }

    /**
     * 获取 正负平 情感的统计量
     * 
     * @param keyword
     * @return
     * @throws SQLException
     */
    public static int[] getEmotionStatisCount(int kid) throws SQLException {
        String sql = "select emotion from " + KEYWORDPAGE_TABLE
                + " where keyword=?";
        int[] counts = new int[3];
        List<Integer> emotions = DBOperator.select(sql,
                new BeanListHandler<Integer>(Integer.class),
                new Object[] { kid });
        for (Integer emotion : emotions) {
            if (emotion > 0) {
                counts[0]++;
            } else if (emotion < 0) {
                counts[1]++;
            } else {
                counts[2]++;
            }
        }
        return counts;
    }

    /*************************** 关键词统计部分结束 **************************/

    /*************************** data export部分 **************************/

    public static List<Article> exportData(Condition condition) throws SQLException {
        return null;
    }

    /*************************** data export部分结束 **************************/

    public static void main(String[] args) throws SQLException {
        // Article a = new Article("title1", "content", "url", "website");
        // insertArticle(a);
        // logger.info(DigestUtils.md5Hex("6"));
        // User user = new User("nickname1", "email1", DigestUtils
        // .md5Hex("password1"));
        // logger.info("..." + createUser(user));
        // logger.info(checkUserExist("email2"));
        // logger.info(loginCheck("email3","password13"));
        // logger.info(getEmotionWords().get(0).getWord());
        logger.info(getUserKeyWord(9l).size());
        logger.info(getUserKeyWord(9l).get(0).getId());
        logger.info(getUserKeyWord(9l).get(0).getUid());
        logger.info(getUserKeyWord(9l).get(0).getKeyword());
        
    }

}
