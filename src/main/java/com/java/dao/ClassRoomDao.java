package com.java.dao;

import com.java.entity.ClassRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


public interface ClassRoomDao extends JpaRepository<ClassRoom, Integer>, JpaSpecificationExecutor<ClassRoom> {

	@Query("select c from ClassRoom c where c.active = :active")
	Page<ClassRoom> findAllByActive(Boolean active, Pageable pageable);

	ClassRoom findByCnoAndActive(String cno, Boolean active);

	ClassRoom findByIdAndActive(Integer id, Boolean active);
}
