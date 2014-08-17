package com.hhhy.db.beans;

/**
 * 用户类
 * 对应a_admin表
 * @author chenlingpeng
 *
 */
public class User {
    private long id;
    private String nickname;
    private String email;
    private String password;
    private String phone;
    // need email, message, or alert
    private int needemail;
    private int needphone
    private int needalert;
    
    public User(){
        this.id = -1l;
    }

    public User(String nickname, String email, String password) {
        this.id = -1l;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNeedemail() {
        return needemail;
    }

    public void setNeedemail(int needemail) {
        this.needemail = needemail;
    }

    public int getNeedphone() {
        return needphone;
    }

    public void setNeedphone(int needphone) {
        this.needphone = needphone;
    }

    public int getNeedalert() {
        return needalert;
    }

    public void setNeedemail(int needalert) {
        this.needalert = needalert;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
