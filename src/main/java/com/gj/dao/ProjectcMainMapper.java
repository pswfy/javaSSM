package com.gj.dao;

import com.gj.pojo.ProjectcMain;
import com.gj.pojo.ProjectcMainWithBLOBs;

public interface ProjectcMainMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectcMainWithBLOBs record);

    int insertSelective(ProjectcMainWithBLOBs record);

    ProjectcMainWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectcMainWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ProjectcMainWithBLOBs record);

    int updateByPrimaryKey(ProjectcMain record);
}