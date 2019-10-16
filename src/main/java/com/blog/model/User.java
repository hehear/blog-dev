package com.blog.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private String userId;

    private String userPwd;

    private Date lastLoginTm;

    private Date updateTm;

    private String updateUsr;

    private static final long serialVersionUID = 1L;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    public Date getLastLoginTm() {
        return lastLoginTm;
    }

    public void setLastLoginTm(Date lastLoginTm) {
        this.lastLoginTm = lastLoginTm;
    }

    public Date getUpdateTm() {
        return updateTm;
    }

    public void setUpdateTm(Date updateTm) {
        this.updateTm = updateTm;
    }

    public String getUpdateUsr() {
        return updateUsr;
    }

    public void setUpdateUsr(String updateUsr) {
        this.updateUsr = updateUsr == null ? null : updateUsr.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", userPwd=").append(userPwd);
        sb.append(", lastLoginTm=").append(lastLoginTm);
        sb.append(", updateTm=").append(updateTm);
        sb.append(", updateUsr=").append(updateUsr);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}