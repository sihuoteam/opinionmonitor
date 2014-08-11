package com.hhhy.db.beans;


/**
 * 对关键词各项统计类
 * 对应表a_keywordtrend
 * 暂时直接用webpage表做统计
 * @author chenlingpeng
 *
 */
public class KeyWordTrend {
private String url;
private int kid;
private long createtime;
private int type; // 媒体来源
private String website;// 数据网站来源
private int emotion;
public String getUrl() {
    return url;
}
public void setUrl(String url) {
    this.url = url;
}

public int getKid() {
    return kid;
}
public void setKid(int kid) {
    this.kid = kid;
}
public long getCreatetime() {
    return createtime;
}
public void setCreatetime(long createtime) {
    this.createtime = createtime;
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
public int getEmotion() {
    return emotion;
}
public void setEmotion(int emotion) {
    this.emotion = emotion;
}


}
