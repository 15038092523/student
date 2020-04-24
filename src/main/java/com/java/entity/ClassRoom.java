package com.java.entity;

import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Proxy(lazy = false)
@Table(name="t_class", uniqueConstraints = {
        @UniqueConstraint(name = "index_cno",columnNames = {"cno"})
})
public class ClassRoom extends EntityAbstract{

    @NotNull
    @Column(length=100)
    private String name;

    @NotNull
    @Column(length=100)
    private String cno;

    @Column(length=50)
    private String grade;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
