package com.lq.security.entity;

/**
 * @author qili
 * @create 2022-06-04-11:40
 */
public class Admin {
    private Integer id;
    private String loginAcct;
    private String loginPswd;
    private String username;
    private String email;
    private String createTime;

    public Admin() {
    }

    public Admin(Integer id, String loginAcct, String loginPswd, String username, String email, String createTime) {
        this.id = id;
        this.loginAcct = loginAcct;
        this.loginPswd = loginPswd;
        this.username = username;
        this.email = email;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginAcct() {
        return loginAcct;
    }

    public void setLoginAcct(String loginAcct) {
        this.loginAcct = loginAcct;
    }

    public String getLoginPswd() {
        return loginPswd;
    }

    public void setLoginPswd(String loginPswd) {
        this.loginPswd = loginPswd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", loginAcct='" + loginAcct + '\'' +
                ", loginPswd='" + loginPswd + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
