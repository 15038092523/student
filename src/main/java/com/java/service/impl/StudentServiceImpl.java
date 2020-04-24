package com.java.service.impl;

import com.java.dao.StudentDao;
import com.java.entity.Student;
import com.java.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDao studentDao;

    @Override
    public Page<Student> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return studentDao.findAllByActive(true,pageable);
    }

    @Override
    public void delete(Student student) {
        // TODO Auto-generated method stub
        studentDao.save(student);
    }

    @Override
    public Student find(Integer id) {
        return studentDao.findByIdAndActive(id,true);
    }

    @Override
    public Student update(Student student) {
        return studentDao.save(student);
    }

    @Override
    public Student save(Student student) {
        return studentDao.save(student);
    }

    @Override
    public Page<Student> findByCid(Integer cid,Pageable pageable){
        return studentDao.findAllByActiveAndCid(true,cid,pageable);
    }

}
