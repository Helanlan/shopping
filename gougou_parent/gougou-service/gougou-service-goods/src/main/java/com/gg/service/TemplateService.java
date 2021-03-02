package com.gg.service;

import com.gg.pojo.Template;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TemplateService {
    void addTemplate(Template template);
    List<Template> selAll();
    Template selTemplateById(Integer id);
    void deleteById(Integer id);
    void update(Template template);


    /**
     * 根据多条件查询
     */
    List<Template> findList(Template template);

    /**
     * 分页查询
     * @param page 当前页
     * @param size 每一页要显示的数量
     * @return
     */
    PageInfo<Template> findByPage(Integer page, Integer size);


    /**
     * 条件+分页查询
     * @param page 当前页
     * @param size 每一页要显示的数量
     * @return
     */
    PageInfo<Template> findByPage(Template template,Integer page,Integer size);
}
