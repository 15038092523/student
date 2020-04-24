package com.java.service;

import com.java.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface StudentService {
     Page<Student> findAll(Pageable pageable);

     void delete(Student Student);

     Student find(Integer id);

     Student update(Student Student);

     Student save(Student Student);

     Page<Student> findByCid(Integer cid,Pageable pageable);
}
