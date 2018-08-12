package com.gj.dao;

import com.gj.pojo.ProjectClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectClassMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectClass record);

    int insertSelective(ProjectClass record);

    ProjectClass selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectClass record);

    int updateByPrimaryKey(ProjectClass record);

    /*根据分类ID获取当前下面的子分类项目分类评级而不递归*/
    List<ProjectClass> selectProjectChildParentId(Integer parentid);
    /*查询一共有几页*/
    int selectPagesCount();



}