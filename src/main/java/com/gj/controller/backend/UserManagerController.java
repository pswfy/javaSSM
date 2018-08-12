package com.gj.controller.backend;

import com.gj.common.Const;
import com.gj.common.ServerResponse;
import com.gj.pojo.User;
import com.gj.service.IUserService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.processing.Filer;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;

/**
 * Created by pswfy on 2018\4\29 0029.
 */
@Controller
@RequestMapping("/background/user")
public class UserManagerController {

    @Autowired
    private IUserService iUserService;

    /**
     * 管理员登录接口
     * @param uLogincode
     * @param upassword
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String uLogincode, String upassword, HttpSession session){
        ServerResponse<User> response=iUserService.login(uLogincode,upassword);
        if(response.isSuccess()){
            User user=response.getData();
            if(user.getUrole()== Const.Role.ROLE_ADMIN){
                session.setAttribute(Const.CURRENT_USER,user);
                return  response;
            }else{
                return ServerResponse.createByErrorMessage("不是管理员无法登录");
            }
        }
        return response;
    }
}
