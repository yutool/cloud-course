package com.anko.coursems.dao;

import com.anko.coursems.common.support.BaseMapper;
import com.anko.coursems.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@BaseMapper.Meta(table = "t_user", exId = "user_id")
public interface UserMapper extends BaseMapper<User> {
    int updatePassword(User user);

    User findUserByAccount(String account);

    User login(@Param("account") String account, @Param("password") String password);


//    @Insert("insert into t_user(user_id, user_name,email, password, nickname, avatar, salt, role)" +
//            " values(#{userId}, #{userName}, #{email}, #{password}, #{nickname}, #{avatar}, #{salt}, #{role})")
//    int register(User registerForm);
//
//    @Update("update t_user" +
//            " set user_name=#{userName}, nickname=#{nickname}," +
//            "     stu_num=#{stuNum}, gender=#{gender}," +
//            "     birthday=#{birthday}, signature=#{signature}" +
//            " where user_id = #{userId}")
//    int updateUserInfo(User user);
//
//    @Update("update t_user" +
//            " set avatar = #{avatar}" +
//            " where user_id = #{userId}")
//    int updateAvatar(@Param("userId") String id, @Param("avatar") String path);
//
//    @Update("update t_user" +
//            " set email = #{email}" +
//            " where user_id = #{userId}")
//    int bindEmail(User user);
//
//    @Update("update t_user" +
//            " set phone_num = #{phoneNum}" +
//            " where user_id = #{userId}")
//    int bindPhone(User user);

}
