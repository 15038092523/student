package com.java.service;

import com.java.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TeacherService {
     Page<Teacher> findAll(Pageable pageable);

     void delete(Teacher teacher);

     Teacher findByTno(String tno);

     Teacher update(Teacher teacher);

     Teacher save(Teacher teacher);

     Teacher findById(Integer id);
}
