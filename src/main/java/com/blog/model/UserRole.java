package com.blog.model;

import java.io.Serializable;
import java.util.Date;

public class UserRole implements Serializable {
    private Integer roleNo;

    private String userId;

    private Date updateTm;

    private String updateUsr;

    private static final long serialVersionUID = 1L;

    public Integer getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(Integer roleNo) {
        this.roleNo = roleNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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
        sb.append(", roleNo=").append(roleNo);
        sb.append(", userId=").append(userId);
        sb.append(", updateTm=").append(updateTm);
        sb.append(", updateUsr=").append(updateUsr);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}