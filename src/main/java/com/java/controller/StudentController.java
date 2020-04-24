package com.java.controller;

import com.java.entity.Student;

import com.java.exception.ResponseData;
import com.java.service.ClassRoomService;
import com.java.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    Map<String,Object> map = new HashMap<>();

    @Resource
    private StudentService studentService;

    @Resource
    private ClassRoomService classRoomService;

    //按id查询
    @GetMapping("/{id}")
    public Student getById(@PathVariable Integer id) {
        Student student = studentService.find(id);
        return student;
    }

    //按id删除
    @DeleteMapping("/{id}")
    public void DeleteByTno(@PathVariable Integer id) {
        Student student = studentService.find(id);
        if (student != null){
            student.setActive(false);
            logger.info("开始删除学生: {}", id);
            studentService.delete(student);
        }
    }

    /**
     * 查询所有学生
     * <p>
     * 分页
     */
    @GetMapping
    public List<Student> getAll(@RequestParam(value = "start", defaultValue = "0") Integer start,
                                @RequestParam(value = "size", defaultValue = "5") Integer size) {
        start = start < 0 ? 0 : start;
        Sort sort = Sort.by(Sort.Order.asc("id"));
        Pageable pageable = PageRequest.of(start, size, sort);
        Page<Student> page = studentService.findAll(pageable);
        return page.getContent();
    }

    //更新学生信息
    @PutMapping(value = "/{id}")
    public Map<String,Object> updateEntity(@Valid @RequestBody Student student, @PathVariable Integer id) {
        //校验学生数据
        Student s = studentService.find(id);
        if(s == null || classRoomService.findById(student.getCid()) == null){
            map.put("status", ResponseData.FAIL_STATUS);
            map.put("message",ResponseData.DATA_NOT_EXIST);
            return map;
        }
        try{
            student.setCreateTime(s.getCreateTime());
            student.setId(s.getId());
            student.setActive(true);
            studentService.update(student);
            map.put("data",student);
            map.put("status",ResponseData.SUCCESS_STATUS);
            map.put("message",ResponseData.CREATE_SUCCESS);
        }catch(Exception e){
            map.put("status",ResponseData.FAIL_STATUS);
            map.put("message",ResponseData.UPDATE_FAIL);
        }
        return map;
    }

    //创建学生信息
    @PostMapping
    public Map<String,Object> createEntity(@Valid @RequestBody Student student) {
        Student s = studentService.find(student.getId());
        if(s != null || classRoomService.findById(student.getCid()) == null){
            map.put("status",ResponseData.FAIL_STATUS);
            map.put("message",ResponseData.DUPLICATE_DEFINITION);
            return map;
        }
        try{
            student.setCreateTime(new Date());
            student.setActive(true);
            studentService.save(student);
            map.put("data",student);
            map.put("status",ResponseData.SUCCESS_STATUS);
            map.put("message",ResponseData.CREATE_SUCCESS);
        }catch(Exception e){
            map.put("status",ResponseData.FAIL_STATUS);
            map.put("message",ResponseData.CREATE_FAIL);
        }
        return map;
    }
}