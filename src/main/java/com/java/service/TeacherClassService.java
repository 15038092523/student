package com.java.service;


import com.java.entity.Teacher;
import com.java.entity.TeacherClass;


public interface TeacherClassService {

     //根据班级id查询班主任
     Teacher findDefaultTeacher(Integer cid);

     TeacherClass save(TeacherClass teacherClass);


}
