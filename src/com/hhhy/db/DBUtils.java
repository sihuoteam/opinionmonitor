package com.hhhy.db;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.hhhy.common.utils.DateFormatUtils;
import com.hhhy.db.beans.Article;
import com.hhhy.db.beans.EmotionWord;
import com.hhhy.db.beans.KeyWord;
import com.hhhy.db.beans.KeyWordPage;
import com.hhhy.db.beans.User;
import com.hhhy.db.beans.item.Condition;
import com.hhhy.db.beans.item.Pair;
import com.hhhy.db.beans.item.SrcType;

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
    private static final String KEYWORDPAGE_TABLE = "a_keywordpage";
//    private static final String USERKEYWORD_TABLE = "a_userkeyword";

    public static long isArticleExist(String url) throws SQLException {
        String sql = "select * from " + ARTICLE_TABLE + " where url=?";
        Article art = DBOperator.select(sql, new BeanHandler<Article>(
                Article.class), new Object[] { url });
        if (art == null)
            return -1l; // TODO: need check null?
        return art.getId();
    }

    public static long insertArticle(Article article) throws SQLException {
        String sql = "insert into " + ARTICLE_TABLE + "(title,summary, time, url,website,type,emotion) values(?,?,?,?,?,?,?)";
        long time = System.currentTimeMillis();
        if(article.getTime()!=null && !article.getTime().equals("")){
            try {
                time = DateFormatUtils.getTime(article.getTime(), DateFormatUtils.yyyyMMdd);
            } catch (ParseException e) {
                logger.warn(e.getMessage());
            }
        }
        Object[] params = { article.getTitle(), article.getSummary(),
                time, article.getUrl(), article.getWebsite(), 
                article.getType(),article.getEmotion() };
//        return DBOperator.insert(sql, params);
        synchronized (DBUtils.class) {
            boolean flag =  DBOperator.update(sql, params);
            if(flag){
                return DBOperator.maxlong("select max(id) from "+ARTICLE_TABLE);
            }
            return -1;
        }
    }

    /*************************** 账号管理部分 **************************/
    // 不能连续create TODO
    public static long createUser(User user) throws SQLException {
        String sql = "insert into " + ADMIN_TABLE
                + " (email, password) values(?,?)";
        Object[] params = { user.getEmail(), user.getPassword() };
        boolean flag = DBOperator.update(sql, params);
        if(flag){
            return DBOperator.maxlong("select max(id) from "+ADMIN_TABLE);
        }
        return -1;
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

    public static User getUserById(long uid) throws SQLException {
        String sql = "select * from " + ADMIN_TABLE + " where id=?";
        User user = DBOperator.select(sql, new BeanHandler<User>(User.class),
                new Object[] { uid });
        return user;
    }

    public static void updateWarnMail(String email, String enable, long uid) throws SQLException {
        String sql = "update "+ADMIN_TABLE+" set reportemail=?, needemail=? where id=?";
        int able = -1;
        if("1".equals(enable)){
            able=1;
        }else if("0".equals(enable)){
            able=0;
        }
        if(able<0) return;
        DBOperator.update(sql,new Object[]{email, able, uid});
    }

    public static void updateWarnPhone(String phone, String enable, long uid) throws SQLException {
        String sql = "update "+ADMIN_TABLE+" set reportphone=?, needphone=? where id=?";
        int able = -1;
        if("1".equals(enable)){
            able=1;
        }else if("0".equals(enable)){
            able=0;
        }
        if(able<0) return;
        DBOperator.update(sql,new Object[]{phone, able, uid});
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
        String sql = "select * from " + KEYWORD_TABLE +" where uid>0";
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
        String sql = "select distinct keyword from " + KEYWORD_TABLE +" where uid>0";
        List<Object[]> keywords = DBOperator.selectArrayList(sql);
        List<String> keywordList = new ArrayList<String>();
        for(Object[] word:keywords){
            keywordList.add((String)word[0]);
        }
//        List<KeyWord> objs = getAllKeyWordObj();
//        for(KeyWord obj:objs){
//            keywords.add(obj.getKeyword());
//        }
        return keywordList;
        
    }
    
    public static String getKeyWordById(int kid) throws SQLException{
        String sql = "select * from "+KEYWORD_TABLE+ " where id=?";
        KeyWord keyword = DBOperator.select(sql, new BeanHandler<KeyWord>(KeyWord.class), new Object[]{kid});
        if(keyword==null) return null;
        return keyword.getKeyword();
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
        List<KeyWord> keys = getUserKeyWord(userid);
        List<String> keywords = new ArrayList<String>();
        for(KeyWord key:keys){
            keywords.add(key.getKeyword());
        }
        return keywords;
    }

    public static int getKeyWordId(String keyword) throws SQLException {
        String sql = "select * from " + KEYWORD_TABLE + " where keyword=?";
        KeyWord kid = DBOperator.select(sql, new BeanHandler<KeyWord>(
                KeyWord.class), new Object[] { keyword });
        if (kid != null)
            return kid.getId();
        else
            return -1;
    }

    public static List<Long> getUserSet(String keyword) throws SQLException {
        String sql = "select * from " + KEYWORD_TABLE + " where keyword=? and uid>0";
        List<KeyWord> users = DBOperator.select(sql,
                new BeanListHandler<KeyWord>(KeyWord.class),
                new Object[] { keyword });
        List<Long> us = new ArrayList<Long>();
        for(KeyWord u:users){
            us.add(u.getUid());
        }
        return us;

    }

    public static boolean deleteUserKeyWord(long userid, int keywordid)
            throws SQLException {
        String sql = "update "+KEYWORD_TABLE +" set uid=-1 where uid=? and id=?";
        boolean flag = DBOperator.update(sql, new Object[] { userid, keywordid });
        return flag;
    }

    public static boolean addUserKeyWord(long userid, String keyword)
            throws SQLException {
        int kid = getKeyWordId(keyword);
        String sql = null;
        if(kid<0){
            sql = "insert into " + KEYWORD_TABLE
                + "(uid,keyword) values(?,?)";
            sql = "select max(id) from "+KEYWORD_TABLE;
            kid = DBOperator.max(sql);
            kid++;
        }
            sql = "insert into " + KEYWORD_TABLE
                + "(id, uid,keyword) values("+kid+",?,?)";
        
        boolean flag = DBOperator.update(sql, new Object[] { userid, keyword });
//        int kid = getKeyWordId(keyword);
        return flag;
    }
    // TODO: for test
    public static List<Article> getNegArticles(int kid) throws SQLException {
        String sql = "select * from " + KEYWORDPAGE_TABLE
                + " where emotion<0 and kid=? limit 10";
        List<KeyWordPage> pagesid = DBOperator.select(sql, new BeanListHandler<KeyWordPage>(
                KeyWordPage.class), new Object[] { kid });
        List<Article> arts = new ArrayList<Article>();
        sql = "select * from " + ARTICLE_TABLE + " where id=?";
        for (KeyWordPage pageid : pagesid) {
            Article art = DBOperator.select(sql, new BeanHandler<Article>(
                    Article.class), new Object[] { pageid.getPid() });
            if (art != null && art.getTitle() != null
                    && !"".equals(art.getTitle())) {
                art.setContent(""); // 内容置空，减少存储消耗
                art.setSummary("");
                arts.add(art);
            }
        }
        return arts;
    }

    public static List<Article> getRecentArticles(int kid) throws SQLException {
        String sql = "select distinct pid from " + KEYWORDPAGE_TABLE
                + " where kid=? and emotion<>0 order by ctime desc limit 10";
        List<Object[]> pagesid = DBOperator.selectArrayList(sql, new Object[] { kid });
        List<Article> arts = new ArrayList<Article>();
        sql = "select * from " + ARTICLE_TABLE + " where id=?";
        for (Object[] pageid : pagesid) {
            Article art = DBOperator.select(sql, new BeanHandler<Article>(
                    Article.class), new Object[] {(Long)pageid[0]});
            if (art != null && art.getTitle() != null
                    && !"".equals(art.getTitle())) {
                // art.setContent(""); // 内容置空，减少存储消耗
                // art.setSummary("");
                arts.add(art);
            }
        }
        return arts;
    }

    // get all articles for user(uid), should paging
    public static List<Article> getUserArticle2(long uid) throws SQLException {
        String sql = "select distinct a_keywordpage.pid from a_keywordpage inner join a_keyword on a_keyword.id=a_keywordpage.kid where a_keyword.uid=?";
        List<Object[]> pids = DBOperator.selectArrayList(sql, new Object[]{uid});
        List<Article> arts = new ArrayList<Article>();
        for(Object[] pid:pids){
            sql = "select * from "+ARTICLE_TABLE+" where id=?";
            Article art = DBOperator.select(sql, new BeanHandler<Article>(Article.class), new Object[]{(Long)pid[0]});
            if(art!=null && art.getId()>0){
                arts.add(art);
            }
        }
        return arts;
    }

    // TODO: compare with getUserArticle tr verify
    public static List<Article> getUserArticle(long uid) throws SQLException {
        String sql = "select distinct id from "+ KEYWORD_TABLE + " where uid=?";
        List<Object[]> kids = DBOperator.selectArrayList(sql, new Object[]{uid});
        Set<Long> pids = new HashSet<Long>();
        sql = "select distinct pid from "+KEYWORDPAGE_TABLE+" where kid=?";
        for(Object[] kid:kids){
            List<Object[]> _pids = DBOperator.selectArrayList(sql,new Object[]{(Integer)kid[0]});
            for(Object[] pid:_pids){
                pids.add((Long)pid[0]);
            }
        }

        List<Article> arts = new ArrayList<Article>();
        for(Long pid:pids){
            sql = "select * from "+ARTICLE_TABLE+" where id=?";
            Article art = DBOperator.select(sql, new BeanHandler<Article>(Article.class), new Object[]{pid});
            if(art!=null && art.getId()>0){
                arts.add(art);
            }
        }
        return arts;
    }

    /*************************** 关键词处理部分结束 **************************/

    /*************************** 关键词统计部分 **************************/

    public static boolean addTrend(KeyWordPage keyWordPage) throws SQLException {
        String sql ="select count(*) from "+ KEYWORDPAGE_TABLE +" where pid=? and kid=?";
        Long cnt = (Long)DBOperator.select(sql, new ScalarHandler(), new Object[]{keyWordPage.getPid(), keyWordPage.getKid()});
        if(cnt!=null && cnt>0)return false;
        sql = "insert into "
                + KEYWORDPAGE_TABLE
                + "(type,kid,emotion,url,website,ctime,pid) values(?,?,?,?,?,?,?)";
        Object[] params = new Object[] { keyWordPage.getType(),
                keyWordPage.getKid(), keyWordPage.getEmotion(),
                keyWordPage.getUrl(), keyWordPage.getWebsite(),
                keyWordPage.getCtime(), keyWordPage.getPid() };
        return DBOperator.update(sql, params);
    }

    /**
     * 媒体来源统计
     */
    public static Map<String, Integer> getMediaSourceStatis2(int kid)
            throws SQLException {
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
    
    public static Map<String, Integer> getMediaSourceStatis(int kid)
    throws SQLException {
// String sql0 = "select website from "+KEYWORDPAGE_TABLE
// +" where kid=?";
// List<Integer> websites =
String sql = "select website from " + KEYWORDPAGE_TABLE
        + " where kid=?";
//List<String> websites = DBOperator
//        .select(sql, new BeanListHandler<String>(String.class),
//                new Object[] { kid });

List<Object[]> websites = DBOperator.selectArrayList(sql, new Object[] { kid });
Map<String, Integer> res = new HashMap<String, Integer>();
for (Object[] website : websites) {
    int count = MapUtils.getInteger(res, (String)website[0], 0);
    res.put((String)website[0], count + 1);
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
    public static Map<String, Integer> getSourceTypeStatis(int kid)
            throws SQLException {
        String sql = "select type from " + KEYWORDPAGE_TABLE + " where kid=?";
        List<Object[]> types = DBOperator.selectArrayList(sql, new Object[]{kid});
        Map<String, Integer> res = new HashMap<String, Integer>();
        for (Object[] type : types) {
            String stype = SrcType.getName((Integer)type[0]);
            int count = MapUtils.getInteger(res, stype, 0);
            res.put(stype, count + 1);
        }
        return res;
    }

    /**
     * 获取正负
     * deplicated?
     * @param keyword
     * @return
     * @throws SQLException
     */
    public static Pair<Map<String, Integer>, Map<String, Integer>> getEmotionTrendStatis(
            int kid) throws SQLException {
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
        Pair<Map<String, Integer>, Map<String, Integer>> pair = new Pair<Map<String, Integer>, Map<String, Integer>>();
        pair.setFirst(posMap);
        pair.setSecond(negMap);
        return pair;
    }

    public static Pair<List<String>, List<Integer>> getEmotionTrendStatis2(
            int kid) throws SQLException {
        String sql = "select emotion,ctime from " + KEYWORDPAGE_TABLE
                + " where kid=?  order by ctime";
        List<Object[]> emotions = DBOperator.selectArrayList(sql,
                new Object[] { kid });
        List<String> dates = new ArrayList<String>();
        List<Integer> negt = new ArrayList<Integer>();
        List<Integer> post = new ArrayList<Integer>();

        for (Object[] emotion : emotions) {
            int emotionV = (Integer) emotion[0];
            long ctimeV = (Long) emotion[1];
            String dateF = DateFormatUtils.formatTime(ctimeV,
                    DateFormatUtils.yyyyMMdd);
            int size = dates.size();
            if (size == 0 || !dates.get(size-1).equals(dateF)) {
                dates.add(dateF);
                negt.add(0);
                post.add(0);
            }
            size = dates.size();
            if (emotionV > 0) {
                post.set(size - 1, post.get(size - 1) + 1);
            } else if (emotionV < 0) {
                negt.set(size - 1, negt.get(size - 1) + 1);
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
                + " where kid=?";
        int[] counts = new int[3];
        List<Object[]> emotions = DBOperator.selectArrayList(sql,new Object[] {kid});
        for (Object[] _emotion : emotions) {
            int emotion = (Integer)_emotion[0];
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

    public static List<Article> exportData(Condition condition)
            throws SQLException {
        String sql = "select distinct pid from " + KEYWORDPAGE_TABLE
                + " where ";
        // String where = "";
        if (!condition.posNeed() && !condition.negNeed()
                && !condition.plainNeed()) {
            logger.info("no emotion selected");
            return null;
        }
        String[] sources = condition.getSources();
        if (sources == null || sources.length == 0) {
            logger.info("no source selected");
            return null;
        }
        int kid = condition.getKeyword();
        if (kid<0) {
             logger.info("no keywords selected");
             return null;
        }
        long start = condition.getStart();
        long end = condition.getEnd();

        String emotion = "";
        if(condition.negNeed()&&condition.posNeed()&&condition.plainNeed()){
            emotion=null;
        }else if (condition.negNeed()) {
            emotion += "emotion<0";
            if (condition.posNeed()) {
                emotion += " or emotion>0";
            }
            if (condition.plainNeed()) {
                emotion += " or emotion=0";
            }
        } else if (condition.posNeed()) {
            emotion += "emotion>0";
            if (condition.plainNeed()) {
                emotion += " or emotion=0";
            }
        } else if (condition.plainNeed()) {
            emotion += "emotion=0";
        }

        String time = "ctime>" + start + " and ctime<" + end;

        String source = "";
        for (String src : sources) {
            int type = SrcType.getIndex(src);
            if (type > 0) {
                if (source.length() == 0) {
                    source = "type=" + type;
                } else {
                    source += " or type=" + type;
                }
            }
        }

        String keyword = "kid="+kid;
        /*
        for (String kid : keywords) {
            if (keyword.length() == 0) {
                keyword = "kid=" + Integer.parseInt(kid);
            } else {
                keyword += " or kid=" + Integer.parseInt(kid);
            }
        }*/

        String limit = " limit " + condition.getSize();
        String orderby = " order by ctime";
        String where = "";
        if(emotion==null){
            // where = "(" + time + ") and (" + source + ") and (" + keyword + ") ";
            where = "(" + time + ") and (" + source + ") and (" + keyword + ") ";
        }else{
         where = "(" + time + ") and (" + source + ") and (" + emotion
                + ") and (" + keyword + ") ";
        }

        sql += where + orderby + limit;
        logger.info("export sql: " + sql);

        List<Object[]> pids = DBOperator.selectArrayList(sql);
        List<Article> arts = new ArrayList<Article>();

        for (Object[] pid : pids) {
            Article art = DBOperator.select("select * from " + ARTICLE_TABLE
                    + " where id=?", new BeanHandler<Article>(Article.class),
                    new Object[] {(Long)pid[0]});
            if (art.getTitle() != null && !"".equals(art.getTitle())) {
                arts.add(art);
            }
        }

        // where += " and ctime>"+start+" and ctime<"+end;

        return arts;
    }

    public static void importEmotionWord() throws SQLException, IOException{
        // neg
        List<String> lines = FileUtils.readLines(new File("file/负面情感词语（中文）.txt"),"gbk");
        String sql = "insert into "+EMOTIONWORD_TABLE+"(word,val) values(?,?)";
        for(String line:lines){
            line = line.trim();
            DBOperator.update(sql, new Object[]{line,-1});
        }
        lines = FileUtils.readLines(new File("file/正面情感词语（中文）.txt"),"gbk");
        for(String line:lines){
            line = line.trim();
            DBOperator.update(sql, new Object[]{line,1});
        }
    }

    /*************************** data export部分结束 
     * @throws IOException **************************/

    public static void main(String[] args) throws SQLException, IOException {
        Map<String, String> map1 = new ConcurrentHashMap<String, String>();
        map1.put("1", "1");
        Map<String, String> map2 = new ConcurrentHashMap<String, String>(map1);
        map1.clear();
        logger.info(map2);
//        User user = new User();
//        user.setEmail("clp3");
//        user.setPassword("psw");
//        logger.info("1"+createUser(user));
//        logger.info("2"+createUser(user));
//        logger.info("3"+createUser(user));
//        logger.info(getAllKeyWord());
        
        /*logger.info(isArticleExist("http://www.djtz.net/forum.php?mod=redirect&goto=findpost&ptid=2942416&pid=3156303"));
        logger.info(isArticleExist("sdfsd"));
        User user = new User();
        user.setEmail("clp3");
        user.setPassword("psw");
        logger.info("1"+createUser(user));
        logger.info("2"+createUser(user));
        logger.info("3"+createUser(user));
        logger.info("4"+createUser(user));
        logger.info(checkUserExist("clp3"));
        logger.info(checkUserExist("clp344"));
        logger.info(loginCheck("clp3","psw"));
        logger.info(loginCheck("d","d"));
        logger.info(getUserById(45));
        logger.info(getUserById(100)==null);*/
        
        /*updateWarnMail("warn", "1", 45);
        updateWarnPhone("12345", "0", 460);
        
        logger.info(getEmotionWords().get(0).getWord());
        logger.info(getAllKeyWordObj().get(0).getKeyword());
        logger.info(getUserKeyWord(15).get(0).getKeyword());*/
        
//        logger.info(getUserKeyWordStr(158));
//        logger.info(getKeyWordId("关键词1"));
//        logger.info(getKeyWordId("关键词2"));
//        logger.info(getKeyWordId("关键词3"));
        
//        logger.info(getUserSet("关键词1"));
//        logger.info(getUserSet("关键词3"));
//        logger.info(getMediaSourceStatis2(1));
       /* logger.info(getRecentArticles(1));
        logger.info(getRecentArticles(30));
        KeyWordPage page = new KeyWordPage();
        page.setKid(9);
        page.setCtime(123);
        page.setEmotion(-1);
        page.setType(3);
        page.setUrl("url1");
        page.setWebsite("wangyi");
        page.setPid(213);
        logger.info(addTrend(page));
        page.setKid(8);
        page.setCtime(345);
        page.setEmotion(1);
        page.setType(2);
        page.setUrl("url3");
        page.setWebsite("wangyi");
        page.setPid(211);
        logger.info(addTrend(page));
        page.setKid(8);
        page.setCtime(3435);
        page.setEmotion(-1);
        page.setType(2);
        page.setUrl("url4");
        page.setWebsite("wangyi");
        page.setPid(231);
        logger.info(addTrend(page));*/
        /*logger.info(getSourceTypeStatis(1));
        logger.info(getSourceTypeStatis(22));
        Pair<Map<String, Integer>, Map<String, Integer>> pair =  getEmotionTrendStatis(1);
        logger.info(pair.getFirst());
        logger.info(pair.getSecond());
        pair =  getEmotionTrendStatis(2);
        logger.info(pair.getFirst());
        logger.info(pair.getSecond());*/
        /*Pair<List<String>, List<Integer>> pair1 = getEmotionTrendStatis2(1);
        Pair<List<String>, List<Integer>> pair2 = getEmotionTrendStatis2(2);
        logger.info(pair1.getFirst());
        logger.info(pair1.getSecond());
        logger.info(pair2.getFirst());
        logger.info(pair2.getSecond());*/
        /*int[] count1 = getEmotionStatisCount(1);
        int[] count2 = getEmotionStatisCount(2);
        int[] count3 = getEmotionStatisCount(9);
        logger.info(count1[0]+" "+count1[1]+" "+count1[2]);
        logger.info(count2[0]+" "+count2[1]+" "+count2[2]);
        logger.info(count3[0]+" "+count3[1]+" "+count3[2]);*/
        /*Condition condition = new Condition();
        condition.setStart(0);
        condition.setEnd(Long.MAX_VALUE);
        condition.setKeyword(1);
        condition.setSentiments(new int[]{1,1,1});
        condition.setSize(10);
        condition.setSources(new String[]{"bbs","blog","search"});
        logger.info(exportData(condition));*/
//        logger.info(getUserById(45));
//        importEmotionWord();
//        logger.info(DBUtils.deleteUserKeyWord(57, 8));
//        logger.info(DBUtils.getAllKeyWord());
        /*KeyWordPage page = new KeyWordPage();
        page.setKid(6);
        page.setCtime(123);
        page.setEmotion(-1);
        page.setType(3);
        page.setUrl("url1");
        page.setWebsite("wangyi");
        page.setPid(4916);
        logger.info(addTrend(page));
        page.setKid(66);
        logger.info(addTrend(page));*/
//        contert();
//        logger.info(getUserArticle(58).size());
//        logger.info(getUserArticle2(58).size());
    }
    
    public static void contert(){
        try {
            List<String> lines = FileUtils.readLines(new File("file/正面情感词语.txt"),"gbk");
            FileUtils.writeLines(new File("file/正面情感词语.txt"), lines);
            lines = FileUtils.readLines(new File("file/负面情感词语.txt"),"gbk");
            FileUtils.writeLines(new File("file/负面情感词语.txt"), lines);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
