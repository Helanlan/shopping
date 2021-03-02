package com.gg.controller;

import com.gg.pojo.Template;
import com.gg.service.TemplateService;
import com.gg.utils.Result;
import com.gg.utils.StatusCode;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/template")
@CrossOrigin
public class TemplateController {
    
    @Autowired
    private TemplateService templateService;

    @PostMapping
    public Result addTemplate(@RequestBody Template template) {
        templateService.addTemplate(template);
        return new Result(true, StatusCode.OK, "模板添加成功");
    }

    @GetMapping(value = "/selAll")
    public Result<List<Template>> selAlltemplate() {
        List<Template> templates = templateService.selAll();
        return new Result(true, StatusCode.OK, "模板查询成功", templates);
    }

    @GetMapping(value = "/{id}")
    public Result<Template> seltemplateById(@PathVariable(value = "id") Integer id) {
        Template template = templateService.selTemplateById(id);
        return new Result<Template>(true, StatusCode.OK, "模板查询成功", template);
    }

    @DeleteMapping(value = "/{id}")
    public Result deleteById(@PathVariable(value = "id") Integer id) {
        templateService.deleteById(id);
        return new Result(true, StatusCode.OK, "模板删除成功");
    }

    @PutMapping(value = "/{id}")
    public Result update(@PathVariable(value = "id") Integer id, @RequestBody Template template) {
        template.setId(id);
        templateService.update(template);
        return new Result(true, StatusCode.OK, "模板修改成功");
    }

    /**
     * 条件查询
     *
     * @param template
     * @return
     */
    @PostMapping(value = "/search")
    public Result<List<Template>> findList(@RequestBody Template template) {
        List<Template> templateList = templateService.findList(template);
        return new Result<List<Template>>(true, StatusCode.OK, "条件查询成功", templateList);
    }

    /**
     * 分页查询
     *
     * @param page 当前页
     * @param size 每一页要显示的数量
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Template>> findByPage(@PathVariable(value = "page") Integer page, @PathVariable(value = "size") Integer size) {
        PageInfo<Template> byPage = templateService.findByPage(page, size);
        return new Result<PageInfo<Template>>(true, StatusCode.OK, "分页查询成功", byPage);
    }

    /**
     * 条件 分页查询
     *
     * @param template
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Template>> findByPage(@RequestBody Template template, @PathVariable(value = "page") Integer page, @PathVariable(value = "size") Integer size) {
        PageInfo<Template> byPage = templateService.findByPage(template, page, size);
        return new Result<PageInfo<Template>>(true, StatusCode.OK, "条件分页查询成功", byPage);
    }
}
