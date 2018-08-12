package com.gj.service.impl;
import com.gj.common.ServerResponse;
import com.gj.service.IProjectService;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gj.pojo.ProjectClass;
import com.gj.dao.ProjectClassMapper;
import java.util.List;
import java.util.Set;


@Service("iProjectService")
public class ProjectServiceImpl implements IProjectService {

    private Logger logger=LoggerFactory.getLogger(ProjectServiceImpl.class);

    @Autowired
    private ProjectClassMapper projectClassMapper;

    /**
     * 添加项目类别
     * @param name
     * @param id
     * @return
     */
    public ServerResponse addProject(String name,Integer id){
        if(id==null|| StringUtils.isBlank(name)){
            return  ServerResponse.createByErrorMessage("添加类别叁数错误");
        }
        ProjectClass projectc=new ProjectClass();
        projectc.setName(name);
        projectc.setParentid(id);
        projectc.setStatus(true);//这个分类是可用的

        int rowCount=projectClassMapper.insert(projectc);
        if(rowCount>0){
            return ServerResponse.createBySuccessMessage("添加项目分成功");
        }
        return ServerResponse.createByErrorMessage("添加项目分类不成功");

    }

    /**
     * 更新新闻类别
     * @param id
     * @param name
     * @return
     */
    public ServerResponse updataProjectClass(Integer id,String name){
        if(id==null|| StringUtils.isBlank(name)){
            return  ServerResponse.createByErrorMessage("添加类别叁数错误");
        }
        ProjectClass projectc=new ProjectClass();
        projectc.setId(id);
        projectc.setName(name);
        int rowCount=projectClassMapper.updateByPrimaryKeySelective(projectc);
        if(rowCount>0){
            return ServerResponse.createBySuccessMessage("更新项目类别成功");
        }
        return  ServerResponse.createByErrorMessage("更新项目类别不成功");
    }

    /**
     * 根据分类ID获取当前下面的子分类项目分类评级而不递归
     * @param parentid
     * @return
     */
    public ServerResponse<List<com.gj.pojo.ProjectClass>> getChildrenParallelProjectClass(Integer parentid){
        List<com.gj.pojo.ProjectClass> projectclassList =projectClassMapper.selectProjectChildParentId(parentid);
        if(CollectionUtils.isEmpty(projectclassList)){
            logger.info("未找到当前前分类的子分类");
        }
        return ServerResponse.createBySuccess(projectclassList);
    }

    /**
     * 根据分类ID获取当前下面的子分类项目分类评级而递归
     * @param parentid
     * @return
     */
    public ServerResponse SelectProjectAndChildById(Integer parentid){
            Set<ProjectClass> projectClasSes= Sets.newHashSet();
            findChildPrijectClass(projectClasSes,parentid);
            List<Integer> projectList= Lists.newArrayList();
            if(parentid!=null){
                for(ProjectClass projectClassItem:projectClasSes){
                    projectList.add(projectClassItem.getId());
                }
            }
            return ServerResponse.createBySuccess(projectList);
    }

    /**
     * 查询总页数
     * @return
     */
    public ServerResponse<Integer> selectPagesCount(){
        Integer pagesCount=projectClassMapper.selectPagesCount();
        if(pagesCount>0){
            return ServerResponse.createBySuccess(pagesCount);
        }
        return ServerResponse.createByErrorMessage("查询总页数有错");
    }

    /**
     * 递归函数
     * @param ProjectClassSet
     * @param parentid
     * @return
     */
   private Set<ProjectClass> findChildPrijectClass(Set<ProjectClass> ProjectClassSet,Integer parentid){
       ProjectClass projectClass=projectClassMapper.selectByPrimaryKey(parentid);
       if(projectClass!=null){
           ProjectClassSet.add(projectClass);
       }
       /*查找子节点*/
       List<ProjectClass> projectClassList=projectClassMapper.selectProjectChildParentId(parentid);
       for(ProjectClass projectClassItem : projectClassList){
           findChildPrijectClass(ProjectClassSet,projectClassItem.getId());
       }
       return ProjectClassSet;
   }

    /**
     * 根据Id删除一级项目分类
     * @param id
     * @return
     */
   public ServerResponse deleteProjectClass(Integer id){
       if(id==null){
           ServerResponse.createByErrorMessage("删除一级项目类别叁数错误");
       }
       int rowCount= projectClassMapper.deleteByPrimaryKey(id);
       if(rowCount>0){
           return ServerResponse.createByErrorMessage("删除一级项目类别成功");
       }
       return ServerResponse.createBySuccessMessage("删除一级项目类别不成功");
   }
}
