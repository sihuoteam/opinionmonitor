package com.hhhy.db.beans;


/**
 * 对关键词各项统计类
 * 对应表a_keywordtrend
 * 暂时直接用webpage表做统计
 * @author chenlingpeng
 *
 */
public class KeyWordTrend {
    private int kid;
    // private long pageid;
    private int emotion;
    private int type;
    private String website;
    private long ctime;
    private String url;

    public int getKid() {
        return kid;
    }

    public void setKid(int kid) {
        this.kid = kid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getEmotion() {
        return emotion;
    }

    public void setEmotion(int emotion) {
        this.emotion = emotion;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }
}
