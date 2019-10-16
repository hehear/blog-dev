package com.blog.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Tag implements Serializable {
    private Integer id;

    private Integer tagId;
    
    private String tagNm;

    private Date updtTm;

    private static final long serialVersionUID = 1L;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagNm() {
        return tagNm;
    }

    public void setTagNm(String tagNm) {
        this.tagNm = tagNm;
    }

    public Date getUpdtTm() {
        return updtTm;
    }

    public void setUpdtTm(Date updtTm) {
        this.updtTm = updtTm;
    }
}