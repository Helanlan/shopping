package com.gg.service;

import com.gg.pojo.Category;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CategoryService {
    void addCategory(Category category);
    List<Category> selAll();
    Category selCategoryById(Integer id);
    void deleteById(Integer id);
    void update(Category category);


    /**
     * 根据多条件查询
     */
    List<Category> findList(Category category);

    /**
     * 分页查询
     * @param page 当前页
     * @param size 每一页要显示的数量
     * @return
     */
    PageInfo<Category> findByPage(Integer page, Integer size);


    /**
     * 条件+分页查询
     * @param page 当前页
     * @param size 每一页要显示的数量
     * @return
     */
    PageInfo<Category> findByPage(Category category,Integer page,Integer size);


    /**
     * 按照分类的parent_id查询所有子节点
     * @param pid parent_id，1及分类的pid是0
     * @return
     */
    List<Category> findByParentId(Integer pid);
}
