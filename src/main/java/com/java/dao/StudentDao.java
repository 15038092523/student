package com.java.dao;

import com.java.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface StudentDao extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {

	@Query("select s from Student s where s.active = :active")
	Page<Student> findAllByActive(Boolean active, Pageable pageable);

	Student findByIdAndActive(Integer id, Boolean active);

	@Query("select s from Student s where s.active = :active and s.cid = :cid")
	Page<Student> findAllByActiveAndCid(@Param("active") Boolean active, @Param("cid") Integer cid, Pageable pageable);

}
