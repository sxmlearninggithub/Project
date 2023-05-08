package com.douding.business.controller.admin;


import com.douding.server.domain.Teacher;
import com.douding.server.dto.PageDto;
import com.douding.server.dto.ResponseDto;
import com.douding.server.dto.TeacherDto;
import com.douding.server.service.TeacherService;
import com.douding.server.util.CopyUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.UUID;


@RestController
@RequestMapping("/admin/teacher")
public class TeacherController {

    private static final Logger LOG = LoggerFactory.getLogger(TeacherController.class);
    //给了日志用的
    public  static final String BUSINESS_NAME ="讲师";

    @Resource
    private TeacherService teacherService;

    @GetMapping("/list")
    public ResponseDto list(@RequestParam("page") int page,@RequestParam("size") int size){
        PageDto pageDto=new PageDto();
        pageDto.setPage(page);
        pageDto.setSize(size);
        teacherService.list(pageDto);
        ResponseDto<Object> responseDto = new ResponseDto<>();
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody TeacherDto teacherDto){
        ResponseDto<Object> responseDto = new ResponseDto<>();
        //获取id
        String id = teacherDto.getId();
        if (StringUtils.isBlank(id)){
            Teacher teacher = CopyUtil.copy(teacherDto, Teacher.class);
            String uuid = UUID.randomUUID().toString().replace("-", "").substring(0,8);
            teacher.setId(uuid);
            //id为空，添加数据库
            teacherService.insert(teacher);
        }
        //id不为空，修改数据库
        teacherService.save(teacherDto);
        responseDto.setSuccess(true);
        return responseDto;
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        ResponseDto<Object> responseDto = new ResponseDto<>();
        if (StringUtils.isBlank(id)){
            responseDto.setSuccess(false);
        }
        teacherService.delete(id);
        responseDto.setSuccess(true);
        return responseDto;
    }

    @RequestMapping("/all")
    public ResponseDto all(){

        return null;
    }

}//end class