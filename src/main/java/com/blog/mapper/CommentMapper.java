package com.blog.mapper;

import com.blog.model.Comment;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface CommentMapper {
    @Delete({
        "delete from comment",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into comment (ID, COMMENT_CONTENT, ",
        "CREATE_TIME, USER_ID, ",
        "ARTICLE_ID, P_ID, ",
        "REPLY_USER_ID, USER_NM, ",
        "EMAIL, INTERNET_SITE)",
        "values (#{id,jdbcType=INTEGER}, #{commentContent,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{userId,jdbcType=INTEGER}, ",
        "#{articleId,jdbcType=INTEGER}, #{pId,jdbcType=INTEGER}, ",
        "#{replyUserId,jdbcType=INTEGER}, #{userNm,jdbcType=VARCHAR}, ",
        "#{email,jdbcType=VARCHAR}, #{internetSite,jdbcType=VARCHAR})"
    })
    int insert(Comment record);

    @Select({
        "select",
        "ID, COMMENT_CONTENT, CREATE_TIME, USER_ID, ARTICLE_ID, P_ID, REPLY_USER_ID, ",
        "USER_NM, EMAIL, INTERNET_SITE",
        "from comment",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    Comment selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "ID, COMMENT_CONTENT, CREATE_TIME, USER_ID, ARTICLE_ID, P_ID, REPLY_USER_ID, ",
        "USER_NM, EMAIL, INTERNET_SITE",
        "from comment"
    })
    List<Comment> selectAll();
    
    @Select({
        "select",
        "ID, COMMENT_CONTENT, CREATE_TIME, USER_ID, ARTICLE_ID, P_ID, REPLY_USER_ID, ",
        "USER_NM, EMAIL, INTERNET_SITE",
        "from comment where P_ID=0"
    })
    List<Comment> selectList();
    
    @Select({
        "select",
        "ID, COMMENT_CONTENT, CREATE_TIME, USER_ID, ARTICLE_ID, P_ID, REPLY_USER_ID, ",
        "USER_NM, EMAIL, INTERNET_SITE",
        "from comment where P_ID=0 and ARTICLE_ID=#{articleId,jdbcType=INTEGER} "
    })
    List<Comment> selectListByArticleId(Integer articleId);
    
    @Select({
        "select",
        " a.ID,a.COMMENT_CONTENT,a.CREATE_TIME,a.USER_ID,a.ARTICLE_ID,a.P_ID,", 
        " a.REPLY_USER_ID,b.USER_NM AS REPLY_USER_nm,a.USER_NM,a.EMAIL,a.iNTERNET_SITE",
        " FROM  blog.comment a ",
        " LEFT JOIN (SELECT ARTICLE_ID, P_ID, USER_NM, user_id FROM  blog.comment  ",
        " GROUP BY ARTICLE_ID , P_ID , USER_NM , user_id) b ON a.p_id = b.p_id ",
        " AND a.REPLY_USER_ID = b.user_id " + 
        "WHERE  a.p_id != 0 and a.p_id=#{userId,jdbcType=INTEGER}",
    })
    List<Comment> selectListByPid(Integer userId);

    @Update({
        "update comment",
        "set COMMENT_CONTENT = #{commentContent,jdbcType=VARCHAR},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "USER_ID = #{userId,jdbcType=INTEGER},",
          "ARTICLE_ID = #{articleId,jdbcType=INTEGER},",
          "P_ID = #{pId,jdbcType=INTEGER},",
          "REPLY_USER_ID = #{replyUserId,jdbcType=INTEGER},",
          "USER_NM = #{userNm,jdbcType=VARCHAR},",
          "EMAIL = #{email,jdbcType=VARCHAR},",
          "INTERNET_SITE = #{internetSite,jdbcType=VARCHAR}",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Comment record);
    
    @Select({
        "select",
        "count(*)",
        "from comment",
        "where ARTICLE_ID=#{articleId,jdbcType=INTEGER}"
    })
	int selectCommentCounts(Integer articleId);
}