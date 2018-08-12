package com.gj.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class ProjectcMain {
    private Integer id;

    private Integer projectclassid;

    private String name;

    private String pcode;

    private String pcontract;

    private Integer pcompany;

    private String ptimeneed;

    private String ptbackdrop;

    private String pcomment;

    private String paptitudevneed;

    private String subtitle;

    private String mainimage;

    private BigDecimal price;

    private Integer status;

    private Date createtime;

    private Date updatetime;

    public ProjectcMain(Integer id, Integer projectclassid, String name, String pcode, String pcontract, Integer pcompany, String ptimeneed, String ptbackdrop, String pcomment, String paptitudevneed, String subtitle, String mainimage, BigDecimal price, Integer status, Date createtime, Date updatetime) {
        this.id = id;
        this.projectclassid = projectclassid;
        this.name = name;
        this.pcode = pcode;
        this.pcontract = pcontract;
        this.pcompany = pcompany;
        this.ptimeneed = ptimeneed;
        this.ptbackdrop = ptbackdrop;
        this.pcomment = pcomment;
        this.paptitudevneed = paptitudevneed;
        this.subtitle = subtitle;
        this.mainimage = mainimage;
        this.price = price;
        this.status = status;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public ProjectcMain() {
        super();
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
        this.name = name == null ? null : name.trim();
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode == null ? null : pcode.trim();
    }

    public String getPcontract() {
        return pcontract;
    }

    public void setPcontract(String pcontract) {
        this.pcontract = pcontract == null ? null : pcontract.trim();
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
        this.ptimeneed = ptimeneed == null ? null : ptimeneed.trim();
    }

    public String getPtbackdrop() {
        return ptbackdrop;
    }

    public void setPtbackdrop(String ptbackdrop) {
        this.ptbackdrop = ptbackdrop == null ? null : ptbackdrop.trim();
    }

    public String getPcomment() {
        return pcomment;
    }

    public void setPcomment(String pcomment) {
        this.pcomment = pcomment == null ? null : pcomment.trim();
    }

    public String getPaptitudevneed() {
        return paptitudevneed;
    }

    public void setPaptitudevneed(String paptitudevneed) {
        this.paptitudevneed = paptitudevneed == null ? null : paptitudevneed.trim();
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle == null ? null : subtitle.trim();
    }

    public String getMainimage() {
        return mainimage;
    }

    public void setMainimage(String mainimage) {
        this.mainimage = mainimage == null ? null : mainimage.trim();
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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}