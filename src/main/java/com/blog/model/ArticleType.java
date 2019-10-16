package com.blog.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ArticleType implements Serializable {
    private Integer id;

    private Integer articleTpId;
    
    private String articleTpNm;
    
    private Date updtTm;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleTpId() {
        return articleTpId;
    }

    public void setArticleTpId(Integer articleTpId) {
        this.articleTpId = articleTpId;
    }
    


	public String getArticleTpNm() {
        return articleTpNm;
    }

    public void setArticleTpNm(String articleTpNm) {
        this.articleTpNm = articleTpNm == null ? null : articleTpNm.trim();
    }

	public Date getUpdtTm() {
        return updtTm;
    }

    public void setUpdtTm(Date updtTm) {
        this.updtTm = updtTm;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", articleTpId=").append(articleTpId);
        sb.append(", articleTpNm=").append(articleTpNm);
        sb.append(", updtTm=").append(updtTm);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}