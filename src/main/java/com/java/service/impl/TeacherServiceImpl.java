package com.java.service.impl;

import com.java.dao.TeacherDao;
import com.java.entity.Teacher;
import com.java.service.TeacherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Resource
    private TeacherDao teacherDao;

    @Override
    public Page<Teacher> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return teacherDao.findAllByActive(true,pageable);
    }

    @Override
    public void delete(Teacher teacher) {
        // TODO Auto-generated method stub
        teacherDao.save(teacher);
    }

    @Override
    public Teacher findByTno(String tno) {
        return teacherDao.findByTnoAndActive(tno,true);
    }

    @Override
    public Teacher update(Teacher teacher) {
        return teacherDao.save(teacher);
    }

    @Override
    public Teacher save(Teacher teacher) {
        return teacherDao.save(teacher);
    }

    @Override
    public Teacher findById(Integer id) {
        return teacherDao.findByIdAndActive(id,true);
    }
}
