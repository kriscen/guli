package com.kris.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kris.commonutils.R;
import com.kris.eduservice.entity.EduTeacher;
import com.kris.eduservice.entity.vo.EduTeacherQuery;
import com.kris.eduservice.service.EduTeacherService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author kris
 * @since 2020-06-18
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    /**
     * 查询所有
     * @return
     */
    @GetMapping("teachers")
    public R findAllTeacher(){
        return R.ok().data("items",this.eduTeacherService.list(null));
    }

    @DeleteMapping("teacher/{id}")
    public R removeTeacher(@PathVariable String id){
        boolean b = eduTeacherService.removeById(id);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     * 分页查询
     * @param current
     * @param pageSize
     * @return
     */
    @GetMapping("pageTeacher/{current}/{pageSize}")
    public R pageListTeacher(@PathVariable long current,@PathVariable long pageSize){
        Page<EduTeacher> pageTeacher = new Page<>(current,pageSize);
        IPage<EduTeacher> page = this.eduTeacherService.page(pageTeacher, null);
        long total = page.getTotal();
        return R.ok().data("rows",page).data("total",total);
    }

    /**
     * 多条件组合查询
     *     必须用post
     * @param eduTeacherQuery 2
     * @param current 1
     * @param pageSize 3
     * @return 3
     */
    @PostMapping(value = "pageTeacherCondition/{current}/{pageSize}")
    public R pageTeacherCondition(@RequestBody(required = false)EduTeacherQuery eduTeacherQuery,
                                  @PathVariable long current,@PathVariable long pageSize){
        Page<EduTeacher> pageTeacher = new Page<>(current,pageSize);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        //多条件组合查询
        if(StringUtils.isNotBlank(eduTeacherQuery.getName())){
            wrapper.like("name",eduTeacherQuery.getName());
        }
        if(StringUtils.isNotBlank(eduTeacherQuery.getLevel())){
            wrapper.eq("level",eduTeacherQuery.getLevel());
        }
        if(StringUtils.isNotBlank(eduTeacherQuery.getBegin())){
            wrapper.ge("gmt_create",eduTeacherQuery.getBegin());
        }
        if(StringUtils.isNotBlank(eduTeacherQuery.getEnd())){
            wrapper.le("gmt_update",eduTeacherQuery.getEnd());
        }
        wrapper.orderByDesc("gmt_create");

        IPage<EduTeacher> page = this.eduTeacherService.page(pageTeacher, wrapper);
        long total = page.getTotal();
        return R.ok().data("rows",page).data("total",total);
    }

    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = this.eduTeacherService.save(eduTeacher);
        if(save){
            return R.ok();
        }else{
            return R.error();
        }
    }

    /**
     * 查询单个老师
     * @param id
     * @return
     */
    @GetMapping("teacher/{id}")
    public R getTeacher(@PathVariable String id){
        EduTeacher teacher = this.eduTeacherService.getById(id);
        return R.ok().data("teacher",teacher);
    }

    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        if(this.eduTeacherService.updateById(eduTeacher)){
            return R.ok();
        }else{
            return R.error();
        }
    }


}

