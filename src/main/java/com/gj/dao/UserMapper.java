package com.gj.dao;

import com.gj.pojo.User;
import org.apache.ibatis.annotations.Param;


public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    /*校验用户名是否存在*/
    int checkUsername( String uLogincode);
    /* 校验email是否存在*/
    int checkEmail(String email);
    /*验证用户名和密码登录*/
    User selectLogin(@Param("uLogincode") String uLogincode,@Param("upassword") String upassword);
    /*用户忘记密码*/
    String selectQuestionByUsername(String uLogincode);
    /* 校验问题的答案是否正确*/
    int checkAnswer(@Param("uLogincode")String uLogincode,@Param("question")String question,@Param("answer")String answer);
    /*忘记密码中的重置密码*/
    int updataPasswordByUsername(@Param("uLogincode")String uLogincode,@Param("passwordNew")String passwordNew);
    /*校验用户的旧密码*/
    int checkPassword(@Param("upassword")String upassword,@Param("id")Integer id);
    /*根据用户id校验用户email*/
    int checkEmailUserId(@Param("email")String email,@Param("userId")Integer userId);
    /*根据ID用户获取用户信息*/
    User selectUserId(String id);
   /* 修改用户最后登录的时间和IP集中时间用mysql的now()方法*/
    int updataTimeIp(@Param("ip")String ip, @Param("id")String id);






}