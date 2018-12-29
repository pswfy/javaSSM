package com.gj.vo;

import java.math.BigDecimal;

/**
 * Created by geely
 */
public class ProductDetailVo {

    private Integer id;//项目id

    private Integer projectclassid;//分类id,对应projectclass表的parentid

    private String name;//项目名称

    private String pcode;//项目编号

    private String pcontract;//项目简介

    private Integer pcompany;//承接行业

    private String ptimeneed;//预计工期（月）

    private String ptbackdrop;//项目背景

    private String pcomment;//项目背景

    private String detail;//项目详情富文本

    private String paptitudevneed;//项目所需资质

    private String subtitle;//项目副标题

    private String mainimage;//项目主图,url相对地址

    private BigDecimal price;//项目报价,单位-元保留两位小数

    private Integer status;//项目状态.1-在售 2-下架 3-删除

    private String createtime;//创建时间

    private String updatetime;//修改时间

    private String subimages;//图片地址,json格式,扩展用





    private String imagesHost;//图片服务器的全cui

    private String DarentCategoryId;//父分类ID


    private Integer narentCategoryId;


    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDarentCategoryId() {
        return DarentCategoryId;
    }

    public void setDarentCategoryId(String darentCategoryId) {
        DarentCategoryId = darentCategoryId;
    }

    public String getSubimages() {
        return subimages;
    }

    public void setSubimages(String subimages) {
        this.subimages = subimages;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectclassid() {
        return projectclassid;
    }

    public void setProjectclassid(Integer projectclassid) {
        this.projectclassid = projectclassid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getPcontract() {
        return pcontract;
    }

    public void setPcontract(String pcontract) {
        this.pcontract = pcontract;
    }

    public Integer getPcompany() {
        return pcompany;
    }

    public void setPcompany(Integer pcompany) {
        this.pcompany = pcompany;
    }

    public String getPtimeneed() {
        return ptimeneed;
    }

    public void setPtimeneed(String ptimeneed) {
        this.ptimeneed = ptimeneed;
    }

    public String getPtbackdrop() {
        return ptbackdrop;
    }

    public void setPtbackdrop(String ptbackdrop) {
        this.ptbackdrop = ptbackdrop;
    }

    public String getPcomment() {
        return pcomment;
    }

    public void setPcomment(String pcomment) {
        this.pcomment = pcomment;
    }

    public String getPaptitudevneed() {
        return paptitudevneed;
    }

    public void setPaptitudevneed(String paptitudevneed) {
        this.paptitudevneed = paptitudevneed;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getMainimage() {
        return mainimage;
    }

    public void setMainimage(String mainimage) {
        this.mainimage = mainimage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getImagesHost() {
        return imagesHost;
    }

    public void setImagesHost(String imagesHost) {
        this.imagesHost = imagesHost;
    }

    public Integer getNarentCategoryId() {
        return narentCategoryId;
    }

    public void setNarentCategoryId(Integer narentCategoryId) {
        this.narentCategoryId = narentCategoryId;
    }
}
