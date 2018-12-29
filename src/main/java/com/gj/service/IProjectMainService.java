package com.gj.service;
import com.gj.common.ServerResponse;
import com.gj.pojo.ProjectcMainWithBLOBs;
import com.gj.vo.ProductDetailVo;

/**
 * Created by Administrator on 2018\5\6 0006.
 */
public interface IProjectMainService {
    /**
     * 添加项目和更新项目
     * @param projectcMainWithBLOBs
     * @return
     */
    ServerResponse saveOrUpdateProduct(ProjectcMainWithBLOBs projectcMainWithBLOBs);

    /**
     * 修改项目上下架的状态
     * @param productId
     * @param status
     * @return
     */
    ServerResponse<String> setSaleStatus(Integer productId,Integer status);

    /**
     * 获取项目详情信息
     * @param productId
     * @return
     */
    ServerResponse<ProductDetailVo> managePriductdetail(Integer productId);

}
