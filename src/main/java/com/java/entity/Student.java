package com.java.entity;

import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Proxy(lazy = false)
@Table(name="t_student", uniqueConstraints = {
        @UniqueConstraint(name = "index_sno",columnNames = {"sno"})
})
public class Student extends EntityAbstract {

    @NotNull
    @Column(length=100)
    private String name;

    @NotNull
    @Column(length=100)
    private String sno;

    @Column(length=50)
    private String sex;

    @Column(length=50)
    private Integer cid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }
}
