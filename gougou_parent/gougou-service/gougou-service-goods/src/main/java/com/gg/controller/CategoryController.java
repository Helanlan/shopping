package com.gg.controller;

import com.gg.pojo.Category;
import com.gg.service.CategoryService;
import com.gg.utils.Result;
import com.gg.utils.StatusCode;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result add(@RequestBody Category category){
        categoryService.addCategory(category);
        return new Result(true, StatusCode.OK,"分类项添加成功");
    }

    @GetMapping(value = "/selAll")
    public Result<List<Category>> selAllCategory() {
        List<Category> categories = categoryService.selAll();
        return new Result(true, StatusCode.OK, "分类项查询成功", categories);
    }

    @GetMapping(value = "/{id}")
    public Result<Category> selCategoryById(@PathVariable(value = "id") Integer id) {
        Category category = categoryService.selCategoryById(id);
        return new Result<Category>(true, StatusCode.OK, "分类项查询成功", category);
    }

    @DeleteMapping(value = "/{id}")
    public Result deleteById(@PathVariable(value = "id") Integer id) {
        categoryService.deleteById(id);
        return new Result(true, StatusCode.OK, "分类项删除成功");
    }

    @PutMapping(value = "/{id}")
    public Result update(@PathVariable(value = "id") Integer id, @RequestBody Category category) {
        category.setId(id);
        categoryService.update(category);
        return new Result(true, StatusCode.OK, "分类项修改成功");
    }

    /**
     * 条件查询
     * @param category
     * @return
     */
    @PostMapping(value = "/search")
    public Result<List<Category>> findList(@RequestBody Category category) {
        List<Category> categoryList = categoryService.findList(category);
        return new Result<List<Category>>(true, StatusCode.OK, "条件查询成功", categoryList);
    }

    /**
     * 分页查询
     * @param page 当前页
     * @param size 每一页要显示的数量
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Category>> findByPage(@PathVariable(value = "page") Integer page, @PathVariable(value = "size") Integer size) {
        PageInfo<Category> byPage = categoryService.findByPage(page, size);
        return new Result<PageInfo<Category>>(true, StatusCode.OK, "分页查询成功", byPage);
    }

    /**
     * 条件 分页查询
     * @param category
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Category>> findByPage(@RequestBody Category category, @PathVariable(value = "page") Integer page, @PathVariable(value = "size") Integer size) {
        PageInfo<Category> byPage = categoryService.findByPage(category, page, size);
        return new Result<PageInfo<Category>>(true, StatusCode.OK, "条件分页查询成功", byPage);
    }

    /**
     * 按照分类的parent_id查询所有子节点
     */
    @GetMapping(value = "/list/{pid}")
    public Result<List<Category>> findByParentId(@PathVariable(value = "pid")Integer pid){
        List<Category> categories = categoryService.findByParentId(pid);
        return new Result<>(true,StatusCode.OK,"查询成功",categories);
    }
}
