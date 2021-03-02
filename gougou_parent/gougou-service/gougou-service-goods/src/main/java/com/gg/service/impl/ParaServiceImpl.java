package com.gg.service.impl;

import com.gg.dao.CategoryMapper;
import com.gg.dao.ParaMapper;
import com.gg.pojo.Category;
import com.gg.pojo.Para;
import com.gg.service.ParaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service
@Transactional
public class ParaServiceImpl  implements ParaService {

    @Autowired
    private ParaMapper paraMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void addPara(Para para) {
        paraMapper.insertSelective(para);
    }

    @Override
    public List<Para> selAll() {
        return paraMapper.selectAll();
    }

    @Override
    public Para selParaById(Integer id) {
        return paraMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteById(Integer id) {
        paraMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Para para) {
        paraMapper.updateByPrimaryKey(para);
    }

    public Example createExample(Para para){
        Example example=new Example(Para.class);
        Example.Criteria criteria = example.createCriteria();
        if (para!=null){
            if (!StringUtils.isEmpty(para.getName())){
                criteria.andLike("name","%"+para.getName()+"%");
            }
        }
        return example;
    }
    /*模糊查询*/
    @Override
    public List<Para> findList(Para para) {
        Example example = createExample(para);
        return paraMapper.selectByExample(example);
    }

    /*分页查询*/
    @Override
    public PageInfo<Para> findByPage(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<Para> paras = paraMapper.selectAll();
        return new PageInfo<>(paras);
    }

    /*分页+条件查询*/
    @Override
    public PageInfo<Para> findByPage(Para para, Integer page, Integer size) {
        Example example = createExample(para);
        PageHelper.startPage(page,size);
        List<Para> paras = paraMapper.selectByExample(example);
        return new PageInfo<>(paras);
    }

    @Override
    public List<Para> findParaByCategory(Integer categoryId) {
        //先查询模板id，再查询参数
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        Para para =new Para();
        para.setTemplateId(category.getTemplateId());
        return paraMapper.select(para);
    }
}
