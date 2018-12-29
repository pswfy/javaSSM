package com.gj.service;

import com.gj.common.ServerResponse;
import com.gj.pojo.User;

/**
 * Created by pswfy on 2018\4\27 0027.
 */
public interface IUserService {
    /**
     * 用户登录接口
     *
     * @param uLogincode
     * @param upassword
     * @return
     */
    ServerResponse<User> login(String uLogincode, String upassword);

    /**
     * 用户注册接口
     *
     * @param user
     * @return
     */
    ServerResponse<String> register(User user);

    /**
     * 校验用户名和email是否存在
     *
     * @param str
     * @param type
     * @return
     */
    ServerResponse<String> checkValid(String str, String type);

    /**
     * 用户忘记密码问题
     *
     * @param uLogincode
     * @return
     */
    ServerResponse selectQuestion(String uLogincode);

    /**
     * 用户忘记密码回答的答案
     *
     * @param uLogincode
     * @param question
     * @param answer
     * @return
     */
    ServerResponse<String> checkAnswer(String uLogincode, String question, String answer);

    /**
     * 忘记密码中的重置密码
     *
     * @param uLogincode
     * @param passwordNew
     * @param ttoken
     * @return
     */
    ServerResponse<String> forgetRestPassword(String uLogincode, String passwordNew, String ttoken);

    /**
     * 登录状态下的更新密码
     *
     * @param passwordOld
     * @param passwordNew
     * @param user
     * @return
     */
    ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user);

    /**
     * 更新个人信息
     *
     * @param user
     * @return
     */
    ServerResponse<User> updataInformation(User user);

    /**
     * 获取用户详情信息强制登录
     *
     * @param id
     * @return
     */
    ServerResponse<User> getInfomation(Integer id);

    /**
     * 根据用户ID查找User
     *
     * @param id
     * @return
     */
    ServerResponse<User> getUserId(String id);

    /**
     * @param ip
     * @param id
     * @return
     */
    ServerResponse<Integer> setloginTime(String ip, String id);

    /**
     * 校验是否是管理员
     *
     * @param user
     * @return
     */
    ServerResponse checkAdminRole(User user);


}


