package com.gj.controller.portal;

import com.gj.common.Const;
import com.gj.common.ResponseCode;
import com.gj.common.ServerResponse;
import com.gj.pojo.User;
import com.gj.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by geely
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;


    /**
     * 用户登录接口
     *
     * @param uLogincode
     * @param upassword
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String uLogincode, String upassword, HttpSession session) {
        ServerResponse<User> response = iUserService.login(uLogincode, upassword);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    /**
     * 获取用户登录的ip和date
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "get_id_date.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> GetIdAndDate(HttpServletRequest request, String id) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (ip != null && ip.indexOf(",") != -1) {
            String[] ipWithMultiProxy = ip.split(",");
            for (int i = 0; i < ipWithMultiProxy.length; ++i) {
                String eachIpSegement = ipWithMultiProxy[i];
                if (!"unknown".equalsIgnoreCase(eachIpSegement)) {
                    ip = eachIpSegement;
                    break;
                }
            }
        }
        /*根据等前用户的id修改登录时间和ip地址(其中最后登录时间用mysql的now()方法)向数据库修改到数据库*/
        iUserService.setloginTime(ip, id);

        return ServerResponse.createBySuccess();
    }

    /**
     * 用户对出登录
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }


    /**
     * 用户注册接口
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(User user) {
        System.out.println("密码:" + user.getUpassword());
        System.out.println("账号:" + user.getUlogincode());
        System.out.println("邮箱:" + user.getEmail());
        return iUserService.register(user);
    }

    /**
     * 校验email和用户名是否存在接口
     *
     * @return username
     */
    @RequestMapping(value = "check_valid.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkValid(String str, String type) {
        return iUserService.checkValid(str, type);
    }

    /**
     * 获取用户登录信息的接口
     *
     * @return
     */
    @RequestMapping(value = "get_user_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user != null) {
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登录，无法获取用户的信息");
    }

    /**
     * 根据ID用户获取用户信息
     *
     * @return
     */
    @RequestMapping(value = "get_user_info_id.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserId(String id) {
        ServerResponse<User> UserID = iUserService.getUserId(id);
        if (UserID == null) {
            ServerResponse.createByErrorMessage("没有这个用户");
        }
        return UserID;
    }

    ;

    /**
     * 用户忘记密码问题接口
     *
     * @return
     */
    @RequestMapping(value = "for_get_question.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgerQuestion(String uLogincode) {
        return iUserService.selectQuestion(uLogincode);
    }

    /**
     * 校验问题的答案是否正确接口
     *
     * @param uLogincode
     * @param question
     * @param answer
     * @return
     */
    @RequestMapping(value = "for_get_answre.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswre(String uLogincode, String question, String answer) {
        return iUserService.checkAnswer(uLogincode, question, answer);
    }

    /**
     * 忘记密码中的重置密码
     *
     * @param uLogincode
     * @param passwordNew
     * @param ttoken
     * @return
     */
    @RequestMapping(value = "for_get_rest_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetRestPassword(String uLogincode, String passwordNew, String ttoken) {
        System.out.println("################:" + ttoken);
        return iUserService.forgetRestPassword(uLogincode, passwordNew, ttoken);
    }

    /**
     * 登录下重置密码
     *
     * @param session
     * @param passwordOld
     * @param passwordNew
     * @return
     */
    @RequestMapping(value = "reset_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpSession session, String passwordOld, String passwordNew) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户还没登录");
        }
        return iUserService.resetPassword(passwordOld, passwordNew, user);
    }

    /**
     * 用户更新个人信息
     *
     * @param session
     * @param user
     * @return
     */
    @RequestMapping(value = "updata_information.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> updata_information(HttpSession session, User user) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorMessage("用户还没登录");
        }
        user.setId(currentUser.getId());
        ServerResponse<User> pesponse = iUserService.updataInformation(user);
        if (pesponse.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, pesponse.getData());
        }
        return pesponse;
    }

    /**
     * 获取用户详细用户并需要强制登录
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "get_information.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> get_information(HttpSession session) {
        User currentuser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentuser == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录需要强制登录status=10");
        }
        return iUserService.getInfomation(currentuser.getId());
    }


}
