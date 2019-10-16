package com.blog.mapper;

import com.blog.model.ArticleTagRltn;
import com.blog.model.ArticleType;
import com.blog.model.Tag;
import com.blog.model.Wiki;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface WikiMapper {


    @Delete({
        "delete from blog.article_wiki",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Select({
            "select",
            " max(wiki_id) ",
            "from blog.article_wiki"
    })
    Integer selectMaxWikiId();


    @Insert({
        "insert into blog.article_wiki (id, wiki_id, ",
        "wiki_name,wiki_pid,wiki_content,wiki_content_md, updt_tm)",
        "values (#{id,jdbcType=INTEGER}, #{wikiId,jdbcType=INTEGER}, ",
        "#{wikiName,jdbcType=VARCHAR},#{wikiPid,jdbcType=INTEGER}," ,
        "#{wikiContent,jdbcType=VARCHAR},#{wikiContentMd,jdbcType=VARCHAR}," ,
        "#{updtTm,jdbcType=TIMESTAMP})"
    })
    int insert(Wiki record);

    @Select({
        "select",
        "id, TAG_ID, TAG_NM, updt_tm",
        "from blog.tag",
        "where id = #{id,jdbcType=INTEGER}"
    })
    ArticleType selectByPrimaryKey(Integer id);

    @Select({
        " select ",
        " a.TAG_ID,a.TAG_NM,a.UPDT_TM,a.ID ",
        " from blog.tag a",
        " order by a.id,a.updt_tm desc "
    })
    List<Wiki> selectAll();

    @Update({
        "update blog.article_wiki",
        "set WIKI_ID = #{wikiId,jdbcType=INTEGER},",
          "WIKI_NAME = #{wikiName,jdbcType=VARCHAR},",
          " WIKI_PID = #{wikiPid,jdbcType=INTEGER},",
          " WIKI_CONTENT = #{wikiContent,jdbcType=VARCHAR},",
          " WIKI_CONTENT_MD = #{wikiContentMd,jdbcType=VARCHAR},",
          "updt_tm = #{updtTm,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Wiki record);


    @Select({
            "<script>",
            "select",
            "ID, WIKI_ID, WIKI_NAME, WIKI_PID, WIKI_CONTENT, WIKI_CONTENT_MD, UPDT_TM ",
            "from  blog.article_wiki b",
            "where 1=1 ",
            " <if test='wiki.wikiId !=null and !wiki.wikiId.isEmpty() '>AND WIKI_ID = #{wiki.wikiId}</if>",
            "<choose> ",
            "<when test='wiki.wikiName'>",
            "    <bind name='wikiName' value=\"'%' + wiki.wikiName + '%'\"/>",
            "</when>",
            "<otherwise>",
            "    <bind name='wikiName' value=\"'%%'\"/> ",
            "</otherwise>",
            "</choose>",
            " <if test='wiki.wikiName !=null and !(\"\").equals(wiki.wikiName)'>AND WIKI_NAME like #{wikiName}</if>",
            " order by ID,UPDT_TM desc ",
            "</script>"
    })

    List<Wiki> selectList(@Param(value = "wiki") Wiki wiki);
}