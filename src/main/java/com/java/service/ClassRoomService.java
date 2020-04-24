package com.java.service;

import com.java.entity.ClassRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClassRoomService {
     Page<ClassRoom> findAll(Pageable pageable);

     void delete(ClassRoom classRoom);

     ClassRoom findByCno(String cno);

     ClassRoom update(ClassRoom classRoom);

     ClassRoom save(ClassRoom classRoom);

     ClassRoom findById(Integer id);
}
