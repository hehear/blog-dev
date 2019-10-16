package com.blog.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Wiki implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String wikiId;

    private String wikiName;

    private Integer wikiPid;

    private Date updtTm;

    private String wikiContent;

    private String wikiContentMd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWikiId() {
        return wikiId;
    }

    public void setWikiId(String wikiId) {
        this.wikiId = wikiId;
    }

    public String getWikiName() {
        return wikiName;
    }

    public void setWikiName(String wikiName) {
        this.wikiName = wikiName;
    }

    public Integer getWikiPid() {
        return wikiPid;
    }

    public void setWikiPid(Integer wikiPid) {
        this.wikiPid = wikiPid;
    }

    public Date getUpdtTm() {
        return updtTm;
    }

    public void setUpdtTm(Date updtTm) {
        this.updtTm = updtTm;
    }

    public String getWikiContent() {
        return wikiContent;
    }

    public void setWikiContent(String wikiContent) {
        this.wikiContent = wikiContent;
    }

    public String getWikiContentMd() {
        return wikiContentMd;
    }

    public void setWikiContentMd(String wikiContentMd) {
        this.wikiContentMd = wikiContentMd;
    }
}