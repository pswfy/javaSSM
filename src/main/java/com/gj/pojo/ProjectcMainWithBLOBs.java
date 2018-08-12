package com.gj.pojo;

import java.math.BigDecimal;
import java.sql.Date;

public class ProjectcMainWithBLOBs extends ProjectcMain {
    private String subimages;

    private String detail;

    public ProjectcMainWithBLOBs(Integer id, Integer projectclassid, String name, String pcode, String pcontract, Integer pcompany, String ptimeneed, String ptbackdrop, String pcomment, String paptitudevneed, String subtitle, String mainimage, BigDecimal price, Integer status, Date createtime, Date updatetime, String subimages, String detail) {
        super(id, projectclassid, name, pcode, pcontract, pcompany, ptimeneed, ptbackdrop, pcomment, paptitudevneed, subtitle, mainimage, price, status, createtime, updatetime);
        this.subimages = subimages;
        this.detail = detail;
    }

    public ProjectcMainWithBLOBs() {
        super();
    }

    public String getSubimages() {
        return subimages;
    }

    public void setSubimages(String subimages) {
        this.subimages = subimages == null ? null : subimages.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}