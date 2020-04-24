package com.java.service.impl;

import com.java.dao.TeacherClassDao;
import com.java.entity.ClassRoom;
import com.java.entity.Teacher;
import com.java.entity.TeacherClass;
import com.java.service.TeacherClassService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeacherClassServiceImpl implements TeacherClassService {

    @Resource
    private TeacherClassDao teacherClassDao;

    //根据班级id查询班主任
   public Teacher findDefaultTeacher(Integer cid){
       List<Teacher> list = teacherClassDao.findDefaultByCid(true,cid);
       if(CollectionUtils.isNotEmpty(list)) {
           return list.get(0);
       }
       return null;
   }

    @Override
    public TeacherClass save(TeacherClass teacherClass) {
        return teacherClassDao.save(teacherClass);
    }

    //根据教师id查询所教班级
    public List<ClassRoom> getClassesByTid(){
       return null;
    }


}
