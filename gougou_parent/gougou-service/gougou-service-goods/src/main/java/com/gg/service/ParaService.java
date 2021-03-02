package com.gg.service;

import com.gg.pojo.Para;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ParaService {
    void addPara(Para para);
    List<Para> selAll();
    Para selParaById(Integer id);
    void deleteById(Integer id);
    void update(Para para);


    /**
     * 根据多条件查询
     */
    List<Para> findList(Para para);

    /**
     * 分页查询
     * @param page 当前页
     * @param size 每一页要显示的数量
     * @return
     */
    PageInfo<Para> findByPage(Integer page, Integer size);


    /**
     * 条件+分页查询
     * @param page 当前页
     * @param size 每一页要显示的数量
     * @return
     */
    PageInfo<Para> findByPage(Para para,Integer page,Integer size);

    /**
     * 根据分类id查询它的模板id，再通过模板id查询参数列表
     * @param categoryId
     * @return
     */
    List<Para> findParaByCategory(Integer categoryId);
}
