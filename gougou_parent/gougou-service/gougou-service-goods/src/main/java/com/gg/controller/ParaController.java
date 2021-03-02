package com.gg.controller;

import com.gg.pojo.Para;
import com.gg.service.ParaService;
import com.gg.utils.Result;
import com.gg.utils.StatusCode;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/para")
@CrossOrigin
public class ParaController {
    
    @Autowired
    private ParaService paraService;

    @PostMapping
    public Result addPara(@RequestBody Para para) {
        paraService.addPara(para);
        return new Result(true, StatusCode.OK, "参数项添加成功");
    }

    @GetMapping(value = "/selAll")
    public Result<List<Para>> selAllPara() {
        List<Para> paras = paraService.selAll();
        return new Result(true, StatusCode.OK, "参数项查询成功", paras);
    }

    @GetMapping(value = "/{id}")
    public Result<Para> selParaById(@PathVariable(value = "id") Integer id) {
        Para para = paraService.selParaById(id);
        return new Result<Para>(true, StatusCode.OK, "参数项查询成功", para);
    }

    @DeleteMapping(value = "/{id}")
    public Result deleteById(@PathVariable(value = "id") Integer id) {
        paraService.deleteById(id);
        return new Result(true, StatusCode.OK, "参数项删除成功");
    }

    @PutMapping(value = "/{id}")
    public Result update(@PathVariable(value = "id") Integer id, @RequestBody Para para) {
        para.setId(id);
        paraService.update(para);
        return new Result(true, StatusCode.OK, "参数项修改成功");
    }

    /**
     * 条件查询
     *
     * @param para
     * @return
     */
    @PostMapping(value = "/search")
    public Result<List<Para>> findList(@RequestBody Para para) {
        List<Para> paraList = paraService.findList(para);
        return new Result<List<Para>>(true, StatusCode.OK, "条件查询成功", paraList);
    }

    /**
     * 分页查询
     *
     * @param page 当前页
     * @param size 每一页要显示的数量
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Para>> findByPage(@PathVariable(value = "page") Integer page, @PathVariable(value = "size") Integer size) {
        PageInfo<Para> byPage = paraService.findByPage(page, size);
        return new Result<PageInfo<Para>>(true, StatusCode.OK, "分页查询成功", byPage);
    }

    /**
     * 条件 分页查询
     *
     * @param para
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Para>> findByPage(@RequestBody Para para, @PathVariable(value = "page") Integer page, @PathVariable(value = "size") Integer size) {
        PageInfo<Para> byPage = paraService.findByPage(para, page, size);
        return new Result<PageInfo<Para>>(true, StatusCode.OK, "条件分页查询成功", byPage);
    }


    @GetMapping(value = "/category/{id}")
    public Result<List<Para>> findParaByCategory(@PathVariable(value = "id")Integer categoryId){
        List<Para> paras = paraService.findParaByCategory(categoryId);
        return new Result<>(true, StatusCode.OK,"此分类下的所有额外参数都在此了....",paras);
    }
}
