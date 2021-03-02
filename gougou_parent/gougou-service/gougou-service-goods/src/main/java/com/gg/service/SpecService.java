package com.gg.service;

import com.gg.pojo.Spec;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 规格
 */
public interface SpecService {
    void addSpec(Spec spec);
    List<Spec>selAll();
    Spec selSpecById(Integer id);
    void deleteById(Integer id);
    void update(Spec spec);


    /**
     * 根据多条件查询
     */
    List<Spec> findList(Spec spec);

    /**
     * 分页查询
     * @param page 当前页
     * @param size 每一页要显示的数量
     * @return
     */
    PageInfo<Spec> findByPage(Integer page,Integer size);


    /**
     * 条件+分页查询
     * @param page 当前页
     * @param size 每一页要显示的数量
     * @return
     */
    PageInfo<Spec> findByPage(Spec spec,Integer page,Integer size);

    /**
     * 根据所选分类的id查询到它的模板id--》再通过模板id（规格表中template_id相同的数据都属于同一个分类项）查询规格集合
     * @param categoryId
     * @return
     */
    List<Spec> findSpecByCategory(Integer categoryId);
}
