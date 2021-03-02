package com.gg.controller;

import com.gg.pojo.Spec;
import com.gg.utils.Result;
import com.gg.utils.StatusCode;
import com.gg.service.SpecService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/spec")
@CrossOrigin //跨域。就是a域名访问b域名的数据，使用场景：域名、请求端口、协议不一致的时候就表示跨域了，不加@CrossOrigin就会禁止跨域
public class SpecController {

    @Autowired
    private SpecService specService;

    @PostMapping
    public Result addSpec(@RequestBody Spec spec) {
        specService.addSpec(spec);
        return new Result(true, StatusCode.OK, "规格添加成功");
    }

    @GetMapping(value = "/selAll")
    public Result<List<Spec>> selAllSpec() {
        List<Spec> specs = specService.selAll();
        return new Result(true, StatusCode.OK, "规格查询成功", specs);
    }

    @GetMapping(value = "/{id}")
    public Result<Spec> selSpecById(@PathVariable(value = "id") Integer id) {
        Spec spec = specService.selSpecById(id);
        return new Result<Spec>(true, StatusCode.OK, "规格查询成功", spec);
    }

    @DeleteMapping(value = "/{id}")
    public Result deleteById(@PathVariable(value = "id") Integer id) {
        specService.deleteById(id);
        return new Result(true, StatusCode.OK, "规格删除成功");
    }

    @PutMapping(value = "/{id}")
    public Result update(@PathVariable(value = "id") Integer id, @RequestBody Spec spec) {
        spec.setId(id);
        specService.update(spec);
        return new Result(true, StatusCode.OK, "规格修改成功");
    }

    /**
     * 条件查询
     *
     * @param spec
     * @return
     */
    @PostMapping(value = "/search")
    public Result<List<Spec>> findList(@RequestBody Spec spec) {
        List<Spec> SpecList = specService.findList(spec);
        return new Result<List<Spec>>(true, StatusCode.OK, "条件查询成功", SpecList);
    }

    /**
     * 分页查询
     *
     * @param page 当前页
     * @param size 每一页要显示的数量
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Spec>> findByPage(@PathVariable(value = "page") Integer page, @PathVariable(value = "size") Integer size) {
        PageInfo<Spec> byPage = specService.findByPage(page, size);
        return new Result<PageInfo<Spec>>(true, StatusCode.OK, "分页查询成功", byPage);
    }

    /**
     * 条件 分页查询
     *
     * @param spec
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Spec>> findByPage(@RequestBody Spec spec, @PathVariable(value = "page") Integer page, @PathVariable(value = "size") Integer size) {
        PageInfo<Spec> byPage = specService.findByPage(spec, page, size);
        return new Result<PageInfo<Spec>>(true, StatusCode.OK, "条件分页查询成功", byPage);
    }

    /**
     * 根据所选分类的id查询到它的模板id--》再通过模板id（规格表中template_id相同的数据都属于同一个分类项）查询规格集合
     * @param categoryId
     * @return
     */
    @GetMapping(value = "/category/{id}")
    public Result<List<Spec>> findSpecsByCategory(@PathVariable(value = "id") Integer categoryId){
        List<Spec> specList = specService.findSpecByCategory(categoryId);
        return new Result<>(true, StatusCode.OK,"此分类下的所有规格在此了....",specList);
    }
}
