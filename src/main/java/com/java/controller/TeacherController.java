package com.java.controller;
import com.java.entity.Teacher;
import com.java.exception.ResponseData;
import com.java.service.TeacherService;
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
@RequestMapping("/teacher")
public class TeacherController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private TeacherService teacherService;

    //按工号查询
    @GetMapping("/{tno}")
    public Teacher getByTno(@PathVariable String tno) {
        Teacher teacher = teacherService.findByTno(tno);
        return teacher;
    }

    //按工号删除
    @DeleteMapping("/{tno}")
    public void DeleteByTno(@PathVariable String tno) {
        Teacher teacher = teacherService.findByTno(tno);
        if (teacher != null){
            teacher.setActive(false);
            logger.info("开始删除教师: {}", tno);
            teacherService.delete(teacher);
        }
    }

    /**
     * 查询所有教师
     * <p>
     * 分页
     */
    @GetMapping
    public List<Teacher> getAll(@RequestParam(value = "start", defaultValue = "0") Integer start,
                                @RequestParam(value = "size", defaultValue = "5") Integer size) {
        start = start < 0 ? 0 : start;
        Sort sort = Sort.by(Sort.Order.asc("id"));
        Pageable pageable = PageRequest.of(start, size, sort);
        Page<Teacher> page = teacherService.findAll(pageable);
        return page.getContent();
    }

    //更新教师信息
    @PutMapping(value = "/{tno}")
    public Map<String,Object> updateEntity(@Valid @RequestBody Teacher teacher, @PathVariable String tno) {
        Teacher t = teacherService.findByTno(tno);
        Map<String,Object> map = new HashMap<>();
        if(t == null){
            map.put("status", ResponseData.FAIL_STATUS);
            map.put("message",ResponseData.DATA_NOT_EXIST);
            return map;
        }
        try{
            teacher.setCreateTime(t.getCreateTime());
            teacher.setId(t.getId());
            teacher.setActive(true);
            teacherService.update(teacher);
            map.put("data",teacher);
            map.put("status",ResponseData.SUCCESS_STATUS);
            map.put("message",ResponseData.UPDATE_SUCCESS);
        }catch(Exception e){
            map.put("status",ResponseData.FAIL_STATUS);
            map.put("message",ResponseData.UPDATE_FAIL);
        }
        return map;
    }

    //创建教师信息
    @PostMapping
    public Map<String,Object> createEntity(@Valid @RequestBody Teacher teacher) {
        Teacher t = teacherService.findByTno(teacher.getTno());
        Map<String,Object> map = new HashMap<>();
        if(t != null){
            map.put("status",ResponseData.FAIL_STATUS);
            map.put("message",ResponseData.DUPLICATE_DEFINITION);
            return map;
        }
        try{
            teacher.setCreateTime(new Date());
            teacher.setActive(true);
            teacherService.save(teacher);
            map.put("data",teacher);
            map.put("status",ResponseData.SUCCESS_STATUS);
            map.put("message",ResponseData.CREATE_SUCCESS);
        }catch(Exception e){
            map.put("status",ResponseData.FAIL_STATUS);
            map.put("message",ResponseData.CREATE_FAIL);
        }
        return map;
    }
}