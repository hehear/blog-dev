package com.blog.mapper;

import com.blog.model.Role;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface RoleMapper {
    @Delete({
        "delete from role",
        "where ROLE_NO = #{roleNo,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer roleNo);

    @Insert({
        "insert into role (ROLE_NO, ROLE_NM, ",
        "UPDATE_TM, UPDATE_USR)",
        "values (#{roleNo,jdbcType=INTEGER}, #{roleNm,jdbcType=VARCHAR}, ",
        "#{updateTm,jdbcType=TIMESTAMP}, #{updateUsr,jdbcType=VARCHAR})"
    })
    int insert(Role record);

    @Select({
        "select",
        "ROLE_NO, ROLE_NM, UPDATE_TM, UPDATE_USR",
        "from role",
        "where ROLE_NO = #{roleNo,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ROLE_NO", property="roleNo", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ROLE_NM", property="roleNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_TM", property="updateTm", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_USR", property="updateUsr", jdbcType=JdbcType.VARCHAR)
    })
    Role selectByPrimaryKey(Integer roleNo);

    @Select({
        "select",
        "ROLE_NO, ROLE_NM, UPDATE_TM, UPDATE_USR",
        "from role"
    })
    @Results({
        @Result(column="ROLE_NO", property="roleNo", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ROLE_NM", property="roleNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_TM", property="updateTm", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_USR", property="updateUsr", jdbcType=JdbcType.VARCHAR)
    })
    List<Role> selectAll();

    @Update({
        "update role",
        "set ROLE_NM = #{roleNm,jdbcType=VARCHAR},",
          "UPDATE_TM = #{updateTm,jdbcType=TIMESTAMP},",
          "UPDATE_USR = #{updateUsr,jdbcType=VARCHAR}",
        "where ROLE_NO = #{roleNo,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Role record);
}