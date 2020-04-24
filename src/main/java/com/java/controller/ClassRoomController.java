package com.java.controller;

import com.java.entity.ClassRoom;
import com.java.entity.Student;
import com.java.entity.Teacher;
import com.java.exception.ResponseData;
import com.java.service.ClassRoomService;
import com.java.service.StudentService;
import com.java.service.TeacherClassService;
import org.apache.commons.collections4.CollectionUtils;
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
@RequestMapping("/class")
public class ClassRoomController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    Map<String,Object> map = new HashMap<>();

    @Resource
    private ClassRoomService classRoomService;

    @Resource
    private StudentService studentService;

    @Resource
    private TeacherClassService teacherClassService;

    //按班级编号查询
    @GetMapping("/{cno}")
    public ClassRoom getByCno(@PathVariable String cno) {
        ClassRoom classRoom = classRoomService.findByCno(cno);
        return classRoom;
    }

    //按班级id查询班主任
    @GetMapping("/{id}/teacher")
    public Teacher getByCno(@PathVariable Integer id) {
        Teacher teacher = teacherClassService.findDefaultTeacher(id);
        return teacher;
    }

    //按班级号删除
    @DeleteMapping("/{cno}")
    public void DeleteByCno(@PathVariable String cno) {
        ClassRoom classRoom  = classRoomService.findByCno(cno);
        if (classRoom != null &&
                CollectionUtils.isEmpty(studentService.findByCid(classRoom.getId(),PageRequest.of(0, 1, Sort.by(Sort.Order.asc("id")))).getContent())){
            classRoom.setActive(false);
            logger.info("开始删除班级编号: {}", cno);
            classRoomService.delete(classRoom);
            return ;
        }
        logger.info("非法删除班级：{}",cno);
    }

    /**
     * 查询所有班级
     * <p>
     * 分页
     */
    @GetMapping
    public List<ClassRoom> getAll(@RequestParam(value = "start", defaultValue = "0") Integer start,
                                @RequestParam(value = "size", defaultValue = "5") Integer size) {
        start = start < 0 ? 0 : start;
        Sort sort = Sort.by(Sort.Order.asc("id"));
        Pageable pageable = PageRequest.of(start, size, sort);
        Page<ClassRoom> page = classRoomService.findAll(pageable);
        return page.getContent();
    }

    //更新班级信息
    @PutMapping(value = "/{cno}")
    public Map<String,Object> updateEntity(@Valid @RequestBody ClassRoom classRoom, @PathVariable String cno) {
        ClassRoom c = classRoomService.findByCno(cno);
        if(c == null){
            logger.warn("班级编号: {} 不存在", cno);
            map.put("status", ResponseData.FAIL_STATUS);
            map.put("message",ResponseData.DATA_NOT_EXIST);
            return map;
        }
        try{
            classRoom.setCreateTime(c.getCreateTime());
            classRoom.setId(c.getId());
            classRoom.setActive(true);
            logger.info("更新班级: {} ", cno);
            classRoomService.update(classRoom);
            map.put("data",classRoom);
            map.put("status",ResponseData.SUCCESS_STATUS);
            map.put("message",ResponseData.UPDATE_SUCCESS);
        }catch(Exception e){
            logger.warn("班级编号: {} 更新失败", cno);
            map.put("status",ResponseData.FAIL_STATUS);
            map.put("message",ResponseData.UPDATE_FAIL);
        }
        return map;
    }

    //创建班级信息
    @PostMapping
    public Map<String,Object> createEntity(@Valid @RequestBody ClassRoom classRoom) {
        ClassRoom c = classRoomService.findByCno(classRoom.getCno());
        if(c != null){
            logger.warn("班级编号: {} 已存在", classRoom.getCno());
            map.put("status",ResponseData.FAIL_STATUS);
            map.put("message",ResponseData.DUPLICATE_DEFINITION);
            return map;
        }
        try{
            classRoom.setActive(true);
            classRoom.setCreateTime(new Date());
            classRoomService.save(classRoom);
            map.put("data",classRoom);
            map.put("status",ResponseData.SUCCESS_STATUS);
            map.put("message",ResponseData.CREATE_SUCCESS);
        }catch(Exception e){
            logger.warn("班级编号: {} 创建失败", classRoom.getCno());
            map.put("status",ResponseData.FAIL_STATUS);
            map.put("message",ResponseData.CREATE_FAIL);
        }
        return map;
    }

    //按班级id查询所有学生
    @GetMapping("/{id}/student")
    public List<Student> getStudentById(@PathVariable Integer id,
                                        @RequestParam(value = "start", defaultValue = "0") Integer start,
                                        @RequestParam(value = "size", defaultValue = "5") Integer size) {
        List<Student> list = studentService.findByCid(id,PageRequest.of(start, size, Sort.by(Sort.Order.asc("id")))).getContent();
        return list;
    }
}