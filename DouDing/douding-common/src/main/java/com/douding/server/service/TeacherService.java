package com.douding.server.service;

import com.douding.server.domain.Teacher;
import com.douding.server.domain.TeacherExample;
import com.douding.server.dto.PageDto;
import com.douding.server.dto.TeacherDto;
import com.douding.server.mapper.TeacherMapper;
import com.douding.server.util.CopyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class TeacherService {

    @Resource
    private TeacherMapper teacherMapper;


    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        TeacherExample teacherExample = new TeacherExample();
        List<Teacher> teacherList = teacherMapper.selectByExample(teacherExample);
        PageInfo<Teacher> pageInfo = new PageInfo<>(teacherList);
        pageDto.setTotal(pageInfo.getTotal());
        List<TeacherDto> teacherDtoList = CopyUtil.copyList(teacherList, TeacherDto.class);
        pageDto.setList(teacherDtoList);
    }

    public void save(TeacherDto teacherDto) {
        //获取id
        Teacher teacher = CopyUtil.copy(teacherDto, Teacher.class);
        teacherMapper.updateByPrimaryKey(teacher);
    }

    //新增数据
    public void insert(Teacher teacher) {
        teacherMapper.insert(teacher);
    }

    //更新数据
    private void update(Teacher teacher) {

    }

    public void delete(String id) {
       teacherMapper.deleteByPrimaryKey(id);
    }

    public List<TeacherDto> all() {
       return null;
    }


    /**
     * 查找
     * @param id
     */
    public TeacherDto findById(String id) {

        return null;
    }
}//end class
