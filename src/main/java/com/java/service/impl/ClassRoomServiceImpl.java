package com.java.service.impl;

import com.java.dao.ClassRoomDao;

import com.java.entity.ClassRoom;

import com.java.service.ClassRoomService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ClassRoomServiceImpl implements ClassRoomService {

    @Resource
    private ClassRoomDao classRoomDao;

    @Override
    public Page<ClassRoom> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return classRoomDao.findAllByActive(true,pageable);
    }

    @Override
    public void delete(ClassRoom classRoom) {
        // TODO Auto-generated method stub
        classRoomDao.save(classRoom);
    }

    @Override
    public ClassRoom findByCno(String cno) {
        return classRoomDao.findByCnoAndActive(cno,true);
    }

    @Override
    public ClassRoom update(ClassRoom classRoom) {
        return classRoomDao.save(classRoom);
    }

    @Override
    public ClassRoom save(ClassRoom classRoom) {
        return classRoomDao.save(classRoom);
    }

    @Override
    public ClassRoom findById(Integer id) {
        return classRoomDao.findByIdAndActive(id,true);
    }

}
