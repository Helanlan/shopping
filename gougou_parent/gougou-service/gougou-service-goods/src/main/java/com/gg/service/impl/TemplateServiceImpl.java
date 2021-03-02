package com.gg.service.impl;

import com.gg.dao.TemplateMapper;
import com.gg.pojo.Template;
import com.gg.service.TemplateService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

@Service
@Transactional
public class TemplateServiceImpl implements TemplateService {
    @Autowired
    private TemplateMapper templateMapper;

    @Override
    public void addTemplate(Template template) {
        templateMapper.insertSelective(template);
    }

    @Override
    public List<Template> selAll() {
        return templateMapper.selectAll();
    }

    @Override
    public Template selTemplateById(Integer id) {
        return templateMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteById(Integer id) {
        templateMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Template template) {
        templateMapper.updateByPrimaryKey(template);
    }


    /**
     * example方法提取 模糊查询
     * @param template
     * @return
     */
    public Example createExample(Template template){
        Example example =new Example(Template.class);
        Example.Criteria criteria = example.createCriteria();//条件构造器
        if (template!=null){
            //根据名字模糊查询
            if (!StringUtil.isEmpty(template.getName())){ //如果输入的title不是空，则开始模糊查询
                criteria.andLike("name","%"+template.getName()+"%");
            }
        }
        return example;
    }
    /*模糊查询*/
    @Override
    public List<Template> findList(Template template) {
        Example example = createExample(template);
        return templateMapper.selectByExample(example);
    }



    /*分页查询*/
    @Override
    public PageInfo<Template> findByPage(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<Template> templates = templateMapper.selectAll();
        return new PageInfo<Template>(templates);
    }

    /**
     * 分页+模糊查询
     * @param template
     * @param page 当前页
     * @param size 每一页要显示的数量
     * @return
     */
    @Override
    public PageInfo<Template> findByPage(Template template, Integer page, Integer size) {
        Example example = createExample(template);
        PageHelper.startPage(page,size);
        List<Template> templates = templateMapper.selectByExample(example);
        return new PageInfo<>(templates);
    }
}
