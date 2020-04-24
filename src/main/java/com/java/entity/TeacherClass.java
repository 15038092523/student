package com.java.entity;

import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Proxy(lazy = false)
@Table(name="t_teacher_class",uniqueConstraints = {
@UniqueConstraint(name = "index_tid_cid", columnNames = {"tid","cid"})})
public class TeacherClass extends EntityAbstract {

    @NotNull
    @Column(length=50)
    private Integer cid;

    @NotNull
    @Column(length=50)
    private Integer tid;

    @Column
    private Boolean isDefault;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }
}
