package com.blog.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Comment implements Serializable {
    private Integer id;

    private String commentContent;

    private Date createTime;

    private Integer userId;

    private Integer articleId;

    private Integer pId;

    private Integer replyUserId;
    
    private String replyUserNm;

    private String userNm;

    private String email;

    private String internetSite;
    
    private List<Comment> commentList;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Integer getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(Integer replyUserId) {
        this.replyUserId = replyUserId;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm == null ? null : userNm.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getInternetSite() {
        return internetSite;
    }

    public void setInternetSite(String internetSite) {
        this.internetSite = internetSite == null ? null : internetSite.trim();
    }
    

    public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	

	public String getReplyUserNm() {
		return replyUserNm;
	}

	public void setReplyUserNm(String replyUserNm) {
		this.replyUserNm = replyUserNm;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", commentContent=").append(commentContent);
        sb.append(", createTime=").append(createTime);
        sb.append(", userId=").append(userId);
        sb.append(", articleId=").append(articleId);
        sb.append(", pId=").append(pId);
        sb.append(", replyUserId=").append(replyUserId);
        sb.append(", userNm=").append(userNm);
        sb.append(", email=").append(email);
        sb.append(", internetSite=").append(internetSite);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}