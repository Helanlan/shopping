package com.gg.service.impl;

import com.gg.dao.CategoryMapper;
import com.gg.dao.SpecMapper;
import com.gg.pojo.Category;
import com.gg.pojo.Spec;
import com.gg.service.SpecService;
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
public class SpecServiceImpl implements SpecService {

    @Autowired
    private SpecMapper specMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void addSpec(Spec spec) {
        /**
         * 如果选择insert 那么所有的字段都会添加一遍即使没有值
         * 如果使用inserSelective就会只给有值的字段赋值（会对传进来的值做非空判断）
         */
        specMapper.insertSelective(spec);
    }

    @Override
    public List<Spec> selAll() {
        return specMapper.selectAll();
    }

    @Override
    public Spec selSpecById(Integer id) {  //selectByPrimaryKey
        return specMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteById(Integer id) {
        specMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Spec spec) {
        specMapper.updateByPrimaryKey(spec);
    }

    /**
     * example方法提取 模糊查询
     *
     * @param spec
     * @return
     */
    public Example createExample(Spec spec) {
        Example example = new Example(Spec.class);
        Example.Criteria criteria = example.createCriteria();//条件构造器
        if (spec != null) {
            //根据名字模糊查询
            if (!StringUtil.isEmpty(spec.getName())) { //如果输入的title不是空，则开始模糊查询
                criteria.andLike("name", "%" + spec.getName() + "%");
            }
        }
        return example;
    }

    /**
     * 模糊查询
     *
     * @param spec
     * @return
     */
    @Override
    public List<Spec> findList(Spec spec) {
        Example example = createExample(spec);
        return specMapper.selectByExample(example);
    }

    /**
     * 分页查询
     *
     * @param page 当前页
     * @param size 每一页要显示的数量
     * @return
     */
    @Override
    public PageInfo<Spec> findByPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Spec> specs = specMapper.selectAll();
        return new PageInfo<Spec>(specs);
    }

    /**
     * 条件+分页搜索
     *
     * @param spec
     * @param page 当前页
     * @param size 每一页要显示的数量
     * @return
     */
    @Override
    public PageInfo<Spec> findByPage(Spec spec, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        Example example = createExample(spec);
        List<Spec> specs = specMapper.selectByExample(example);
        return new PageInfo<Spec>(specs);
    }


    /**
     * 根据所选分类的id查询到它的模板id--》再通过模板id（规格表中template_id相同的数据都属于同一个分类项）查询规格集合
     * @param categoryId
     * @return
     */
    @Override
    public List<Spec> findSpecByCategory(Integer categoryId) {
        //分类的template_id
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        Spec spec =new Spec();
        spec.setTemplateId(category.getTemplateId());
        //根据templateId查询规格
        return specMapper.select(spec);
    }
}
