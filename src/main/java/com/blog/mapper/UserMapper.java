package com.blog.mapper;

import com.blog.model.User;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface UserMapper {
    @Delete({
        "delete from user",
        "where USER_ID = #{userId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String userId);

    @Insert({
        "insert into user (USER_ID, USER_PWD, ",
        "LAST_LOGIN_TM, UPDATE_TM, ",
        "UPDATE_USR)",
        "values (#{userId,jdbcType=VARCHAR}, #{userPwd,jdbcType=VARCHAR}, ",
        "#{lastLoginTm,jdbcType=TIMESTAMP}, #{updateTm,jdbcType=TIMESTAMP}, ",
        "#{updateUsr,jdbcType=VARCHAR})"
    })
    int insert(User record);

    @Select({
        "select",
        "USER_ID, USER_PWD, LAST_LOGIN_TM, UPDATE_TM, UPDATE_USR",
        "from user",
        "where USER_ID = #{userId,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="USER_ID", property="userId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="USER_PWD", property="userPwd", jdbcType=JdbcType.VARCHAR),
        @Result(column="LAST_LOGIN_TM", property="lastLoginTm", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TM", property="updateTm", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_USR", property="updateUsr", jdbcType=JdbcType.VARCHAR)
    })
    User selectByPrimaryKey(String userId);

    @Select({
        "select",
        "USER_ID, USER_PWD, LAST_LOGIN_TM, UPDATE_TM, UPDATE_USR",
        "from user"
    })
    @Results({
        @Result(column="USER_ID", property="userId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="USER_PWD", property="userPwd", jdbcType=JdbcType.VARCHAR),
        @Result(column="LAST_LOGIN_TM", property="lastLoginTm", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TM", property="updateTm", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_USR", property="updateUsr", jdbcType=JdbcType.VARCHAR)
    })
    List<User> selectAll();

    @Update({
        "update user",
        "set USER_PWD = #{userPwd,jdbcType=VARCHAR},",
          "LAST_LOGIN_TM = #{lastLoginTm,jdbcType=TIMESTAMP},",
          "UPDATE_TM = #{updateTm,jdbcType=TIMESTAMP},",
          "UPDATE_USR = #{updateUsr,jdbcType=VARCHAR}",
        "where USER_ID = #{userId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(User record);
}