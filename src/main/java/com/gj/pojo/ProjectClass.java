package com.gj.pojo;

import java.util.Date;
import java.util.Objects;

public class ProjectClass {
    private Integer id;

    private Integer parentid;

    private String name;

    private Boolean status;

    private Integer sortorder;

    private Date createtime;

    private Date updatetime;

    public ProjectClass(Integer id, Integer parentid, String name, Boolean status, Integer sortorder, Date createtime, Date updatetime) {
        this.id = id;
        this.parentid = parentid;
        this.name = name;
        this.status = status;
        this.sortorder = sortorder;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public ProjectClass() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getSortorder() {
        return sortorder;
    }

    public void setSortorder(Integer sortorder) {
        this.sortorder = sortorder;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectClass that = (ProjectClass) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}