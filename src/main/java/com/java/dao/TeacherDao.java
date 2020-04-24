package com.java.dao;

import com.java.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


public interface TeacherDao extends JpaRepository<Teacher, Integer>, JpaSpecificationExecutor<Teacher> {

	@Query("select t from Teacher t where t.active = :active")
	Page<Teacher> findAllByActive(Boolean active, Pageable pageable);
	
	Teacher findByTnoAndActive(String tno,Boolean active);

	Teacher findByIdAndActive(Integer id, Boolean active);
}
