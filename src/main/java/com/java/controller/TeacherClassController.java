package com.java.controller;

import com.java.entity.ClassRoom;
import com.java.entity.Teacher;
import com.java.entity.TeacherClass;
import com.java.exception.ResponseData;
import com.java.service.ClassRoomService;
import com.java.service.TeacherClassService;
import com.java.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/teacher-class")
public class TeacherClassController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private TeacherService teacherService;

    @Resource
    private ClassRoomService classRoomService;

    @Resource
    private TeacherClassService teacherClassService;

    Map<String,Object> map = new HashMap<>();

    //创建授课信息
    @PostMapping
    public Map<String,Object> createEntity(@Valid @RequestBody TeacherClass teacherClass) {
        Teacher teacher = teacherService.findById(teacherClass.getTid());
        ClassRoom classRoom = classRoomService.findById(teacherClass.getCid());
        if(teacher == null || classRoom == null){
            map.put("status",ResponseData.FAIL_STATUS);
            map.put("message",ResponseData.DATA_NOT_EXIST);
            return map;
        }
        try{
            teacherClass.setCreateTime(new Date());
            teacherClass.setActive(true);
            teacherClassService.save(teacherClass);
            map.put("data",teacherClass);
            map.put("status",ResponseData.SUCCESS_STATUS);
            map.put("message",ResponseData.CREATE_SUCCESS);
        }catch(Exception e){
            map.put("status",ResponseData.FAIL_STATUS);
            map.put("message",ResponseData.CREATE_FAIL);
        }
        return map;
    }
}