package com.java.entity;

import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Proxy(lazy = false)
@Table(name="t_teacher", uniqueConstraints = {
		@UniqueConstraint(name = "index_tno",columnNames = {"tno"})
})
public class Teacher extends EntityAbstract {

	@NotNull
	@Column(length=100)
	private String name;

	@NotNull
	@Column(length=100)
	private String tno;
	
	@Column(length=50)
	private String sex;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTno() {
		return tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
