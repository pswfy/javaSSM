package com.gj.service.impl;

import com.gj.common.Const;
import com.gj.common.ServerResponse;
import com.gj.common.TokenCache;
import com.gj.dao.UserMapper;
import com.gj.pojo.User;
import com.gj.service.IUserService;
import com.gj.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


/**
 * Created by pswfy on 2018\4\27 0027.
 */
@Service("iUserService")
public class UserServiceImlp implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录实现
     * @param uLogincode
     * @param upassword
     * @return
     */
    @Override
    public ServerResponse<User> login(String uLogincode, String upassword) {
        /*校验用户名是否存在*/
        int resoultCount=userMapper.checkUsername(uLogincode);
        if(resoultCount==0){
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        //todo 密码加密
       String md5upasswordd=MD5Util.MD5EncodeUtf8(upassword);
        User user=userMapper.selectLogin(uLogincode,md5upasswordd);
        if(user==null){
            return ServerResponse.createByErrorMessage("密码错误");
        }
       user.setUpassword(org.apache.commons.lang3.StringUtils.EMPTY);
        user.setQuestion(null);
        user.setAnswer(null);
        return ServerResponse.createBySuccess("登录成功",user);
    }

    /**
     * 用户注册
     * @param user
     * @return
     */

    @Override
    public ServerResponse<String> register(User user) {
        /*检验用户名是否存在*/
        ServerResponse valldResponse=this.checkValid(user.getUlogincode(),Const.USERNAME);
        if(!(valldResponse.isSuccess())){
            return valldResponse;
        }
        /*校验email是否存在*/
        valldResponse=this.checkValid(user.getEmail(),Const.EMAIL);
        if(!(valldResponse.isSuccess())){
            return valldResponse;
        }
        user.setUrole(Const.Role.ROLE_CUSTOMER);
        //MD5加密
        user.setUpassword(MD5Util.MD5EncodeUtf8(user.getUpassword()));
        int resoultCount=userMapper.insertSelective(user);
        if(resoultCount==0){
            return ServerResponse.createByErrorMessage("注册不成功");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    };
    /**
     * 检验用户名和email是否存在
     */
    @Override
    public ServerResponse<String> checkValid(String str,String type){
        if(org.apache.commons.lang3.StringUtils.isNoneBlank(type)){
            if(Const.USERNAME.equals(type)){
                int resoultCount=userMapper.checkUsername(str);
                if(resoultCount>0){
                    return ServerResponse.createByErrorMessage("用户名已经存在");
                }
            }
            if(Const.EMAIL.equals(type)){
                int resoultCount=userMapper.checkEmail(str);
                if(resoultCount>0){
                    return ServerResponse.createByErrorMessage("email已经存在");
                }
            }
        }else{
           return ServerResponse.createByErrorMessage("叁数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");

    }

    /**
     * 用户忘记密码问题
     * @param uLogincode
     * @return
     */
    @Override
    public ServerResponse selectQuestion(String uLogincode){
       ServerResponse validResponse=this.checkValid(uLogincode,Const.USERNAME);
       if(validResponse.isSuccess()){
           return ServerResponse.createByErrorMessage("用户不存在");
       }
       String question=userMapper.selectQuestionByUsername(uLogincode);
       if(org.apache.commons.lang3.StringUtils.isNoneBlank(question)){
           return ServerResponse.createBySuccess(question);
       }
        return ServerResponse.createByErrorMessage("找回密码的问题是空的");
    }
    /**
     * 校验问题的答案是否正确接口
     */
    @Override
    public ServerResponse<String> checkAnswer(String uLogincode,String question,String answer){
        int resultCount=userMapper.checkAnswer(uLogincode,question,answer);
        if(resultCount>0){
            String forgetToken= UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX+uLogincode,forgetToken);
            return ServerResponse.createBySuccess(forgetToken);
        }
        return ServerResponse.createByErrorMessage("问题的答案错误");
    }
    /**
     * 忘记密码中的重置密码
     */
    @Override
    public ServerResponse<String> forgetRestPassword(String uLogincode,String passwordNew,String ttoken){
        if(org.apache.commons.lang3.StringUtils.isBlank(ttoken)){
            return ServerResponse.createByErrorMessage("叁数错误，token需要传递");
        }
        ServerResponse validResponse=this.checkValid(uLogincode,Const.USERNAME);
        if(validResponse.isSuccess()){
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        String token=TokenCache.getKey(TokenCache.TOKEN_PREFIX+uLogincode);
        if(org.apache.commons.lang3.StringUtils.isBlank(token)){
            return ServerResponse.createByErrorMessage("token无效或过期");
        }
        if(org.apache.commons.lang3.StringUtils.equals(ttoken,token)){
            String md5Password=MD5Util.MD5EncodeUtf8(passwordNew);
            int rowCount=userMapper.updataPasswordByUsername(uLogincode,md5Password);
            if(rowCount>0){
                return ServerResponse.createBySuccessMessage("修改密码成功");
            }
        }else{
            return ServerResponse.createByErrorMessage("token错误请重新获取重置密码的token");
        }
        return ServerResponse.createByErrorMessage("修改密码不成功");
    }
    /**
     * 登录状态下的重置密码
     */
    @Override
    public ServerResponse<String> resetPassword(String passwordOld,String passwordNew,User user){
        //防止横向越权，要校验一下这个用户的旧密码，一定是这个用户的密码count大于0
        int resultCount=userMapper.checkPassword(MD5Util.MD5EncodeUtf8(passwordOld),user.getId());
        if(resultCount==0){
            return ServerResponse.createByErrorMessage("旧密码错误");
        }
        user.setUpassword(MD5Util.MD5EncodeUtf8(passwordNew));
        int updataCount=userMapper.updateByPrimaryKeySelective(user);
        if(updataCount>0){
            return ServerResponse.createBySuccessMessage("密码更新成功");
        }
        return ServerResponse.createByErrorMessage("旧密更新不成功");
    }

    /**
     * 用户更新个人信息
     * @param user
     * @return
     */
    @Override
    public ServerResponse<User> updataInformation(User user){
        int resultCount=userMapper.checkEmailUserId(user.getEmail(),user.getId());
        if(resultCount>0){
            return ServerResponse.createByErrorMessage("email已经存在，请更换email再更新");
        }
        User updateUser=new User();
        updateUser.setId(user.getId());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
        int updateCount=userMapper.updateByPrimaryKeySelective(updateUser);
        if(updateCount>0){
            return ServerResponse.createBySuccess("更新个人信息成功",updateUser);
        }
        return ServerResponse.createByErrorMessage("更新个人信息不成功");
    }

    /**
     * 获取用户详情信息强制登录
     * @param id
     * @return
     */
    @Override
    public ServerResponse<User> getInfomation(Integer id){
        User user=userMapper.selectByPrimaryKey(id);
        if(user==null){
            return ServerResponse.createByErrorMessage("找不到当前用户");
        }
        user.setUpassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return ServerResponse.createBySuccess(user);
    }

    /**
     * 根据ID用户获取用户信息
     * @param id
     * @return
     */
    @Override
    public ServerResponse<User> getUserId(String id){
        User userId=userMapper.selectUserId(id);
        if(userId==null){
            return ServerResponse.createByErrorMessage("找不到当前用户");
        }
        userId.setUpassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return ServerResponse.createBySuccess(userId);
    }

    /**
     * 退出时修改用户最后登录时间和最后登录的ip地址
     * @param ip
     * @param id
     * @return
     */
    public ServerResponse<Integer> setloginTime(String ip,String id){
            int time=userMapper.updataTimeIp(ip,id);
            if(time<0){
                return ServerResponse.createByErrorMessage("修改不成功");
            }
        return ServerResponse.createBySuccessMessage("修改成功");

    }

    //ProjectClass

    /**
     * 校验是否是管理员
     * @param user
     * @return
     */
    public ServerResponse checkAdminRole(User user){
        if(user!=null&&user.getUrole().intValue()==Const.Role.ROLE_ADMIN){
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }



}
