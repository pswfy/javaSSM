package com.gj.service;

import com.gj.common.ServerResponse;
import com.gj.pojo.ProjectClass;

import java.util.List;

/**
 * Created by Administrator on 2018\5\2 0002.
 */
public interface IProjectService {
    /**
     * 添加项目分类
     * @param name
     * @param parentid
     * @return
     */
    ServerResponse addProject(String name, Integer parentid);

    /**
     * 更新项目类别
     * @param id
     * @param name
     * @return
     */
    ServerResponse updataProjectClass(Integer id,String name);

    /**
     * 根据分类ID获取当前下面的子分类项目分类评级而不递归
     * @param parentid
     * @return
     */
    ServerResponse<List<ProjectClass>> getChildrenParallelProjectClass(Integer parentid);

    /**
     * 据分类ID获取当前下面的子分类项目分类评级而递归
     * @param parentid
     * @return
     */
    ServerResponse SelectProjectAndChildById(Integer parentid);

    /**
     * 查询中的页数
     * @return
     */
    ServerResponse<Integer> selectPagesCount();

    /**
     * 删除一级项目分类
     * @param id
     * @return
     */
    ServerResponse<Integer> deleteProjectClass(Integer id);


}

