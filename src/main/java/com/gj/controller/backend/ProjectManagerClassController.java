package com.gj.controller.backend;

import com.gj.common.Const;
import com.gj.common.ResponseCode;
import com.gj.common.ServerResponse;
import com.gj.pojo.User;
import com.gj.service.IProjectService;
import com.gj.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018\5\2 0002.
 */
@Controller
@RequestMapping("/background/project")
public class ProjectManagerClassController {

    @Autowired
    private IUserService iUserService;

    @Autowired
   private IProjectService iProjectService;

    /**
     * 添加项目类别
     * @param session
     * @param name
     * @param parentid
     * @return
     */
    @RequestMapping(value = "add_project.do",method = RequestMethod.POST)
    @ResponseBody
   public ServerResponse addPricectClass(HttpSession session,String name, @RequestParam(value = "parentid",defaultValue = "0") int parentid){
       User user=(User) session.getAttribute(Const.CURRENT_USER);
       if(user==null){
           return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录。请登录");

       }
       //校验是否是管理员
       if(iUserService.checkAdminRole(user).isSuccess()){
           //是管理员
           //处理分类的逻辑
           return iProjectService.addProject(name,parentid);
       }else{
           return ServerResponse.createByErrorMessage("无权限操作，需要管理员的权限");
       }
   }

    /**
     * 添加项目类别不做严格验证
     * @param name
     * @param parentid
     * @return
     */
    @RequestMapping(value = "add_projectU.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addPricectClassU(String name, @RequestParam(value = "parentid",defaultValue = "0") int parentid){
            //处理分类的逻辑
            return iProjectService.addProject(name,parentid);

    }

    /**
     * 更新项目类别名称需要session登录以及管理员权限验证
     * @param session
     * @param id
     * @param name
     * @return
     */
    @RequestMapping(value = "update_project.do",method = RequestMethod.POST)
    @ResponseBody
   public ServerResponse setProjectClassName(HttpSession session,Integer id,String name){
       User user=(User) session.getAttribute(Const.CURRENT_USER);
       if(user==null){
           return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录。请登录");
       }
       if(iUserService.checkAdminRole(user).isSuccess()){
           //更新项目类别名称
           return iProjectService.updataProjectClass(id,name);

       }else{
           return ServerResponse.createByErrorMessage("无权限操作，需要管理员的权限");
       }
   }

    /**
     * 更新项目类别名称需要只需要可以登录后台即可使用没有做严格验证
     * @param id
     * @param name
     * @return
     */
    @RequestMapping(value = "update_projectU.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse setProjectClassNameU(Integer id,String name){
            //更新项目类别名称
            return iProjectService.updataProjectClass(id,name);
    }

    /**
     * 根据分类ID获取当前下面的子分类项目分类评级而不递归
     * @param session
     * @param parentid
     * @return
     */
    @RequestMapping(value = "get_child_project.do",method = RequestMethod.POST)
    @ResponseBody
   public ServerResponse getChildProjectClass(HttpSession session,@RequestParam(value = "parentid",defaultValue = "0")
            Integer parentid){
       User user=(User) session.getAttribute(Const.CURRENT_USER);
       if(user==null){
           return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录。请登录");
       }
       if(iUserService.checkAdminRole(user).isSuccess()){
          //查询子节点的分类的信息，不递归，保持平级
           return iProjectService.getChildrenParallelProjectClass(parentid);

       }else{
           return ServerResponse.createByErrorMessage("无权限操作，需要管理员的权限");
       }
   }

    /**
     * 根据分类ID获取当前下面的子分类项目分类评级而不递归,不做严格验证
     * @param parentid
     * @return
     */
    @RequestMapping(value = "get_child_projectU.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getChildProjectClassU(@RequestParam(value = "parentid",defaultValue = "0") Integer parentid){
        return iProjectService.getChildrenParallelProjectClass(parentid);
    }

    /**
     * 根据分类ID获取当前下面的子分类项目分类评级而递归
     * @param session
     * @param parentid
     * @return
     */
    @RequestMapping(value = "get_deep_project.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getProjectAndDeepChildClass(HttpSession session,@RequestParam(value = "parentid",defaultValue = "0") Integer parentid){
        User user=(User) session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录。请登录");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //查询子节点的分类的信息，递归，子节点
           return iProjectService.SelectProjectAndChildById(parentid);

        }else{
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员的权限");
        }
    }

    /**
     * 根据分类ID获取当前下面的子分类项目分类评级而递归,不做严格验证
     * @param parentid
     * @return
     */
    @RequestMapping(value = "get_deep_projectU.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getProjectAndDeepChildClassU(@RequestParam(value = "parentid",defaultValue = "0") Integer parentid){
            return iProjectService.SelectProjectAndChildById(parentid);
    }

    /**
     * 查询项目分类一共几页
     * @return
     */
    @RequestMapping(value = "get_pages_countU.do",method = RequestMethod.GET)
    @ResponseBody
     public ServerResponse selectPagesCount(){
          return iProjectService.selectPagesCount();

     }

    /**
     * 删除一级项目分类
     * @param session
     * @param id
     * @return
     */
    @RequestMapping(value = "delete_private_class.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse deleteProjectClass(HttpSession session,Integer id){
        User user=(User) session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录。请登录");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //查询子节点的分类的信息，递归，子节点
           return iProjectService.deleteProjectClass(id);

        }else{
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员的权限");
        }
    }

    /**
     * 删除一级项目分类,不做严格验证
     * @param id
     * @return
     */
    @RequestMapping(value = "delete_private_classU.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse deleteProjectClassU(Integer id){
            return iProjectService.deleteProjectClass(id);
    }


}

