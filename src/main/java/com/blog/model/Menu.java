package com.blog.model;

import java.io.Serializable;
import java.util.Date;

public class Menu implements Serializable {
    private Integer menuNo;

    private String menuNm;

    private Integer menuPno;

    private String menuLnk;

    private Date updateTm;

    private String updateUsr;

    private static final long serialVersionUID = 1L;

    public Integer getMenuNo() {
        return menuNo;
    }

    public void setMenuNo(Integer menuNo) {
        this.menuNo = menuNo;
    }

    public String getMenuNm() {
        return menuNm;
    }

    public void setMenuNm(String menuNm) {
        this.menuNm = menuNm == null ? null : menuNm.trim();
    }

    public Integer getMenuPno() {
        return menuPno;
    }

    public void setMenuPno(Integer menuPno) {
        this.menuPno = menuPno;
    }

    public String getMenuLnk() {
        return menuLnk;
    }

    public void setMenuLnk(String menuLnk) {
        this.menuLnk = menuLnk == null ? null : menuLnk.trim();
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
        sb.append(", menuNo=").append(menuNo);
        sb.append(", menuNm=").append(menuNm);
        sb.append(", menuPno=").append(menuPno);
        sb.append(", menuLnk=").append(menuLnk);
        sb.append(", updateTm=").append(updateTm);
        sb.append(", updateUsr=").append(updateUsr);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}