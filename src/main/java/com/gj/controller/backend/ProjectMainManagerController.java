package com.gj.controller.backend;
import com.gj.common.Const;
import com.gj.common.ResponseCode;
import com.gj.common.ServerResponse;
import com.gj.pojo.ProjectcMain;
import com.gj.pojo.ProjectcMainWithBLOBs;
import com.gj.pojo.User;
import com.gj.service.IProjectMainService;
import com.gj.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018\5\6 0006.
 */
@Controller
@RequestMapping("/backgroud/project")
public class ProjectMainManagerController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    IProjectMainService iProjectMainService;


    /**
     * 添加和更新项目信息
     * @param session
     * @param projectcMainWithBLOBs
     * @return
     */
    @RequestMapping(value = "save.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse productSave(HttpSession session, ProjectcMainWithBLOBs projectcMainWithBLOBs){
        User user=(User) session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户还没登录");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //添加和修改项目的业务逻辑
            return iProjectMainService.saveOrUpdateProduct(projectcMainWithBLOBs);
        }else{
            return ServerResponse.createByErrorMessage("不是管理员无权限操作");
        }
    }

    /**
     * 修改项目上下架的状态
     * @param session
     * @param productId
     * @param status
     * @return
     */
    @RequestMapping(value = "setSaleStatus.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse setSaleStatus(HttpSession session, Integer productId,Integer status){
        User user=(User) session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户还没登录");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            return iProjectMainService.setSaleStatus(productId,status);
        }else{
            return ServerResponse.createByErrorMessage("不是管理员无权限操作");
        }
    }

    /**
     * 获取项目信息
     * @param session
     * @param productId
     * @return
     */
    @RequestMapping(value = "detail.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getdetail(HttpSession session, Integer productId){
        User user=(User) session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户还没登录");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            return iProjectMainService.managePriductdetail(productId);
        }else{
            return ServerResponse.createByErrorMessage("不是管理员无权限操作");
        }
    }


}
