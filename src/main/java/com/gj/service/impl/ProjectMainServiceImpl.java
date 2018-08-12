package com.gj.service.impl;

import com.gj.common.ResponseCode;
import com.gj.common.ServerResponse;
import com.gj.dao.ProjectcMainMapper;
import com.gj.pojo.ProjectcMainWithBLOBs;
import com.gj.service.IProjectMainService;

import com.gj.util.DateTimeUtil;
import com.gj.util.PropertiesUtil;
import com.gj.vo.ProductDetailVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by Administrator on 2018\5\6 0006.
 */
@Service("iProjectMainService")
public class ProjectMainServiceImpl implements IProjectMainService {

    @Autowired
    private ProjectcMainMapper projectcMainMapper;


    /**
     * 添加项目和修改项目
     * @param projectcMainWithBLOBs
     * @return
     */
    public ServerResponse saveOrUpdateProduct(ProjectcMainWithBLOBs projectcMainWithBLOBs){
        if(projectcMainWithBLOBs!=null){
            if(StringUtils.isNoneBlank(projectcMainWithBLOBs.getSubimages())){
                String[] subImageArray=projectcMainWithBLOBs.getSubimages().split(",");
                if(subImageArray.length>0){
                    projectcMainWithBLOBs.setMainimage(subImageArray[0]);
                }
            }
            if(projectcMainWithBLOBs.getId()!=null){
               int rowCount= projectcMainMapper.updateByPrimaryKey(projectcMainWithBLOBs);
               if(rowCount>0){
                   return ServerResponse.createBySuccess("更新项目成功");
               }
                return ServerResponse.createBySuccess("更新项目不成功");
            }else{
                int rowCount= projectcMainMapper.insert(projectcMainWithBLOBs);
                if(rowCount>0){
                    return ServerResponse.createBySuccess("添加项目成功");
                }
                return ServerResponse.createBySuccess("添加项目不成功");
            }

        }
        return  ServerResponse.createByErrorMessage("添加项目或更新项目叁数错误");
    }

    /**
     * 修改项目上下架的状态
     * @param productId
     * @param status
     * @return
     */
    public ServerResponse<String> setSaleStatus(Integer productId,Integer status){
        if(productId==null || status==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        ProjectcMainWithBLOBs projectcMainWithBLOBs=new ProjectcMainWithBLOBs();
        projectcMainWithBLOBs.setId(productId);
        projectcMainWithBLOBs.setStatus(status);
        int rowCount=projectcMainMapper.updateByPrimaryKeySelective(projectcMainWithBLOBs);
        if(rowCount>0){
            return ServerResponse.createBySuccess("修改项目上架加成功");
        }
        return  ServerResponse.createByErrorMessage("修改项目上架加不成功");
    }

    /**
     * 获取项目
     * @param productId
     * @return
     */
    public ServerResponse<ProductDetailVo> managePriductdetail(Integer productId){
        if(productId==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        ProjectcMainWithBLOBs projectcMainWithBLOBs=projectcMainMapper.selectByPrimaryKey(productId);
        if(projectcMainWithBLOBs==null){
            return  ServerResponse.createByErrorMessage("项目已下架或删除");
        }
        /*VO对象-value-object*/
        ProductDetailVo productDetailVo=assembleProductListVo(projectcMainWithBLOBs);
        return ServerResponse.createBySuccess(productDetailVo);
    }

    private ProductDetailVo  assembleProductListVo(ProjectcMainWithBLOBs projectcMainWithBLOBs){
        ProductDetailVo productDetailVo=new ProductDetailVo();

        productDetailVo.setId(projectcMainWithBLOBs.getId());
        productDetailVo.setProjectclassid(projectcMainWithBLOBs.getProjectclassid());
        productDetailVo.setPcode(productDetailVo.getPcode());
        productDetailVo.setPcontract(productDetailVo.getPcontract());
        productDetailVo.setPcompany(productDetailVo.getPcompany());
        productDetailVo.setPtimeneed(productDetailVo.getPtimeneed());
        productDetailVo.setPtbackdrop(productDetailVo.getPtbackdrop());
        productDetailVo.setPcomment(productDetailVo.getPcomment());
        productDetailVo.setPaptitudevneed(productDetailVo.getPaptitudevneed());
        productDetailVo.setSubtitle(productDetailVo.getSubtitle());
        productDetailVo.setMainimage(productDetailVo.getMainimage());
        productDetailVo.setSubimages(productDetailVo.getSubimages());
        productDetailVo.setDetail(productDetailVo.getDetail());
        productDetailVo.setPrice(productDetailVo.getPrice());
        productDetailVo.setStatus(productDetailVo.getStatus());

        productDetailVo.setImagesHost(PropertiesUtil.getProperty("ftp.server.http.prefix","ftp://127.0.0.1/images/"));
        ProjectcMainWithBLOBs projectcMainWithBLOBs1=projectcMainMapper.selectByPrimaryKey(projectcMainWithBLOBs.getProjectclassid());
        if(projectcMainWithBLOBs1==null){
            productDetailVo.setProjectclassid(0);
        }else{
            productDetailVo.setProjectclassid(projectcMainWithBLOBs1.getProjectclassid());
        }
        productDetailVo.setCreatetime(DateTimeUtil.dateToStr(projectcMainWithBLOBs.getCreatetime()));
        productDetailVo.setUpdatetime(DateTimeUtil.dateToStr(projectcMainWithBLOBs.getUpdatetime()));
        return productDetailVo;


    }
}
