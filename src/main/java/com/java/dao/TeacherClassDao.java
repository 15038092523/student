package com.java.dao;

import com.java.entity.Teacher;
import com.java.entity.TeacherClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TeacherClassDao extends JpaRepository<TeacherClass, Integer>, JpaSpecificationExecutor<TeacherClass> {

	@Query("select t from Teacher t ,TeacherClass tc where t.id = tc.tid " +
			"and tc.isDefault = :isDefault and tc.cid = :cid")
	List<Teacher> findDefaultByCid(@Param("isDefault") Boolean isDefault, @Param("cid") Integer cid);

}
